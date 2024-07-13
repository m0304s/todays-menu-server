package idblab.todaysmenu.user.repository;


import idblab.todaysmenu.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
}