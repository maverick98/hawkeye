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
package org.cricket.hawkeye.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.common.di.AppContainer;
import org.cricket.hawkeye.dao.country.AbstractCountryDAO;
import org.cricket.hawkeye.dao.exception.DAOException;
import org.cricket.hawkeye.dao.player.AbstractPlayerDAO;
import org.cricket.hawkeye.exception.HawkEyeException;
import org.cricket.hawkeye.service.ICommonService;
import org.cricket.hawkeye.service.file.IFileService;
import org.cricket.hawkeye.service.file.exception.FileServiceException;
import org.cricket.hawkeye.service.url.IURLService;
import org.cricket.hawkeye.string.IStringService;
import org.cricket.hawkeye.string.exception.StringServiceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author manoranjan
 */
//@Component("cricOfflineDAO")
//@Qualifier("default")
public class DefaultCricDataDAOImpl implements ICricDataDAO {

    private static final Logger logger = Logger.getLogger(DefaultCricDataDAOImpl.class);
    @Autowired(required = true)
    //@Qualifier("default")
    IFileService fileService;
    @Autowired(required = true)
    //@Qualifier("default")
    IStringService stringService;

    @Autowired(required = true)
    //@Qualifier("default")
    ICommonService commonService;

    @Autowired(required = true)
    //@Qualifier("default")
    IURLService urlService;

    public IURLService getUrlService() {
        return urlService;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public IFileService getFileService() {
        return fileService;
    }

    public IStringService getStringService() {
        return stringService;
    }

    public void setFileService(IFileService fileService) {
        this.fileService = fileService;
    }

    public void setStringService(IStringService stringService) {
        this.stringService = stringService;
    }

    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
    }

    public void setUrlService(IURLService urlService) {
        this.urlService = urlService;
    }

    @Override
    public String findCountrysDir() throws DAOException {
        String countriesDir =  this.getCommonService().getTabPath()+File.separator+"country";
     
        return countriesDir;
    }

    @Override
    public String findCountrysPath() throws DAOException {
        return new StringBuilder(this.findCountrysDir()).append("/country.html").toString();
    }

    @Override
    public String findCountrysHTML() throws DAOException {
        String countriesHTML = null;

            try {
            countriesHTML = this.getFileService().readFile(this.findCountrysPath());
            } catch (FileServiceException ex) {
            throw new DAOException(ex);
            }
        return countriesHTML;
    }

    @Override
    public String findCountryDir(String countryName) throws DAOException {

        String countryDir = new StringBuilder(this.findCountrysDir()).append("/").append(countryName).toString();
            File f = new File(countryDir);
            if (!f.exists()) {
                f.mkdirs();
            }
        String countryFile = new StringBuilder().append(countryDir).toString();
        return countryFile;
    }

    @Override
    public List<String> findCountryPath(String countryName) throws DAOException {

        String countryFile = new StringBuilder().append(this.findCountryDir(countryName)).append("/").append(countryName).append(".html").toString();
        List<String> result = new ArrayList<>();
        result.add(countryFile);
        return result;
    }

    @Override
    public String findCountryHTML(String countryName) throws DAOException {
        String countryHTML = null;

            try {
            countryHTML = this.getFileService().readFile(this.findCountryPath(countryName).get(0));
            } catch (HawkEyeException ex) {
            throw new DAOException(ex);
            }
        return countryHTML;
    }

    @Override
    public String findPlayerPath(String countryName, String playerName) throws DAOException {
        String playerHTML = new StringBuilder(this.findCountryDir(countryName)).append("/").append(playerName).append(".html").toString();
        return playerHTML;
    }

    @Override
    public String findPlayerHTML(String countryName, String playerName) throws DAOException {
        String playerHTML = null;

            try {
            playerHTML = this.getFileService().readFile(this.findPlayerPath(countryName, playerName));
            } catch (HawkEyeException ex) {
            throw new DAOException(ex);
            }
        return playerHTML;
    }

    @Override
    public List<String> findCountrys() throws DAOException {

        String countriesHTML = this.findCountrysHTML();

        final List<String> countries = new ArrayList<String>();

        new AbstractCountryDAO(this.getCountryPattern()) {

            @Override
            public void countryCallback(String countryName) throws DAOException {
                countries.add(countryName);
            }
        }.find(countriesHTML);

        return countries;
    }

