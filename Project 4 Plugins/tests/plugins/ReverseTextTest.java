package plugins;

import org.junit.Test;

/**
 * Tests the ReverseText class
 */
public class ReverseTextTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new ReverseTextPlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("Hello world", "dlrow olleH");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Reverse text");
	}
}
