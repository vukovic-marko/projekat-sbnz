package sbnz.integracija.example.facts;

public class Zapisnik {
	
	public enum Zona {
		REDOVNA, ZONA_USPORENOG_SAOBRACAJA, ZONA_30, ZONA_SKOLE
	};
	
	// polja vezana za prekoracenje brzine
	private Boolean naseljenoMesto;
	private Zona zona;
	private Double dozvoljenaBrzina;
	private Double ostvarenaBrzina;
	
	// polja vezana za voznju pod uticajem
	private Double prisustvoAlkohola; // u mg/l
	private Boolean prisustvoPsihoaktivnihSupstanci;
	private Boolean ometa;
	
	// podaci o vozacu
	private Vozac vozac;
	
	// podaci o prekrsaju prekoracenja brzine
	private KaznaPrekoracenjaBrzine prekoracenjeBrzine;
	
	// podaci o prekrasju voznje pod uticajem
	private KaznaVoznjaPodUticajem voznjaPodUticajem;
	
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
}
