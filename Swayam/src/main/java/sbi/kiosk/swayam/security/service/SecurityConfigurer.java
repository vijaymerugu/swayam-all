package sbi.kiosk.swayam.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import sbi.kiosk.swayam.common.filter.JwtRequestFilter;
import sbi.kiosk.swayam.common.service.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailService;

	@Autowired
	private JwtRequestFilter jwtFilter;
	
	//@Autowired
	//RestTemplate restTemplate;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(myUserDetailService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/login*").permitAll()
			.antMatchers("/getToken").permitAll().anyRequest().authenticated()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            .logoutSuccessUrl("https://adfs.sbi.co.in/adfs/ls/?wa=wsignout1.0")
            .deleteCookies("JSESSIONID") 
            .invalidateHttpSession(true)        // set invalidation state when logout            
				.and()
				.sessionManagement()
				//.sessionFixation().migrateSession()
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
				.expiredUrl("https://adfs.sbi.co.in/adfs/ls/?wa=wsignout1.0");
		http.headers().
		httpStrictTransportSecurity()
		.includeSubDomains(true)
		.maxAgeInSeconds(31536000);
		//http.csrf()
    	//.ignoringAntMatchers("/getToken");

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
   
	   @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	        return source;
	    }
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers( "/**/*.*" );
		
		//web.ignoring().antMatchers( "/**/*.js", "/**/*.css" );

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception { 
																				
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder posswordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}
	
	@Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
