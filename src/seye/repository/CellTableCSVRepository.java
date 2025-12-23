package seye.repository;

import java.io.File;
import java.util.List;

import seye.table.CellTable;
import seye.table.TRecord;

public class CellTableCSVRepository extends TableCSVRepository{
	@Override
	public CellTable get() {
		List<TRecord> records = CSVFileOperater.read(getPath());
		CellTable table = new CellTable(records);
		return table;
	}

	@Override
	public File getPath() {
		return new File("C:\\development\\settings\\oldcelltable.csv");
	}
}
