package pluginfinder;
import static org.junit.Assert.*;
import model.pluginfinder.PluginFilter;

import org.junit.Before;
import org.junit.Test;


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
		
	/*
	 * if(!nameEndsWithClass(name)) {
			return false;
		}
		
		Class<?> theClass;
		try {
			theClass = getTheClass(name);
		} catch (PluginException e) {
			this.nonChargedPluginsList.add(name);
			return false;
		}
		
		if(isAbstractOrInterfaceOrEnum(theClass)
				|| !isInPluginPackage(theClass)
				|| !isSubclassOfPlugin(theClass)
				|| !hasDefaultConstructor(theClass)) {
			this.nonChargedPluginsList.add(name);
			return false;
		}
		
		*/


}
