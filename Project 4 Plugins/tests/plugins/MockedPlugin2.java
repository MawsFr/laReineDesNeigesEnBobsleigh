package plugins;

/**
 * A second valid mocked plugin
 */
public class MockedPlugin2 implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return null;
	}

}
