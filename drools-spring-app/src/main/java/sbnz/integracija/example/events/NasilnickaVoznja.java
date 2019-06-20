package sbnz.integracija.example.events;

import java.util.Date;
import java.util.List;

public class NasilnickaVoznja {
	private List<Date> prekrsaji;
	private String tablice;
	
	public NasilnickaVoznja(List<Date> prekrsaji, String tablice) {
		this.prekrsaji = prekrsaji;
		this.tablice = tablice;
	}
	
	public List<Date> getPrekrsaji() {
		return prekrsaji;
	}
	
	public void setPrekrsaji(List<Date> prekrsaji) {
		this.prekrsaji = prekrsaji;
	}
	
	public String getTablice() {
		return tablice;
	}
	
	public void setTablice(String tablice) {
		this.tablice = tablice;
	}
}
