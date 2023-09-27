package lk.agri.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CSRFFilter csrfFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers( "/**").permitAll();//test

//        http
//                .csrf()
//                .csrfTokenRepository(csrfTokenRepository()) // Use the CsrfTokenRepository you defined
//                .and()
//                // Other security configurations (e.g., authentication, authorization) go here
//                .authorizeRequests()
//                .antMatchers("/farmer/csrf-token").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        http
//                .authorizeRequests()
//                // Secure all endpoints except "/public/your-unsecured-endpoint"
//                    .antMatchers("/farmer/csrf-token").permitAll()
//                    .anyRequest().authenticated();
//                .and()
//                .httpBasic()
//                .and()
//                .csrf()
//                     .csrfTokenRepository(csrfTokenRepository());
        http.addFilterBefore(csrfFilter, BasicAuthenticationFilter.class);

    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}
