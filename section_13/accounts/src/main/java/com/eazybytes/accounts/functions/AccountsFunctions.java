package com.eazybytes.accounts.functions;

import com.eazybytes.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);


    /**
     * @param accountsService
     * @return
     */
    @Bean
    public Consumer <Long> updateCommunication(IAccountsService accountsService) {

        return accountNumber -> {

            logger.info("Updating Communication for the account number: " + accountNumber.toString());

            accountsService.updateCommunication(accountNumber);
        };
    }
}
