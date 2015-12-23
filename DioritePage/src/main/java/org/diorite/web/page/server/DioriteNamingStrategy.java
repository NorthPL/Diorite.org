package org.diorite.web.page.server;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class DioriteNamingStrategy implements PhysicalNamingStrategy
{
    private static final String PREFIX = "DIORITEORG_";

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment)
    {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment)
    {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment)
    {
        return Identifier.toIdentifier(this.addPrefix(identifier.getText()));
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment)
    {
        return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment)
    {
        return identifier;
    }

    private String addPrefix(final String composedTableName)
    {
        return PREFIX + composedTableName;
    }
}
