package sbnz.integracija.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import sbnz.integracija.example.facts.Zapisnik;

import java.time.LocalDate;
import java.util.List;

public interface SampleAppRepository extends MongoRepository<Zapisnik, String> {

    @Query("{ 'vozac.jmbg' : ?0 }")
    List<Zapisnik> findByVozac(String jmbg);
    
    @Query("{ 'vozac.jmbg' : ?0, 'datum' : { $gte : ?1 } }")
    List<Zapisnik> findByVozacDate(String jmgb, LocalDate date);
}
