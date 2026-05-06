package com.nicolasogawa.workshopmongo.resources;

import com.nicolasogawa.workshopmongo.domain.User;
import com.nicolasogawa.workshopmongo.dto.UserDTO;
import com.nicolasogawa.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    public UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findALl() {

        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
