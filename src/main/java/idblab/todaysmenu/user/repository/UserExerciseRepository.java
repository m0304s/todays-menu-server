package idblab.todaysmenu.user.repository;

import idblab.todaysmenu.user.domain.UserExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseRepository extends JpaRepository<UserExercise,Long> {
}
