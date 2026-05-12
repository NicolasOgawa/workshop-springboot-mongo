package com.nicolasogawa.workshopmongo.services;

import com.nicolasogawa.workshopmongo.domain.User;
import com.nicolasogawa.workshopmongo.dto.UserDTO;
import com.nicolasogawa.workshopmongo.repository.UserRepository;
import com.nicolasogawa.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException ("Usuário não encontrado"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDto) {
        return new User (objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
