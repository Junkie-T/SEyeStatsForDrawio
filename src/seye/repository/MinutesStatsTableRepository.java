package seye.repository;

import java.io.File;
import java.util.List;

import seye.table.MinutesStatsTable;
import seye.table.TRecord;

public class MinutesStatsTableRepository extends TableCSVRepository{
	@Override
	public MinutesStatsTable get() {
		List<TRecord> records = CSVFileOperater.read(getPath());
		MinutesStatsTable table = new MinutesStatsTable(records);
		return table;
	}

	@Override
	public File getPath() {
		return new File("C:\\development\\settings\\mstats.csv");
	}
}
