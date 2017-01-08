package de.praxisit.tips.profiles.services;

import de.praxisit.tips.profiles.entities.Patient;
import de.praxisit.tips.profiles.exceptions.PatientNotFoundException;
import de.praxisit.tips.profiles.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * REST-Service for {@link Patient}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@RestController
@RequestMapping(path = "/patient")
public class PatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientService( PatientRepository repository ) {
        this.repository = repository;
    }

    @GetMapping("/{id:\\d+}")
    public Patient findPatient(@PathVariable("id") final int id) {
        return repository.findOne( id ).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @GetMapping("/")
    public List<Patient> findAll() {
        return repository.findAll().collect(toList());
    }

}
