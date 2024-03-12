package com.ispan.projectX.config.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return  http

                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(request -> request

                        .requestMatchers("/loginPage","/mainPage","/registerPage","/register/**","/forgetPassword/**","/clearSessionAndCookies").permitAll()


//                        .requestMatchers("/oauthLogin","/logoutPage").authenticated()
                        .requestMatchers("/userLogin").hasAnyRole("HR1", "M1", "USER","SELLER")
                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginPage")
                        .defaultSuccessUrl("/oauthLogin", true)
                )

                .formLogin(form -> form
                        .loginPage("/loginPage")
                        .defaultSuccessUrl("/userLogin", true)
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )

                .logout(logout -> logout.permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/mainPage")
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/access-denied")
                )


                //設定session創建機制
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))


                .csrf(csrf -> csrf.disable())
                //設定csrf保護，要開啟的話，必須連sessionManagement也開啟，並且在requestHeader必須帶XSRF-TOKEN的值
                //requestHeader的key必須是:X-XSRF-TOKEN，value為cookie中XSRF-TOKEN的value
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(createCsrfHandler())
//                        .ignoringRequestMatchers("/register","/userLogin")
//                )

                //同源設定，createCoreConfig()寫在下面
                .cors(cors -> cors
                        .configurationSource(createCoreConfig())
                )

                .build();

        // 一般寫法
//        http.authorizeHttpRequests()
//                .requestMatchers("/home","/loginPage").permitAll()
//                .requestMatchers("/userLogin","/oauthLogin").hasAnyRole("HR2","S2","USER")
//                .requestMatchers("/adminPage").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//
//
//                .oauth2Login()
//                .loginPage("/loginPage")
//                .defaultSuccessUrl("/oauthLogin", true)
//                .and()
//
//
//                .formLogin()
//                .loginPage("/loginPage")
//                .defaultSuccessUrl("/userLogin",true)
//                .loginProcessingUrl("/authenticateTheUser")
//                .and()
//
//
//                .logout()
//                .logoutSuccessUrl("/home")
//                .permitAll()
//                .and()
//
//                .exceptionHandling()
//                .accessDeniedPage("/access-denied")
//
//                .and()
//                .cors(cors -> cors
//                        .configurationSource(createCoreConfig())
//                );
//
//
//        return http.build();
    }


    //csrf設定
    private CorsConfigurationSource createCoreConfig(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        //config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);

        return source;
    }

    private CsrfTokenRequestAttributeHandler createCsrfHandler(){
        CsrfTokenRequestAttributeHandler csrf = new CsrfTokenRequestAttributeHandler();
        csrf.setCsrfRequestAttributeName(null);
        return csrf;
    }
}