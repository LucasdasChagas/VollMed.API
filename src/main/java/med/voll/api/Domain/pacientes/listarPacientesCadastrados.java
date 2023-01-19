package med.voll.api.Domain.pacientes;

public record listarPacientesCadastrados(

      Long id,

    String nome,

    int idade,

    String cpf,

    String email,

    String sintoma
    )

{
    public listarPacientesCadastrados(Pacientes pacientes){
        this(pacientes.getId(), pacientes.getNome(),pacientes.getIdade(),pacientes.getCpf(),pacientes.getEmail(),pacientes.getSintoma());
    }

}
