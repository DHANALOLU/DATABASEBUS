package pack;

import java.util.List;

public interface IBill {
	
	double printBill(String bus, List<Bus> busDetails, int ticketsCount);

}

