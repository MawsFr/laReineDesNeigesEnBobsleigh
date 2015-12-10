package plugins;

import org.junit.Test;

public class KeepOnlyLettersTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new KeepOnlyLettersPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("h2e0l1l5o", "hello");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Keep only letters");
	}
}
