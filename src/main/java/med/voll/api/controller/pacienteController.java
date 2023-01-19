package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Domain.pacientes.Pacientes;
import med.voll.api.Domain.pacientes.pacientesRepository;
import med.voll.api.Domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class pacienteController {

    @Autowired
    private pacientesRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid dadosPacientes dados , UriComponentsBuilder uriBuilder){
        var paciente = new Pacientes(dados);
        repository.save(paciente);

        var URI = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(URI).body(new listarPacientesCadastrados(paciente));

    }

    @GetMapping
    public ResponseEntity <Page<listarPacientesCadastrados>> listarPacientes(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(listarPacientesCadastrados :: new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid dadosAtualizarPacientes dados){
        var Pacientes = repository.getReferenceById(dados.id());
        Pacientes.atualizarInformacaoPaciente(dados);

        return ResponseEntity.ok( new dadosDetalhamentoPacientes(Pacientes));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(Long id){
        var Pacientes = repository.getReferenceById(id);
        Pacientes.excluirPaciente();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(Long id){
        var detalhar = repository.getReferenceById(id);

        return ResponseEntity.ok(new dadosDetalhamentoPacientes(detalhar));

    }

}
