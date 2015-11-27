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

	private static final long serialVersionUID = -2901605648414032427L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 680;
	protected PluginFinder pluginFinder;
	protected MenuBar menuBar;
	protected Container c;
	
	public MainFrame(PluginFinder pluginFinder){
		
		this.pluginFinder = pluginFinder;
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
		this.setResizable(true);
		
		
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		PluginFinder pluginFinder = new PluginFinder("./plugins");
		
		new MainFrame(pluginFinder);
		pluginFinder.start();
	}

	@Override
	public void update(List<Plugin> newPlugins) {
		menuBar.removePluginsMenu();
		for(Plugin plugin: newPlugins){
			menuBar.createMenu(plugin);
		}
	}
}
