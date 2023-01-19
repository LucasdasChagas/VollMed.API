package med.voll.api.Domain.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Domain.Endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String cpf;

    private Boolean ativo = true;
    private String email;
    private String sintoma;
    @Embedded
    private  Endereco endereco;

    public Pacientes(dadosPacientes dados){
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.cpf = dados.cpf();
        this.ativo = true;
        this.email = dados.email();
        this.sintoma = dados.sintoma();
        this.endereco = new Endereco(dados.endereco());
    }


    public void atualizarInformacaoPaciente(dadosAtualizarPacientes dados) {

        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.idade() != null) this.idade = dados.idade();
        if (dados.email() != null) this.email = (dados.email());
        if (dados.sintoma() != null) this.sintoma = dados.sintoma();
        if (dados.endereco() != null) this.endereco.atualizarInformacaoPaciente(dados.endereco());
    }

    public void excluirPaciente() {
        this.ativo = false;
    }
}
