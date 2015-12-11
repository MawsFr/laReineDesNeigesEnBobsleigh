package plugins;

import plugins.Plugin;

/**
 * A valid mocked plugin  
 */
public class MockedPlugin implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Mocked plugin";
	}

	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.replaceAll("[sS]", "$");
	}
	
	

}
