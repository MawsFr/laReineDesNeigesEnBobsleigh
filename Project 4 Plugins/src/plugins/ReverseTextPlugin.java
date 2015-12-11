/**
 * @author Margot
 */

package plugins;

/**
 * A plugin that reverse text
 */
public class ReverseTextPlugin implements Plugin{
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Reverse text";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		String reversedText = "";
		for (int i = s.length()-1; i >= 0; i--) {
			reversedText += s.charAt(i);
		}
		return reversedText;
	}
}
