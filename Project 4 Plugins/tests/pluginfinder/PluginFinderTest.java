package pluginfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import model.pluginfinder.PluginFinder;
import observer.MockObserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PluginFinderTest {
	
	private static final String PLUGINS_DIRECTORY = "testdropins/plugins";
	private static final String PLUGINS_TOCOPY_DIRECTORY = "testdropins/pluginsToCopy";
	
	protected PluginFinder pluginFinder;
	protected File pluginDirectory = new File(PLUGINS_DIRECTORY);
	protected MockObserver observer;
	
	@Before
	public void setUp() {
		pluginFinder = new PluginFinder(PLUGINS_DIRECTORY);
		observer = new MockObserver();
		pluginFinder.addObserver(observer);
	}
	
	private void copyPluginsToPluginPath(String... files) throws URISyntaxException, IOException {
		for(String file : files) {
			File plugin = new File(PLUGINS_TOCOPY_DIRECTORY + File.separator + file);
			if(plugin.exists()) {
				Files.copy(Paths.get(plugin.getAbsolutePath()), Paths.get(PLUGINS_DIRECTORY).resolve(plugin.getName()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		
	}
	
	@Test(expected=NullPointerException.class)
	public void nullParameterConstructorTest() {
		new PluginFinder(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyParameterConstructorTest() {
		new PluginFinder("");
	}
	
	
	@Test
	public void getAllFilesFromEmptyFolderReturnEmptyListTest() {
		cleanPluginFolder();
		assertTrue(pluginFinder.getAllFiles().isEmpty());
	}
	
	@Test
	public void getAllFilesFromInvalidFolderReturnEmptyListTest() {
		pluginFinder = new PluginFinder("Bonjour");
		assertTrue(pluginFinder.getAllFiles().isEmpty());
	}
	
	@Test
	public void getAllFilesFromValidFolderReturnEmptyListTest() throws URISyntaxException, IOException {
		copyPluginsToPluginPath("MockedPlugin.class", "MockedInheritFromAbstractPlugin.class", "MockedAbstractPlugin.class");
		assertEquals(2, pluginFinder.getAllFiles().size());
	}
	
	@Test
	public void addPluginsTest() throws URISyntaxException, IOException {
		assertEquals(0, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedPlugin.class"); //We add one valid plugin
		pluginFinder.actionPerformed(null);
		assertEquals(1, observer.getNumberOfPlugins());
		pluginFinder.actionPerformed(null); //for testing when plugins found are equals to plugins already found
		assertEquals(1, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedPlugin2.class");//We add another valid plugin
		pluginFinder.actionPerformed(null);
		assertEquals(2, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedAbstractPlugin.class", "MockEnum.class", "MockInterface.class"); // we add an abstract class, an enum and interface
		pluginFinder.actionPerformed(null);
		assertEquals(2, observer.getNumberOfPlugins()); //no changes
		copyPluginsToPluginPath("MockedAbstractPlugin.class", "MockedInheritFromAbstractPlugin.class"); // we add a plugin depending of an abstract plugin
		pluginFinder.actionPerformed(null);
		assertEquals(3, observer.getNumberOfPlugins());
		cleanPluginFolder(); //we clear
		pluginFinder.actionPerformed(null);
		assertEquals(0, observer.getNumberOfPlugins());
//		copyPluginsToPluginPath("MockedInheritFromAbstractPlugin.class"); // we add a plugin depending of an abstract plugin
//		pluginFinder.actionPerformed(null);
//		assertEquals(0, observer.getNumberOfPlugins()); //if there wasn't mockedAbstractPlugin.class but as we can't delete it, we can't do this...
		
	}
	
	@Test(expected=NullPointerException.class)
	public void notifyNullParameterTest() {
		pluginFinder.notify(null);
	}
	
	private void cleanPluginFolder() {
		for(File f : pluginDirectory.listFiles()) {
			f.delete();
		}
	}
	
	@After
	public void clean() {
		cleanPluginFolder();
	}

}
