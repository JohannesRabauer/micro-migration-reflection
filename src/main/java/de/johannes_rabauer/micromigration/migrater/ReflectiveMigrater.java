package de.johannes_rabauer.micromigration.migrater;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import de.johannes_rabauer.micromigration.scripts.MigrationScript;

/**
 * Contains all the available scripts to migrate the datastore to a certain version.
 * <p>
 * Searches all implementation of {@link MigrationScript} in the specified package
 * and it's the sub packages.
 * 
 * @author Johannes Rabauer
 * 
 */
public class ReflectiveMigrater extends AbstractMigrater
{
	private final TreeSet<MigrationScript<?>> sortedScripts = new TreeSet<>(MigrationScript.COMPARATOR);
	
	/**
	 * @param packagePath defines the package in which {@link MigrationScript}s will be searched.
	 * Also searches through all sub packages of <code>packagePath</code>
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings("rawtypes")
	public ReflectiveMigrater(final String packagePath) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		final Reflections reflections = new Reflections(packagePath, new SubTypesScanner(true));
		
		for (Class<? extends MigrationScript> script : reflections.getSubTypesOf(MigrationScript.class)) 
		{
			this.sortedScripts.add(script.getDeclaredConstructor().newInstance());
		}
	}

	@Override
	public TreeSet<MigrationScript<?>> getSortedScripts() 
	{
		return this.sortedScripts;
	}
}
