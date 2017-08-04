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
import org.hawk.plugin.metadata.Classpath;
import org.hawk.plugin.metadata.Configuration;
import org.hawk.plugin.metadata.HawkPluginMetaData;
import org.hawk.plugin.metadata.Jar;
import org.hawk.software.Contributor;
import org.hawk.software.Software;
import org.hawk.software.Version;
import org.hawk.xml.XMLUtil;

/**
 *
 * @author Manoranjan Sahu
 */
@XmlRootElement
public class HawkEyeConfig {

    private String tabPath;

    public String getTabPath() {
        return tabPath;
    }

    public void setTabPath(String tabPath) {
        this.tabPath = tabPath;
    }

    public static void main(String args[]) throws Exception {
        HawkPluginMetaData hawkEyePluginMetaData = new HawkPluginMetaData();
        Configuration configuration = new Configuration();
        configuration.setConfigXML("config.xml");
        configuration.setParserClazz("org.cricket.hawkeye.plugin.HawkEyeConfig");
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
        contributor.setAddress("#1211,2nd floor,22nd cross,3rd sector, HSR Layout, Bangalore-560102,Karnataka,India");
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
        System.out.println(FileUtil.readFile("metadata.xml"));

        System.out.println(XMLUtil.unmarshal("metadata.xml", HawkPluginMetaData.class).getSoftware().getAbout());

        HawkEyeConfig hawkEyeConfig = new HawkEyeConfig();
        hawkEyeConfig.setTabPath(".");
        XMLUtil.marshal(hawkEyeConfig, "config.xml");
        System.out.println(FileUtil.readFile("config.xml"));

        System.out.println(XMLUtil.unmarshal("config.xml", HawkEyeConfig.class).getTabPath());




    }
}
