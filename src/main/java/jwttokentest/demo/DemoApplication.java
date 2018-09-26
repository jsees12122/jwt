package jwttokentest.demo;

import jwttokentest.demo.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean testAuthFilter(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthFilter());
		registrationBean.addUrlPatterns("/test/token");
		registrationBean.setName("testFilter");
		return registrationBean;
	}
}
