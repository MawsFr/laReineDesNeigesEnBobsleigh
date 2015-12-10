package mockclasses;

import plugins.Plugin;



public class MockedPlugin implements Plugin {
	
	@Override
	public String getLabel() {
		return "Mocked plugin";
	}

	@Override
	public String transform(String s) {
		return s.replaceAll("[sS]", "$");
	}
	
	

}
