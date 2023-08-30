package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String CPF;
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteCadastroDTO patientData) {
        this.nome = patientData.nome();
        this.telefone = patientData.telefone();
        this.CPF = patientData.cpf();
        this.email = patientData.email();
        this.endereco = new Endereco(patientData.endereco());
    }

    public void updateData(PatientDataUpdate updateData) {
        if (updateData.nome() != null)
            this.nome = updateData.nome();
        if (updateData.telefone() != null)
            this.telefone = updateData.telefone();
        if (updateData.endereco() != null)
            this.endereco.atualizarEndereco(updateData.endereco());
    }
}


