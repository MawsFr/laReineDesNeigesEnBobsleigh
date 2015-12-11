package plugins;

import org.junit.Test;

/**
 * Tests the RemoveWhitespaces class
 */
public class RemoveWhitespacesTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new RemoveWhitespacesPlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("Hello World !", "HelloWorld!");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Delete spaces");
	}
}
