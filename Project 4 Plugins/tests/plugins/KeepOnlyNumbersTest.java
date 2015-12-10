package plugins;

import org.junit.Test;

public class KeepOnlyNumbersTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new KeepOnlyNumbersPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("h2e0l1l5o", "2015");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Keep only numbers");
	}
}
