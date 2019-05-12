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

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestOdredjivanjeKazneZonaSkole {

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
    public void testNasilnicka() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(91.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(true);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_329, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testNasilnickaVanNaselja() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(101.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_329, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testOd61() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(61.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_330, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testOd61VanVremenskogOkvira() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(61.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(true);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("06:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_331, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(7, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(4,is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd61VanVremenskogOkviraVanNaselja() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(61.0);
        z.setPrekoracenjeBrzine(k);
        z.setNaseljenoMesto(false);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("06:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_331, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(6, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(3, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd51Do60() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(51.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_331, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(6, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(5, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd51Do60VanVremenskogOkvira() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(51.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setNaseljenoMesto(true);
        z.setVreme(LocalTime.parse("22:00:00"));

        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_331, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(7, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(4, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd31Do50() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(31.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));


        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_332, is(z.getPrekoracenjeBrzine().getClan()));
        assertThat(4, is(z.getPrekoracenjeBrzine().getKazneniPoeni()));
        assertThat(2, is(z.getPrekoracenjeBrzine().getZabranaUpravljanja()));
    }

    @Test
    public void testOd31Do50VanVremenskogOkviraVanNaselja() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(31.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setNaseljenoMesto(false);
        z.setVreme(LocalTime.parse("06:00:00"));


        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_332a, is(z.getPrekoracenjeBrzine().getClan()));
    }

    @Test
    public void testOd21Do30() {
        Zapisnik z = new Zapisnik();
        KaznaPrekoracenjaBrzine k = new KaznaPrekoracenjaBrzine();
        k.setIznosPrekoracenja(21.0);
        z.setPrekoracenjeBrzine(k);
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));


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
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));


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
        z.setZona(Zapisnik.Zona.ZONA_SKOLE);
        z.setVreme(LocalTime.parse("07:00:00"));


        kSession.insert(z);
        kSession.insert(k);

        kSession.fireAllRules();

        assertThat(Kazna.Clanovi.CLAN_334, is(z.getPrekoracenjeBrzine().getClan()));
    }
}
