package sbnz.integracija.example.dto;

import sbnz.integracija.example.facts.Zapisnik;

public class ZapisnikDTO {

	private Zapisnik zapisnik;
	private Integer godisnjiKazneniPoeni;
	private Boolean oduzimanjeDozvole;
	
	public ZapisnikDTO(Zapisnik zapisnik, Integer godisnjiKazneniPoeni, Boolean oduzimanjeDozvole) {
		this.zapisnik = zapisnik;
		this.godisnjiKazneniPoeni = godisnjiKazneniPoeni;
		this.oduzimanjeDozvole = oduzimanjeDozvole;
	}

	public Zapisnik getZapisnik() {
		return zapisnik;
	}

	public void setZapisnik(Zapisnik zapisnik) {
		this.zapisnik = zapisnik;
	}

	public Integer getGodisnjiKazneniPoeni() {
		return godisnjiKazneniPoeni;
	}

	public void setGodisnjiKazneniPoeni(Integer godisnjiKazneniPoeni) {
		this.godisnjiKazneniPoeni = godisnjiKazneniPoeni;
	}

	public Boolean getOduzimanjeDozvole() {
		return oduzimanjeDozvole;
	}

	public void setOduzimanjeDozvole(Boolean oduzimanjeDozvole) {
		this.oduzimanjeDozvole = oduzimanjeDozvole;
	}
}
