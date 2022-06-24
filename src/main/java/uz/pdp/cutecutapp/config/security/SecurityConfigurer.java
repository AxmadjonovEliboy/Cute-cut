package uz.pdp.cutecutapp.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.cutecutapp.config.security.filters.CustomAuthenticationFilter;
import uz.pdp.cutecutapp.config.security.filters.CustomAuthorizationFilter;
import uz.pdp.cutecutapp.services.auth.AuthUserService;
import uz.pdp.cutecutapp.utils.JwtUtils;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    public final static String[] WHITE_LIST = {
//            "/**",
            "/api/login",
            "/auth/api/v1/token",
            "/auth/api/v1/loginByPhone",
            "/auth/api/v1/register",
            "/actuator/**",
            "/auth/api/v1/confirmUserCode",
            "/auth/api/v1/confirmAdminCode",
            "/swagger-ui/**",
            "/api/docs/**",

    };

    private final AuthUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").permitAll();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
//   http.exceptionHandling().authenticationEntryPoint()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtUtils));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtUtils, userService), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

