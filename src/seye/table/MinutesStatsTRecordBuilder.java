package seye.table;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MinutesStatsTRecordBuilder {
	private final String	groupid;
	private final PortTable port;
	private final String [] stats;
	public MinutesStatsTRecordBuilder(String groupid,PortTable port){
		this.groupid	= groupid;
		this.port		= port;
		this.stats		= new String[8];
		for(int i = 0;i < 8;i++) 
			this.stats[i] = "";
	}
	
	public void setStat(String statid,String value) {
		List<TRecord> records = port.select(1,statid);
		if(records.size() == 0)
			return;
					
		Integer cursor = Integer.parseInt(records.get(0).FIELDS[0]) - 1;
		this.stats[cursor] = value;
	}
	
	public TRecord getMinuteStatsTRecord() {
		String [] i = new String[10];
		
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
        
		i[0] = groupid;
		i[1] = sdf.format(cal.getTime());
		i[2] = sdf2.format(cal.getTime());
		for(int j = 3;j < 10;j++) {
			i[j] = stats[j-3];
		}
		
		return new TRecord(i);
	}
	
}
