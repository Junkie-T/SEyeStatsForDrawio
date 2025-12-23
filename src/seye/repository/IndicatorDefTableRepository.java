package seye.repository;

import java.io.File;
import java.util.List;

import seye.table.IndicatorDefTable;
import seye.table.TRecord;

public class IndicatorDefTableRepository extends TableCSVRepository{
	@Override
	public IndicatorDefTable get() {
		List<TRecord> records = CSVFileOperater.read(getPath());
		IndicatorDefTable table = new IndicatorDefTable(records);
		return table;
	}
	@Override
	public File getPath() {
		return new File("C:\\development\\seye_test\\indicatordef.csv");
	}

}
