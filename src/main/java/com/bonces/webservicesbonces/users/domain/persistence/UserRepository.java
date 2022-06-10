package com.bonces.webservicesbonces.users.domain.persistence;

import com.bonces.webservicesbonces.users.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
