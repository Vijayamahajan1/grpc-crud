package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
