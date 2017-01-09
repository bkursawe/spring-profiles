package de.praxisit.tips.profiles.controller;

import de.praxisit.tips.profiles.common.DateSupplier;
import de.praxisit.tips.profiles.data.Patient;
import de.praxisit.tips.profiles.exceptions.PatientNotFoundException;
import de.praxisit.tips.profiles.exceptions.WrongRequestParameterException;
import de.praxisit.tips.profiles.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Patient findPatient( @PathVariable("id") final int id ) {
        Optional<Patient> result = repository.findOne( id );
        return result.orElseThrow( () -> new PatientNotFoundException( id ) );
    }

    @GetMapping("")
    public List<Patient> findAll() {
        return repository.findAll( ).collect( toList( ) );
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public Patient create( @RequestBody final Patient patient ) {
        if (patient == null) {
            throw new WrongRequestParameterException( "patient" );
        }

        patient.setId( null );

        try {
            return repository.save( patient );
        } catch (DataAccessException ex) {
            throw new WrongRequestParameterException( "patient", ex );
        }
    }

    @PutMapping("")
    @Transactional
    public Patient save( @RequestBody final Patient patient ) {
        if (patient == null || patient.getId( ) == null) {
            throw new WrongRequestParameterException( "patient" );
        }

        try {
            return repository.save( patient );
        } catch (DataAccessException ex) {
            throw new WrongRequestParameterException( "patient", ex );
        }
    }

    @DeleteMapping("/{id:\\d+}")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete( @PathVariable("id") final int id ) {
        try {
            repository.delete( id );
        } catch (DataAccessException ex) {
            throw new PatientNotFoundException( id );
        }
    }

}
