package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.AppointmentCanceling;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final AgendaDeConsultas agendaDeConsultas;

    public ConsultaController(AgendaDeConsultas agendaDeConsultas) {
        this.agendaDeConsultas = agendaDeConsultas;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        DadosDetalhamentoConsulta agendarDTO = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(agendarDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid AppointmentCanceling dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.ok("Consulta Cancelada!");
    }
}
