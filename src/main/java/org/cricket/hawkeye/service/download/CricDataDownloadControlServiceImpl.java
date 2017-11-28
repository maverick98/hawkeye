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


package org.cricket.hawkeye.service.download;

import org.cricket.hawkeye.service.file.IFileService;
import org.cricket.hawkeye.exception.HawkEyeException;
import java.util.ArrayList;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.dao.exception.DAOException;
import org.cricket.hawkeye.service.ICommonService;
import org.cricket.hawkeye.service.download.exception.DownloadControlServiceException;
import static org.cricket.hawkeye.service.download.constant.DownloadConstant.*;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author manoranjan
 */
//@Service("cricDataDownloadControlService")
//@Qualifier("default")
public class CricDataDownloadControlServiceImpl implements ICricDataDownloadControlService{

    private static final Logger logger = Logger.getLogger(CricDataDownloadControlServiceImpl.class);

    @Autowired (required=true)
    //@Qualifier  ("default")
    ICricDataDAO cricOfflineDAO;

    @Autowired (required=true)
    //@Qualifier  ("default")
    IFileService  fileService;

    @Autowired (required=true)
    //@Qualifier  ("default")
    ICommonService commonService;

    public ICommonService getCommonService() {
        return commonService;
    }

    public ICricDataDAO getCricOfflineDAO() {
        return cricOfflineDAO;
    }

    public IFileService getFileService() {
        return fileService;
    }


    

    @Override
    public boolean createCountriesDir() throws DownloadControlServiceException {

        boolean result = false;

        String countriesDirStr = null;

        try {

            countriesDirStr = this.getCricOfflineDAO().findCountrysDir();

        } catch (DAOException ex) {

            logger.error("error occurred",ex);

            throw new DownloadControlServiceException("could not find countries dir ", ex);
        }

        File countriesDir = new File(countriesDirStr);

        if(!countriesDir.exists()){

            result = countriesDir.mkdirs();
        }

        return result;
    }

    private boolean modifyDownloadControlFileInternal(String path, List<String> itemList , boolean pending, boolean adding ) throws DownloadControlServiceException{

        if(path ==null || path.isEmpty()){

            throw new DownloadControlServiceException("empty path");

        }

        boolean result = false;

        String downloadControlFileStr = this.getDownloadControlFileInternal(path);

        File downloadControlFile = new File(downloadControlFileStr);

        if(downloadControlFile.exists()){

                Properties props = this.getCommonService().loadProperties(downloadControlFile);

                if(props == null){

                    throw new DownloadControlServiceException("unable to load control file {"+downloadControlFileStr+"}");

                }
                String value = null;

                if(pending){

                    value = props.getProperty(DOWNLOADCONTROL_PENDING);

                }else{

                    value = props.getProperty(DOWNLOADCONTROL_FINISHED);
                }

                StringBuilder sb = new StringBuilder();

                if(adding){

                    sb = new StringBuilder(value)
                                          .append(DOWNLOADCONTROL_SEPARATOR)
                                          .append
                                            (
                                                this.getCommonService().stringify
                                                            (
                                                                itemList,
                                                                DOWNLOADCONTROL_SEPARATOR
                                                            )
                                            );

                }else{

                    List<String> pendingValueList = this.getCommonService().listify(value, DOWNLOADCONTROL_SEPARATOR);

                    boolean removed = pendingValueList.removeAll(itemList);

                    sb.append(this.getCommonService().stringify(pendingValueList, DOWNLOADCONTROL_SEPARATOR));

                }

                if(pending){

                    props.setProperty(DOWNLOADCONTROL_PENDING, sb.toString());
                    
                }else{

                    props.setProperty(DOWNLOADCONTROL_FINISHED, sb.toString());
                    
                }

                this.getCommonService().saveProperties(props, downloadControlFileStr);

                result = true;

        }else{

            result = false;

        }

        return result;
    }
    

    @Override
    public boolean addFinishedItemToDownloadControlFile(String path, String finishedItem) throws DownloadControlServiceException {

       List<String> finishedItemList = new ArrayList<String>();

       finishedItemList.add(finishedItem);

       return this.modifyDownloadControlFileInternal(path, finishedItemList, false,true);
    }

    @Override
    public boolean addFinishedItemsToDownloadControlFile(String path, List<String> finishedItems) throws DownloadControlServiceException {
        return this.modifyDownloadControlFileInternal(path, finishedItems, false,true);
    }

    @Override
    public boolean addPendingItemToDownloadControlFile(String path, String pendingItem) throws DownloadControlServiceException {

        List<String> pendingItemList = new ArrayList<String>();

        pendingItemList.add(pendingItem);

        return this.modifyDownloadControlFileInternal(path, pendingItemList, true,true);
    }

    @Override
    public boolean addPendingItemsToDownloadControlFile(String path, List<String> pendingItems) throws DownloadControlServiceException {
        
        return this.modifyDownloadControlFileInternal(path, pendingItems, true,true);

    }

    @Override
    public boolean deleteCountriesHTML() throws DownloadControlServiceException {

        boolean result = false;

        try {

            String countriesPath = this.getCricOfflineDAO().findCountrysPath();

            File countriesPathFile = new File(countriesPath);

            if(countriesPathFile.exists()){

                result = countriesPathFile.delete();

            }

        } catch (DAOException ex) {

            logger.error("error occurred", ex);

            throw new DownloadControlServiceException("unable get countries path",ex);

        }

        return result;
    }

