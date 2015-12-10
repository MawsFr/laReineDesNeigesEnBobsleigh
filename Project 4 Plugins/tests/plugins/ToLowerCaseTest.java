package plugins;

import org.junit.Test;

public class ToLowerCaseTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new ToLowerCasePlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("HELLO WORLD", "hello world");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("To lower case");
	}
}
