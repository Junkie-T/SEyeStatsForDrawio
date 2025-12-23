package seye.table;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractTable {
	protected List<TRecord> RECORDS;
	public AbstractTable(List<TRecord> records){
		this.RECORDS = records;
	}
	public List<TRecord> getRecord(){
		return this.RECORDS;
	}
	
	public int getLength() {
		return this.RECORDS.size();
	}
	
	public List<String> projection(int index) {
		List<String> retval = new ArrayList<>();
		for(TRecord record : RECORDS) 
			retval.add(record.FIELDS[index]);
		return retval;
	}
	
	public List<TRecord> select(int field_index,List<String> list_) {
		ArrayList<TRecord> newrecord = new ArrayList<TRecord>();
		for(TRecord record : getRecord()) {
			if(list_.contains(record.FIELDS[field_index]))
				newrecord.add(record);
		}
		return newrecord;
	}
	
	public List<TRecord> select(int field_index,String find) {
		ArrayList<TRecord> newrecord = new ArrayList<TRecord>();
		for(TRecord record : getRecord()) {
			if(record.FIELDS[field_index].equals(find))
				newrecord.add(record);
		}
		return newrecord;
	}
	
	public void show() {
		for(TRecord record : RECORDS) {
			System.out.println(record.toString());
		}
	}
}
