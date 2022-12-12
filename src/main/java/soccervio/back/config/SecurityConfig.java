package soccervio.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import soccervio.back.constants.ApiContant;
import soccervio.back.filters.AuthentificationFilter;
import soccervio.back.filters.AutorisationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AutorisationFilter autorisationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers(ApiContant.BASE_URL + "/users/sign-up").permitAll()
                .antMatchers(ApiContant.BASE_URL+ "/users/sign-in").permitAll();

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilter(new AuthentificationFilter(authenticationManager(new AuthenticationConfiguration())));
        httpSecurity.addFilterBefore(autorisationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
