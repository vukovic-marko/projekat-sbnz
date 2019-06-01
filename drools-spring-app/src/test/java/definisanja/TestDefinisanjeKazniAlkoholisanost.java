package definisanja;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sbnz.integracija.example.facts.Kazna;
import sbnz.integracija.example.facts.KaznaVoznjaPodUticajem;
import sbnz.integracija.example.facts.Zapisnik;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDefinisanjeKazniAlkoholisanost {

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
    public void testNasilnickeVoznjeSaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_329);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {45, 60}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {130000.0, 150000.0}, is(k.getNovcanaKazna()));
        assertThat(17, is(k.getKazneniPoeni()));
        assertThat(11, is(k.getZabranaUpravljanja()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testNasilnickeVoznje() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_329);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {30, 60}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {120000.0, 140000.0}, is(k.getNovcanaKazna()));
        assertThat(15, is(k.getKazneniPoeni()));
        assertThat(9, is(k.getZabranaUpravljanja()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan330SaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_330);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {45}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {120000.0, 150000.0}, is(k.getNovcanaKazna()));
        assertThat(16, is(k.getKazneniPoeni()));
        assertThat(9, is(k.getZabranaUpravljanja()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan330() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_330);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {15, 0}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {100000.0, 120000.0}, is(k.getNovcanaKazna()));
        assertThat(14, is(k.getKazneniPoeni()));
        assertThat(8, is(k.getZabranaUpravljanja()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan331SaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_331);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {0, 60}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {40000.0, 60000.0}, is(k.getNovcanaKazna()));
        assertThat(3, is(k.getKazneniPoeni()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan331() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_331);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {30}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {20000.0, 40000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan332SaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_332);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Integer[] {0, 45}, is(k.getZatvorskaKazna()));
        assertThat(new Double[] {20000.0, 40000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan332() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_332);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Double[] {10000.0, 20000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan333SaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_333);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Double[] {6000.0, 18000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan333() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_333);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Double[] {5000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan334SaobracajnaNesreca() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_334);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(true);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Double[] {5000.0, 15000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }

    @Test
    public void testClan334() {
        Zapisnik z = new Zapisnik();
        KaznaVoznjaPodUticajem k = new KaznaVoznjaPodUticajem();
        k.setClan(Kazna.Clanovi.CLAN_334);
        k.setObradjena(false);
        z.setVoznjaPodUticajem(k);
        z.setSaobracajnaNesreca(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(new Double[] {3000.0}, is(k.getNovcanaKazna()));
        assertThat(true, is(k.getObradjena()));
    }
}
