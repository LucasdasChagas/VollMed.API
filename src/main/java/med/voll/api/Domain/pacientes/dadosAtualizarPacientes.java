package med.voll.api.Domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.medico.DadosEndereco;

public record dadosAtualizarPacientes(
        @NotNull
        Long id,
        String nome,
        Integer idade,
        @NotNull
        @Email
        String email,
        String sintoma,
        @NotNull
        @Valid
        DadosEndereco endereco

) {
}
