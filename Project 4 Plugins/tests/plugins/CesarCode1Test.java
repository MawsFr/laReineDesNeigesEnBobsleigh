package plugins;

import org.junit.Test;

public class CesarCode1Test extends PluginTest {

	@Override
	public Plugin createPlugin() {
		return new CesarCode1Plugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Ifmmp Xpsme");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Cesar Code 1");
	}
}
