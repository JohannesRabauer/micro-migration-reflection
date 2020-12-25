package de.johannes_rabauer.micromigration.migrater;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import de.johannes_rabauer.micromigration.scripts.MigrationScript;

/**
 * Executes all the available scripts to migrate the datastore to a certain version.
 * <p>
 * This class searches all implementation of {@link MigrationScript} in the specified package 
 * then includes in the migration process.
 * 
 * @author Johannes Rabauer
 * 
 */
public class ReflectiveMigrater extends AbstractMigrater
{
	private final TreeSet<MigrationScript<?>> sortedScripts = new TreeSet<>(MigrationScript.COMPARATOR);
	
	@SuppressWarnings("rawtypes")
	public ReflectiveMigrater(final String packagePath) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		final Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
		
		for (Class<? extends MigrationScript> script : reflections.getSubTypesOf(MigrationScript.class)) 
		{
			this.sortedScripts.add(script.getDeclaredConstructor().newInstance());
		}
	}

	@Override
	public TreeSet<MigrationScript<?>> getSortedScripts() {
		return this.sortedScripts;
	}
}
