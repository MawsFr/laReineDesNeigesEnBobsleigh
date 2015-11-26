/**
 * @author Margot
 */

package plugins;

public class ReverseTextPlugin implements Plugin{
	@Override
	public String getLabel() {
		return "Reverse text";
	}
	
	@Override
	public String transform(String s) {
		String reversedText = "";
		for (int i = s.length()-1; i >= 0; i--) {
			reversedText += s.charAt(i);
		}
		return reversedText;
	}
}
