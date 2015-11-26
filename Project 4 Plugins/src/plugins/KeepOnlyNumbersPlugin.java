package plugins;

public class KeepOnlyNumbersPlugin implements Plugin{
	
	@Override
	public String getLabel() {
		return "Keep only numbers";
	}
	
	@Override
	public String transform(String s) {
		return s.replaceAll("[^0-9.]", "");
	}
	
}
