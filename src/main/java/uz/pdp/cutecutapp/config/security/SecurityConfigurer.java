package uz.pdp.cutecutapp.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.cutecutapp.config.security.filters.CustomAuthenticationFilter;
import uz.pdp.cutecutapp.config.security.filters.CustomAuthenticationProvider;
import uz.pdp.cutecutapp.config.security.filters.CustomAuthorizationFilter;
import uz.pdp.cutecutapp.services.auth.AuthUserService;
import uz.pdp.cutecutapp.utils.JwtUtils;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    public final static String[] WHITE_LIST = {
//            "/**",
            "/api/login",
            "/api/v1/refresh-token",
            "/api/v1/auth/token",
            "/api/v1/auth/loginByPassword",
            "/api/v1/auth/loginByPhone",
            "/api/v1/auth/register",
            "/api/v1/auth/confirmLoginCode",
            "/api/v1/auth/confirmRegisterCode",
            "/api/v1/auth/register",
            "/api/v1/auth/update",

            "/api/v1/file/auth/uploadPicture",
            "/api/v1/file/auth/download/**",

            "/swagger-ui/**",
            "/api/docs/**",

    };

    private final AuthUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final CustomAuthenticationProvider authenticationProvider;

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

        http.addFilter(new CustomAuthenticationFilter(authenticationProvider, jwtUtils));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtUtils, userService), UsernamePasswordAuthenticationFilter.class);

    }


}

