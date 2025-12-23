package seye.table;

import java.util.List;

abstract public class AbstractChangableTable extends AbstractTable{
	public AbstractChangableTable(List<TRecord> records) {
		super(records);
	}
	public void addRecord(TRecord record) {
		this.RECORDS.add(record);
	}
}
