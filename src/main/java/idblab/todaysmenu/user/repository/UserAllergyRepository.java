package idblab.todaysmenu.user.repository;

import idblab.todaysmenu.user.domain.UserAllergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAllergyRepository extends JpaRepository<UserAllergy, Long> {
}
