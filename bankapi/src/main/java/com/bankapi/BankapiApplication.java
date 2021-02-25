package com.bankapi;

//import com.bankapi.data.UserDetailsRepository;
import static com.bankapi.vo.Currency.UAH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.bankapi.data.AccountOwnerRepository;
import com.bankapi.data.AccountRepository;
import com.bankapi.data.PaymentRepository;
import com.bankapi.data.UserRepository;
import com.bankapi.entity.Account;
import com.bankapi.entity.AccountOwner;
import com.bankapi.entity.Payment;
import com.bankapi.entity.User;
import com.bankapi.entity.UserDetails;
import com.bankapi.vo.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class BankapiApplication {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private AccountOwnerRepository accountOwnerRepo;

    public static void main(String[] args) {
        SpringApplication.run(BankapiApplication.class, args);
    }


    @Bean
    public CommandLineRunner dataLoader() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {


                List<String> nationalities = Arrays.asList(
                        "Albanian",
                        "Danish",
                        "German",
                        "Russian",
                        "Ukrainian",
                        "Sri Lankan"
                );

                Stream.of("Ivan Prokofev",
                        "Bruce Herman",
                        "Valerie Gin",
                        "Jane Smith",
                        "Oliver Jamie",
                        "Marconi Guglielmo"
                ).forEach(name -> {
                    int age = getRandomNumber(16, 99);
                    String nationality = nationalities.get(getRandomNumber(0, nationalities.size()-1));
                    User user = userRepo.save(new User(name, new UserDetails(age, nationality)));
                    List<Account> accounts = new ArrayList<>();
                    for (int i = 0; i < getRandomNumber(4, 10); i++) {
                        Currency currency = Currency.values()[getRandomNumber(0, Currency.values().length - 1)];
                        Account account = accountRepo.save(new Account(new BigDecimal(getRandomNumber(1, 1000) * 10), currency));
                        accountOwnerRepo.save(new AccountOwner(user, account));
                        accounts.add(account);
                    }
                    for (int i = 0; i < getRandomNumber(10, 100); i++) {
                        Account debitAccount = accounts.get(getRandomNumber(0, accounts.size() - 1));
                        Account creditAccount = accounts.get(getRandomNumber(0, accounts.size() - 1));
                        paymentRepo.save(new Payment(debitAccount, creditAccount, new BigDecimal(getRandomNumber(1, 100) * 5)));
                    }
                });
            }
        };
    }


    public int getRandomNumber(int min, int max) {
        int i = (int) ((Math.random() * (max - min)) + min);
//        log.info("getRandomNumber" + i);
        return i;
    }
}
