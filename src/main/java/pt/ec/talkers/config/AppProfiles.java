/**
 * 
 */
package pt.ec.talkers.config;

/**
 * @author ecartaxo
 *
 */
public final class AppProfiles {
	private AppProfiles() {

	}

	/*
	 * Cycle application profiles
	 */
	public final static String DEVELOPMENT = "dev";
	public final static String TEST = "test";
	public final static String PRODUTION = "prod";

	/*
	 * Database Profile
	 */
	public final static String DB_JPA = "jpa";
	public final static String DB_MONGO = "mongo";
}
