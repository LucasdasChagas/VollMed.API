package med.voll.api.Domain.medico;

import jakarta.validation.constraints.NotNull;

public record dadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        Especialidade especialidade,
        DadosEndereco endereco
){


}