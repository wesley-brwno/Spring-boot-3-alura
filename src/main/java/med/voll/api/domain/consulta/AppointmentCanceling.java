package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record AppointmentCanceling(
        @NotNull
        @JsonAlias("consulta_id")
        Long id,
        @NotNull
        @JsonAlias("motivo")
        @Enumerated(EnumType.STRING)
        MotivoCancelamento motivoCancelamento
) {
}
