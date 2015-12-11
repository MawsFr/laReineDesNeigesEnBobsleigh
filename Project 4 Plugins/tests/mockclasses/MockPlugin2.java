package mockclasses;

import plugins.Plugin;

/**
 * This class represents a Mocked Plugin that ISN'T IN THE PLUGIN PACKAGE
 */
public class MockPlugin2 implements Plugin {
	
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
