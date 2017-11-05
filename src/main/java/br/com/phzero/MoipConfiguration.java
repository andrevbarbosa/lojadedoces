package br.com.phzero;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;

@Configuration
@EnableWebMvc
@ComponentScan({ "br.com.phzero.controller" })
public class MoipConfiguration {
    @Value("${moip.token}")
    private String token;

    @Value("${moip.key}")
    private String key;

    @Bean
    @Scope("prototype")
    public Client moipClient() {
		Authentication auth = new BasicAuth(token, key);
		Client client = new Client(Client.SANDBOX, auth);
		
		return client;
    }
}
