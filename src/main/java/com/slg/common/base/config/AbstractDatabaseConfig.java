package com.slg.common.base.config;

/**
 * Database configuration.
 */
abstract class AbstractDatabaseConfig implements DatabaseConfig {
    private final AppConfig appConfig;
    private final String prefix;

    AbstractDatabaseConfig(final AppConfig appConfig, final String prefix) {
        this.appConfig = appConfig;
        this.prefix = prefix;
    }

    @Override
    public String getDriverClass() {
        return appConfig.getString(prefix + ".driverClass", null);
    }

    @Override
    public String getUsername() {
        return appConfig.getString(prefix + ".username", null);
    }

    @Override
    public String getPassword() {
        return appConfig.getString(prefix + ".password", null);
    }

    @Override
    public String getServer() {
        return appConfig.getString(prefix + ".server", null);
    }

    @Override
    public String getServer(final int clusterNumber) {
        return String.format(appConfig.getString(prefix + ".server", null), clusterNumber);
    }

    @Override
    public String getDb() {
        return appConfig.getString(prefix + ".db", null);
    }

    @Override
    public int getConnectionMin() {
        return appConfig.getInt(prefix + ".connection.min", 0);
    }

    @Override
    public int getConnectionMax() {
        return appConfig.getInt(prefix + ".connection.max", 1);
    }

    @Override
    public long getConnectionTimeoutMs() {
        return appConfig.getLong(prefix + ".connection.timeout.ms", 30000);
    }

    @Override
    public long getConnectionIdleTimeoutMs() {
        return appConfig.getLong(prefix + ".connection.idle.timeout.ms", 600000);
    }

    @Override
    public long getConnectionMaxLifetimeMs() {
        return appConfig.getLong(prefix + ".connection.max.lifetime.ms", 1800000);
    }

    @Override
    public String getConnectionTestQuery() {
        return appConfig.getString(prefix + ".connection.test.query", "SELECT 1");
    }
}
