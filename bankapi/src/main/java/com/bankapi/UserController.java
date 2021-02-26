package com.bankapi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bankapi.data.AccountOwnerRepository;
import com.bankapi.data.UserRepository;
import com.bankapi.entity.Account;
import com.bankapi.entity.AccountOwner;
import com.bankapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users", produces="application/hal+json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountOwnerRepository accountOwnerRepository;

    @GetMapping(produces = "application/hal+json")
    public CollectionModel<User> allUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        for (final User user : users) {
            Link selfLink = linkTo(methodOn(UserController.class)
                    .byId(user.getSsn())).withSelfRel();
            user.add(selfLink);
            Link accountsLink = linkTo(methodOn(UserController.class)
                    .getAccountsByUser(user.getSsn())).withRel("accounts");
            user.add(accountsLink);
        }


        Link link = linkTo(UserController.class).withSelfRel();
        return CollectionModel.of(users, link);
    }

    @GetMapping(path = "/{userId}/accounts", produces = "application/hal+json")
    public CollectionModel<Account> getAccountsByUser(@PathVariable("userId") String userId) {
        List<AccountOwner> accountOwnerList = accountOwnerRepository.findAccountOwnerByUserSsn(userId);

        Link link = linkTo(methodOn(UserController.class)
                .getAccountsByUser(userId)).withSelfRel();
        return CollectionModel.of(accountOwnerList.stream().map(accountOwner -> accountOwner.getAccount().addLink()).collect(Collectors.toList()), link);
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public User byId(@PathVariable("userId") String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping(path = "/{userId}", consumes = "application/json")
    public User putUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PatchMapping(path = "/{userId}", consumes = "application/json")
    public User patchUser(@PathVariable("userId") String userId,
                          @RequestBody User patch) {
        User user = byId(userId);
        if (patch.getName() != null) {
            user.setName(patch.getName());
        }
        if (patch.getUserDetails() != null) {
            if (patch.getUserDetails().getAge() != 0) {
                user.getUserDetails().setAge(patch.getUserDetails().getAge());
            }
            if (patch.getUserDetails().getNationality() != null) {
                user.getUserDetails().setNationality(patch.getUserDetails().getNationality());
            }
        }
        return userRepository.save(user);
    }
}
