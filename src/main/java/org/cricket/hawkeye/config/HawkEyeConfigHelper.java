/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.common.di.AppContainer;
import org.commons.reflection.ClazzUtil;
import org.cricket.hawkeye.context.HawkEyeExecutionContext;
import org.cricket.hawkeye.context.IHawkEyeExecutionContext;
import org.cricket.hawkeye.dao.CricDataDAOImplProxy;
import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.exception.HawkEyeException;
import org.cricket.hawkeye.plugin.HawkEyePluginConfiguration;
import org.cricket.hawkeye.plugin.HawkEyeSpringConfig;
import org.hawk.plugin.metadata.Jar;
import org.hawk.xml.XMLUtil;

/**
 * This configures the hawk eye ... Essentially it sets the provider as
 * currently it is cricinfo.com not to be confused with hawkpluginconfig obejct.
 *
 * @author manosahu
 */


public class HawkEyeConfigHelper  {

    private static final HawkEyeConfigHelper theInstance = new HawkEyeConfigHelper();
    
    private HawkEyeConfigHelper(){
        
    }
    public static HawkEyeConfigHelper getInstance(){
        return theInstance;
    }
    public  IHawkEyeExecutionContext getHawkEyeExecutionContext() {
        return AppContainer.getInstance().getBean( IHawkEyeExecutionContext.class);
    }

    public ICricDataDAO getCricWebSiteDAO() {
        return AppContainer.getInstance().getBean("cricWebSiteDAO", ICricDataDAO.class);
    }
    private HawkEyeExecutionContext ctx;

    public HawkEyeExecutionContext getCtx() {
        return ctx;
    }

    public void setCtx(HawkEyeExecutionContext ctx) {
        this.ctx = ctx;
    }
    
    
   
    public boolean configureSpringBeans(){
        AppContainer.getInstance().registerConfig(HawkEyeSpringConfig.class);
        AppContainer.getInstance().getAppContext().refresh();
        return true;
    }
   
    public boolean configure() throws HawkEyeException {

        boolean status;
        System.out.println("I am Invoked !!!!");
        //this.configureSpringBeans();
        HawkEyePluginConfiguration hawkEyePluginConfig = this.loadConfig();
        status = this.configureCricDataProvider(hawkEyePluginConfig);

        return status;
    }
    

     private HawkEyePluginConfiguration loadConfig() throws HawkEyeException {
        HawkEyePluginConfiguration hawkEyePluginConfig = null;
       
        if (this.getHawkEyeExecutionContext().getExecutionContext().getType() == HawkEyeExecutionContext.Type.MAIN) {
            try {
                hawkEyePluginConfig = XMLUtil.unmarshal("config.xml", HawkEyePluginConfiguration.class);
            } catch (Exception ex) {
               throw new HawkEyeException(ex);
            }
        } else if (this.getHawkEyeExecutionContext().getExecutionContext().getType() == HawkEyeExecutionContext.Type.PLUGIN) {
            hawkEyePluginConfig = this.getHawkEyeExecutionContext().getExecutionContext().getConfiguration();
        }
        this.ctx = this.getHawkEyeExecutionContext().getExecutionContext();
        this.ctx.setConfiguration(hawkEyePluginConfig);
        return hawkEyePluginConfig;
    }

    public boolean configureCricDataProvider(HawkEyePluginConfiguration hawkEyePluginConfig) throws HawkEyeException {
     
        this.getHawkEyeExecutionContext().setExecutionContext(this.ctx);
        CricDataDAOImplProxy cricWebSiteDAOProxy = (CricDataDAOImplProxy) this.getCricWebSiteDAO();
        cricWebSiteDAOProxy.setCricDataProviderDAO(this.getProviderCricDataDAO(hawkEyePluginConfig));
        cricWebSiteDAOProxy.injectDependenciesInProviderDAO();

        return true;
    }

    private ICricDataDAO getProviderCricDataDAO(HawkEyePluginConfiguration hawkEyePluginConfig) {
        String cricDataDAOStr = hawkEyePluginConfig.getCricketDataProvider().getCricDataDAO();
        ICricDataDAO cricDataDAO = null;
        try {
            Class cricDataDAOClazz;
            cricDataDAOClazz = ClazzUtil.loadClass(cricDataDAOStr);

            cricDataDAO = ClazzUtil.instantiate(cricDataDAOClazz, ICricDataDAO.class);
        } catch (Exception ex) {
            Logger.getLogger(HawkEyeConfigHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cricDataDAO;
    }

  

   
    
    
     private List<String> getJarPaths(List<Jar> jars) {
        List<String> result = new ArrayList<String>();
        String rootPath = this.getHawkEyeExecutionContext().getExecutionContext().getRootPath();

        for (Jar jar : jars) {
            //result.add(jar.getPath());
            StringBuilder path = new StringBuilder();
            path.append(rootPath);
            path.append(File.separator);
            path.append(jar.getPath());
            result.add(path.toString());
            path = new StringBuilder();
        }
        return result;
    }
}
