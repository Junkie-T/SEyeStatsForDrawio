package seye.table;

public class TRecord {
	public final String [] FIELDS;
	public TRecord(String [] fields) {
		this.FIELDS = fields;
	}
	
	public boolean fieldEquals(int index,String val) {
		return FIELDS[index].equals(val);
	} 
	
	public String toString() {
		return String.join(",",this.FIELDS);	
	}
}
