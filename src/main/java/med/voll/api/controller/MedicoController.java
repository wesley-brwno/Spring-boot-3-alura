package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
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
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadasrtoMedico dados, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(dados);
        repository.save(medico);

        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }
    // 201 Created	The request was successful and the server created a new resource.

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page<DadosListagemMedicos> page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }
    //200 OK	The request was successful and the server is returning content.


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Optional<Medico> optionalMedico = repository.findById(dados.id());

        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.atualizarInformacaos(dados);
            repository.save(medico);
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 404 Not Found | The requested resource could not be found.

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
    // 204 No Content | The request was successful but the server is not returning any content.
}
