package com.atsistemas.concesionario.configuracion;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true,jsr250Enabled=true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService());
	}

	public UserDetailsService userDetailsService(){
	    Properties usuarios = new Properties();
	        usuarios.put("Gerente_1","gerente,ROLE_GERENTE,enabled");
	        usuarios.put("Comercial_1"  ,"comercial,ROLE_COMERCIAL,enabled");
	        usuarios.put("Administrativo_1"  ,"administrativo,ROLE_ADMINISTRATIVO,enabled");

	        return new InMemoryUserDetailsManager(usuarios);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests()
			.antMatchers("/paginas/*").permitAll()
			.antMatchers("/css/*").permitAll()
			.antMatchers("/imagenes/*").permitAll()
			.antMatchers("/**").access("hasAnyRole('AGENTE_ESPECIAL','DIRECTOR')");*/

		http.csrf().disable();

        http.formLogin()
        		.loginPage("/nuestro-login.jsp")
        		.failureUrl("/nuestro-login.jsp?login_error")
        		.defaultSuccessUrl("/inicio.jsp");
        
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/desconectado.jsp");
        		

       /* http
        	.requiresChannel()
                .anyRequest().requiresSecure()
            .and()
            	.portMapper()
                	.http(8080).mapsTo(8443);*/
        
        http
        	.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);
        
        http
        	.sessionManagement()
            	.sessionFixation()
            	.migrateSession();
	}
}
