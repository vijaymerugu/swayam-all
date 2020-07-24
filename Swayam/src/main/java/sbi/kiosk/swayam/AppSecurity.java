/*
 * package sbi.kiosk.swayam;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true) public class AppSecurity
 * extends WebSecurityConfigurerAdapter {
 * 
 * @Autowired DataSource dataSource;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { //
 * TODO Auto-generated method stub
 * 
 * http.authorizeRequests(). antMatchers("/", "login").permitAll().
 * anyRequest().authenticated().and().formLogin(). loginPage("/").permitAll();
 * 
 * http //.csrf().disable() .authorizeRequests()
 * .antMatchers("/**").permitAll(); }
 * 
 * 
 * 
 * 
 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
 * auth) throws Exception {
 * 
 * auth.jdbcAuthentication().dataSource(dataSource) .usersByUsernameQuery(
 * "select username, enabled from users where username=?")
 * .authoritiesByUsernameQuery(
 * "select username, role from user_roles where username=?"); }
 * 
 * }
 */