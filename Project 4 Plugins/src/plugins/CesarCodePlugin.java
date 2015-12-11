package plugins;


/**
 * A plugin that encode a string into another by applying the cesar code 13
 */
public abstract class CesarCodePlugin implements Plugin{
	/**
	 * An array with all lower Case letters
	 */
	protected final static String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
	
	/**
	 * An array with all upper Case letters
	 */
	protected final static String upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	/**
	 * The shift number
	 */
	protected int number;
	
	/**
	 * Constructor with the number that defines the decalage
	 * @param number
	 */
	public CesarCodePlugin(int number) {
		//DONE : Verifier que c'est superieur ou egale a zero
		this.number = number;
	}
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Cesar Code "+this.number;
	} 
	
	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
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
