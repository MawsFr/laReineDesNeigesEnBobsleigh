package plugins;

public class DeleteVowelsPlugin implements Plugin {
	@Override
	public String getLabel() {
		return "Delete vowels";
	}
	
	@Override
	public String transform(String s) {
		return s.replaceAll("[aeiouyAEIOUYéèùàâêûîôäëüöïòì]", "");	
		//TODO: vérifier qu'il n'y a pas d'autres vowels
	}

}
