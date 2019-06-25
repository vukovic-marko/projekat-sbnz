package sbnz.integracija.example;

import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbnz.integracija.example.dto.NasilnickaDTO;
import sbnz.integracija.example.dto.TipPrekrsaja;
import sbnz.integracija.example.dto.ZapisnikDTO;
import sbnz.integracija.example.events.NasilnickaVoznja;
import sbnz.integracija.example.events.PreticanjePrekoPuneLinije;
import sbnz.integracija.example.events.ProlazakKrozCrvenoSvetlo;
import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class SampleAppController {

	private final SampleAppService sampleService;
	
	@Autowired
	public SampleAppController(SampleAppService sampleService) {
		this.sampleService = sampleService;
	}

	@Autowired
	public SampleAppRepository sampleAppRepository;

	// modul 1
	@PostMapping("/zapisnik")
	public ResponseEntity<ZapisnikDTO> sendZapisnik(@RequestBody Zapisnik zapisnik) {
		return ResponseEntity.ok(sampleService.obradiZapisnik(zapisnik));
	}

	@GetMapping("/zapisnik")
	public ResponseEntity<List<Zapisnik>> getVozaci(@RequestBody Vozac vozac) {
		return ResponseEntity.ok(sampleAppRepository.findByVozac(vozac.getJmbg()));
	}
	
	// modul 2
	
	@GetMapping("/nasilnicka/dates")
	public ResponseEntity<List<NasilnickaVoznja>> getDates() {
		return ResponseEntity.ok(sampleService.getDates());
	}
	
	@GetMapping("/nasilnicka/dates2")
	public ResponseEntity<List<NasilnickaVoznja>> getDates2() {
		return ResponseEntity.ok(sampleService.getDates2());
	}
	
	@GetMapping("/nasilnicka/dates3")
	public ResponseEntity<List<NasilnickaVoznja>> getDates3() {
		return ResponseEntity.ok(sampleService.getDates3());
	}
	
	@PostMapping("/nasilnicka")
	public ResponseEntity<Boolean> addNas(@RequestBody NasilnickaDTO n) {
		if (n.getTipPrekrsaja() == TipPrekrsaja.Prolazak_kroz_crveno_svetlo) {
			ProlazakKrozCrvenoSvetlo p = new ProlazakKrozCrvenoSvetlo(n.getTablice());
			return ResponseEntity.ok(sampleService.dodajPrekrsaj(p));
		} else if (n.getTipPrekrsaja() == TipPrekrsaja.Prelazak_preko_pune_linije) {
			PreticanjePrekoPuneLinije p = new PreticanjePrekoPuneLinije(n.getTablice());
			return ResponseEntity.ok(sampleService.dodajPrekrsaj(p));
		}
				
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/nasilnicka/dates/{idx}")
	public ResponseEntity<Boolean> deleteFromDates(@PathVariable Integer idx) {
		if (sampleService.getDates().size() > idx) {
			sampleService.getDates().remove(idx.intValue());
			return ResponseEntity.ok(true);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/nasilnicka/dates2/{idx}")
	public ResponseEntity<Boolean> deleteFromDates2(@PathVariable Integer idx) {
		if (sampleService.getDates2().size() > idx) {
			sampleService.getDates2().remove(idx.intValue());
			return ResponseEntity.ok(true);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/nasilnicka/dates3/{idx}")
	public ResponseEntity<Boolean> deleteFromDates3(@PathVariable Integer idx) {
		if (sampleService.getDates3().size() > idx) {
			sampleService.getDates3().remove(idx.intValue());
			return ResponseEntity.ok(true);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	// dodavanje pravila
	
	@SuppressWarnings("serial")
	@GetMapping("/maven")
	public void maven() {
		File pom = new File("../drools-spring-kjar/pom.xml");

		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(pom);
		request.setGoals(new ArrayList<String>() {{ add("clean"); add("install"); }});

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File("/opt/maven")); // TODO vidi sta s ovim
		try {
			invoker.execute(request);
			
			Thread.sleep(5000);
			
			sampleService.reload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
