package plugins;

import org.junit.Test;

public class RemoveWhitespacesTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new RemoveWhitespacesPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("Hello World !", "HelloWorld!");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Delete spaces");
	}
}
