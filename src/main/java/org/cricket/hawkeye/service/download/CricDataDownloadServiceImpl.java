/*
 * This file is part of hawkeye
 * Copyright (C) 2012-2013 Manoranjan Sahu, All Rights Reserved.
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

import org.cricket.hawkeye.dao.player.AbstractPlayerDAO;
import org.apache.log4j.Logger;
import org.common.di.AppContainer;
import org.cricket.hawkeye.context.IHawkEyeExecutionContext;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.cricket.hawkeye.service.url.IURLService;
import org.cricket.hawkeye.service.file.exception.FileServiceException;

import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.dao.country.AbstractCountryDAO;
import org.cricket.hawkeye.dao.exception.DAOException;
import org.cricket.hawkeye.service.download.exception.DownloadServiceException;
import org.cricket.hawkeye.exception.HawkEyeException;
import org.cricket.hawkeye.service.download.exception.DownloadControlServiceException;
import org.cricket.hawkeye.service.file.IFileService;
import org.cricket.hawkeye.service.url.exception.URLServiceException;



/**
 * @TODO Use a dependency injection tool to manage the below instances...
 * @author manoranjan
 */
//@Service("cricDataDownloadService")
//@Qualifier("default")
public class CricDataDownloadServiceImpl implements ICricDataDownloadService {

    private static final Logger logger = Logger.getLogger(CricDataDownloadServiceImpl.class);

    public CricDataDownloadServiceImpl(){
        
    }

    @Autowired(required = true)
   // @Qualifier("default")
    ICricDataDAO cricOfflineDAO;
    @Autowired(required = true)
   // @Qualifier("default")
    IFileService fileService;
    @Autowired(required = true)
   // @Qualifier("default")
    IURLService urlService;
    @Autowired(required = true)
   // @Qualifier("default")
    ICricDataDownloadControlService cricDataDownloadControlService;

     public  IHawkEyeExecutionContext getHawkEyeExecutionContext() {
        return AppContainer.getInstance().getBean( IHawkEyeExecutionContext.class);
    }
    public ICricDataDownloadControlService getCricDataDownloadControlService() {
        return cricDataDownloadControlService;
    }

    public ICricDataDAO getCricOfflineDAO() {
        return cricOfflineDAO;
    }

     public ICricDataDAO getCricWebSiteDAO() {
        return AppContainer.getInstance().getBean("cricWebSiteDAO", ICricDataDAO.class);
    }

    public IFileService getFileService() {
        return fileService;
    }

    public IURLService getUrlService() {
        return urlService;
    }

    @Override
    public String downloadCountriesHTML() throws DownloadServiceException {

        String result = null;

        try {

            this.getCricDataDownloadControlService().deleteCountriesHTML();

            this.getCricDataDownloadControlService().createCountriesDir();

            String countryOfflinePath = this.getCricOfflineDAO().findCountrysPath();

            result = this.getCricWebSiteDAO().findCountrysHTML();

            this.getFileService().writeFile(countryOfflinePath, result);

        } catch (DAOException ex) {

            logger.error("error in DAO layer", ex);

            throw new DownloadServiceException(ex);

        } catch (FileServiceException ex) {

            logger.error("error in File Service", ex);

            throw new DownloadServiceException(ex);

        } catch (HawkEyeException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        }

        return result;
    }

