package seye.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import seye.table.NetGraphDirectoryTable;
import seye.table.TRecord;

public class NetGraphDirectoryTableRepository extends TableCSVRepository{
	@Override
	public NetGraphDirectoryTable get() {
		ArrayList<TRecord> records = new ArrayList<>();
		
		List<File> files = FileSystemUtility.findFilesRecursivelyWithEx(getRoot(),"drawio");
		for(File file : files) {
			File directory = file.getParentFile();
			String [] str = new String[2];
			try {
				str[0] = Integer.toString(Files.getLastModifiedTime(file.toPath()).hashCode());
			} catch (IOException e) {
				e.printStackTrace();
			}
			str[1] = Integer.toString(directory.hashCode());
			
			TRecord record = new TRecord(str);
			records.add(record);
		}
		return new NetGraphDirectoryTable(records);
	}

	public File getRoot() {
		return new File("C:\\Users\\Mirai\\OneDrive\\drawio_study");
	}

	@Override
	public File getPath() {
		return new File("C:\\development\\seye_test\\netgraphtable.csv");
	}
}
