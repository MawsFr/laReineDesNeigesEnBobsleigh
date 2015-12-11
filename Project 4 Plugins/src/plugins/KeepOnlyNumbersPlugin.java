/**
 * @author Margot
 */

package plugins;

/**
 * A plugin that keeps only numbers of a string
 */
public class KeepOnlyNumbersPlugin implements Plugin{
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Keep only numbers";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.replaceAll("[^0-9.]", "");
	}
	
}
