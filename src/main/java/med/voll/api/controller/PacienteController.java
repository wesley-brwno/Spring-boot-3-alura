package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PatientRepository patientRepository;
    public PacienteController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping
    public ResponseEntity<PatientDataDetails> patientRegistration(@RequestBody @Valid PacienteCadastroDTO patientData, UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = new Paciente(patientData);
        patientRepository.save(paciente);

        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDataDetails(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDataListing>> listPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page<PatientDataListing> page = patientRepository.findAll(pageable).map(PatientDataListing::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<PatientDataDetails> updatePatient(@RequestBody @Valid PatientDataUpdate patientUpdate) {
        Optional<Paciente> patientById = patientRepository.findById(patientUpdate.id());

        if (patientById.isPresent()) {
            Paciente paciente = patientById.get();
            paciente.updateData(patientUpdate);
            patientRepository.save(paciente);
            return ResponseEntity.ok(new PatientDataDetails(paciente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<Paciente> patientById = patientRepository.findById(id);
        if (patientById.isPresent()) {
            Paciente paciente = patientById.get();
            paciente.inativatePatient();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
