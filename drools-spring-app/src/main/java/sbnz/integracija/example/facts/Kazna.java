package sbnz.integracija.example.facts;

public class Kazna {
	
	public enum Clanovi {
		CLAN_329, CLAN_330, CLAN_331, CLAN_332, 
		CLAN_332a, CLAN_333, CLAN_334
	}
	
	private Integer[] zatvorskaKazna;
	private Double[] novcanaKazna;
	private Integer kazneniPoeni;
	private Integer zabranaUpravljanja;

	private Clanovi clan;
	
	public Kazna() {
		
	}
	
	public Clanovi getClan() {
		return clan;
	}

	public void setClan(Clanovi clan) {
		this.clan = clan;
	}

	public Integer[] getZatvorskaKazna() {
		return zatvorskaKazna;
	}

	public void setZatvorskaKazna(Integer[] zatvorskaKazna) {
		this.zatvorskaKazna = zatvorskaKazna;
	}

	public Double[] getNovcanaKazna() {
		return novcanaKazna;
	}

	public void setNovcanaKazna(Double[] novcanaKazna) {
		this.novcanaKazna = novcanaKazna;
	}

	public Integer getKazneniPoeni() {
		return kazneniPoeni;
	}

	public void setKazneniPoeni(Integer kazneniPoeni) {
		this.kazneniPoeni = kazneniPoeni;
	}

	public Integer getZabranaUpravljanja() {
		return zabranaUpravljanja;
	}

	public void setZabranaUpravljanja(Integer zabranaUpravljanja) {
		this.zabranaUpravljanja = zabranaUpravljanja;
	}
	
}
