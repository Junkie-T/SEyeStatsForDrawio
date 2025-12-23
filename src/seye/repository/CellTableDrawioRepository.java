package seye.repository;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import seye.table.AbstractTable;
import seye.table.CellTable;
import seye.table.ITableRepository;
import seye.table.TRecord;

public class CellTableDrawioRepository implements ITableRepository{
	private File root;
	public CellTableDrawioRepository(File root) {
		this.root = root;
	}
	
	@Override
	public CellTable get() {
		ArrayList<TRecord> records = new ArrayList<>();
		
		List<File> files = FileSystemUtility.findFilesRecursivelyWithEx(this.root,"drawio");
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            for(File file : files) {
            	Integer x = Files.getLastModifiedTime(file.toPath()).hashCode();
            	ToDiagrams tod = new ToDiagrams(x.toString());
            	parser.parse(file, tod);	
            	records.addAll(tod.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(TRecord r : records) {
        	System.out.println(r.toString());
        }
		return new CellTable(records);
	}

	@Override
	public void write(AbstractTable table) {
		// TODO Auto-generated method stub
		
	}
}

class ToDiagrams extends DefaultHandler{
	private String NETGRAPHSET_ID;
	private String netgraph_id;
	private ArrayList<TRecord> records;
    public ToDiagrams(String netgraphset_id) {
    	this.records = new ArrayList<>();
    	this.NETGRAPHSET_ID = netgraphset_id;
    } 
    
    public ArrayList<TRecord> getRecords() {
    	return this.records;
    }
    
    private String getAttributeValueByAttrName(Attributes attr,String name) {
    	for(int i = 0;i < attr.getLength();i++) {
    		if(attr.getQName(i).equals(name)){
    			return attr.getValue(i);
    		}
    	}
    	return null;
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equals("diagram")) {
            this.netgraph_id = getAttributeValueByAttrName(attributes,"id");
        }
        if(qName.equals("mxCell")) {
        	int length = attributes.getLength();
        	String id = null;
        	String value = null;
        	for(int i = 0;i < length;i++) {
        		if(attributes.getQName(i).equals("id")){
        			id = attributes.getValue(i);
        		}
        		if(attributes.getQName(i).equals("value")){
        			value = attributes.getValue(i);
        			
        			if(value == "")
        				return;
        		}
        	}
    		if (!(id == null || value == null)){
    			String [] str = new String[4];
    			
    			str[0] = this.NETGRAPHSET_ID;
    			str[1] = this.netgraph_id;
    			str[2] = id;
    			str[3] = Integer.toString(value.hashCode());
    			this.records.add(new TRecord(str));
    		}
        } 
    }
 
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals("diagram")) {
            netgraph_id = "";
        }
    }
}
