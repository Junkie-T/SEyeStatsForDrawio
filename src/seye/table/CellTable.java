package seye.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CellTable extends AbstractTable{
	public CellTable(List<TRecord> records) {
		super(records);
	}
	
	public CellTable selectByNetgraphSetID(List<String> netgraph_id_list) {
		return new CellTable(select(0,netgraph_id_list));
	}
	
	public int amountOfIncreasedCell(CellTable old) {
		return this.getLength() - old.getLength();
	}
	
	public CellTable removeSameCell(CellTable table) {
		List<TRecord> left		= getRecord();
		List<TRecord> right		= table.getRecord();
		
		HashSet<String> hashtable = new HashSet<String>();
		
		for(TRecord r : right) {
			String netgraph_id	= r.FIELDS[1];
			String cell_id 		= r.FIELDS[2];	
			hashtable.add(netgraph_id + cell_id);
		}
		
		List<TRecord> retlist = new ArrayList<>();
		
		for(TRecord r : left) {
			String netgraph_id	= r.FIELDS[1];
			String cell_id 		= r.FIELDS[2];	
			if(!hashtable.contains(netgraph_id + cell_id))
				retlist.add(r);
		}
		return new CellTable(retlist);
	}
	
	public CellTable getUpdatedCell(CellTable table) {
		List<TRecord> left		= getRecord();
		List<TRecord> right		= table.getRecord();
		
		HashSet<String> hashtable_same = new HashSet<String>();
		HashSet<String> hashtable_completelysame = new HashSet<String>();
		
		for(TRecord r : right) {
			String netgraph_id	= r.FIELDS[1];
			String cell_id 		= r.FIELDS[2];	
			String cell_value 	= r.FIELDS[3];	
			hashtable_same.add(netgraph_id + cell_id);
			hashtable_completelysame.add(netgraph_id + cell_id + cell_value);
		}
		
		List<TRecord> retlist = new ArrayList<>();
		
		for(TRecord r : left) {
			String netgraph_id	= r.FIELDS[1];
			String cell_id 		= r.FIELDS[2];	
			String cell_value 	= r.FIELDS[3];	
			if(hashtable_same.contains(netgraph_id + cell_id)) {
				if(!hashtable_completelysame.contains(netgraph_id + cell_id + cell_value)) {
					retlist.add(r);
				}
			}		
		}
		
		return new CellTable(retlist);
	}
}
