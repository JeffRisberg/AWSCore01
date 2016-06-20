
package com.slg.common.base.config;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 */
public class RedshiftDatabaseConfig extends AbstractDatabaseConfig {
    /**
     * Provider for Redshift db config with Postgres driver.
     */
    public static class PProvider implements Provider<RedshiftDatabaseConfig> {
        private final AppConfig config;

        @Inject
        PProvider(final AppConfig config) {
            this.config = config;
        }

        @Override
        public RedshiftDatabaseConfig get() {
            return new RedshiftDatabaseConfig(config, "redshiftp");
        }
    }

    /**
     * Provider for Redshift db config with Redshift driver.
     */
    public static class RProvider implements Provider<RedshiftDatabaseConfig> {
        private final AppConfig config;

        @Inject
        RProvider(final AppConfig config) {
            this.config = config;
        }

        @Override
        public RedshiftDatabaseConfig get() {
            return new RedshiftDatabaseConfig(config, "redshiftr");
        }
    }

    RedshiftDatabaseConfig(final AppConfig config, final String prefix) {
        super(config, prefix);
    }

    public String getUrl() {
        throw new IllegalArgumentException("You need to specify a cluster number");
    }

    @Override
    public String getUrl(int clusterNumber) {
        return buildRedshiftUrl(getDriverClass(), getServer(clusterNumber), getDb());
    }

    static String buildRedshiftUrl(String driverClass, String server, String db) {
        return String.format("jdbc:%s://%s/%s", extractRedshiftDriverString(driverClass), server, db);
    }

    /**
     * We can use either Postgres or Redshift driver to connect to Redshift. This method returns
     * the JDBC driver string that needs to go in the DB connection URL based on the specified driver class.
     *
     * @param driverClass the specified driver class
     * @return Redshift JDBC driver string.
     */
    static String extractRedshiftDriverString(String driverClass) {
        // figure out the driver string from the driver class name
        String driver;
        if (driverClass.contains("postgresql"))
            driver = "postgresql";
        else if (driverClass.contains("redshift"))
            driver = "redshift";
        else
            throw new IllegalArgumentException("Unknown driver class: " + driverClass);

        return driver;
    }
}
