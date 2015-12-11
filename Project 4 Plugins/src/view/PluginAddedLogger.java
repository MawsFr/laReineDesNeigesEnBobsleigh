package view;

import java.util.List;

import plugins.Plugin;
import model.observer.Observable;
import model.observer.Observer;

/**
 * The console version of display
 */
public class PluginAddedLogger implements Observer<List<Plugin>> {

	/**
	 * Prints a message to the console
	 * @param message The message to display
	 */
	public void printMessage(String message) {
		if(!message.isEmpty()) {
			System.out.println(message);	
		}
	}

	/* (non-Javadoc)
	 * @see model.observer.Observer#update(model.observer.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable<List<Plugin>> source, List<Plugin> pluginsFinded) {
		printMessage("Modifications in plugins folder !");
		for(Plugin plugin : pluginsFinded){
			printMessage("Added " + plugin.getLabel());
		}
	}
	
//	@Override
//	public void showMessage(String title, String message, int type) {
//		printMessage(title + " : " +message);
//	}
}
