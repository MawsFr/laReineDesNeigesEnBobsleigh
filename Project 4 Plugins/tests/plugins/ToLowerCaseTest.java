package plugins;

import org.junit.Test;

/**
 * Tests the ToLowerCase class
 */
public class ToLowerCaseTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new ToLowerCasePlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("HELLO WORLD", "hello world");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("To lower case");
	}
}
