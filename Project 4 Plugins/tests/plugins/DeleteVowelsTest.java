package plugins;

import org.junit.Test;

/**
 * Tests the DeleteVowels class
 */
public class DeleteVowelsTest extends PluginTest {
	
	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new DeleteVowelsPlugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Hll Wrld");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Delete vowels");
	}
}
