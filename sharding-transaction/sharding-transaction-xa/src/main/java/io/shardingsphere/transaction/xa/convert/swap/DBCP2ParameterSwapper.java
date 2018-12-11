/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.transaction.xa.convert.swap;

import io.shardingsphere.core.constant.transaction.ProxyPoolType;

/**
 * DBCP2 parameter swapper.
 *
 * @author zhaojun
 */
public final class DBCP2ParameterSwapper extends DataSourceSwapperAdapter {
    
    private static final String DBCP2_CLASS_NAME = "org.apache.commons.dbcp2.BasicDataSource";
    
    private static final String TOMCAT_DBCP2_CLASS_NAME = "org.apache.tomcat.dbcp.dbcp2.BasicDataSource";
    
    @Override
    protected void convertProperties(final AdvancedMapUpdater<String, Object> updater) {
        updater.transfer("maxTotal", "maximumPoolSize");
        updater.transfer("minIdle", "minimumPoolSize");
        updater.transfer("minEvictableIdleTimeMillis", "idleTimeout");
        updater.transfer("maxWaitMillis", "connectionTimeout");
        updater.transfer("maxConnLifetimeMillis", "maxLifetime");
        updater.transfer("timeBetweenEvictionRunsMillis", "maintenanceInterval");
        updater.getDelegateMap().put("proxyDatasourceType", ProxyPoolType.TOMCAT_DBCP2);
    }
    
    @Override
    public String originClassName() {
        return DBCP2_CLASS_NAME + ":" + TOMCAT_DBCP2_CLASS_NAME;
    }
}
