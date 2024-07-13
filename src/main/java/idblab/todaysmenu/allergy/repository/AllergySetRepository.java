package idblab.todaysmenu.allergy.repository;

import idblab.todaysmenu.allergy.domain.AllergySet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergySetRepository extends JpaRepository<AllergySet,Long> {
}
