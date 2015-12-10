package exceptions;

/**
 * This class is an exception thrown when having problems with plugins 
 */
public class PluginException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8229313600647708499L;

	/**
	 * Default constructor
	 * @param message The message to display
	 */
	public PluginException(String message) {
		super(message);
	}
	
	

}
