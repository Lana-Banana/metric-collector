package com.naver.se.test.repository;

import com.naver.se.test.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface JpaUserRepository extends JpaRepository<UserVO, String> {

    List<UserVO> findByUserName(String Name);
}
