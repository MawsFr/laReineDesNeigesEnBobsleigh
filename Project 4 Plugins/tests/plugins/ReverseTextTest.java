package plugins;

import org.junit.Test;

public class ReverseTextTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new ReverseTextPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("Hello world", "dlrow olleH");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Reverse text");
	}
}
