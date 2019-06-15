package integracija;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sbnz.integracija.example.facts.Kazna;
import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestIntegracijaPravilaPrekoracenjeBrzine {

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
     *      - prekoracenje od 5km/h u nasljenom mestu, u redovnoj zoni
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
        zapisnik.setOstvarenaBrzina(55.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNotNull(zapisnik.getPrekoracenjeBrzine());
        assertNull(zapisnik.getVoznjaPodUticajem());

        assertThat(5.0, is(zapisnik.getPrekoracenjeBrzine().getIznosPrekoracenja()));

        assertThat(Kazna.Clanovi.CLAN_334, is(zapisnik.getPrekoracenjeBrzine().getClan()));

        assertThat(Arrays.asList(new Double[]{3000.0}), is(zapisnik.getPrekoracenjeBrzine().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getPrekoracenjeBrzine().getObradjena()));
    }

    /**
     *  test slucaj 2:
     *      - prekoracenje od 5km/h u nasljenom mestu, u usporenoj zoni
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
        zapisnik.setZona(Zapisnik.Zona.ZONA_USPORENOG_SAOBRACAJA);
        zapisnik.setDozvoljenaBrzina(50.0);
        zapisnik.setOstvarenaBrzina(55.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNotNull(zapisnik.getPrekoracenjeBrzine());
        assertNull(zapisnik.getVoznjaPodUticajem());

        assertThat(5.0, is(zapisnik.getPrekoracenjeBrzine().getIznosPrekoracenja()));

        assertThat(Kazna.Clanovi.CLAN_333, is(zapisnik.getPrekoracenjeBrzine().getClan()));

        assertThat(Arrays.asList(new Double[]{5000.0}), is(zapisnik.getPrekoracenjeBrzine().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getPrekoracenjeBrzine().getObradjena()));
    }

    /**
     *  test slucaj 3:
     *      - prekoracenje od 15km/h u nasljenom mestu, u zoni 30
     *      - vozac sa trajnom dozvolom
     */
    @Test
    public void test3() {
        Zapisnik zapisnik = new Zapisnik();

        Vozac vozac = new Vozac();
        vozac.setIme("Ime");
        vozac.setPrezime("Prezime");
        vozac.setJmbg("123");
        vozac.setBrojDozvole("456");
        vozac.setTipDozvole(Vozac.TipDozvole.TRAJNA);

        zapisnik.setVozac(vozac);
        zapisnik.setNaseljenoMesto(true);
        zapisnik.setZona(Zapisnik.Zona.ZONA_30);
        zapisnik.setDozvoljenaBrzina(30.0);
        zapisnik.setOstvarenaBrzina(45.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNotNull(zapisnik.getPrekoracenjeBrzine());
        assertNull(zapisnik.getVoznjaPodUticajem());

        assertThat(15.0, is(zapisnik.getPrekoracenjeBrzine().getIznosPrekoracenja()));

        assertThat(Kazna.Clanovi.CLAN_333, is(zapisnik.getPrekoracenjeBrzine().getClan()));

        assertThat(Arrays.asList(new Double[]{5000.0}), is(zapisnik.getPrekoracenjeBrzine().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getPrekoracenjeBrzine().getObradjena()));
    }

    /**
     *  test slucaj 4:
     *      - prekoracenje od 5km/h u nasljenom mestu, u redovnoj zoni
     *      - vozac sa probnom dozvolom
     */
    @Test
    public void test4() {
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
        zapisnik.setOstvarenaBrzina(50.0);

        zapisnik.setUlica("Ulica");
        zapisnik.setPrisustvoAlkohola(0.0);
        zapisnik.setPrisustvoPsihoaktivnihSupstanci(false);
        zapisnik.setOmeta(false);
        zapisnik.setSaobracajnaNesreca(false);

        kSession.insert(zapisnik);
        kSession.insert(vozac);

        kSession.fireAllRules();

        assertNotNull(zapisnik.getPrekoracenjeBrzine());
        assertNull(zapisnik.getVoznjaPodUticajem());

        assertThat(5.0, is(zapisnik.getPrekoracenjeBrzine().getIznosPrekoracenja()));

        assertThat(Kazna.Clanovi.CLAN_334, is(zapisnik.getPrekoracenjeBrzine().getClan()));

        assertThat(Arrays.asList(new Double[]{3000.0}), is(zapisnik.getPrekoracenjeBrzine().getNovcanaKazna()));
        assertThat(true, is(zapisnik.getPrekoracenjeBrzine().getObradjena()));
    }
}
