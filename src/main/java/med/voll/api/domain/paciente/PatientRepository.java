package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Paciente , Long> {
    Page<Paciente> findAll(Pageable pageable);

    @Query("SELECT p.ativo FROM Paciente p WHERE p.id = :id")
    boolean findAtivoById(Long id);
}