    @Override
    public List<String> findPlayers(final String forCountryName) throws DAOException {

        final List<String> players = new ArrayList<String>();

        String countriesHTML = this.findCountrysHTML();

        new AbstractCountryDAO(this.getCountryPattern()) {

            @Override
            public void countryCallback(String countryName) throws DAOException {

                if(forCountryName.equalsIgnoreCase(countryName)){
                    String countryHTML = null;

                    try {

                        countryHTML = findCountryHTML(countryName);

                    } catch (DAOException ex) {

                        logger.warn("could not find countryHTML for country {" + countryName + "} due to {" + ex.getMessage() + "}");

                    }

                    if (countryHTML != null && !countryHTML.isEmpty()) {

                        new AbstractPlayerDAO() {

                            @Override
                            public void playerCallback(String playerName) throws DAOException {

                                players.add(playerName);
                            }
                        }.find(countryHTML);
                    }
                }
            }
        }.find(countriesHTML);

        return players;
    }

    @Override
    public List<String> findPlayers() throws DAOException {

        final List<String> players = new ArrayList<String>();

        String countriesHTML = this.findCountrysHTML();

        new AbstractCountryDAO(this.getCountryPattern()) {

            @Override
            public void countryCallback(String countryName) throws DAOException {

                String countryHTML = null;

                try {

                    countryHTML = findCountryHTML(countryName);

                } catch (DAOException ex) {

                    logger.warn("could not find countryHTML for country {" + countryName + "} due to {" + ex.getMessage() + "}");

                }

                if (countryHTML != null && !countryHTML.isEmpty()) {

                    new AbstractPlayerDAO() {

                        @Override
                        public void playerCallback(String playerName) throws DAOException {

                            players.add(playerName);
                        }
                    }.find(countryHTML);
                }
            }
        }.find(countriesHTML);

        return players;
    }

    @Override
    public Map<Integer, List<String>> searchPlayersByApproxStringMatching(String searchCriteria) throws DAOException {

        Map<Integer, List<String>> result = new TreeMap<Integer, List<String>>();

        List<String> players = this.findPlayers();

        try {

            result = this.getStringService().findEditDistanceMap(searchCriteria, players);

        } catch (StringServiceException ex) {

            logger.error("error while createing edit distance map", ex);

            throw new DAOException("error while createing edit distance map", ex);
        }

        return result;

    }

    @Override
    public List<String> searchPlayersByBestApproxStringMatching(String searchCriteria) throws DAOException {

        List<String> players = this.findPlayers();

        List<String> result = null;

        try {

            result = this.getStringService().findStringsWithMinimumEditDistance(searchCriteria, players);

        } catch (StringServiceException ex) {

            logger.error("error while createing edit distance map", ex);

            throw new DAOException("error while createing edit distance map", ex);
        }

        return result;
    }

    @Override
    public Map<Integer, List<String>> searchCountriesByApproxStringMatching(String searchCriteria) throws DAOException {

        Map<Integer, List<String>> result = new TreeMap<Integer, List<String>>();

        List<String> countries = this.findCountrys();

        try {

            result = this.getStringService().findEditDistanceMap(searchCriteria, countries);

        } catch (StringServiceException ex) {

            logger.error("error while createing edit distance map", ex);

            throw new DAOException("error while createing edit distance map", ex);
        }

        return result;

    }

    @Override
    public List<String> searchCountriesByBestApproxStringMatching(String searchCriteria) throws DAOException {

        List<String> countries = this.findCountrys();

        List<String> result = null;

        try {

            result = this.getStringService().findStringsWithMinimumEditDistance(searchCriteria, countries);

        } catch (StringServiceException ex) {

            logger.error("error while createing edit distance map", ex);

            throw new DAOException("error while createing edit distance map", ex);
        }

        return result;
    }
    public ICricDataDAO getCricWebSiteDAO() {
        return AppContainer.getInstance().getBean("cricWebSiteDAO", ICricDataDAO.class);
    }
    @Override
    public DataPattern getCountryPattern() throws DAOException{
        return this.getCricWebSiteDAO().getCountryPattern();
    }

    @Override
    public DataPattern getPlayerPattern() throws DAOException{
        return this.getCricWebSiteDAO().getPlayerPattern();
    }

    
}
