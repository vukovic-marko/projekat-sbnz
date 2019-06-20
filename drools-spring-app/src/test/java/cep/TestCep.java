package cep;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import static org.hamcrest.Matchers.*;

import sbnz.integracija.example.events.NasilnickaVoznja;
//import sbnz.integracija.example.events.KretanjePrekoNeisprekidaneLinije;
//import sbnz.integracija.example.events.NasilnickaVoznja;
import sbnz.integracija.example.events.ProlazakKrozCrvenoSvetlo;

public class TestCep {

	public static KieSession kSession;
	public static SessionPseudoClock clock;
//	public static List<NasilnickaVoznja> lista;

    @Before
    public void setUpBeforeClass() throws Exception {        
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		
	    KieBaseConfiguration config = ks.newKieBaseConfiguration();
	    config.setOption(EventProcessingOption.STREAM);
	    
	    KieSessionConfiguration kConfig = ks.newKieSessionConfiguration();
	    kConfig.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
	    
		
		kSession = kContainer.newKieBase(config).newKieSession(kConfig, null);

//        lista = new ArrayList<>();
//        kSession.setGlobal("lista", lista);
    }

    @After
    public void tearDownAfterClass() throws Exception {
        kSession.dispose();
    }
    
    @Before
    public void setUpBefore() throws Exception {
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();
    }
    
    @Test
    public void test() {
    	clock = kSession.getSessionClock();
//    	List<Date> dates = new ArrayList<Date>();
    	List<NasilnickaVoznja> dates = new ArrayList<NasilnickaVoznja>();
    	kSession.setGlobal("lista", dates);
        //LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault())

        ProlazakKrozCrvenoSvetlo e = new ProlazakKrozCrvenoSvetlo(new Date(clock.getCurrentTime()),"NS123");
        kSession.insert(e);

        kSession.fireAllRules();
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();

        System.err.println(dates.size());
        assertThat(0, is(dates.size()));
        
        clock.advanceTime(3, TimeUnit.MINUTES);
        ProlazakKrozCrvenoSvetlo e2 = new ProlazakKrozCrvenoSvetlo(new Date(clock.getCurrentTime()),"NS123");
        kSession.insert(e2);

        kSession.fireAllRules();
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();
        
        System.err.println(dates.size());
        System.err.println(dates.get(0).getPrekrsaji());
        
        assertThat(1, is(dates.size()));

        
        clock.advanceTime(2, TimeUnit.MINUTES);
        ProlazakKrozCrvenoSvetlo e1 = new ProlazakKrozCrvenoSvetlo(new Date(clock.getCurrentTime()),"NS123");
        kSession.insert(e1);

        kSession.fireAllRules();
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();
                
//        for (int i = 0; i < dates.size(); i++) {
//        	for (int j = 0; j < dates.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
//        		
//        		if (dates.get(i) == dates.get(j)) {
//        			dates.remove(i);
//        		}
//        	}
//        }
//        for (int i = 0; i < dates.size(); i++) {
//        	for (int j = 0; j < dates.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
//        		
//        		if (dates.get(i).getPrekrsaji().get(0).equals(dates.get(j).getPrekrsaji().get(0))) {
//        			dates.remove(i);
//        		}
//        	}
//        }
        for (int i = 0; i < dates.size(); i++) {
        	for (int j = 0; j < dates.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		//if (dates.get(i).getPrekrsaji().get(0).equals(dates.get(j).getPrekrsaji().get(0))) {
        		if (dates.get(i).getTablice().equals(dates.get(j).getTablice()) && dates.get(j).getPrekrsaji().containsAll(dates.get(i).getPrekrsaji())) {
        			System.out.println("removing: " + dates.get(i).getPrekrsaji());
        			dates.remove(i);
        		}
        	}
        }
        
        System.err.println(dates.size());
        System.err.println(dates.get(0).getPrekrsaji());
        
        assertThat(1, is(dates.size()));
        
        clock.advanceTime(2, TimeUnit.MINUTES);
        ProlazakKrozCrvenoSvetlo e3 = new ProlazakKrozCrvenoSvetlo(new Date(clock.getCurrentTime()),"NS123");
        kSession.insert(e3);

        kSession.fireAllRules();
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();
                
//        for (int i = 0; i < dates.size(); i++) {
//        	for (int j = 0; j < dates.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
//        		
//        		if (dates.get(i).getPrekrsaji().get(0).equals(dates.get(j).getPrekrsaji().get(0))) {
//        			dates.remove(i);
//        		}
//        	}
//        }
        
        for (int i = 0; i < dates.size(); i++) {
        	for (int j = 0; j < dates.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		//if (dates.get(i).getPrekrsaji().get(0).equals(dates.get(j).getPrekrsaji().get(0))) {
        		if (dates.get(i).getTablice().equals(dates.get(j).getTablice()) && dates.get(j).getPrekrsaji().containsAll(dates.get(i).getPrekrsaji())) {
        			System.out.println("removing: " + dates.get(i).getPrekrsaji());
        			dates.remove(i);
        		}
        	}
        }
        
        System.err.println(dates.size());
        System.err.println(dates.get(0).getPrekrsaji());
        
        assertThat(1, is(dates.size()));
        
        clock.advanceTime(5, TimeUnit.MINUTES);
        ProlazakKrozCrvenoSvetlo e4 = new ProlazakKrozCrvenoSvetlo(new Date(clock.getCurrentTime()),"NS123");
        kSession.insert(e4);

        kSession.fireAllRules();
        kSession.getAgenda().getAgendaGroup("modul2").setFocus();
        
        for (int i = 0; i < dates.size(); i++) {
        	for (int j = 0; j < dates.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		//if (dates.get(i).getPrekrsaji().get(0).equals(dates.get(j).getPrekrsaji().get(0))) {
        		if (dates.get(i).getTablice().equals(dates.get(j).getTablice()) && dates.get(j).getPrekrsaji().containsAll(dates.get(i).getPrekrsaji())) {
        			System.out.println("removing: " + dates.get(i).getPrekrsaji());
        			dates.remove(i);
        		}
        	}
        }
        
        System.err.println(dates.size());
        System.err.println(dates.get(0).getPrekrsaji());
        System.err.println(dates.get(1).getPrekrsaji());
        
        assertThat(2, is(dates.size()));

    }
}
