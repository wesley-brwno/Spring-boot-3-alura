package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Modifying
    @Query("DELETE FROM Consulta c WHERE FUNCTION('TIMESTAMPDIFF', HOUR, CURRENT_TIMESTAMP(), c.data) > 24 AND c.id = :id")
    void deleteAppointmentIfBefore24Hours(Long id);

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    @Query("SELECT c.data FROM Consulta  c WHERE c.paciente.id = :pacienteId")
    Optional<LocalDateTime> findByPacienteId(Long pacienteId);
}
