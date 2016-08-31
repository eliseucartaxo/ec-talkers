/**
 * 
 */
package pt.ec.talkers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import pt.ec.talkers.config.TalkerTokenKeys;

/**
 * @author ecartaxo
 *
 */
public class RequestBuilder {

	private TalkerTokenKeys tokenKeys;

	public RequestBuilder(TalkerTokenKeys tokenKeys) {
		this.tokenKeys = tokenKeys;
	}

	public HttpEntity<?> buildRequestEntity(String authenticationToken, Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(tokenKeys.getTokenHeader(), authenticationToken);
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		return entity;
	}

	public HttpEntity<Object> buildRequestEntityWithoutBody(String authenticationToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(tokenKeys.getTokenHeader(), authenticationToken);
		headers.add("Content-Type", "application/json");
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		return entity;
	}

	public HttpEntity<?> buildRequestEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		return entity;
	}
}
