package de.johannes_rabauer.micromigration.migrater.scripts.noCorrectConstructor;

import de.johannes_rabauer.micromigration.scripts.Context;
import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public class NoCorrectConstructorScript implements MigrationScript<Object>
{
	private final String argument;
	
	public NoCorrectConstructorScript(String argument)
	{
		this.argument = argument;
	}
	
	@Override
	public MigrationVersion getTargetVersion() 
	{
		return new MigrationVersion(1);
	}

	@Override
	public void migrate(Context<Object> context) 
	{
		System.out.println(this.argument);
	}
}
