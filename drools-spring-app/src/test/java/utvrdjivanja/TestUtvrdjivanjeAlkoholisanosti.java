package utvrdjivanja;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.KaznaVoznjaPodUticajem;
import sbnz.integracija.example.facts.Zapisnik;

public class TestUtvrdjivanjeAlkoholisanosti {

	public static KieSession kSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		kSession = kContainer.newKieSession();
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
	public void testBlaga() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(0.1);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.BLAGA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testUmerena() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(0.3);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.UMERENA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testSrednja() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(0.5);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.SREDNJA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testVisoka() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(0.9);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VISOKA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testTeska() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(1.2);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.TESKA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}

	@Test
	public void testVeomaTeska() {
			
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(1.7);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VEOMA_TESKA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testPotpuna() {
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(2.1);
		kSession.insert(z);
		
		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.POTPUNA_ALK, is(z.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testNepostojeca() {
		Zapisnik z = new Zapisnik();
		kSession.insert(z);
		
		kSession.fireAllRules();

		assertNull(z.getVoznjaPodUticajem());
	}
	
}
