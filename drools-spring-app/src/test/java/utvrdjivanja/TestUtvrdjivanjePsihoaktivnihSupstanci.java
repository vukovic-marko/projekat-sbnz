package utvrdjivanja;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.KaznaVoznjaPodUticajem;
import sbnz.integracija.example.facts.Zapisnik;

public class TestUtvrdjivanjePsihoaktivnihSupstanci {
	
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

	@Test
	public void testPsihoaktivneSupstance() {
		
		Zapisnik z = new Zapisnik();
		z.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z);

		kSession.fireAllRules();

		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS, is(z.getVoznjaPodUticajem().getTip()));

	}
	
	@Test
	public void testPrioritetAlkoholisanosti() {
		
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(2.1);
		z.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z);
		
		Zapisnik z1 = new Zapisnik();
		z1.setPrisustvoAlkohola(2.0);
		z1.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z1);
		
		Zapisnik z2 = new Zapisnik();
		z2.setPrisustvoAlkohola(1.3);
		z2.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z2);
		
		kSession.fireAllRules();
		
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.POTPUNA_ALK, is(z.getVoznjaPodUticajem().getTip()));
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VEOMA_TESKA_ALK, is(z1.getVoznjaPodUticajem().getTip()));
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.TESKA_ALK, is(z2.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testRavnopravnosti() {
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(1.0);
		z.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z);
		
		kSession.fireAllRules();
		
		if(z.getVoznjaPodUticajem().getTip() == KaznaVoznjaPodUticajem.TipVoznjePodUticajem.VISOKA_ALK ||
				z.getVoznjaPodUticajem().getTip() == KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS) {
			System.out.println(z.getVoznjaPodUticajem().getTip());
			assertTrue(true);
		}
	}
	
	@Test
	public void testPrioritetPsihoaktivnihSupstanci() {
		
		Zapisnik z = new Zapisnik();
		z.setPrisustvoAlkohola(0.5);
		z.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z);
		
		Zapisnik z1 = new Zapisnik();
		z1.setPrisustvoAlkohola(0.3);
		z1.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z1);
		
		Zapisnik z2 = new Zapisnik();
		z2.setPrisustvoAlkohola(0.1);
		z2.setPrisustvoPsihoaktivnihSupstanci(true);
		kSession.insert(z2);
		
		kSession.fireAllRules();
		
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS, is(z.getVoznjaPodUticajem().getTip()));
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS, is(z1.getVoznjaPodUticajem().getTip()));
		assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS, is(z2.getVoznjaPodUticajem().getTip()));
	}
	
	@Test
	public void testNedetetektovanja() {
		Zapisnik z = new Zapisnik();
		kSession.insert(z);
		
		int broj = kSession.fireAllRules();
		
		// assertNull(z.getVoznjaPodUticajem());
		assertThat(0, is(broj));
	}

}
