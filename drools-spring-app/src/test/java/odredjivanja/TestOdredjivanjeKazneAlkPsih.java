package odredjivanja;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.Kazna;
import sbnz.integracija.example.facts.KaznaVoznjaPodUticajem;
import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

public class TestOdredjivanjeKazneAlkPsih {

public static KieSession kSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		
	    KieBaseConfiguration config = ks.newKieBaseConfiguration();
	    config.setOption(EventProcessingOption.STREAM);
		
		kSession = kContainer.newKieBase(config).newKieSession();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		kSession.dispose();
	}

	@Before
	public void setUpBefore() throws Exception {
		kSession.getAgenda().getAgendaGroup("modul1").setFocus();
	}

	@Test
	public void testPotpuneAlkoholisanosti() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.POTPUNA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_329, is(z.getVoznjaPodUticajem().getClan()));
	}
	
	@Test
	public void testVeomaTeskeAlkoholisanosti() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VEOMA_TESKA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_330, is(z.getVoznjaPodUticajem().getClan()));
	}
	
	@Test
	public void testTeskeAlkoholisanosti() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.TESKA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_330, is(z.getVoznjaPodUticajem().getClan()));
	}
	
	@Test
	public void testVoznjaPodUticajemPsihoaktivnihSups() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_331, is(z.getVoznjaPodUticajem().getClan()));
		assertThat(8, is(z.getVoznjaPodUticajem().getKazneniPoeni()));
		assertThat(6, is(z.getVoznjaPodUticajem().getZabranaUpravljanja()));
	}
	
	@Test
	public void testVisokaAlk() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VISOKA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_331, is(z.getVoznjaPodUticajem().getClan()));
		assertThat(8, is(z.getVoznjaPodUticajem().getKazneniPoeni()));
		assertThat(4, is(z.getVoznjaPodUticajem().getZabranaUpravljanja()));
	}
	
	@Test
	public void testSrednjaAlk() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.SREDNJA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_332, is(z.getVoznjaPodUticajem().getClan()));
		assertThat(6, is(z.getVoznjaPodUticajem().getKazneniPoeni()));
		assertThat(3, is(z.getVoznjaPodUticajem().getZabranaUpravljanja()));
	}
	
	@Test
	public void testUmerenaAlk() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.UMERENA_ALK);
		z.setVoznjaPodUticajem(k);
		
		kSession.insert(z);
		kSession.insert(k);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_332a, is(z.getVoznjaPodUticajem().getClan()));
	}
	
	@Test
	public void testBlagaAlk() {
		Zapisnik z = new Zapisnik();
		KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
		Vozac v = new Vozac();
		v.setTipDozvole(Vozac.TipDozvole.PROBNA);
		k.setTip(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.BLAGA_ALK);
		k.setClan(null);
		z.setVoznjaPodUticajem(k);
		z.setVozac(v);
		
		kSession.insert(z);
		kSession.insert(k);
		kSession.insert(v);
		
		kSession.fireAllRules();
		
		assertThat(Kazna.Clanovi.CLAN_332a, is(z.getVoznjaPodUticajem().getClan()));
		
		v.setTipDozvole(Vozac.TipDozvole.TRAJNA);
		k.setClan(null);
		z.setVoznjaPodUticajem(k);
		z.setVozac(v);
		
		kSession.insert(z);
		kSession.insert(k);
		kSession.insert(v);
		
		kSession.fireAllRules();
		
		assertNull(z.getVoznjaPodUticajem().getClan());
	}

}
