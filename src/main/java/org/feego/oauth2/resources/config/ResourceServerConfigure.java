package org.feego.oauth2.resources.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfigure implements ResourceServerConfigurer {
	@Autowired
	private DataSource datasource;
	
	
	@Autowired
	private JdbcTokenStore jdbcTokenStore;
	@Override
	public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
		// TODO Auto-generated method stub
		resource.tokenStore(jdbcTokenStore).resourceId("testResourceId");//需要和clientdetails中的resourceId相一致
		//resource.authenticationEntryPoint(new RestAuthenticationEntryPoint());	//如果需要custom验证不通过的输出格式，可增加自定义entryPoint
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/api/**").hasAuthority("TRUSTED_CLIENT") //需要和clientdetails中的authorities一致
				.antMatchers("/api/**").hasRole("USER")	//在grant_type为password时，需要和Users中的role相一致
				.anyRequest().authenticated()
			.and()
				.headers().frameOptions().disable();
	}
	
	@Bean
	public JdbcTokenStore getTokenStore() {
		return new JdbcTokenStore(datasource);
	}
	
	@Bean
	public RemoteTokenServices getRemoteTokenServices() {//远程验证token的配置,一旦流入context，服务器自动进行远程验证
		RemoteTokenServices svr=new RemoteTokenServices();
		svr.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
		svr.setClientId("testResource");//clientdetails中需要拥有resource服务器的clientId及相关信息
		svr.setClientSecret("secret");
		return svr;
	}

}
