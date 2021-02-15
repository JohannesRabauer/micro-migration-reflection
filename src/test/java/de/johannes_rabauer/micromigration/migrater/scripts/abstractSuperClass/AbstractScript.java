package de.johannes_rabauer.micromigration.migrater.scripts.abstractSuperClass;

import de.johannes_rabauer.micromigration.scripts.MigrationScript;
import de.johannes_rabauer.micromigration.version.MigrationVersion;

public abstract class AbstractScript implements MigrationScript<Object>
{
	@Override
	public MigrationVersion getTargetVersion() 
	{
		return new MigrationVersion(1);
	}
}
