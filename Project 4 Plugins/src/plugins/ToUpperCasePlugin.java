package plugins;

public class ToUpperCasePlugin implements Plugin {
	@Override
	public String getLabel() {
		return "To upper case";
	}
	
	@Override
	public String transform(String s) {
		return s.toUpperCase();
	}

}
