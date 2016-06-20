package com.slg.common.base.config;

import com.google.inject.Inject;

/**
 */
public class MySQLDatabaseConfig extends AbstractDatabaseConfig {
    @Inject
    MySQLDatabaseConfig(AppConfig appConfig) {
        super(appConfig, "mysql");
    }

    @Override
    public String getUrl() {
        return buildMySQLUrl(getServer(), getDb());
    }

    @Override
    public String getUrl(int clusterNumber) {
        return buildMySQLUrl(getServer(clusterNumber), getDb());
    }

    static String buildMySQLUrl(String server, String db) {
        return String.format("jdbc:mysql://%s/%s?allowMultiQueries=true&characterEncoding=utf8", server, db);
    }
}
