package com.bankapi;

//import com.bankapi.data.UserDetailsRepository;
import java.math.BigDecimal;

import com.bankapi.data.AccountRepository;
import com.bankapi.data.PaymentRepository;
import com.bankapi.data.UserRepository;
import com.bankapi.entity.Account;
import com.bankapi.entity.Payment;
import com.bankapi.entity.User;
import com.bankapi.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankapiApplication {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    public static void main(String[] args) {
        SpringApplication.run(BankapiApplication.class, args);
    }


    @Bean
    public CommandLineRunner dataLoader(
//            UserRepository userRepo
//            , UserDetailsRepository userDetailsRepo
    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

//                Account account1 = new Account("2323434234234", "UAH", new BigDecimal("342"));
//                Account account2 = new Account("45252343565", "UAH", new BigDecimal("643"));
                Account account1 = accountRepo.save(new Account("2323434234234", "UAH", new BigDecimal("342")));
                Account account2 = accountRepo.save(new Account("45252343565", "UAH", new BigDecimal("643")));

                paymentRepo.save(new Payment(account1, account2, "USD", new BigDecimal(123)));

//                UserDetails userDetails = userDetailsRepo.save(new UserDetails(10, "a"));

                userRepo.save(new User("first", new UserDetails(10, "a")));
                userRepo.save(new User("sec", new UserDetails(15, "b")));
//                userRepo.save(new User("third", new UserDetails(25, "v")));
//                userRepo.save(new User("fourth", new UserDetails(50, "c")));
            }
        };
    }
}
