/**
 * @author Margot
 */

package plugins;

/**
 * A plugin that delete vowels of a string
 */
public class DeleteVowelsPlugin implements Plugin {
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Delete vowels";
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return s.replaceAll("[aeiouyAEIOUYéèùàâêûîôäëüöïòì]", "");	
		//DONE: vérifier qu'il n'y a pas d'autres vowels
	}

}
