package sbnz.integracija.example.facts;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zapisnik {

	@Id
	private String id;

	public enum Zona {
		REDOVNA, ZONA_USPORENOG_SAOBRACAJA, ZONA_30, ZONA_SKOLE
	}
	
	// polja vezana za prekoracenje brzine
	private Boolean naseljenoMesto;
	private Zona zona;
	private Double dozvoljenaBrzina;
	private Double ostvarenaBrzina;

	private LocalDate datum;
	private LocalTime vreme;

	private String ulica;
	
	// polja vezana za voznju pod uticajem
	private Double prisustvoAlkohola; // u mg/l
	private Boolean prisustvoPsihoaktivnihSupstanci;
	private Boolean ometa;
	private Boolean saobracajnaNesreca;
	private Boolean prisutnoDete;
	
	// podaci o vozacu
	private Vozac vozac;
	
	// podaci o prekrsaju prekoracenja brzine
	private KaznaPrekoracenjaBrzine prekoracenjeBrzine;
	
	// podaci o prekrasju voznje pod uticajem
	private KaznaVoznjaPodUticajem voznjaPodUticajem;
	
	private Integer kaznenihPoenaUkupno;
	
	public Zapisnik() {
		
	}

	public Boolean getNaseljenoMesto() {
		return naseljenoMesto;
	}

	public void setNaseljenoMesto(Boolean naseljenoMesto) {
		this.naseljenoMesto = naseljenoMesto;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Double getDozvoljenaBrzina() {
		return dozvoljenaBrzina;
	}

	public void setDozvoljenaBrzina(Double dozvoljenaBrzina) {
		this.dozvoljenaBrzina = dozvoljenaBrzina;
	}

	public Double getOstvarenaBrzina() {
		return ostvarenaBrzina;
	}

	public void setOstvarenaBrzina(Double ostvarenaBrzina) {
		this.ostvarenaBrzina = ostvarenaBrzina;
	}

	public Double getPrisustvoAlkohola() {
		return prisustvoAlkohola;
	}

	public void setPrisustvoAlkohola(Double prisustvoAlkohola) {
		this.prisustvoAlkohola = prisustvoAlkohola;
	}

	public Boolean getPrisustvoPsihoaktivnihSupstanci() {
		return prisustvoPsihoaktivnihSupstanci;
	}

	public void setPrisustvoPsihoaktivnihSupstanci(Boolean prisustvoPsihoaktivnihSupstanci) {
		this.prisustvoPsihoaktivnihSupstanci = prisustvoPsihoaktivnihSupstanci;
	}

	public Boolean getOmeta() {
		return ometa;
	}

	public void setOmeta(Boolean ometa) {
		this.ometa = ometa;
	}

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	public KaznaPrekoracenjaBrzine getPrekoracenjeBrzine() {
		return prekoracenjeBrzine;
	}

	public void setPrekoracenjeBrzine(KaznaPrekoracenjaBrzine prekoracenjeBrzine) {
		this.prekoracenjeBrzine = prekoracenjeBrzine;
	}

	public KaznaVoznjaPodUticajem getVoznjaPodUticajem() {
		return voznjaPodUticajem;
	}

	public void setVoznjaPodUticajem(KaznaVoznjaPodUticajem voznjaPodUticajem) {
		this.voznjaPodUticajem = voznjaPodUticajem;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}

	public Boolean getSaobracajnaNesreca() {
		return saobracajnaNesreca;
	}

	public void setSaobracajnaNesreca(Boolean saobracajnaNesreca) {
		this.saobracajnaNesreca = saobracajnaNesreca;
	}

	public Boolean getPrisutnoDete() {
		return prisutnoDete;
	}

	public void setPrisutnoDete(Boolean prisutnoDete) {
		this.prisutnoDete = prisutnoDete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	
	public Integer getKaznenihPoenaUkupno() {
		return kaznenihPoenaUkupno;
	}
	
	public void setKaznenihPoenaUkupno(Integer kaznenihPoenaUkupno) {
		this.kaznenihPoenaUkupno = kaznenihPoenaUkupno;
	}
}
