package med.voll.api.Domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Domain.Endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(dadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.ativo = true;
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacaoMerdico(dadosAtualizarMedico dados) {

        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.especialidade() != null) this.especialidade = dados.especialidade();
        if (dados.endereco() != null) this.endereco.atualizarInformacaoMedico(dados.endereco());
    }

    public void excluirMedico(){
        this.ativo = false;
    }
}