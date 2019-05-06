package utvrdjivanja;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

public class TestUtvrdjenjePrekoracenjaBrzine {

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
	public void testirajTrajnuDozvolu() {
		
		Zapisnik z = new Zapisnik();
		Vozac v = new Vozac();
		v.setTipDozvole(Vozac.TipDozvole.TRAJNA);
		z.setVozac(v);
		z.setDozvoljenaBrzina(50.0);
		z.setOstvarenaBrzina(55.0);
		
		kSession.insert(z);
		kSession.insert(v);
		
		Zapisnik z1 = new Zapisnik();
		Vozac v1 = new Vozac();
		v1.setTipDozvole(Vozac.TipDozvole.TRAJNA);
		z1.setVozac(v1);
		z1.setDozvoljenaBrzina(50.0);
		z1.setOstvarenaBrzina(45.0);
		
		kSession.insert(z1);
		kSession.insert(v1);
		
		kSession.fireAllRules();
		
		assertThat(5.0, is(z.getPrekoracenjeBrzine().getIznosPrekoracenja()));
		assertNull(z1.getPrekoracenjeBrzine());
	}
	
	@Test
	public void testirajProbnuDozvolu() {
		
		Zapisnik z = new Zapisnik();
		Vozac v = new Vozac();
		v.setTipDozvole(Vozac.TipDozvole.PROBNA);
		z.setVozac(v);
		z.setDozvoljenaBrzina(130.0);
		z.setOstvarenaBrzina(105.0);
		
		kSession.insert(z);
		kSession.insert(v);
		
		Zapisnik z1 = new Zapisnik();
		Vozac v1 = new Vozac();
		v1.setTipDozvole(Vozac.TipDozvole.PROBNA);
		z1.setVozac(v1);
		z1.setDozvoljenaBrzina(100.0);
		z1.setOstvarenaBrzina(95.0);
		
		kSession.insert(z1);
		kSession.insert(v1);
		
		Zapisnik z2 = new Zapisnik();
		Vozac v2 = new Vozac();
		v2.setTipDozvole(Vozac.TipDozvole.PROBNA);
		z2.setVozac(v2);
		z2.setDozvoljenaBrzina(50.0);
		z2.setOstvarenaBrzina(50.0);
		
		kSession.insert(z2);
		kSession.insert(v2);
		
		Zapisnik z3 = new Zapisnik();
		Vozac v3 = new Vozac();
		v3.setTipDozvole(Vozac.TipDozvole.PROBNA);
		z3.setVozac(v3);
		z3.setDozvoljenaBrzina(60.0);
		z3.setOstvarenaBrzina(54.0);
		
		kSession.insert(z2);
		kSession.insert(v2);
		
		kSession.fireAllRules();
		
		assertThat(5.0, is(z.getPrekoracenjeBrzine().getIznosPrekoracenja()));
		assertThat(5.0, is(z1.getPrekoracenjeBrzine().getIznosPrekoracenja()));
		assertThat(5.0, is(z2.getPrekoracenjeBrzine().getIznosPrekoracenja()));
		assertNull(z3.getPrekoracenjeBrzine());
	}

}
