package seye.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import seye.table.AbstractTable;
import seye.table.GroupDirectoryTable;
import seye.table.ITableRepository;
import seye.table.TRecord;

/**
 * グループIdに対応するディレクトリのハッシュ名
 */
public class GroupDirectoryTableRepository implements ITableRepository{
	public final File propertyfile = new File("C:\\development\\settings\\inspection_dirs.property");
	
	public File getRootDirectory() {
		Properties properties = new Properties();
        try {
            FileInputStream istream = new FileInputStream(propertyfile);
            properties.load(istream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File((String) properties.get("ROOT"));
	}
	
	@Override
	public GroupDirectoryTable get() {
        Properties properties = new Properties();
        try {
            FileInputStream istream = new FileInputStream(propertyfile);
            properties.load(istream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mapに格納
        Map<String, String> propMap = new HashMap<>();
        for(Map.Entry<Object, Object> e : properties.entrySet()) {
            propMap.put(e.getKey().toString(), e.getValue().toString());
            System.out.println(e.getKey().toString() + " plus" +  e.getValue().toString());
        }
        
        List<TRecord> records = new ArrayList<TRecord>();
        
        Set<String> keyset = propMap.keySet();
        for(String key : keyset) {
        	File parentfile = new File(propMap.get(key));
        	
        	List<File> directories = FileSystemUtility.findChildDirectories(parentfile);
        	directories.addFirst(parentfile);
        	Integer id = 0;
        	for(File dir : directories) {
        		id = id + 1;
        		
        		String [] str = new String[3];
        		str[0] = key;
        		str[1] = id.toString();
        		str[2] = Integer.toString(dir.hashCode());
        		
        		records.add(new TRecord(str));
        	}
        }
		return new GroupDirectoryTable(records);
	}

	@Override
	public void write(AbstractTable table) {
		
	}
}
