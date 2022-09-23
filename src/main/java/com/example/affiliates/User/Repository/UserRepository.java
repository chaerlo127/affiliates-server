package com.example.affiliates.User.Repository;

import com.example.affiliates.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByStudentNum(String studentNum);

    boolean existsByStudentNum(String studentNum);

    boolean existsByNickName(String nickName);
}
