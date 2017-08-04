/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.plugin;

import org.hawk.plugin.HawkPlugin;
import org.hawk.plugin.event.PostHawkPluginDeploymentEvent;
import org.hawk.plugin.event.PreHawkPluginUndeploymentEvent;
import org.commons.event.HawkEvent;
import org.commons.event.HawkEventPayload;
import org.commons.event.callback.IHawkEventCallback;
import org.hawk.plugin.event.callback.IHawkPluginCallbackPlugin;
import org.hawk.plugin.exception.HawkPluginException;

/**
 *
 * @author manosahu
 */
//@Component("hawkEyePluginCallback")
//@Qualifier("default")
public class HawkEyePluginCallbackImpl implements IHawkPluginCallbackPlugin{

  

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HawkEyePluginCallbackImpl.class);

   

    @Override
    public Integer getSequence() {
        return 1;
    }

    @Override
    public int compareTo(IHawkEventCallback o) {
        return this.getSequence().compareTo(o.getSequence());
    }

    @HawkEvent(type = PreHawkPluginUndeploymentEvent.class)
    public boolean onPreHawkPluginUndeployment(HawkEventPayload hawkPluginPayload) throws HawkPluginException {
        PreHawkPluginUndeploymentEvent hawkPluginEvent = (PreHawkPluginUndeploymentEvent) hawkPluginPayload.getEvent();
        HawkPlugin hawkPlugin = (HawkPlugin)hawkPluginPayload.getPayload();

        boolean status = true;

        System.out.println("in hawk eye plugin recvd data for PreHawkPluginUndeploymentEvent");

        return status;

    }

    @HawkEvent(type = PostHawkPluginDeploymentEvent.class)
    public boolean onPostHawkPluginDeployment(HawkEventPayload hawkPluginPayload) throws HawkPluginException {
        PostHawkPluginDeploymentEvent hawkPluginEvent = (PostHawkPluginDeploymentEvent) hawkPluginPayload.getEvent();
        HawkPlugin hawkPlugin = (HawkPlugin)hawkPluginPayload.getPayload();

        boolean status = true;

        System.out.println("in hawk eye plugin recvd data for PostHawkPluginDeploymentEvent");

        return status;
    }

}
