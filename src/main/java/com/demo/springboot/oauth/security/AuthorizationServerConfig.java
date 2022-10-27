package com.demo.springboot.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Configuracion del servidor de autenticacion que se encarga de todo el proceso de login con JWT y OAuth2
 * @author mia97
 *
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	/** Configuracion del tokenStore y accessTokenConverter
	 * Guardar info del usuario en el token OAuth2
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager) //Maneja las peticiones de autenticacion
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter());
	}

	/**
	 * Configuracion de los permisos que va a tener el endpoint en el servidor de autorizacion
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()") //Definir permisos para generar el token, en este ejemplo cualquiera puede generar token
		.checkTokenAccess("isAuthenticated()"); //Validar el token. En este ejemplo, solo valida el token si esta autenticado
	}

	/**
	 * Configuracion del acceso del cliente que se va a conectar a la aplicacion
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("androidApp") //Se va a registrar el cliente llamado androidApp en memoria
		.secret(passwordEnconder.encode("12345"))   //Se asigna una clave que a su vez va a ser encriptada
		.scopes("read", "write") //Permisos de la aplicacion cliente
		.authorizedGrantTypes("password", "refresh_token") //Definir como se va a obtener el token
		.accessTokenValiditySeconds(3600) //Definir tiempo validez del token (en segundos)
		.refreshTokenValiditySeconds(3600); //Definir tiempo de refresh del token (en segundos)
	}

	/**
	 * Componente para almacenar el token
	 * @return
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	/**
	 * Componente encargado de firmar el token agregando una clave
	 * @return tokenConverter
	 */

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter= new JwtAccessTokenConverter();
		//Se debe agregar codigo secreto al token
		tokenConverter.setSigningKey("${token.signing.key}");
		return tokenConverter;
	}
	
	

}
