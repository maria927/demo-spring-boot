package com.demo.springboot.oauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Se encarga de procesar todos los accesos a los endpoints de acuerdo al token que estén enviando.
 * Valida que el token sea correcto con la misma firma del servidor de autorizacion
 * @author mia97
 *
 */
@Configuration
@EnableResourceServer //Habilidar el servidor de recurso
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	/**
	 * Configuracion para el token
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStoreConfig());
	}

	/**
	 * Configuracion para proteger las rutas de los endpoints
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/token").permitAll()    //antMatchers para colocar la ruta a la cual queremos dar permiso, permitAll para indicar que la ruta es publica
		.antMatchers(HttpMethod.GET, "/api/hello").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.GET, "/api/hello/name/{name}").hasRole("ADMIN")
		.anyRequest().authenticated(); //los requests necesitan autenticacion
	}
	
	@Bean
	public JwtTokenStore tokenStoreConfig() {
		return new JwtTokenStore(accessTokenConverterConfig());
	}
	

	@Bean
	public JwtAccessTokenConverter accessTokenConverterConfig() {
		JwtAccessTokenConverter tokenConverter= new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("${token.signing.key}");
		return tokenConverter;
	}
	
	

}
