/*
 * This file is part of hawkeye
 * Copyright (C) BigBang<->BigCrunch Manoranjan Sahu, All Rights Reserved.
 *
 * This software is provided under the GNU GPL Version 2. In particular,
 *
 * 1) If you modify a source file, make a comment in it containing your name and the date.
 * 2) If you distribute a modified version, you must do it under the GPL 2.
 * 3) Developers are encouraged but not required to notify the hawkeye maintainers at abeautifulmind98@gmail.com
 * when they make a useful addition. It would be nice if significant contributions could be merged into the main distribution.
 *
 * A full copy of the license can be found in gpl.txt or online at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * END_HEADER
 */


package org.cricket.hawkeye.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.common.di.AppContainer;
import org.cricket.hawkeye.context.IHawkEyeExecutionContext;
import org.hawk.plugin.constant.HawkPluginConstant;
import org.hawk.software.Software;

/**
 *
 * @author manoranjan
 */
//@Service("commonService")
//@Qualifier("default")
public class CommonServiceImpl implements ICommonService{

    
    private static final Logger logger = Logger.getLogger(CommonServiceImpl.class);
    
    @Override
    public  List<String> listify(String input, String separator) {

        if (input == null || input.isEmpty() || separator == null || separator.isEmpty()) {

            return null;

        }
        List<String> result = new ArrayList<String>();

        StringTokenizer strToken = new StringTokenizer(input,separator);

        while(strToken.hasMoreTokens()){

            result.add(strToken.nextToken());

        }

        return result;


    }

    @Override
    public  String stringify(List<String> input, String separator) {

        if (input == null || input.isEmpty() || separator == null || separator.isEmpty()) {

            return "";

        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        int size = input.size();

        for (; i < size; i++) {

            sb.append(input.get(i));

            if (i + 1 != size) {

                sb.append(separator);

            }

        }

        return sb.toString();
    }


     /**
     * This saves properties into the file.
     * @param props
     * @param file
     * @return <tt>true</tt> on success <tt>false</tt> otherwise.
     */
    @Override
    public  boolean saveProperties(Properties props, String file){

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            props.store(fos, "");
        } catch (IOException ex) {
            logger.error("error occurred",ex);
            return false;
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException ex) {
                    logger.error("error occurred",ex);
                }
            }
        }
        return true;
    }

    /**
     * This loads properties from  the file.
     * @param props
     * @param file
     * @return <tt>Properties</tt>
     */
    @Override
    public  Properties loadProperties(String file){
        return loadProperties(new File(file));
    }
    /**
     * This loads properties from  the file.
     * @param props
     * @param file
     * @return <tt>Properties</tt>
     */
    @Override
    public  Properties loadProperties(File file){
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            props.load(fis);
        } catch (IOException ex) {
            logger.error("error occurred",ex);
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException ex) {
                   logger.error("error occurred",ex);
                }
            }
        }
        return props;
    }


    @Override
    public  boolean isNullOrEmpty(String input){
        return (input == null || input.isEmpty());
    }
     public  IHawkEyeExecutionContext getHawkEyeExecutionContext() {
        return AppContainer.getInstance().getBean( IHawkEyeExecutionContext.class);
    }

    @Override
    public String getTabPath(){
        //String tabPath = AppContainer.getInstance().getBean(HAWKTARGETSETTING, HawkTargetSetting.class).getProperty("TABPATH");
        Software software= this.getHawkEyeExecutionContext().getExecutionContext().getConfiguration().getCricketDataProvider().getSoftware();
        String pluginName= software.getPluginName();
        String pluginVersion = software.getPluginVersion().getVersion();
        String pluginDir = HawkPluginConstant.PLUGINDIR;
        
        
        String tabPath=pluginDir+System.getProperty("file.separator")+pluginName+HawkPluginConstant.SEPARATOR+pluginVersion;
        if( tabPath == null || tabPath.isEmpty()){
            tabPath = ".";
        }
        return tabPath;
    }
}
