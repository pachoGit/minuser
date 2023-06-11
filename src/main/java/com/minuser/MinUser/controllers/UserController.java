package com.minuser.MinUser.controllers;

import java.util.Optional;

import com.minuser.MinUser.entities.UserEntity;
import com.minuser.MinUser.models.ResponseEstandar;
import com.minuser.MinUser.models.UserEntityCreateRequest;
import com.minuser.MinUser.models.UserEntityQueryParams;
import com.minuser.MinUser.models.UserEntityUpdateRequest;
import com.minuser.MinUser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<ResponseEstandar<UserEntity>> create(@Valid @RequestBody UserEntityCreateRequest request)
    {
        ResponseEstandar<UserEntity> response = null;
        try {
            UserEntity userEntity = this.userService.create(request);
            response = new ResponseEstandar<UserEntity>(HttpStatus.CREATED.value(), "success", "User created", userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e) {
            response = new ResponseEstandar<UserEntity>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "failed", "User not created", null);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }

    @GetMapping(path = "/show")
    public @ResponseBody ResponseEntity<ResponseEstandar<UserEntity>> show(@RequestParam Long id) {
        Optional<UserEntity> userEntity = this.userService.show(id);
        ResponseEstandar<UserEntity> response = null;
        if (userEntity.isPresent()) {
            response = new ResponseEstandar<UserEntity>(200, "success", "User found", userEntity.get());
            return ResponseEntity.ok(response);
        }
        response = new ResponseEstandar<UserEntity>(HttpStatus.NOT_FOUND.value(), "failed", "User not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping(path = "/get")
    public @ResponseBody ResponseEntity<ResponseEstandar<Iterable<UserEntity>>> get(@ModelAttribute UserEntityQueryParams queryParams)
    {
        Iterable<UserEntity> users = this.userService.get(queryParams);
        var response = new ResponseEstandar<Iterable<UserEntity>>(HttpStatus.OK.value(), "success", "List of users", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/list")
    public @ResponseBody ResponseEntity<ResponseEstandar<Page<UserEntity>>> list(@ModelAttribute UserEntityQueryParams queryParams, Pageable pageable)
    {
        Page<UserEntity> users = this.userService.list(queryParams, pageable);
        var response = new ResponseEstandar<Page<UserEntity>>(HttpStatus.OK.value(), "success", "List paginate of users", users);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public @ResponseBody ResponseEntity<ResponseEstandar<UserEntity>> update(@Valid @RequestBody UserEntityUpdateRequest request) {
        ResponseEstandar<UserEntity> response = null;
        try {
            UserEntity userEntity = this.userService.update(request);
            response = new ResponseEstandar<UserEntity>(HttpStatus.OK.value(), "success", "User updated", userEntity);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response = new ResponseEstandar<UserEntity>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "failed", "User not updated", null);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

    }
}
