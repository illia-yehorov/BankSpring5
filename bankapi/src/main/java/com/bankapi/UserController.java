/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi;

import com.bankapi.data.UserRepository;
import com.bankapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users", produces="application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces="application/json")
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/{userId}", produces="application/json")
    public User byId(@PathVariable("userId") Long userId) {
        return userRepository.findById(userId).get();
    }





}
