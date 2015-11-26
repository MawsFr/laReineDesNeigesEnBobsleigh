package view;

import model.PluginFinder;

public class Main {

	public static void main(String[] args) {
		PluginAddedLogger console = new PluginAddedLogger();
		PluginFinder pluginFinder = new PluginFinder("plugins");
		pluginFinder.addObserver(console);
		pluginFinder.start();
	}

}
