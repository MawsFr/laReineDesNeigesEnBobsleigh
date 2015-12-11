/**
 * @author Margot
 */

package plugins;


/**
 * A plugin that removes witespaces
 */
public class RemoveWhitespacesPlugin implements Plugin{
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Delete spaces";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.replaceAll(" ", "");
	}
	
}
