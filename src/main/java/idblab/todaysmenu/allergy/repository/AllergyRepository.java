package idblab.todaysmenu.allergy.repository;

import idblab.todaysmenu.allergy.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllergyRepository extends JpaRepository<Allergy,Long> {
    Optional<Allergy> findByAllergyName(String allergyName);
}
