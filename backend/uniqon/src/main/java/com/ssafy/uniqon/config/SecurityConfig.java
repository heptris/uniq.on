package com.ssafy.uniqon.config;

import com.ssafy.uniqon.config.jwt.JwtAccessDeniedHandler;
import com.ssafy.uniqon.config.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.uniqon.config.jwt.TokenProvider;
import com.ssafy.uniqon.util.NoPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF ?????? Disable
        http.csrf().disable()
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                // exception handling ??? ??? ????????? ?????? ???????????? ??????
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // ??????????????? ??????????????? ????????? ??????
                // ???????????? ????????? ???????????? ?????? ????????? ?????? ????????? Stateless ??? ??????
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // ?????????, ???????????? API ??? ????????? ?????? ???????????? ????????? ???????????? ????????? permitAll ??????
                .and()
                .authorizeRequests()
                .antMatchers( "/api/invest/question/{startupId}/page"
                ,"/api/invest", "/api/invest/{startupId}").permitAll()
                .antMatchers("/api/member/**", "/api/invest/**",
                        "/api/mypage/**", "/api/alarm/**")
                .authenticated()

                // JwtFilter ??? addFilterBefore ??? ???????????? JwtSecurityConfig ???????????? ??????
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("https://j7a507.p.ssafy.io:3000");
        configuration.addAllowedOrigin("https://j7a507.p.ssafy.io:80");
        configuration.addAllowedOrigin("https://j7a507.p.ssafy.io");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
