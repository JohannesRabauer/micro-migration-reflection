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
	 * @throws ScriptInstantiationException if a class in the given package could not be instantiated
	 */
	@SuppressWarnings("rawtypes")
	public ReflectiveMigrater(final String packagePath) throws ScriptInstantiationException 
	{
		final Reflections reflections = new Reflections(packagePath, new SubTypesScanner());
		
		for (Class<? extends MigrationScript> scriptClass : reflections.getSubTypesOf(MigrationScript.class)) 
		{
			this.sortedScripts.add(instanciateClass(scriptClass));
		}
	}

	@SuppressWarnings("rawtypes")
	private MigrationScript<?> instanciateClass(Class<? extends MigrationScript> scriptClass) throws ScriptInstantiationException
	{
		try {
			return scriptClass.getDeclaredConstructor().newInstance();
		} catch (
			InstantiationException    | 
			IllegalAccessException    | 
			IllegalArgumentException  | 
			InvocationTargetException | 
			NoSuchMethodException     | 
			SecurityException        e
		) {
			throw new ScriptInstantiationException("Could not instanciate class " + scriptClass.getName(), e);
		}
	}
	
	@Override
	public TreeSet<MigrationScript<?>> getSortedScripts() 
	{
		return this.sortedScripts;
	}
}
