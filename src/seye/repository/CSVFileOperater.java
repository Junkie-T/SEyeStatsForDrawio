package seye.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import seye.table.AbstractTable;
import seye.table.TRecord;

public class CSVFileOperater {
	public static void write(AbstractTable table,File PATH) throws IOException {
		FileWriter file = new FileWriter(PATH);
        PrintWriter pw = new PrintWriter(new BufferedWriter(file));
        
        for(int i = 0;i < table.getLength();i++) {
        	pw.println(table.toString());
        }   
        pw.close();
	}
		
	public static List<TRecord> read(File PATH) {
		String line;
		List<TRecord> records = new ArrayList<>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(PATH));
			//String headerline = br.readLine();
			while ((line = br.readLine()) != null) {
				records.add(new TRecord(line.split(",")));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return records;
	}
}
