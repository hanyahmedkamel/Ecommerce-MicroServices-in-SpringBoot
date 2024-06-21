package com.Almaamouny.UserService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByEmail(String email);

    @Transactional
    public void deleteByEmail(String email);







}
