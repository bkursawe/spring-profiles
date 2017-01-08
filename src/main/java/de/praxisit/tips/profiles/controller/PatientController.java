package de.praxisit.tips.profiles.controller;

import de.praxisit.tips.profiles.common.DateSupplier;
import de.praxisit.tips.profiles.data.Patient;
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
public class PatientController {

    private final PatientRepository repository;
    private final DateSupplier now;

    @Autowired
    public PatientController( PatientRepository repository, DateSupplier now ) {
        this.repository = repository;
        this.now = now;
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
