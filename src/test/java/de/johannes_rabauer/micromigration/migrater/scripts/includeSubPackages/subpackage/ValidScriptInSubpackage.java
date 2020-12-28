package de.johannes_rabauer.micromigration.migrater.scripts.includeSubPackages.subpackage;

import de.johannes_rabauer.micromigration.scripts.Context;
import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public class ValidScriptInSubpackage implements MigrationScript<Object>
{
	@Override
	public MigrationVersion getTargetVersion() 
	{
		return new MigrationVersion(2);
	}

	@Override
	public void migrate(Context<Object> context) 
	{
		//Do nothing
	}
}
