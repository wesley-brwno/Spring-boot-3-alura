package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "paciente")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String CPF;
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteCadastroDTO patientData) {
        this.nome = patientData.nome();
        this.telefone = patientData.telefone();
        this.CPF = patientData.cpf();
        this.endereco = new Endereco(patientData.dadosEndereco());
    }
}


