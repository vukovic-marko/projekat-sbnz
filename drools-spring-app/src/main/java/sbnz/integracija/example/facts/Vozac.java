package sbnz.integracija.example.facts;

public class Vozac {
	
	public enum TipDozvole {
		TRAJNA, PROBNA
	};
	
	// polja vezana za osnovne podatke o vozacu
	private String ime;
	private String prezime;
	private String jmbg;
	
	// polja vezana za podatke o vozackoj dozvoli
	private String brojDozvole;
	private TipDozvole tipDozvole;
	
	public Vozac() {
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBrojDozvole() {
		return brojDozvole;
	}

	public void setBrojDozvole(String brojDozvole) {
		this.brojDozvole = brojDozvole;
	}

	public TipDozvole getTipDozvole() {
		return tipDozvole;
	}

	public void setTipDozvole(TipDozvole tipDozvole) {
		this.tipDozvole = tipDozvole;
	}
}
