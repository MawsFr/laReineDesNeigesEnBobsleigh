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
	
	public static void main(String[] args) {
		System.out.println(new RemoveWhitespacesPlugin().transform("Bonjour je m'appelle Togam"));
	}
}
