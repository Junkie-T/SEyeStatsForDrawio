package seye.table.service;

import java.util.List;

import seye.table.DayStatsTable;
import seye.table.GroupDirectoryTable;
import seye.table.MinutesStatsTable;
import seye.table.PortTable;
import seye.table.TRecord;

public class StatsDomainService{
	public void minutesStatsToDayStats(String date,GroupDirectoryTable grouptable,MinutesStatsTable minutestatslist,DayStatsTable daystats,PortTable porttable) {
		List<String> groupid_list = grouptable.projection(0);
		for(String groupid : groupid_list) {
			String [] i = new String[10];
			
			i[0] = groupid;
			i[1] = date;
			i[2] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 1));
			i[3] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 2));
			i[4] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 3));
			i[5] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 4));
			i[6] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 5));
			i[7] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 6));
			i[8] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 7));
			i[9] = Integer.toString(minutestatslist.getSumOnTheDate(groupid,date, 8));
			TRecord record = new TRecord(i);
			daystats.addRecord(record);
		}
	}
}
