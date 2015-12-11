/**
 * @author Margot
 */

package plugins;

/**
 * A plugin that keeps only letter from a string
 */
public class KeepOnlyLettersPlugin implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Keep only letters";
	}
	
	@Override
	public String transform(String s) {
		return s.replaceAll("[0-9]", "");
	}
	
}
