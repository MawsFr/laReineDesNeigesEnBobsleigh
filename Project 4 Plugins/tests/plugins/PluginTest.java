package plugins;

import org.junit.Before;
import static org.junit.Assert.*;

public abstract class PluginTest {

	protected Plugin plugin;
	public abstract Plugin createPlugin();
	
	@Before
	public void setUp() {
		this.plugin = createPlugin();
	}
	
	public void getLabelTest(String name) {
		assertEquals(name, plugin.getLabel());
	}
	
	public void transformTest(String text, String expectedText) {
		assertEquals(expectedText, plugin.transform(text));
	}
	
}
