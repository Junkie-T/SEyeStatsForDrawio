package seye.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemUtility {
	public static List<File> findChildDirectories(File parent) {
		if (!parent.isDirectory()) 
			return null;
		
		ArrayList<File> list = new ArrayList<File>();
		
		File [] files = parent.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				list.add(file);
				List<File> tmp = findChildDirectories(file);
				if(tmp.size() != 0)
					list.addAll(tmp);
			}
		}
		return list;
	}
	
	public static List<File> findFilesWithEx(File parent,String ex) {
		List<File> ret	= new ArrayList<File>();
		
		File [] files 	= parent.listFiles();
		for(File file : files) {
			if (file.getName().endsWith(ex)) {
				ret.add(file);
			}
		}
		return ret;
	}
	
	public static List<File> findFilesRecursivelyWithEx(File parent,String ex) {
		List<File> directories 		= FileSystemUtility.findChildDirectories(parent);
		directories.add(parent);
		
		List<File> retdirlist		= new ArrayList<File>();
		
		for(File dir : directories) {
			List<File> l = FileSystemUtility.findFilesWithEx(dir,ex);
			if(l.size() != 0)
				retdirlist.addAll(l);
		}
		return retdirlist;
	}
}
