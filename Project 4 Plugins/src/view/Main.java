package view;

import model.pluginfinder.PluginFinder;

public class Main {

	public static void main(String[] args) {
		boolean testWithFrame = true; //Modify this variable to start in GUI or Console mode
		
		if(testWithFrame) { //ATTENTION : MainFrame creates its own pluginFinder in its constructor !
			MainFrame frame = MainFrame.getInstance();
			frame.getPluginFinder().start();
		} else {
			PluginAddedLogger console = new PluginAddedLogger();
			PluginFinder pluginFinder = new PluginFinder("./plugins");
			pluginFinder.addObserver(console);
			pluginFinder.start();
		}
		
	}

}
