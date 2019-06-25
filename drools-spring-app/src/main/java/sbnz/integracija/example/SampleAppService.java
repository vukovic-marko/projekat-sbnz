package sbnz.integracija.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.dto.ZapisnikDTO;
import sbnz.integracija.example.events.NasilnickaVoznja;
import sbnz.integracija.example.events.PreticanjePrekoPuneLinije;
import sbnz.integracija.example.events.ProlazakKrozCrvenoSvetlo;
import sbnz.integracija.example.facts.Vozac.TipDozvole;
import sbnz.integracija.example.facts.Zapisnik;

@Service
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;
	
	private KieSession kieSession;
	
	private List<NasilnickaVoznja> dates;
	private List<NasilnickaVoznja> dates2;
	private List<NasilnickaVoznja> dates3;
	
	@Autowired
	private SampleAppRepository repository;
	
	@Autowired
	public SampleAppService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration config = ks.newKieBaseConfiguration();
	    config.setOption(EventProcessingOption.STREAM);
		
//	    KieSessionConfiguration sessionConfig = ks.newKieSessionConfiguration();
//	    sessionConfig.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
	    
//		this.kieSession = kieContainer.newKieBase(config).newKieSession(sessionConfig, null);
		this.kieSession = kieContainer.newKieBase(config).newKieSession();
		
		dates = new ArrayList<NasilnickaVoznja>();
		dates2 = new ArrayList<NasilnickaVoznja>();
		dates3 = new ArrayList<NasilnickaVoznja>();
		
		kieSession.setGlobal("lista", dates);
    	kieSession.setGlobal("pravilo2", dates2);
    	kieSession.setGlobal("pravilo3", dates3);
	}
	
	public ZapisnikDTO obradiZapisnik(Zapisnik z) {
		kieSession.getAgenda().getAgendaGroup("modul1").setFocus();
		
		z.setDatum(LocalDate.now(ZoneId.systemDefault()));
		z.setVreme(LocalTime.now(ZoneId.systemDefault()));
				
//?		List<Zapisnik> prethodniPrekrsaji = repository.findByVozac(z.getVozac().getJmbg());
		List<Zapisnik> prethodniPrekrsaji = repository.findByVozacDate(z.getVozac().getJmbg(), z.getDatum().minusYears(2));
		
		if (prethodniPrekrsaji != null)
			System.out.println("prethodnih prekrsaja je bilo: " + prethodniPrekrsaji.size());
		
		FactHandle zh = kieSession.insert(z);
		FactHandle vh = kieSession.insert(z.getVozac());
		kieSession.fireAllRules();
		
		int suma = 0;
		
		for (Zapisnik zapisnik : prethodniPrekrsaji) {
			suma += zapisnik.getKaznenihPoenaUkupno();
		}
		
		// izvuci preostale zapise iz baze i proveriti da li se za prekrsaj oduzima dozvola
		
		kieSession.delete(vh);
		kieSession.delete(zh);
		
		z.setKaznenihPoenaUkupno(0);
		
		if (z.getPrekoracenjeBrzine() != null)
			if (z.getPrekoracenjeBrzine().getKazneniPoeni() != null)
				z.setKaznenihPoenaUkupno(z.getKaznenihPoenaUkupno() + z.getPrekoracenjeBrzine().getKazneniPoeni());
		
		if (z.getVoznjaPodUticajem() != null)
			if (z.getVoznjaPodUticajem().getKazneniPoeni() != null)
				z.setKaznenihPoenaUkupno(z.getKaznenihPoenaUkupno() + z.getVoznjaPodUticajem().getKazneniPoeni());
		
		suma += z.getKaznenihPoenaUkupno();
		
		System.err.println("ukupno kaznenih poena u poslednje 2 godine: " + suma);
		
		repository.save(z);
		
		Boolean oduzimanje = false;
		
		if (z.getVozac().getTipDozvole() == TipDozvole.PROBNA) {
			if (suma >= 9)
				oduzimanje = true;
		} else if (z.getVozac().getTipDozvole() == TipDozvole.TRAJNA) {
			if (suma >= 18)
				oduzimanje = true;
		}
		
		return new ZapisnikDTO(z, suma, oduzimanje);
	}
	
	public Boolean dodajPrekrsaj(ProlazakKrozCrvenoSvetlo p) {
		kieSession.getAgenda().getAgendaGroup("modul2").setFocus();
		
//		kieSession.setGlobal("lista", dates);
//    	kieSession.setGlobal("pravilo2", dates2);
//    	kieSession.setGlobal("pravilo3", dates3);
    	
    	kieSession.insert(p);
    	kieSession.fireAllRules();
    	
    	for (int i = 0; i < dates.size(); i++) {
        	for (int j = 0; j < dates.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		if (dates.get(i).getTablice().equals(dates.get(j).getTablice()) && dates.get(j).getPrekrsaji().containsAll(dates.get(i).getPrekrsaji())) {
//        			System.out.println("removing: " + dates.get(i).getPrekrsaji());
        			dates.remove(i);
        		}
        	}
        }
    	
    	for (int i = 0; i < dates2.size(); i++) {
        	for (int j = 0; j < dates2.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		if (dates2.get(i).getTablice().equals(dates2.get(j).getTablice()) && dates2.get(j).getPrekrsaji().containsAll(dates2.get(i).getPrekrsaji())) {
//        			System.out.println("removing: " + dates2.get(i).getPrekrsaji());
        			dates2.remove(i);
        		}
        	}
        }
    	
    	for (int i = 0; i < dates.size(); i++) {
        	for (int j = 0; j < dates2.size(); j++) {
        		if (dates.get(i).getTablice().equals(dates2.get(j).getTablice()) && dates.get(i).getPrekrsaji().containsAll(dates2.get(j).getPrekrsaji())) {
//        			System.out.println("removing: " + dates2.get(j).getPrekrsaji());
        			dates2.remove(j);
        		}
        	}
        }
    			
		return true;
	}
	
	public Boolean dodajPrekrsaj(PreticanjePrekoPuneLinije p) {
		kieSession.getAgenda().getAgendaGroup("modul2").setFocus();
		
//		kieSession.setGlobal("lista", dates);
//    	kieSession.setGlobal("pravilo2", dates2);
//    	kieSession.setGlobal("pravilo3", dates3);

    	kieSession.insert(p);
    	kieSession.fireAllRules();
    	    	
		return true;
	}
	
	public List<NasilnickaVoznja> getDates() {
		return dates;
	}
	
	public List<NasilnickaVoznja> getDates2() {
		return dates2;
	}
	
	public List<NasilnickaVoznja> getDates3() {
		return dates3;
	}

	public void reload() {
		System.out.println("reloading");
		
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration config = ks.newKieBaseConfiguration();
	    config.setOption(EventProcessingOption.STREAM);
	    
	    kieSession.dispose();
		kieSession = kieContainer.newKieBase(config).newKieSession();
		
		dates = new ArrayList<NasilnickaVoznja>();
		dates2 = new ArrayList<NasilnickaVoznja>();
		dates3 = new ArrayList<NasilnickaVoznja>();
		
		kieSession.setGlobal("lista", dates);
		kieSession.setGlobal("pravilo2", dates2);
		kieSession.setGlobal("pravilo3", dates3);

	}
	
}
