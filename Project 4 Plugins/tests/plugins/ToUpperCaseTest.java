package plugins;

import org.junit.Test;

/**
 * Tests the ToUpperCase class
 */
public class ToUpperCaseTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new ToUpperCasePlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("hello world", "HELLO WORLD");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("To upper case");
	}
}
