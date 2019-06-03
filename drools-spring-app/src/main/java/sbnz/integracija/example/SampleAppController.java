package sbnz.integracija.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.facts.Vozac;
import sbnz.integracija.example.facts.Zapisnik;

import java.util.List;

//import sbnz.integracija.example.facts.Item;

//@CrossOrigin(origins = "http://localhost:3304")
@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final SampleAppService sampleService;

	@Autowired
	public SampleAppController(SampleAppService sampleService) {
		this.sampleService = sampleService;
	}

	@Autowired
	public SampleAppRepository sampleAppRepository;

//	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
//	public Item getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
//			@RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {
//
//		Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);
//
//		log.debug("Item request received for: " + newItem);
//
//		Item i2 = sampleService.getClassifiedItem(newItem);
//
//		return i2;
//	}

	@GetMapping("/hello")
	public List<Zapisnik> hello() {
		Zapisnik zapisnik = new Zapisnik();
		zapisnik.setOmeta(true);
		Vozac vozac = new Vozac();
		vozac.setIme("prvi vozac");
		zapisnik.setVozac(vozac);
		sampleAppRepository.save(zapisnik);

		Zapisnik zapisnik1 = new Zapisnik();
		zapisnik1.setOmeta(false);
		Vozac vozac1 = new Vozac();
		vozac1.setIme("prvi vozac");
		zapisnik1.setVozac(vozac1);
		sampleAppRepository.save(zapisnik1);

		return sampleAppRepository.findByVozac(vozac.getJmbg());
	}

	@PostMapping("/zapisnik")
	public ResponseEntity<Zapisnik> sendZapisnik(@RequestBody Zapisnik zapisnik) {
		System.out.println("***");
		System.out.println("ime: " + zapisnik.getVozac().getIme());
		System.out.println("prezime: " + zapisnik.getVozac().getPrezime());
		System.out.println("jmbg: " + zapisnik.getVozac().getJmbg());
		System.out.println("broj dozvole: " + zapisnik.getVozac().getBrojDozvole());
		System.out.println("tip dozvole: " + zapisnik.getVozac().getTipDozvole());

		System.out.println("ulica: " + zapisnik.getUlica());
		System.out.println("naseljeno mesto: " + zapisnik.getNaseljenoMesto());
		System.out.println("zona: " + zapisnik.getZona());
		System.out.println("ostvarena brzina: " + zapisnik.getOstvarenaBrzina());
		System.out.println("dozvoljena brzina: " + zapisnik.getDozvoljenaBrzina());
		System.out.println("kolicina alkohola: " + zapisnik.getPrisustvoAlkohola());
		System.out.println("psihoaktivne sups: " + zapisnik.getPrisustvoPsihoaktivnihSupstanci());
		System.out.println("saobracajna nesreca: " + zapisnik.getSaobracajnaNesreca());
		System.out.println("ometa: " + zapisnik.getOmeta());


		//return ResponseEntity.ok(sampleAppRepository.save(zapisnik));
		return ResponseEntity.ok(null);

	}

	@GetMapping("/zapisnik")
	public ResponseEntity<List<Zapisnik>> getVozaci(@RequestBody Vozac vozac) {
		return ResponseEntity.ok(sampleAppRepository.findByVozac(vozac.getJmbg()));
	}

	@GetMapping("/hi")
	public String hi() {
		return "hi!";
	}
	
}
