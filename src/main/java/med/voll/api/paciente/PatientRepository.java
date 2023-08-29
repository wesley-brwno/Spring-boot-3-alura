package med.voll.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Paciente , Long> {
    Page<Paciente> findAll(Pageable pageable);
}
