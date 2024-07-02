package idblab.todaysmenu.repository;


import idblab.todaysmenu.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // 필요에 따라 사용자 정의 메서드를 추가할 수 있습니다.
}