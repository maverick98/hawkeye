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

import java.util.List;
import org.cricket.hawkeye.dao.PlayerDownloadTask;
import org.cricket.hawkeye.service.download.exception.DownloadServiceException;

/**
 *
 * @author manoranjan
 */
public interface ICricDataDownloadService {

    /**
     * This downloads all the data that are required for hawk-eye to be useful.
     * This make take hours depending upon the load on target website's  and network traffic.
     * @return
     * @throws DownloadServiceException
     */
    public boolean downloadAll() throws DownloadServiceException;

    /**
     * This downloads the country.html only.
     * This first invokes deleteCountriresHTML() to delete the country.html from the
     * current directory to ensure the data is consistent with target website.
     * @return
     * @throws DownloadServiceException
     */
    public String downloadCountriesHTML() throws DownloadServiceException;

    /**
     * This should be invoked after call to downloadCountriesHTML() has been made.
     * It assumes country.html is already downloaded and present in the current directory
     * It parses the same file to find the http link for the passed country name and returns the html of it.
     * @param countryName
     * @return
     * @throws DownloadServiceException
     */
    public String downloadCountryHTML(String countryName) throws DownloadServiceException;

    /**
     * This downloads the data of all the players who play for this country
     * @param countryName
     * @return
     * @throws DownloadServiceException
     */
    public boolean downloadCountry(String countryName) throws DownloadServiceException;
    
    
    public List<PlayerDownloadTask> downloadPlayerURLs(String countryName) throws DownloadServiceException;

    /**
     * This downloads the data of a player of this country.
     * @param countryName
     * @param playerName
     * @return
     * @throws DownloadServiceException
     */
    public String downloadPlayer(String countryName,String playerName) throws DownloadServiceException;
    


}
