package plugins;

public abstract class CesarCodePlugin implements Plugin{
	protected final static String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
	protected final static String upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected int number;
	
	public CesarCodePlugin(int number) {
		this.number = number;
	}
	
	@Override
	public String getLabel() {
		return "Cesar Code "+this.number;
	} 
	
	@Override
	public String transform(String s) {
		char[] array = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(array[i])){
				if (Character.isLowerCase(array[i])){
					array[i]=(char) (lowerCaseAlphabet.charAt((lowerCaseAlphabet.indexOf(array[i]) + this.number%26)%26));
				}
				else{
					array[i]=(char) (upperCaseAlphabet.charAt((upperCaseAlphabet.indexOf(array[i]) + this.number%26)%26));
				}
			}
			
		}
		s = new String(array);
		return s;
	}
}
