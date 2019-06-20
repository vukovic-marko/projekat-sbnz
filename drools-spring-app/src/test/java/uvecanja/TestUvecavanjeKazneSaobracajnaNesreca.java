package uvecanja;

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

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestUvecavanjeKazneSaobracajnaNesreca {

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
    public void testBlagaAlkoholisanostProbnaDozvola() {
        Zapisnik zapisnik = new Zapisnik();

        Vozac vozac = new Vozac();
        vozac.setIme("Ime");
        vozac.setPrezime("Prezime");
        vozac.setJmbg("123");
        vozac.setBrojDozvole("456");
        vozac.setTipDozvole(Vozac.TipDozvole.PROBNA);

        zapisnik.setVozac(vozac);
        zapisnik.setNaseljenoMesto(true);
        zapisnik.setZona(Zapisnik.Zona.REDOVNA);
        zapisnik.setDozvoljenaBrzina(50.0);
        zapisnik.setOstvarenaBrzina(45.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.1);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(true);
        zapisnik.setPrisutnoDete(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNull(zapisnik.getPrekoracenjeBrzine());
        assertNotNull(zapisnik.getVoznjaPodUticajem());

        assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.BLAGA_ALK, is(zapisnik.getVoznjaPodUticajem().getTip()));

        assertThat(Kazna.Clanovi.CLAN_332a, is(zapisnik.getVoznjaPodUticajem().getClan()));

        assertThat(Arrays.asList(new Double[] {15000.0, 30000.0}), is(zapisnik.getVoznjaPodUticajem().getNovcanaKazna()));
        assertThat(2, is(zapisnik.getVoznjaPodUticajem().getKazneniPoeni()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));
    }

    @Test
    public void prekoracenjeSaobracajnaNesreca() {
        Zapisnik zapisnik = new Zapisnik();

        Vozac vozac = new Vozac();
        vozac.setIme("Ime");
        vozac.setPrezime("Prezime");
        vozac.setJmbg("123");
        vozac.setBrojDozvole("456");
        vozac.setTipDozvole(Vozac.TipDozvole.TRAJNA);

        zapisnik.setVozac(vozac);
        zapisnik.setNaseljenoMesto(true);
        zapisnik.setZona(Zapisnik.Zona.REDOVNA);
        zapisnik.setDozvoljenaBrzina(50.0);
        zapisnik.setOstvarenaBrzina(55.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(true);
        zapisnik.setPrisutnoDete(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNotNull(zapisnik.getPrekoracenjeBrzine());
        assertNull(zapisnik.getVoznjaPodUticajem());

        assertThat(5.0, is(zapisnik.getPrekoracenjeBrzine().getIznosPrekoracenja()));

        assertThat(Kazna.Clanovi.CLAN_334, is(zapisnik.getPrekoracenjeBrzine().getClan()));

        assertThat(Arrays.asList(new Double[] {5000.0, 15000.0}), is(zapisnik.getPrekoracenjeBrzine().getNovcanaKazna()));
        assertThat(2, is(zapisnik.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(true, is(zapisnik.getPrekoracenjeBrzine().getObradjena()));
    }

}
