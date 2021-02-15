package de.johannes_rabauer.micromigration.migrater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReflectiveMigraterTest 
{
	@Test
	void testValidScript() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ReflectiveMigrater migrater = new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.valid");
		assertEquals(1, migrater.getSortedScripts().size());
		assertEquals(
			de.johannes_rabauer.micromigration.migrater.scripts.valid.ValidScript.class, 
			migrater.getSortedScripts().first().getClass()
		);
	}
	
	@Test
	void testValidScriptWithIrrelevantClasses() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ReflectiveMigrater migrater = new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.moreClassesIncludingValid");
		assertEquals(1, migrater.getSortedScripts().size());
		assertEquals(
			de.johannes_rabauer.micromigration.migrater.scripts.moreClassesIncludingValid.ValidScript.class, 
			migrater.getSortedScripts().first().getClass()
		);
	}
	
	@Test
	void testValidScriptWithSubpackages() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ReflectiveMigrater migrater = new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.includeSubPackages");
		assertEquals(2, migrater.getSortedScripts().size());
		assertEquals(
			de.johannes_rabauer.micromigration.migrater.scripts.includeSubPackages.ValidScript.class, 
			migrater.getSortedScripts().first().getClass()
		);
		assertEquals(
			de.johannes_rabauer.micromigration.migrater.scripts.includeSubPackages.subpackage.ValidScriptInSubpackage.class, 
			migrater.getSortedScripts().last().getClass()
		);
	}
	
	@Test
	void testPackageWithNoScript() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ReflectiveMigrater migrater = new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.packageNotExisting");
		assertEquals(0, migrater.getSortedScripts().size());
	}

	@Test
	void testExceptionThrowingScript() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Assertions.assertThrows(InvocationTargetException.class, () -> {
			new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.exceptionThrowing");
		});
	}

	@Test
	void testErrorThrowingScript() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Assertions.assertThrows(InvocationTargetException.class, () -> {
			new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.errorThrowing");
		});
	}

	@Test
	void testNoCorrectConstructor() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Assertions.assertThrows(NoSuchMethodException.class, () -> {
			new ReflectiveMigrater("de.johannes_rabauer.micromigration.migrater.scripts.noCorrectConstructor");
		});
	}
}
