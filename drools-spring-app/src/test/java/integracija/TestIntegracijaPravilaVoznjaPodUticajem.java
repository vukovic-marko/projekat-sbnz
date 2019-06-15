package integracija;

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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestIntegracijaPravilaVoznjaPodUticajem {

    private static KieSession kSession;

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


    /**
     *  test slucaj 1:
     *      - voznja sa 0.5 promila alkohola u krvi
     *      - vozac sa trajnom dozvolom
     */
    @Test
    public void test1() {
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
        zapisnik.setOstvarenaBrzina(50.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.5);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNull(zapisnik.getPrekoracenjeBrzine());
        assertNotNull(zapisnik.getVoznjaPodUticajem());

        assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.SREDNJA_ALK, is(zapisnik.getVoznjaPodUticajem().getTip()));

        assertThat(Kazna.Clanovi.CLAN_332, is(zapisnik.getVoznjaPodUticajem().getClan()));

        assertThat(6, is(zapisnik.getVoznjaPodUticajem().getKazneniPoeni()));
        assertThat(3, is(zapisnik.getVoznjaPodUticajem().getZabranaUpravljanja()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));

        assertThat(Arrays.asList(new Double[]{10000.0, 20000.0}), is(zapisnik.getVoznjaPodUticajem().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));
    }

    /**
     *  test slucaj 2:
     *      - voznja pod uticajem psihoaktivnih supstanci
     *      - vozac sa trajnom dozvolom
     */
    @Test
    public void test2() {
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
        zapisnik.setOstvarenaBrzina(50.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(true);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNull(zapisnik.getPrekoracenjeBrzine());
        assertNotNull(zapisnik.getVoznjaPodUticajem());

        assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.PSIHOAKTIVNA_SUPS, is(zapisnik.getVoznjaPodUticajem().getTip()));

        assertThat(Kazna.Clanovi.CLAN_331, is(zapisnik.getVoznjaPodUticajem().getClan()));
        assertThat(8, is(zapisnik.getVoznjaPodUticajem().getKazneniPoeni()));
        assertThat(6, is(zapisnik.getVoznjaPodUticajem().getZabranaUpravljanja()));

        assertThat(Arrays.asList(new Integer[] {30}), is(zapisnik.getVoznjaPodUticajem().getZatvorskaKazna()));
        assertThat(Arrays.asList(new Double[] {20000.0, 40000.0}), is(zapisnik.getVoznjaPodUticajem().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));
    }

    /**
     *  test slucaj 3:
     *      - voznja sa 0.1 promilom alkohola u krvi
     *      - vozac sa probnom dozvolom
     */
    @Test
    public void test3() {
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
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNull(zapisnik.getPrekoracenjeBrzine());
        assertNotNull(zapisnik.getVoznjaPodUticajem());

        assertThat(KaznaVoznjaPodUticajem.TipVoznjePodUticajem.BLAGA_ALK, is(zapisnik.getVoznjaPodUticajem().getTip()));

        assertThat(Kazna.Clanovi.CLAN_332a, is(zapisnik.getVoznjaPodUticajem().getClan()));

        assertThat(Arrays.asList(new Double[] {10000.0}), is(zapisnik.getVoznjaPodUticajem().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getVoznjaPodUticajem().getObradjena()));
    }
}
