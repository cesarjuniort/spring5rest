package com.cesarjuniort.springboot.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenAdditionalInfo tokenAdditionalInfo;
	
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()") //allow access to anyone to reach the login endpoint, which will provide the token to the authorized user.
			.checkTokenAccess("isAuthenticated()"); // To validate token, only allow access to authenticated used
		
		//header Authorization Basic: Client Id + Client Secret
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		int tokenLifeTime = 60*60*8;
		// here is where we configure our client ids (web app, mobile app etc). 
		clients.inMemory()
			.withClient("angularapp")
			.secret(passwordEncoder.encode("the-client-secret-password"))
			.scopes("read", "write")
			.authorizedGrantTypes("password","refresh_token")
			.accessTokenValiditySeconds(tokenLifeTime)
			.refreshTokenValiditySeconds(tokenLifeTime);
		
		super.configure(clients);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//to add addional info to the vanilla-token
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenAdditionalInfo,accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore()) // this can be ommited, as it will create an instance behind the scenes - here we are being explict creating our own.
			.accessTokenConverter(accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain); //adding the additional info to the token.
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.PRIVATE_RSA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.PUBLIC_RSA);
		return jwtAccessTokenConverter;
	}
	
	
	
}
