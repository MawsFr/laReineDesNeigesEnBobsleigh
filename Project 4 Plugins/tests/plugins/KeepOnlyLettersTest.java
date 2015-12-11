package plugins;

import org.junit.Test;

/**
 * Tests the KeepOnlyLetters class
 */
public class KeepOnlyLettersTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new KeepOnlyLettersPlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("h2e0l1l5o", "hello");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Keep only letters");
	}
}
