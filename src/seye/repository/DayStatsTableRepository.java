package seye.repository;

import java.io.File;
import java.util.List;

import seye.table.AbstractTable;
import seye.table.DayStatsTable;
import seye.table.ITableRepository;
import seye.table.TRecord;

public class DayStatsTableRepository extends TableCSVRepository{
	@Override
	public AbstractTable get() {
		List<TRecord> records = CSVFileOperater.read(getPath());
		DayStatsTable table = new DayStatsTable(records);
		return table;
	}

	@Override
	public File getPath() {
		return new File("C:\\development\\seye_test\\daystatstable.csv");
	}
}
