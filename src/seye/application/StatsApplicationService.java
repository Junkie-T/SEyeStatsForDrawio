package seye.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seye.repository.GroupDirectoryTableRepository;
import seye.repository.MinutesStatsTableRepository;
import seye.repository.NetGraphDirectoryTableRepository;
import seye.repository.PortTableRepository;
import seye.table.CellTable;
import seye.table.GroupDirectoryTable;
import seye.table.MinutesStatsTRecordBuilder;
import seye.table.MinutesStatsTable;
import seye.table.NetGraphDirectoryTable;
import seye.table.PortTable;
import seye.table.TRecord;

public class StatsApplicationService {
	public Map<String,List<String>> getGroupNetSetMap() {
		NetGraphDirectoryTableRepository ngdr = new NetGraphDirectoryTableRepository();
		NetGraphDirectoryTable ntable = ngdr.get();
		
		GroupDirectoryTableRepository gdr = new GroupDirectoryTableRepository();
		GroupDirectoryTable gtable = gdr.get();
		
		List<String> grouplist = gtable.projection(0);
		
		Map<String,List<String>> group_dir = new HashMap<>();
		for(String groupid : grouplist) {
			group_dir.put(groupid,new ArrayList<String>());
			ntable.select(1, groupid);
			List<TRecord> samegroup_records_ingroupdirtable = gtable.select(0,groupid);
			for(TRecord grouprec : samegroup_records_ingroupdirtable) {
				List<TRecord> recs = ntable.select(1,grouprec.FIELDS[2]);
				if(recs.size() == 0)
					continue;
				group_dir.get(groupid).add(recs.get(0).FIELDS[0]);
			}
		}
		return group_dir;
	}
	
	public void writeAllGroupStats(CellTable nctable,CellTable octable) {
		MinutesStatsTableRepository mstats = new MinutesStatsTableRepository();
		MinutesStatsTable mtable = mstats.get();
		
		Map<String,List<String>> netsetmap = getGroupNetSetMap();
		
		for (String key : netsetmap.keySet()) {
			
			List<String> netsetlist = netsetmap.get(key);

			CellTable n_selectedtable = nctable.selectByNetgraphSetID(netsetlist);
			CellTable o_selectedtable = octable.selectByNetgraphSetID(netsetlist);

			TRecord record = getStatsRecord(key,n_selectedtable,o_selectedtable);
			mtable.addRecord(record);
			
		}
		mstats.write(mtable);
	}
	
	public TRecord getStatsRecord(String groupid,CellTable newct,CellTable oldct) {
		int amount		= newct.getLength();
		int new_ 		= newct.removeSameCell(oldct).getLength();
		int removed		= oldct.removeSameCell(newct).getLength();
		int updated 	= newct.getUpdatedCell(oldct).getLength();
		int increased 	= newct.amountOfIncreasedCell(oldct);
		
		PortTable port	= new PortTableRepository().get();
		
		MinutesStatsTRecordBuilder builder = new MinutesStatsTRecordBuilder(groupid,port);
		builder.setStat("ALL",Integer.toString(amount));
		builder.setStat("NEW",Integer.toString(new_));
		builder.setStat("REMOVE",Integer.toString(removed));
		builder.setStat("UPDATE",Integer.toString(updated));
		builder.setStat("INCREASE",Integer.toString(increased));
		
		return builder.getMinuteStatsTRecord();
	}
}
