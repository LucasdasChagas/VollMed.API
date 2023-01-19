package med.voll.api.Domain.medico;

public record dadoslistarMedico(

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public dadoslistarMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
