package pluginfinder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import model.pluginfinder.PluginFilter;

import org.junit.Before;
import org.junit.Test;

import plugins.MockClass;
import plugins.MockClass2;
import plugins.MockEnum;
import plugins.MockInterface;
import plugins.MockedAbstractPlugin;
import plugins.MockedPlugin;
import plugins.Plugin;
import exceptions.PluginException;


public class PluginFilterTest {

	protected PluginFilter pluginFilter;

	@Before
	public void setUp() throws Exception {
		pluginFilter = new PluginFilter();
	}

	@Test
	public void classEndsWithClassTest() {
		assertFalse(pluginFilter.accept(null, "bonjour"));
	}

	@Test
	public void pluginNotInPluginPackageTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockPlugin2.class"));
		pluginFilter.accept(null, "MockPlugin2.class");
		assertEquals("MockPlugin2.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	@Test
	public void invalidPluginNameTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("Bonjour.class"));
		pluginFilter.accept(null, "Bonjour.class");
		assertEquals("Bonjour.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	@Test
	public void abstractPluginTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockedAbstractPlugin.class"));
		assertNotNull(pluginFilter.getTheClass("MockedAbstractPlugin.class"));
		assertTrue(pluginFilter.isAbstractOrInterfaceOrEnum(MockedAbstractPlugin.class));
		pluginFilter.accept(null, "MockedAbstractPlugin.class");
		assertEquals("MockedAbstractPlugin.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}
	
	@Test
	public void enumPluginTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockEnum.class"));
		assertNotNull(pluginFilter.getTheClass("MockEnum.class"));
		assertTrue(pluginFilter.isAbstractOrInterfaceOrEnum(MockEnum.class));
		pluginFilter.accept(null, "MockEnum.class");
		assertEquals("MockEnum.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}
	
	@Test
	public void interfacePluginTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockInterface.class"));
		assertNotNull(pluginFilter.getTheClass("MockInterface.class"));
		assertTrue(pluginFilter.isAbstractOrInterfaceOrEnum(MockInterface.class));
		pluginFilter.accept(null, "MockInterface.class");
		assertEquals("MockInterface.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	
	@Test
	public void notSubclassOfPluginTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockClass.class"));
		assertNotNull(pluginFilter.getTheClass("MockClass.class"));
		assertFalse(pluginFilter.isSubclassOfPlugin(MockClass.class));
		pluginFilter.accept(null, "MockClass.class");
		assertEquals("MockClass.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}
	
	@Test
	public void hasNoDefaultConstructorTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockClass.class"));
		assertNotNull(pluginFilter.getTheClass("MockClass.class"));
		assertTrue(pluginFilter.isInPluginPackage(MockClass.class));
		assertFalse(pluginFilter.hasDefaultConstructor(MockClass.class));
		pluginFilter.accept(null, "MockClass.class");
		assertEquals("MockClass.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}
	
	@Test
	public void hasCorrectMethod() {
		assertTrue(pluginFilter.hasCorrectMethods(MockedPlugin.class));
	}
	
	@Test
	public void hasNotCorrectMethod() {
		assertFalse(pluginFilter.hasCorrectMethods(MockClass.class));
	}
	
	@Test
	public void hasNotCorrectReturnTypes() {
		assertFalse(pluginFilter.hasCorrectMethods(MockClass2.class));
	}
	
	@Test
	public void acceptedPluginTest() {
		pluginFilter.accept(null, "MockedPlugin.class");
		assertEquals(0, pluginFilter.getNonLoadedPluginsList().size());
	}
	
	@Test
	public void fileToPluginTest() throws PluginException, URISyntaxException, FileNotFoundException {
		File plugin = new File(Thread.currentThread().getContextClassLoader().getResource("plugins/MockedPlugin.class").toURI());
		Plugin mockedPlugin = pluginFilter.fileToPlugin(plugin);
		assertNotNull(mockedPlugin);
	}
	
	@Test
	public void filesToPluginTest() throws URISyntaxException, NullPointerException, IllegalArgumentException, FileNotFoundException, PluginException {
		List<File> files = new ArrayList<File>();
		files.add(new File(Thread.currentThread().getContextClassLoader().getResource("plugins/MockedPlugin.class").toURI()));
		files.add(new File(Thread.currentThread().getContextClassLoader().getResource("plugins/MockedPlugin.class").toURI()));
		files.add(new File(Thread.currentThread().getContextClassLoader().getResource("plugins/MockedPlugin.class").toURI()));
		
		List<Plugin> plugins = pluginFilter.filesToPlugins(files);
		for(Plugin p : plugins) {
			assertEquals(MockedPlugin.class, p.getClass());
		}
		assertEquals(3, plugins.size());
		
	}
	
	@Test(expected = PluginException.class)
	public void invalidFileToPluginTest() throws PluginException, URISyntaxException, FileNotFoundException {
		File plugin = new File(Thread.currentThread().getContextClassLoader().getResource("plugins/MockClass.class").toURI());
		pluginFilter.fileToPlugin(plugin);
	}	
	
	//Test on parameters
	
	@Test(expected = NullPointerException.class)
	public void hasDefaultConstructorWithNullParameterTest() {
		pluginFilter.hasDefaultConstructor(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isAbstractOrInterfaceOrEnumWithNullParameterTest() {
		pluginFilter.isAbstractOrInterfaceOrEnum(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void hasCorrectMethodsWithNullParameterTest() {
		pluginFilter.hasCorrectMethods(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void nameEndsWithClassWithNullParameterTest() {
		pluginFilter.nameEndsWithClass(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nameEndsWithClassWithEmptyParameterTest() {
		pluginFilter.nameEndsWithClass("");
	}
	
	@Test(expected = NullPointerException.class)
	public void getTheClassWithNullNameParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.getTheClass(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getTheClassWithEmptyNameParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.getTheClass("");
	}
	
	@Test(expected = NullPointerException.class)
	public void isInPluginPackageWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.isInPluginPackage(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isSubclassOfPluginWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.isSubclassOfPlugin(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void filesToPluginsWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.filesToPlugins(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void fileToPluginsWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.fileToPlugin(null);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void fileToPluginsWithNonExistingFileParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.fileToPlugin(new File("bonjour"));
	}
	
	
}
