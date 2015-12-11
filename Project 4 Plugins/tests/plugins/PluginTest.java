package plugins;

import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Tests a plugin class
 */
public abstract class PluginTest {

	/**
	 * The plugin to tests
	 */
	protected Plugin plugin;
	
	/**
	 * @return A plugin
	 */
	public abstract Plugin createPlugin();
	
	/**
	 * Sets some variables
	 */
	@Before
	public void setUp() {
		this.plugin = createPlugin();
	}
	
	/**
	 * @param name Tests that the label is correct
	 */
	public void getLabelTest(String name) {
		assertEquals(name, plugin.getLabel());
	}
	
	/**
	 * Tests the plugin's transformation on a string
	 * @param text The text to transform
	 * @param expectedText The transformed text
	 */
	public void transformTest(String text, String expectedText) {
		assertEquals(expectedText, plugin.transform(text));
	}
	
}
