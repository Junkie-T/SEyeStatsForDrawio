package seye.application;

import seye.repository.CellTableCSVRepository;
import seye.repository.CellTableDrawioRepository;
import seye.repository.GroupDirectoryTableRepository;
import seye.table.CellTable;
import seye.table.GroupDirectoryTable;

public class CreateCellApplicationService {
	public void main() {
		GroupDirectoryTableRepository rep = new GroupDirectoryTableRepository();
		
		CellTableDrawioRepository drep = new CellTableDrawioRepository(rep.getRootDirectory());
		CellTable ctable = drep.get();
		CellTableCSVRepository crep = new CellTableCSVRepository();
		crep.write(ctable);
	}
}
