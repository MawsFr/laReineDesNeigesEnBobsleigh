/**
 * @author Margot
 */

package plugins;

public class RemoveWhitespacesPlugin implements Plugin{
	@Override
	public String getLabel() {
		return "Delete spaces";
	}
	
	@Override
	public String transform(String s) {
		return s.replaceAll(" ", "");
	}
	
}
