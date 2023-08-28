package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrar(@RequestBody @Valid DadosCadasrtoMedico dados) {
        return ResponseEntity.ok(repository.save(new Medico(dados)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page<DadosListagemMedicos> page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    //200 OK	The request was successful and the server is returning content.


    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Optional<Medico> optionalMedico = repository.findById(dados.id());

        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.atualizarInformacaos(dados);
            repository.save(medico);
            return ResponseEntity.ok("Médico atualizado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
    // 204 No Content | The request was successful but the server is not returning any content.
}
