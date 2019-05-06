package sbnz.integracija.example.facts;

public class KaznaVoznjaPodUticajem extends Kazna {
	
	public enum TipVoznjePodUticajem {
		BLAGA_ALK, UMERENA_ALK, SREDNJA_ALK,
		VISOKA_ALK, TESKA_ALK, VEOMA_TESKA_ALK, POTPUNA_ALK,
		PSIHOAKTIVNA_SUPS
	};
	
	private TipVoznjePodUticajem tip;
	
	public KaznaVoznjaPodUticajem() {
		
	}

	public TipVoznjePodUticajem getTip() {
		return tip;
	}

	public void setTip(TipVoznjePodUticajem tip) {
		this.tip = tip;
	}
	
}
