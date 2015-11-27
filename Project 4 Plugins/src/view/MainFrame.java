package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import model.observer.Observer;
import model.pluginfinder.PluginFinder;
import plugins.Plugin;

public class MainFrame extends JFrame implements Observer<List<Plugin>>{

	public static MainFrame instance;
	
	private static final long serialVersionUID = -2901605648414032427L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 680;
	protected PluginFinder pluginFinder;
	protected MenuBar menuBar;
	
	protected Container c;
	
	private MainFrame(){
		
		this.pluginFinder = new PluginFinder("./plugins");
		pluginFinder.addObserver(this);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		this.menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(getPreferredSize());
		this.setTitle("Plugin's project");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	
	public PluginFinder getPluginFinder() {
		return pluginFinder;
	}
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		
		return instance;
	}
	
	public static void main(String[] args) {
		MainFrame frame = MainFrame.getInstance();
		frame.getPluginFinder().start();
	}

	@Override
	public void update(List<Plugin> newPlugins) {
		menuBar.removePluginsMenu();
		System.out.println("Modifications in plugins folder !");
		for(Plugin plugin: newPlugins){
			menuBar.createMenu(plugin);
			System.out.println("Added " + plugin.getLabel());
		}
		
	}
}
