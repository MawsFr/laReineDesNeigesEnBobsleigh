package plugins;


/**
 * This interface represents a Plugin that applies transformations on a string
 */
public interface Plugin {
	
	/**
	 * Return a transformed string from the string passed in parameter  
	 * @param s The string to transform
	 * @return The transformed string 
	 */
	public String transform(String s);
	
	/**
	 * @return The name of the plugin (to display for example in a menu)
	 */
	public String getLabel();

}
