package view;

import java.util.List;

import plugins.Plugin;
import model.observer.Observable;
import model.observer.Observer;

public class PluginAddedLogger implements Observer<List<Plugin>> {

	public void printMessage(String message) {
		if(!message.isEmpty()) {
			System.out.println(message);	
		}
	}

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
