package med.voll.api.medico;

public record DadosListagemMedicos(long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicos (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
