package plugins;

public class Main {

	public static void main(String[] args) {
		String s = "hello   world";
		s = s.replaceAll("\\s+", " ");
		System.out.println(s);
	}
	
}
