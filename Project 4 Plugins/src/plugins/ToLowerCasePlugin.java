package plugins;

/**
 * A plugin that lower cases of a string
 */
public class ToLowerCasePlugin implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "To lower case";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.toLowerCase();
	}

}
