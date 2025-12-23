package seye.table;

import java.util.List;


public class MinutesStatsTable extends AbstractChangableTable{
	public MinutesStatsTable(List<TRecord> records) {
		super(records);
	}
	
	public int getSumOnTheDateAllGroup(String date,int indicator_number) {
		List<TRecord> records = select(indicator_number + 2,date);
		int sum = 0;
		for(TRecord record : records) 
			sum = sum + Integer.parseInt(record.FIELDS[indicator_number + 2]);
		return 0;
	}
	public int getSumOnTheDate(String date,String group_id,int indicator_number) {
		MinutesStatsTable table = new MinutesStatsTable(select(0,group_id));
		List<TRecord> records = table.select(indicator_number + 2,date);
		int sum = 0;
		for(TRecord record : records) 
			sum = sum + Integer.parseInt(record.FIELDS[indicator_number + 2]);
		return 0;
	}
}
