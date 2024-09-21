package server;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${jws.secret}")
    public String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/user/login", "/vehicleData/report").permitAll()  // Allow unauthenticated access to these endpoints
                .anyRequest().authenticated();  // All other requests require authentication
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint().oidcUserService(oidcUserService())
//                .and()
//                .defaultSuccessUrl("/home") ;

    }


    @Bean
    public OidcUserService oidcUserService() {
        OidcUserService oidcUserService = new OidcUserService();
        return oidcUserService;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter();
//        jwtAuthorizationFilter.setSECRET(secret);
//        http.cors().and().csrf().disable()
//                .antMatcher("/**").authorizeRequests()
//                .antMatchers(new String[]{"/user/login"}).permitAll()
//                .and()
//                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .anyRequest().authenticated();
//
//    }
}
