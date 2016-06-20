package com.slg.common.base.config;

/**
 */
public interface DatabaseConfig {
    String getDriverClass();

    String getUsername();

    String getPassword();

    String getServer();

    String getServer(int clusterNumber);

    String getDb();

    String getUrl();

    String getUrl(int clusterNumber);

    int getConnectionMin();

    int getConnectionMax();

    long getConnectionTimeoutMs();

    long getConnectionIdleTimeoutMs();

    long getConnectionMaxLifetimeMs();

    String getConnectionTestQuery();
}
