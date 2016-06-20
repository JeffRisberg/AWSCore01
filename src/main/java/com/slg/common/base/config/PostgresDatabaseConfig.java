package com.slg.common.base.config;

import com.google.inject.Inject;

/**
 */
public class PostgresDatabaseConfig extends AbstractDatabaseConfig {
    @Inject
    PostgresDatabaseConfig(AppConfig appConfig) {
        super(appConfig, "postgres");
    }

    @Override
    public String getUrl() {
        return buildPostgresUrl(getServer(), getDb());
    }

    @Override
    public String getUrl(int clusterNumber) {
        return buildPostgresUrl(getServer(clusterNumber), getDb());
    }

    static String buildPostgresUrl(String server, String db) {
        return String.format("jdbc:postgresql://%s/%s", server, db);
    }
}
