/**
 * 
 */
package pt.ec.talkers.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base Entity to ec-talker. Provides common methods to all entity domains.
 * 
 * @author ecartaxo
 *
 */
public abstract class BaseEntity {
	/*
	 * Custom toString method to print JSON version object.
	 * 
	 * Based on ReflectionToStringBuilder.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
