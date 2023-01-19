package med.voll.api.Domain.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record dadosDetalhamentoPacientes(String nome, int idade, String sintoma) {

    public dadosDetalhamentoPacientes(Pacientes pacientes){
        this(pacientes.getNome(), pacientes.getIdade(),pacientes.getSintoma());
    }
}
