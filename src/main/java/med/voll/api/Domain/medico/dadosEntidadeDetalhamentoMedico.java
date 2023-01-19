package med.voll.api.Domain.medico;

import med.voll.api.Domain.Endereco.Endereco;

public record dadosEntidadeDetalhamentoMedico(Long id, String nome, String crm, Especialidade especialidade, Endereco endereco) {
    public dadosEntidadeDetalhamentoMedico(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getCrm(), medico.getEspecialidade(),medico.getEndereco());
    }
}
