package de.johannes_rabauer.micromigration.migrater;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import de.johannes_rabauer.micromigration.scripts.MicroMigrationScript;

/**
 * Executes all the available scripts to migrate the datastore to a certain version.
 * <p>
 * This class searches all implementation of {@link MicroMigrationScript} in the specified package 
 * then includes in the migration process.
 * 
 * @author Johannes Rabauer
 * 
 */
public class ReflectiveMigrater implements MicroMigrater
{
	private final TreeSet<MicroMigrationScript> sortedScripts = new TreeSet<>(MicroMigrationScript.COMPARATOR);
	
	public ReflectiveMigrater(final String packagePath) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		final Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
		
		for (Class<? extends MicroMigrationScript> script : reflections.getSubTypesOf(MicroMigrationScript.class)) 
		{
			this.sortedScripts.add(script.getDeclaredConstructor().newInstance());
		}
	}

	@Override
	public TreeSet<MicroMigrationScript> getSortedScripts() {
		return this.sortedScripts;
	}
}
