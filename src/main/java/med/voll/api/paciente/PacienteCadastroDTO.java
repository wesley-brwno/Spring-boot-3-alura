package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record PacienteCadastroDTO(
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @Valid
        DadosEndereco dadosEndereco
) {
}
