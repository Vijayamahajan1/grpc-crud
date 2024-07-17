package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.User;
import com.example.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }

    public User update(User user){
        return userRepository.save(user);
    }
    
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
       return userRepository.findAll();
    }
}
