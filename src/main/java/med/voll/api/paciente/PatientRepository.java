package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Paciente , Long> {
}
