package plugins;

import org.junit.Test;

public class DeleteVowelsTest extends PluginTest {
	
	@Override
	public Plugin createPlugin() {
		return new DeleteVowelsPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Hll Wrld");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Delete vowels");
	}
}
