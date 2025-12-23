package seye.repository;

import java.io.File;
import java.util.List;

import seye.table.PortTable;
import seye.table.TRecord;

public class PortTableRepository extends TableCSVRepository{
	@Override
	public PortTable get() {
		List<TRecord> records = CSVFileOperater.read(getPath());
		PortTable table = new PortTable(records);
		return table;
	}

	@Override
	public File getPath() {
		return new File("C:\\development\\settings\\porttable.csv");
	}
}
