package com.cesarjuniort.springboot.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class TokenAdditionalInfo implements TokenEnhancer {

	// TODO: inject service(s) as needed in order to load/lookup any related info needed by the client to be injected and shipped with the token.
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		// adding additional info to the token.
		Map<String,Object> info = new HashMap<>();
		info.put("a-sample-custom-key","a-sample-custom-value");
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
