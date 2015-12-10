package plugins;

import org.junit.Test;

public class ToUpperCaseTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new ToUpperCasePlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("hello world", "HELLO WORLD");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("To upper case");
	}
}
