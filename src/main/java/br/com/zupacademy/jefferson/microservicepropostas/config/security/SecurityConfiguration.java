package br.com.zupacademy.jefferson.microservicepropostas.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizedRequests  ->
                authorizedRequests
                        .antMatchers(HttpMethod.GET, "/nova-proposta/**").hasAuthority("SCOPE_scope-proposta")
                        .antMatchers(HttpMethod.POST, "/nova-proposta").hasAuthority("SCOPE_scope-proposta")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_scope-proposta")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
