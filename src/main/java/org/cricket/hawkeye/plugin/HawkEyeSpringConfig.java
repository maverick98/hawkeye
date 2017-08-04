/**
 * This file is part of impensa. CopyLeft (C) BigBang<->BigCrunch.All Rights are
 * left.
 *
 * 1) Modify it if you can understand. 2) If you distribute a modified version,
 * you must do it at your own risk.
 *
 */
package org.cricket.hawkeye.plugin;

import org.cricket.hawkeye.context.HawkEyeExecutionContextImpl;
import org.cricket.hawkeye.dao.CricDataDAOImplProxy;
import org.cricket.hawkeye.dao.DefaultCricDataDAOImpl;
import org.cricket.hawkeye.service.CommonServiceImpl;
import org.cricket.hawkeye.service.download.CricDataDownloadControlServiceImpl;
import org.cricket.hawkeye.service.download.CricDataDownloadServiceImpl;
import org.cricket.hawkeye.service.file.FileServiceImpl;
import org.cricket.hawkeye.service.persistence.CricDataPersistenceServiceImpl;
import org.cricket.hawkeye.service.url.URLServiceImpl;
import org.cricket.hawkeye.string.LevenshteinDistanceAlgorithm;
import org.cricket.hawkeye.string.StringServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author msahu98
 */
@Configuration
public class HawkEyeSpringConfig {

    @Bean
    public HawkEyeExecutionContextImpl hawkEyeExecutionContextImpl() {
        return new HawkEyeExecutionContextImpl();
    }

    @Bean
    public CricDataDAOImplProxy cricDataDAOImplProxy() {
        return new CricDataDAOImplProxy();
    }

    @Bean
    public DefaultCricDataDAOImpl cricOfflineDAO() {
        return new DefaultCricDataDAOImpl();
    }

    @Bean
    public CricDataDAOImplProxy cricWebSiteDAO() {
        return new CricDataDAOImplProxy();
    }

    @Bean
    public HawkEyePluginCallbackImpl hawkEyePluginCallbackImpl() {
        return new HawkEyePluginCallbackImpl();
    }

    @Bean
    public HawkEyePluginConfig hawkEyePluginConfig() {
        return new HawkEyePluginConfig();
    }

    @Bean
    public CricDataPersistenceServiceImpl cricDataPersistenceServiceImpl() {
        return new CricDataPersistenceServiceImpl();
    }

    @Bean
    public LevenshteinDistanceAlgorithm editDistanceAlgorithm() {
        return new LevenshteinDistanceAlgorithm();
    }

    @Bean
    public FileServiceImpl fileService() {
        return new FileServiceImpl();
    }

    @Bean
    public CommonServiceImpl commonService() {
        return new CommonServiceImpl();
    }

    @Bean
    public CricDataDownloadControlServiceImpl cricDataDownloadControlService() {
        return new CricDataDownloadControlServiceImpl();
    }

    @Bean 
    public CricDataDownloadServiceImpl cricDataDownloadService(){
        return new CricDataDownloadServiceImpl();
    }
    @Bean
    public URLServiceImpl urlService() {
        return new URLServiceImpl();
    }

    @Bean
    public StringServiceImpl stringService() {
        return new StringServiceImpl();
    }

}
