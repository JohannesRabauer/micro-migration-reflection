[![No Maintenance Intended](http://unmaintained.tech/badge.svg)](http://unmaintained.tech/)
# DEPRECATED
This is no longer supported, please consider using https://github.com/xdev-software/micro-migration instead.

# micro-migration-reflection
Provides a migrater based on reflection

Currently only holds one class `ReflectiveMigrater` which uses the [Reflections library](https://github.com/ronmamo/reflections)
to find all instances of the `MicroMigrationScript` in a defined package.

Because it is using the additional dependency, this class is extracted in this own repository and can be added only if needed 
to the [core repository](https://github.com/JohannesRabauer/micro-migration).