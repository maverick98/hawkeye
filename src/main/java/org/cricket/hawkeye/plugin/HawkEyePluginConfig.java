/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.plugin;

import java.awt.geom.QuadCurve2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.common.di.AppContainer;
import org.commons.reflection.Create;
import org.cricket.hawkeye.config.HawkEyeConfigHelper;
import org.cricket.hawkeye.context.HawkEyeExecutionContext;
import org.cricket.hawkeye.context.IHawkEyeExecutionContext;
import org.cricket.hawkeye.exception.HawkEyeException;
import org.hawk.config.clazzloader.ClassPathHacker;
import org.hawk.plugin.HawkPlugin;
import org.hawk.plugin.IHawkPluginConfig;
import org.hawk.plugin.event.PostHawkPluginDeploymentEvent;
import org.hawk.plugin.event.PreHawkPluginUndeploymentEvent;
import org.commons.event.callback.IHawkEventCallback;
import org.commons.event.callback.IHawkEventCallbackRegistry;
import org.commons.event.exception.HawkEventException;


import org.hawk.plugin.exception.HawkPluginException;
import org.hawk.plugin.metadata.Jar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author manosahu
 */
//@Component("hawkEyePluginConfig")
//@Qualifier("default")
public class HawkEyePluginConfig implements IHawkPluginConfig {

    private HawkPlugin hawkPlugin;

    @Autowired(required = true)
   
    private IHawkEventCallback hawkEyePluginCallbackImpl;

    public IHawkEyeExecutionContext getHawkEyeExecutionContext() {
        return AppContainer.getInstance().getBean( IHawkEyeExecutionContext.class);
    }

    public IHawkEventCallback getHawkEyePluginCallback() {
        return hawkEyePluginCallbackImpl;
    }

    public void setHawkEyePluginCallback(IHawkEventCallback hawkEyePluginCallback) {
        this.hawkEyePluginCallbackImpl = hawkEyePluginCallback;
    }

  

    public HawkPlugin getHawkPlugin() {
        return hawkPlugin;
    }

    public void setHawkPlugin(HawkPlugin HawkPlugin) {
        this.hawkPlugin = HawkPlugin;
    }

    @Override
    public boolean onLoad(HawkPlugin hawkPlugin) throws HawkPluginException {
        try {
            ClassPathHacker.addFile(
                    this.getJarPaths(
                            ((HawkEyePluginConfiguration) hawkPlugin.getConfig())
                                    .getCricketDataProvider()
                                    .getClasspath()
                                    .getJar(),
                            hawkPlugin.getPluginHome()
                    )
            );

            // Make plugin classes visible to reflection / DI / Spring
            Thread.currentThread().setContextClassLoader(
                    ClassPathHacker.getPluginClassLoader()
            );

        } catch (Exception ex) {
            throw new HawkPluginException(ex);
        }
        return true;
    }


    @Override
    public boolean configure(HawkPlugin hawkPlugin) throws HawkPluginException {

        HawkEyeExecutionContext context = new HawkEyeExecutionContext();
        context.setType(HawkEyeExecutionContext.Type.PLUGIN);
        context.setConfiguration((HawkEyePluginConfiguration) hawkPlugin.getConfig());
        context.setRootPath(hawkPlugin.getPluginHome());

        this.getHawkEyeExecutionContext().setExecutionContext(context);
        this.setHawkPlugin(hawkPlugin);
        try {
           // HawkEyeConfigHelper.getInstance().setCtx(context);
            HawkEyeConfigHelper.getInstance().configure();
            this.register(AppContainer.getInstance().getBean(IHawkEventCallbackRegistry.class));

        } catch (HawkEyeException ex) {
            throw new HawkPluginException(ex);
        }
        return true;
    }

    private List<String> getJarPaths(List<Jar> jars , String rootPath) {
        List<String> result = new ArrayList<String>();
        //String rootPath = this.getHawkEyeExecutionContext().getExecutionContext().getRootPath();

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

    @Create
    public IHawkPluginConfig create() {
        return AppContainer.getInstance().getBean("hawkEyePluginConfig", IHawkPluginConfig.class);
    }

    @Override
    public boolean register(IHawkEventCallbackRegistry hawkPluginCallbackRegistry) throws HawkPluginException {
        try {
            hawkPluginCallbackRegistry.register(PreHawkPluginUndeploymentEvent.class, this.getHawkEyePluginCallback());
            hawkPluginCallbackRegistry.register(PostHawkPluginDeploymentEvent.class, this.getHawkEyePluginCallback());
            return true;
        } catch (HawkEventException ex) {
            throw new HawkPluginException(ex);
        }
    }

}
