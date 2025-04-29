package br.com.mybooks.config;

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

import br.com.mybooks.auth.filter.JwtTokenFilter;
import br.com.mybooks.auth.impl.JwtTokenProviderImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtTokenProviderImpl jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        return http
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                    authorizeHttpRequest -> authorizeHttpRequest
                                        .requestMatchers(
                                            "/auth/login",
                                            "/auth/refresh-token/**",
                                            "/swagger-ui/**",
                                            "/v3/api-docs/**"
                                        ).permitAll()
                                        .requestMatchers("/api/**").authenticated()
                                        .requestMatchers("/api/users").denyAll()
                )
                .cors(cors -> {})
                .build();
    }

}
