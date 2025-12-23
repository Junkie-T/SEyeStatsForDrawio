package seye.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import seye.table.AbstractTable;
import seye.table.ITableRepository;
import seye.table.TRecord;

public abstract class TableCSVRepository implements ITableRepository{
	@Override
	public void write(AbstractTable table) {
		FileWriter file = null;
		try {
			file = new FileWriter(getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
        PrintWriter pw = new PrintWriter(new BufferedWriter(file));
        
        List<TRecord> records = table.getRecord();
       
        for(TRecord record : records) {
        	pw.println(record.toString());
        }   
        pw.close();
	}
	
	abstract public File getPath();
}
