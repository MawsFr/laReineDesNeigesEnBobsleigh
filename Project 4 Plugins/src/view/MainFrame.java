package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import plugins.Plugin;
import model.observer.Observer;
import model.pluginfinder.PluginFinder;

public class MainFrame extends JFrame implements Observer<List<Plugin>> {

	public static MainFrame instance;
	
	private static final long serialVersionUID = -2901605648414032427L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 680;
	protected PluginFinder pluginFinder;
	protected MenuBar menuBar;
	protected JTextArea editor;
	protected Container c;
	
	private MainFrame(){
		super("Plugin's project");
		this.pluginFinder = new PluginFinder("dropins/plugins");
		pluginFinder.addObserver(this);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		this.menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		this.editor = new JTextArea();
		c.add(new JScrollPane(editor), BorderLayout.CENTER);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Stopping timer");
				try {
					pluginFinder.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			
		});
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(getPreferredSize());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	
	public JTextArea getEditor() {
		return editor;
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
	
	public void showWarning(String title, String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public void showError(String title, String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.ERROR_MESSAGE);
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
