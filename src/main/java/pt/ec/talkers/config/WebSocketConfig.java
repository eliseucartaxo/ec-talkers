/**
 * 
 */
package pt.ec.talkers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.
	 * springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(MessageDestinations.ENDPOINT).withSockJS();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.
	 * springframework.messaging.simp.config.MessageBrokerRegistry)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(MessageDestinations.BROKER_TARGET);
		registry.setApplicationDestinationPrefixes(MessageDestinations.APP_TARGET);
	}

}
