package com.greboreda.portal.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class LowerCasePhysicalNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		return toLower(identifier);
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		return toLower(identifier);
	}

	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		return toLower(identifier);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		return toLower(identifier);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
		return toLower(identifier);
	}

	private Identifier toLower(Identifier identifier) {
		if(identifier == null) {
			return null;
		}
		final String name = identifier.getText();
		final String lowerName = name.toLowerCase();
		final Identifier converted = Identifier.toIdentifier(lowerName);
		return converted;
	}
}
