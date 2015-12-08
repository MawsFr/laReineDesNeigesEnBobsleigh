package plugins;

public class ToLowerCasePlugin implements Plugin {
	@Override
	public String getLabel() {
		return "To lower case";
	}
	
	@Override
	public String transform(String s) {
		return s.toLowerCase();
	}

}
