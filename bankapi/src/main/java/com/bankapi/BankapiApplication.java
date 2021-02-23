package com.bankapi;

//import com.bankapi.data.UserDetailsRepository;
import com.bankapi.data.UserRepository;
import com.bankapi.entity.User;
import com.bankapi.entity.UserDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankapiApplication.class, args);
    }


    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepo
//            , UserDetailsRepository userDetailsRepo
    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

//                UserDetails userDetails = userDetailsRepo.save(new UserDetails(10, "a"));

                userRepo.save(new User("first", new UserDetails(10, "a")));
                userRepo.save(new User("sec", new UserDetails(15, "b")));
//                userRepo.save(new User("third", new UserDetails(25, "v")));
//                userRepo.save(new User("fourth", new UserDetails(50, "c")));
            }
        };
    }
}
