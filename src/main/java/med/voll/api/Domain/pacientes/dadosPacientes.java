package med.voll.api.Domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Domain.medico.DadosEndereco;


public record dadosPacientes(

        @NotBlank
        String nome,
        @NotNull
        @Pattern(regexp = "\\d{1,2}")
        int idade,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        @NotNull
        @Email
        String email,
        @NotBlank
        String sintoma,
        @NotNull
        @Valid
        DadosEndereco endereco) {

}
