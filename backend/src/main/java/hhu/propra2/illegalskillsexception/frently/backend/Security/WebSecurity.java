package hhu.propra2.illegalskillsexception.frently.backend.Security;

import hhu.propra2.illegalskillsexception.frently.backend.Services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static hhu.propra2.illegalskillsexception.frently.backend.Security.SecurityConstants.*;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST1).permitAll()
                .antMatchers(HttpMethod.POST, PROPAY_TEST1).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST2).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST3).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST4).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST5).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST6).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST7).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST8).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST9).permitAll()
                .antMatchers(HttpMethod.GET, PROPAY_TEST10).permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addExposedHeader(HEADER_STRING);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}