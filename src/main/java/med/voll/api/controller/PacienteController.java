package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteCadastroDTO;
import med.voll.api.paciente.PatientDataDetails;
import med.voll.api.paciente.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
