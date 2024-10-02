package fr.hb.jg.centrale.security;

import fr.hb.jg.centrale.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtTokenFilter jwtTokenFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers("/api/login", "/api/register").permitAll()
                    .requestMatchers(
                        AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                        AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/brand/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/model/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/address/**")
                    ).permitAll()
                    .requestMatchers(
                        AntPathRequestMatcher.antMatcher("/api/favorite/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/listing/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/listing")
                    ).authenticated()
                    .requestMatchers( // Useless there, but example to secure all admin route
                        AntPathRequestMatcher.antMatcher("/api/admin/**")
                    ).hasAuthority("ADMIN")
                    .requestMatchers(
                        AntPathRequestMatcher.antMatcher("/api/**")
                    ).hasAnyAuthority("ADMIN", "SUPER_ADMIN")
            );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
