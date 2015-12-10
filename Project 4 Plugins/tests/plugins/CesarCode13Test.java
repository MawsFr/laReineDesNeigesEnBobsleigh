package plugins;

import org.junit.Test;

public class CesarCode13Test extends PluginTest {

	@Override
	public Plugin createPlugin() {
		return new CesarCode13Plugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("Hello World", "Uryyb Jbeyq");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Cesar Code 13");
	}
}
