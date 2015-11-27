package view;

import java.util.List;

import plugins.Plugin;
import model.observer.Observer;

public class PluginAddedLogger implements Observer<List<Plugin>> {

	public void printMessage(String message) {
		if(!message.isEmpty()) {
			System.out.println(message);	
		}
	}

	@Override
	public void update(List<Plugin> pluginsFinded) {
		printMessage("Modifications in plugins folder !");
		for(Plugin plugin : pluginsFinded){
			printMessage("Added " + plugin.getLabel());
		}
	}	
}
