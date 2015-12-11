package plugins;

import org.junit.Test;

/**
 * Tests the CesarCode13 class
 */
public class CesarCode13Test extends PluginTest {

	/* (non-Javadoc)
	 * @see plugins.PluginTest#createPlugin()
	 */
	@Override
	public Plugin createPlugin() {
		return new CesarCode13Plugin();
	}
	
	/**
	 * Tests the plugin's tranformation on a string
	 */
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Uryyb Jbeyq");
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	@Test
	public void getLabel() {
		super.getLabelTest("Cesar Code 13");
	}
}
