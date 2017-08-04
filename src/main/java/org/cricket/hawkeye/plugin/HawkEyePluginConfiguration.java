/*
 * This file is part of j-hawk
 * Copyright (C) 2012-2013 Manoranjan Sahu, All Rights Reserved.
 *
 * This software is provided under the GNU GPL Version 2. In particular,
 *
 * 1) If you modify a source file, make a comment in it containing your name and the date.
 * 2) If you distribute a modified version, you must do it under the GPL 2.
 * 3) Developers are encouraged but not required to notify the j-hawk maintainers at abeautifulmind98@gmail.com
 * when they make a useful addition. It would be nice if significant contributions could be merged into the main distribution.
 *
 * A full copy of the license can be found in gpl.txt or online at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * END_HEADER
 */
package org.cricket.hawkeye.plugin;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.commons.file.FileUtil;
import org.cricket.hawkeye.config.CricketDataProvider;

import org.hawk.plugin.metadata.Classpath;
import org.hawk.plugin.metadata.Configuration;
import org.hawk.plugin.metadata.HawkPluginMetaData;
import org.hawk.plugin.metadata.Jar;
import org.hawk.software.Contributor;
import org.hawk.software.Software;
import org.hawk.software.Version;
import org.hawk.xml.XMLUtil;

/**
 * This is useful when it is deployed as plugin in j-hawk Not to be confused
 * with HawkEyeConfig
 *
 * @author Manoranjan Sahu
 */
@XmlRootElement
public class HawkEyePluginConfiguration {


    private CricketDataProvider cricketDataProvider;

    public CricketDataProvider getCricketDataProvider() {
        return cricketDataProvider;
    }

    public void setCricketDataProvider(CricketDataProvider cricketDataProvider) {
        this.cricketDataProvider = cricketDataProvider;
    }

   
    public static void main(String args[]) throws  Exception{
        HawkPluginMetaData hawkEyePluginMetaData = new HawkPluginMetaData();
        Configuration configuration = new Configuration();
        configuration.setHawkConfigClazz("org.cricket.hawkeye.plugin.HawkEyePluginConfig");
        configuration.setSpringConfigClazz("org.cricket.hawkeye.plugin.HawkEyeSpringConfig");
        configuration.setConfigXML("config.xml");
        configuration.setParserClazz("org.cricket.hawkeye.plugin.HawkEyePluginConfiguration");
        hawkEyePluginMetaData.setConfiguration(configuration);
        hawkEyePluginMetaData.setBgRun(false);
        Software software = new Software();
        Version hawkVersion = new Version();
        hawkVersion.setVersion("13.10");
        Contributor contributor = new Contributor();
        contributor.setAuthorType(Contributor.ContributorTypeEnum.INDIVIDUAL);
        contributor.setName("Manoranjan Sahu");
        contributor.setEmail("abeautifulmind98@gmail.com");
        contributor.setWebsite("http://www.j-hawk.in");
        contributor.setContactNo("+91-9740319263");
        contributor.setAddress("#1993,AECS Layout,A Block,Singasandra, Bangalore-560068,Karnataka,India");
        software.setVersion(hawkVersion);
        software.setContributor(contributor);
        software.setCategory("Sports Analytics");
        software.setName("hawk-eye");
        software.setReleaseDate(new Date());
        software.setWebsite("http://www.j-hawk.in");
        software.setAbout("This is a software about analyzing cricket data collected from cricinfo.com");
        hawkEyePluginMetaData.setSoftware(software);
        Jar jar1 = new Jar();
        jar1.setDesc("hawkeye plugin");
        jar1.setPath("hawkeye-1.0.jar");
        Classpath classpath = new Classpath();
        classpath.getJar().add(jar1);
        hawkEyePluginMetaData.setClasspath(classpath);
        hawkEyePluginMetaData.setBgRun(false);
        hawkEyePluginMetaData.setLogPath(".");
        XMLUtil.marshal(hawkEyePluginMetaData, "metadata.xml");
     //   System.out.println(FileUtil.readFile("metadata.xml"));

   //     System.out.println(XMLUtil.unmarshal("metadata.xml", HawkPluginMetaData.class).getSoftware().getAbout());
        
         HawkEyePluginConfiguration hawkEyeConfig = new HawkEyePluginConfiguration();
        
         CricketDataProvider cricketDataProvider = new CricketDataProvider();
         hawkEyeConfig.setCricketDataProvider(cricketDataProvider);
         cricketDataProvider.setTabPath(".");

         Jar jar11 = new Jar();
         jar11.setDesc("espnhawkeye");
         jar11.setPath("espnhawkeye-1.0.jar");
         Classpath classpath1 = new Classpath();
         classpath1.getJar().add(jar11);
         cricketDataProvider.setClasspath(classpath1);

         Software espnSoftware = new Software();
         Version espnhawkeyeversion = new Version();
         espnhawkeyeversion.setVersion("1.0");

         espnSoftware.setVersion(espnhawkeyeversion);
         espnSoftware.setContributor(contributor);
         espnSoftware.setCategory("Sports Analytics");
         espnSoftware.setName("espn hawk-eye");
         espnSoftware.setReleaseDate(new Date());
         espnSoftware.setWebsite("http://www.espncricinfo.com");
         espnSoftware.setAbout("hawkeye data provided by espncricinfo");
         cricketDataProvider.setSoftware(espnSoftware);
         XMLUtil.marshal(hawkEyeConfig, "config.xml");
        // System.out.println(FileUtil.readFile("config.xml"));

         System.out.println(XMLUtil.unmarshal("config.xml", HawkEyePluginConfiguration.class));
         
    }
}
