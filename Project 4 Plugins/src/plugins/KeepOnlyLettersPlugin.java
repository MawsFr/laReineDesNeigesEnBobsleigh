/**
 * @author Margot
 */

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
	
}
