package com.kumar.gamesstore;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = MailSenderAutoConfiguration.class)
public class PrimeGamingStoreApplicationTests {
}





