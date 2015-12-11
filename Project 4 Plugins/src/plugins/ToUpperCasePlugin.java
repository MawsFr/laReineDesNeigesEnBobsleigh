package plugins;

/**
 *A plugin that uppers cases of a string
 */
public class ToUpperCasePlugin implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "To upper case";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.toUpperCase();
	}

}
