package de.johannes_rabauer.micromigration.migrater.scripts.exceptionThrowing;

import de.johannes_rabauer.micromigration.scripts.Context;
import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public class ExceptionThrowingScript implements MigrationScript<Object>
{
	public ExceptionThrowingScript() throws Exception
	{
		throw new Exception();
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
