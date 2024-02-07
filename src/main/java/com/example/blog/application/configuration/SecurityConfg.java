

package com.example.blog.application.configuration;




import com.example.blog.application.security.CustomUserDetailsServiceImp;
import com.example.blog.application.security.JwtAuthicationEntryPoint;
import com.example.blog.application.security.JwtAuthicationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;



@Configuration
@EnableWebSecurity
//For swagger MVC
@EnableWebMvc

public class SecurityConfg  {
    @Autowired
    private CustomUserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtAuthicationEntryPoint point;
    @Autowired
    private JwtAuthicationFilter filter;


    // Configuration Method for Authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImp).passwordEncoder(passwordEncoder());
    }
    // Create an AuthenticationManager bean


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()


                . requestMatchers("/test")
                .authenticated()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/v3/api-docs/**"
                        ,"/v2/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                        ,"/swagger-resources"
                        ,"/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
//                .requestMatchers(HttpMethod.GET).permitAll()
//                .requestMatchers(HttpMethod.POST).permitAll()
//                .requestMatchers(HttpMethod.DELETE).permitAll()

                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource());
                });

        return http.build();
    }






    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

//    For react cross connection


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Configure your CORS policy here
        // For example, to allow all origins, headers, and methods:
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:3001", "http://example.com", "http://another-example.com"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}




















//package com.example.blog.application.configuration;
//
//import com.example.blog.application.security.CustomUserDetailsServiceImp;
//import com.example.blog.application.security.JwtAuthicationEntryPoint;
//import com.example.blog.application.security.JwtAuthicationFilter;
//import jakarta.servlet.FilterRegistration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.firewall.DefaultHttpFirewall;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.StrictHttpFirewall;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.List;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
////For swagger MVC
//@EnableWebMvc
//
//public class SecurityConfg  {
//@Autowired
//    private CustomUserDetailsServiceImp userDetailsServiceImp;
//
//    @Autowired
//    private JwtAuthicationEntryPoint point;
//    @Autowired
//    private JwtAuthicationFilter filter;
//
//
//    // Configuration Method for Authentication
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userDetailsServiceImp).passwordEncoder(passwordEncoder());
//    }
//    // Create an AuthenticationManager bean
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable())
//                .authorizeRequests()
//
//
//                . requestMatchers("/test")
//                .authenticated()
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/v3/api-docs/**"
//                        ,"/v2/api-docs/**",
//                        "/swagger-ui/**",
//                        "/swagger-ui.html"
//                        ,"/swagger-resources"
//                        ,"/webjars/**").permitAll()
//                .requestMatchers(HttpMethod.GET).permitAll()
////                .requestMatchers(HttpMethod.POST).permitAll()
////                .requestMatchers(HttpMethod.DELETE).permitAll()
//
//                .anyRequest()
//                .authenticated()
//                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//
//        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class)
//       .cors(cors -> {
//            cors.configurationSource(corsConfigurationSource());
//        });
//
//        return http.build();
//    }
//
//
///*
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Allow access to static resources
//                                .requestMatchers(PathRequest.toH2Console()).permitAll() // Allow access to H2 Console
////                                .requestMatchers(PathRequest.toLivenessProbe()).permitAll() // Allow access to liveness probe
//                                .requestMatchers("/test").authenticated() // Secure the "/test" endpoint
//                                .requestMatchers("/api/v1/auth/**").permitAll() // Allow access to authentication-related endpoints
//                                .requestMatchers(
//                                        "/v3/api-docs/**",
//                                        "/v2/api-docs/**",
//                                        "/swagger-ui/**",
//                                        "/swagger-ui.html",
//                                        "/swagger-resources",
//                                        "/webjars/**"
//                                ).permitAll() // Allow access to Swagger resources
//                                .requestMatchers(HttpMethod.GET).permitAll() // Allow all GET requests
//                                .anyRequest().authenticated() // All other requests require authentication
//                )
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors(withDefaults()); // Apply CORS configuration
//
//        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//*/
//
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//}
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }
//
////    For react cross connection
//
//
//
////@Bean
////public FilterRegistrationBean corsFilter() {
////    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////    CorsConfiguration config = new CorsConfiguration();
////
////    // Configure allowed origins, headers, and methods here
////    config.setAllowCredentials(true);
//////    config.addAllowedOrigin("http://localhost:3000"); // Replace with your frontend URL
////    config.addAllowedOriginPattern("*"); // Replace with your frontend URL
//////    config.addAllowedHeader("*");
////    config.addAllowedHeader("Authorization");
////    config.addAllowedHeader("Content-Type");
////    config.addAllowedHeader("Accept");
////
////    // Specify allowed HTTP methods
////    config.addAllowedMethod("GET");
////    config.addAllowedMethod("POST");
////    config.addAllowedMethod("PUT");
////    config.addAllowedMethod("DELETE");
////    config.addAllowedMethod("OPTIONS");
////    config.setMaxAge(3600L);
////
////
////    // Configure allowed request mappings (e.g., /api/v1/**)
//////    config.addAllowedMethod("/api/v1/**"); // Replace with your desired mapping
//////
////    source.registerCorsConfiguration("/**", config);
////    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////
////    bean.setOrder(-110);
////
////    return bean;
//
//
////
////    return new CorsFilter(source);
////}
//
////    @Bean
////    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
////        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter());
////        bean.setOrder(-110); // Set the order for the CorsFilter
////        return bean;
////    }
//
//
//// Same codes upper and lower
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // Configure your CORS policy here
//        // For example, to allow all origins, headers, and methods:
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowCredentials(true);
////        configuration.addAllowedOrigin("http://localhost:3000"); // Replace with your frontend URL
////        configuration.addAllowedHeader("*");
////        configuration.addAllowedMethod("*");
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////
////        return source;
////    }
////
////    @Bean
////    public CorsFilter corsFilter() {
////        return new CorsFilter(corsConfigurationSource());
////    }
//
//
////    @Bean
////    public HttpFirewall httpFirewall() {
////        StrictHttpFirewall firewall = new StrictHttpFirewall();
////        firewall.setAllowUrlEncodedDoubleSlash(true);
////        return firewall;
////    }
//
//
//
//
//    // Configure authentication (user accounts)
////    @Bean
////    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf->csrf.disable())// Disable CSRF protection
////                .authorizeRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Allow static resources
////
////                                .requestMatchers("/admin/**").hasRole("ADMIN")
////                                .requestMatchers("/user/**").hasRole("USER") // Customize roles as needed
////
////                                .requestMatchers("/**").permitAll() // Allow public access to other URLs
////                                .anyRequest().authenticated()
////                ).httpBasic(Customizer.withDefaults())
////                .formLogin(formlogin-> formlogin.loginPage("/sigin").loginProcessingUrl("/muneeb")
////                                .defaultSuccessUrl("/user/index")
//////                        .failureUrl("/login-fail")
////                ); // Use default login form configuration
////
////        return http.build();
////    }
//}