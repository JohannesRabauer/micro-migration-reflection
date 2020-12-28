package de.johannes_rabauer.micromigration.migrater.scripts.errorThrowing;

import de.johannes_rabauer.micromigration.scripts.Context;
import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public class ErrorThrowingScript implements MigrationScript<Object>
{
	public ErrorThrowingScript()
	{
		throw new Error();
	}
	
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
