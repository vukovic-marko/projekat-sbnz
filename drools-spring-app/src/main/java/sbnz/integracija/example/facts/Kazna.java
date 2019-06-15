package sbnz.integracija.example.facts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kazna {

	public enum Clanovi {
		CLAN_329, CLAN_330, CLAN_331, CLAN_332, 
		CLAN_332a, CLAN_333, CLAN_334
	}
	
	private List<Integer> zatvorskaKazna;
	private List<Double> novcanaKazna;
	private Integer kazneniPoeni;
	private Integer zabranaUpravljanja;

	private Clanovi clan;

	private Boolean obradjena;
	private Boolean dete1;
	private Boolean dete2;
	
	public Kazna() {
		
	}
	
	public Clanovi getClan() {
		return clan;
	}

	public void setClan(Clanovi clan) {
		this.clan = clan;
	}

	public List<Integer> getZatvorskaKazna() {
		return zatvorskaKazna;
	}

	public void setZatvorskaKazna(Integer[] zatvorskaKazna) {
		this.zatvorskaKazna = new ArrayList<Integer>(Arrays.asList(zatvorskaKazna));
	}

	public List<Double> getNovcanaKazna() {
		return novcanaKazna;
	}

	public void setNovcanaKazna(Double[] novcanaKazna) {
		this.novcanaKazna = new ArrayList<Double>(Arrays.asList(novcanaKazna));
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

	public Boolean getObradjena() {
		return obradjena;
	}

	public void setObradjena(Boolean obradjena) {
		this.obradjena = obradjena;
	}

	public Boolean getDete1() {
		return dete1;
	}

	public void setDete1(Boolean dete1) {
		this.dete1 = dete1;
	}

	public Boolean getDete2() {
		return dete2;
	}

	public void setDete2(Boolean dete2) {
		this.dete2 = dete2;
	}
}
