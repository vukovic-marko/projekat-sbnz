package uvecanja;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sbnz.integracija.example.facts.Kazna;
import sbnz.integracija.example.facts.KaznaVoznjaPodUticajem;
import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class TestUvecanjeKazneOmetanje {

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
        zapisnik.setOmeta(true);
        zapisnik.setSaobracajnaNesreca(true);
        zapisnik.setPrisutnoDete(true);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNull(zapisnik.getPrekoracenjeBrzine());
        assertNotNull(zapisnik.getVoznjaPodUticajem());

        assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.BLAGA_ALK, is(zapisnik.getVoznjaPodUticajem().getTip()));

        assertThat(Kazna.Clanovi.CLAN_332a, is(zapisnik.getVoznjaPodUticajem().getClan()));

        assertThat(Arrays.asList(new Double[] {30000.0}), is(zapisnik.getVoznjaPodUticajem().getNovcanaKazna()));
        assertThat(4, is(zapisnik.getVoznjaPodUticajem().getKazneniPoeni()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));
    }
}
