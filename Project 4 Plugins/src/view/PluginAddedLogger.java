package view;

import java.util.Observable;
import java.util.Observer;

public class PluginAddedLogger implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		printMessage((String) arg); 
	}
	
	public void printMessage(String message) {
		if(!message.isEmpty()) {
			System.out.println(message);	
		}
	}
}
