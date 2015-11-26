package plugins;

public class KeepOnlyLettersPlugin implements Plugin {
	@Override
	public String getLabel() {
		return "Keep only letters";
	}
	
	@Override
	public String transform(String s) {
		return s.replaceAll("[0-9]", "");
	}
	
	public static void main(String[] args) {
		System.out.println(new KeepOnlyLettersPlugin().transform("b0nj0ur j3 su1s un3 k1k0u"));
	}
}
