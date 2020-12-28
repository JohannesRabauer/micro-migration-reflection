package de.johannes_rabauer.micromigration.migrater.scripts.moreClassesIncludingValid;

import de.johannes_rabauer.micromigration.scripts.Context;
import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public class ValidScript implements MigrationScript<Object>
{
	@Override
	public MigrationVersion getTargetVersion() 
	{
		return new MigrationVersion(1);
	}

	@Override
	public void migrate(Context<Object> context) 
	{
		//Do nothing
	}
}