    /**
     * This method downloads all the data required for hawkEye
     * @return
     * @throws DownloadServiceException
     */
    @Override
    public boolean downloadAll() throws DownloadServiceException {

        logger.info("inside downloadAll method....");
        try {


            String countriesHTML = this.downloadCountriesHTML();

            final String downloadCountryDir = this.getCricOfflineDAO().findCountrysDir();

            this.getCricDataDownloadControlService().touchDownloadControlFile(
                    downloadCountryDir);

            new AbstractCountryDAO(this.getCricWebSiteDAO().getCountryPattern()) {

                @Override
                public void countryCallback(String countryName) throws DAOException {
                    try {
                        getCricDataDownloadControlService().addPendingItemToDownloadControlFile(
                                downloadCountryDir,
                                countryName);

                        boolean shouldDownloadThisCountry = getCricDataDownloadControlService().shouldDownload(
                                downloadCountryDir,
                                countryName);
                        if (shouldDownloadThisCountry) {
                            String website = getHawkEyeExecutionContext().getExecutionContext().getConfiguration().getCricketDataProvider().getSoftware().getWebsite();
                            logger.info("downloading data for {" + countryName + "}  from {" + website+ "}");
                            System.out.println("downloading data for {" + countryName + "}  from {" + website+ "}");

                            long startTime = System.currentTimeMillis();

                            downloadCountryHTML(countryName);

                            downloadCountry(countryName);

                            long diff = System.currentTimeMillis() - startTime;

                            diff = diff / 1000;

                            logger.info("finished downloading for {" + countryName + "} in {" + diff + "}s");
                            System.out.println("finished downloading for {" + countryName + "} in {" + diff + "}s");

                        } else {

                            logger.info("not downloading data for {" + countryName + "} !!!");
                            System.out.println("not downloading data for {" + countryName + "} !!!");
                        }


                        getCricDataDownloadControlService().removePendingItemToDownloadControlFile(
                                downloadCountryDir,
                                countryName);

                        getCricDataDownloadControlService().addFinishedItemToDownloadControlFile(
                                downloadCountryDir,
                                countryName);
                    } catch (DownloadServiceException ex) {

                        logger.error("error occurred", ex);

                        throw new DAOException(ex);

                    } catch (HawkEyeException ex) {

                        logger.error("error occurred", ex);

                        throw new DAOException(ex);

                    }
                }
            }.find(countriesHTML);



        } catch (DAOException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (DownloadServiceException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (HawkEyeException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        }

        return true;

    }

    @Override
    public String downloadCountryHTML(String countryName) throws DownloadServiceException {

        String countryData = null;
        try {

            String countryFileStr = this.getCricOfflineDAO().findCountryPath(countryName);

            countryData = this.getCricWebSiteDAO().findCountryHTML(countryName);

            this.getCricDataDownloadControlService().touchDownloadControlFile(
                    this.getCricOfflineDAO().findCountryDir(
                    countryName));

            this.getFileService().writeFile(countryFileStr, countryData);


        } catch (FileServiceException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (DAOException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (DownloadControlServiceException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        }

        return countryData;
    }

    @Override
    public boolean downloadCountry(final String countryName) throws DownloadServiceException {

        try {

            String countryDetails = this.getCricOfflineDAO().findCountryHTML(countryName);

            if (countryDetails == null || countryDetails.isEmpty()) {

                throw new DownloadServiceException("could not find details for country {" + countryName + "}... check if it plays cricket!!!");
            }

            new AbstractPlayerDAO() {

                @Override
                public void playerCallback(String playerName) throws DAOException {

                    try {
                        String downloadControlDir = getCricOfflineDAO().findCountryDir(
                                countryName);
                        getCricDataDownloadControlService().addPendingItemToDownloadControlFile(
                                downloadControlDir,
                                playerName);

                        boolean shouldDownloadThisPlayer = getCricDataDownloadControlService().shouldDownload(
                                downloadControlDir,
                                playerName);
                        if (shouldDownloadThisPlayer) {
                            
                            String website = getHawkEyeExecutionContext().getExecutionContext().getConfiguration().getCricketDataProvider().getSoftware().getWebsite();
                          
                            logger.info("downloading data for {" + playerName + "}  from {" + website+ "}");

                            long startTime = System.currentTimeMillis();

                            downloadPlayer(countryName, playerName);

                            long diff = System.currentTimeMillis() - startTime;

                            diff = diff / 1000;

                            logger.info("finished downloading for {" + playerName + "} in {" + diff + "}s");

                        } else {

                            logger.info("not downloading data for {" + playerName + "} !!!");
                        }


                        getCricDataDownloadControlService().removePendingItemToDownloadControlFile(
                                downloadControlDir,
                                playerName);

                        getCricDataDownloadControlService().addFinishedItemToDownloadControlFile(
                                downloadControlDir,
                                playerName);

                    } catch (DAOException ex) {

                        logger.error("error occurred", ex);

                        throw new DAOException(ex);

                    } catch (HawkEyeException ex) {

                        logger.error("error occurred", ex);

                        throw new DAOException(ex);

                    }
                }
            }.find(countryDetails);



        } catch (DAOException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (HawkEyeException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        }

        return true;
    }

    @Override
    public String downloadPlayer(String countryName, String playerName) throws DownloadServiceException {

        String playerData = null;

        try {


            String playerURL = this.getCricWebSiteDAO().findPlayerPath(
                    countryName,
                    playerName);

            playerData = this.getUrlService().fetch(playerURL);

            this.getFileService().writeFile(
                    this.getCricOfflineDAO().findPlayerPath(
                    countryName,
                    playerName),
                    playerData);



        } catch (DAOException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (URLServiceException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        } catch (FileServiceException ex) {

            logger.error("error occurred", ex);

            throw new DownloadServiceException(ex);

        }


        return playerData;
    }
}
