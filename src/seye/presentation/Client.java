package seye.presentation;

import seye.application.StatsApplicationService;
import seye.repository.CellTableCSVRepository;
import seye.repository.CellTableDrawioRepository;
import seye.table.CellTable;

public class Client {
	public static void main(String[]args) {
		StatsApplicationService sas = new StatsApplicationService();
		
		CellTableDrawioRepository drep = new CellTableDrawioRepository(null);
		CellTableCSVRepository crep = new CellTableCSVRepository();
		
		CellTable newct = drep.get();
		CellTable oldct = crep.get();
		
		sas.writeAllGroupStats(newct, oldct);
	}
}
