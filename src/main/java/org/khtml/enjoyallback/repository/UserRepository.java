package org.khtml.enjoyallback.repository;

import org.khtml.enjoyallback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserBySocialId(String socialId);
}