    @Override
    public boolean removeFinishedItemToDownloadControlFile(String path, String finishedItem) throws DownloadControlServiceException {
        
        List<String> finishedItemList = new ArrayList<String>();

        finishedItemList.add(finishedItem);

        return this.modifyDownloadControlFileInternal(path, finishedItemList, false,false);
    }

    @Override
    public boolean removeFinishedItemsToDownloadControlFile(String path, List<String> finishedItemList) throws DownloadControlServiceException {

        return this.modifyDownloadControlFileInternal(path, finishedItemList, false,false);
    }

    @Override
    public boolean removePendingItemToDownloadControlFile(String path, String pendingItem) throws DownloadControlServiceException {

        List<String> pendingItemList = new ArrayList<String>();

        pendingItemList.add(pendingItem);

        return this.modifyDownloadControlFileInternal(path, pendingItemList, true,false);
    }

    @Override
    public boolean removePendingItemsToDownloadControlFile(String path, List<String> pendingItemList) throws DownloadControlServiceException {

        return this.modifyDownloadControlFileInternal(path, pendingItemList, true,false);

    }


    private String getDownloadControlFileInternal(String path) throws DownloadControlServiceException{

        if(path == null || path.isEmpty()){

            throw new DownloadControlServiceException("empty path");

        }

        StringBuilder sb = new StringBuilder(path)
                                            .append("/")
                                            .append(DOWNLOADCONTROLFILE);

        return sb.toString();
    }
    @Override
    public boolean touchDownloadControlFile(String path) throws DownloadControlServiceException {

        boolean result = false;

        String downloadControlFileStr = this.getDownloadControlFileInternal(path);

        File downloadControlFile = new File(downloadControlFileStr);

        if(!downloadControlFile.exists()){

            try {

                result = downloadControlFile.createNewFile();

                Properties props = this.getCommonService().loadProperties(downloadControlFile);

                props.setProperty(DOWNLOADCONTROL_PENDING, "");

                props.setProperty(DOWNLOADCONTROL_FINISHED, "");

                this.getCommonService().saveProperties(props, downloadControlFileStr);

            } catch (IOException ex) {

                logger.error("error occurred",ex);

                throw new DownloadControlServiceException("can not create download control file at {"+path+"}", ex);

            }

        }else{

            result = false;

        }

        return result;
    }

    @Override
    public boolean updateDownloadControlFile(String path, List<String> pendingList, List<String> finishedList) throws DownloadControlServiceException {

        if(path ==null || path.isEmpty()){

            throw new DownloadControlServiceException("empty path");

        }

        boolean result = false;
       
        String downloadControlFileStr = this.getDownloadControlFileInternal(path);

        File downloadControlFile = new File(downloadControlFileStr);

        if(downloadControlFile.exists()){
 
                Properties props = this.getCommonService().loadProperties(downloadControlFile);

                if(props == null){

                    throw new DownloadControlServiceException("unable to load control file {"+downloadControlFileStr+"}");

                }

                props.setProperty(DOWNLOADCONTROL_PENDING, this.getCommonService().stringify(pendingList,DOWNLOADCONTROL_SEPARATOR));

                props.setProperty(DOWNLOADCONTROL_FINISHED, this.getCommonService().stringify(finishedList,DOWNLOADCONTROL_SEPARATOR));

                this.getCommonService().saveProperties(props, downloadControlFileStr);

                result = true;
                
        }else{

            result = false;

        }

        return result;
    }

    @Override
    public boolean shouldDownload(String path, String item) throws DownloadControlServiceException {

        if(item == null || item.isEmpty() || path == null || path.isEmpty()){

            throw new DownloadControlServiceException("illegal args passed");

        }

        boolean result = false;

        String downloadControlFileStr = this.getDownloadControlFileInternal(path);

        File downloadControlFile = new File(downloadControlFileStr);

        if(downloadControlFile.exists()){

            Properties props = this.getCommonService().loadProperties(downloadControlFile);

            if(props == null){

                throw new DownloadControlServiceException("unable to load control file {"+downloadControlFileStr+"}");

            }

            String pendingValue = props.getProperty(DOWNLOADCONTROL_PENDING);

            String finishedValue = props.getProperty(DOWNLOADCONTROL_FINISHED);

            if(pendingValue.contains(item) && !finishedValue.contains(item)){

                result = true;

            }else{

                result = false;

            }

        }else{

            result = true;

        }

        return result;
    }

    @Override
    public boolean resetDownloadControlFile() throws DownloadControlServiceException {

        boolean result = false;

        try {

            String countryDirStr = this.getCricOfflineDAO().findCountrysDir();

            List<File> allDownloadControlFiles = this.getFileService().findFile(countryDirStr, DOWNLOADCONTROLFILE);

            List<String> emptyList = new ArrayList<String>();

            for(File file : allDownloadControlFiles){

                this.updateDownloadControlFile(file.getParent(), emptyList,emptyList);

                result = true;
            }
            

        }catch (DAOException ex) {

            logger.error("error occurred",ex);

        }catch (HawkEyeException ex) {

            logger.error("error occurred",ex);

        }

        return result;

    }

    @Override
    public boolean isUpdateAvailable() throws DownloadControlServiceException {

        boolean result = false;

        

        return result;
    }

    @Override
    public List<String> checkForUpdates() throws DownloadControlServiceException {

        List<String> result = new ArrayList<String>();

        

        return result;
    }

   

   


}
