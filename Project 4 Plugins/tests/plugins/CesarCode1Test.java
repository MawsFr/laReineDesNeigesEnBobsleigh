package plugins;

import org.junit.Test;

/**
 * Tests the CesarCode1 class
 */
public class CesarCode1Test extends PluginTest {

	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new CesarCode1Plugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Ifmmp Xpsme");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Cesar Code 1");
	}
}
