package odredjivanja;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sbnz.integracija.example.facts.Kazna;
import sbnz.integracija.example.facts.KaznaPrekoracenjaBrzine;
import sbnz.integracija.example.facts.Zapisnik;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestOdredjivanjeKazneVanNaselja {

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
    public void testNasilnickeVoznje() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(101.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_329, is(z.getPrekoracenjeBrzine().getClan()));

    }

    @Test
    public void testOd81Do100() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(81.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_330, is(z.getPrekoracenjeBrzine().getClan()));

    }

    @Test
    public void testOd61Do80() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(61.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_331, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(6, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(3, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd41Do60() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(41.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_332, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(3, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(1, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd21Do40() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(21.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_332a, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testOd11Do20() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(11.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_333, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testDo10() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(1.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_334, is(z.getPrekoracenjeBrzine().getClan()));
    }

}
