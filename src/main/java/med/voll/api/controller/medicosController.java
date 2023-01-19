package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Domain.medico.Medico;
import med.voll.api.Domain.medico.dadoslistarMedico;
import med.voll.api.Domain.medico.medicoRepository;
import med.voll.api.Domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class medicosController {

    @Autowired
    private medicoRepository Repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid dadosCadastroMedico dados , UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        Repository.save(medico);

        var URI = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(URI).body(new dadosEntidadeDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity <Page<dadoslistarMedico>> listarMedico (@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        var page = Repository.findAllByAtivoTrue(paginacao).map(dadoslistarMedico :: new);

        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid dadosAtualizarMedico dados)
    {
        var medico = Repository.getReferenceById(dados.id());
        medico.atualizarInformacaoMerdico(dados);

        return ResponseEntity.ok(new dadosEntidadeDetalhamentoMedico(medico));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(Long id){
        var medico = Repository.getReferenceById(id);
        medico.excluirMedico();

        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar (Long id){
        var medico = Repository.getReferenceById(id);

        return ResponseEntity.ok(new dadosEntidadeDetalhamentoMedico(medico));

    }


}
