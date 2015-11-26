package model;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter{
	
	@Override
	public boolean accept(File dir, String name) {
		if(!name.endsWith(".class")) {
			return false;
		}
		
		@SuppressWarnings("unused")
		Class<?> theClass;
		
		try {
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + name.replaceFirst("\\.class",""));
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //TODO : Enlever a la fin
			return false;
		}	
	}

}
