/**
 * 
 */
package pt.ec.talkers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ecartaxo
 *
 */
@Configuration
public class TalkerTokenKeys {
	@Value("${talker.token.header}")
	private String tokenHeader;
	
	@Value("${talker.token.secret}")
	private String secret;

	@Value("${talker.token.expiration}")
	private Long expiration;

	public String getTokenHeader() {
		return tokenHeader;
	}

	public String getSecret() {
		return secret;
	}

	public Long getExpiration() {
		return expiration;
	}
}
