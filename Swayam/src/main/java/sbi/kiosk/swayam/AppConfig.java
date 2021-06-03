package sbi.kiosk.swayam;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.auth10.federation.WSFederationFilter;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
public class AppConfig implements WebMvcConfigurer {
	@Value(value="${spring.datasource.url}")
	private String url;
	@Value(value="${spring.datasource.driver-class-name}")
	private String dirverClassName;
	@Value(value="${spring.datasource.username}")
	private String username;
	@Value(value="${spring.datasource.password}")
	private String password;
	
	
	@Bean
	@Profile("!local")
	public FilterRegistrationBean<WSFederationFilter> filterRegistrationBean() {
	FilterRegistrationBean<WSFederationFilter> registration = new FilterRegistrationBean<WSFederationFilter>();
	registration.setFilter(new WSFederationFilter());
//	registration.addUrlPatterns("/SMTSSO/*");
	registration.addUrlPatterns("/*");
	registration.addInitParameter("login-page-url", "login.jsp");
	registration.addInitParameter("exclude-urls-regex", "/img/|/js/|/css/|/app/|/templates/");
	registration.setName("WSFederationFilter");
	registration.setAsyncSupported(false);
	return registration;
	}
	
	
	
	
	@Bean
    public DataSource getDataSource() 
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dirverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
	
	/*@Bean
    public DataSource getDataSource() 
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@//localhost:1521/xe");
        dataSourceBuilder.username("system");
        dataSourceBuilder.password("asdf1234");
        return dataSourceBuilder.build();
    }*/
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/views/");
	    bean.setSuffix(".jsp");
	    return bean;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");
        
    }

}
