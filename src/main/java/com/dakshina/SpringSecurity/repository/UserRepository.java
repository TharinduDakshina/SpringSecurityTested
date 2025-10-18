package com.dakshina.SpringSecurity.repository;

import com.dakshina.SpringSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByUserName(String username);
}
