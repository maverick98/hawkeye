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

import java.util.List;
import org.cricket.hawkeye.service.download.exception.DownloadControlServiceException;

/**
 *
 * @author manoranjan
 */
public interface ICricDataDownloadControlService {

    /**
     * This deletes the country.html present in the current directory.
     * @return
     * @throws DownloadControlServiceException
     */
    public boolean deleteCountriesHTML() throws DownloadControlServiceException;

    public boolean createCountriesDir() throws DownloadControlServiceException;

    public boolean touchDownloadControlFile(String path) throws DownloadControlServiceException;

    public boolean updateDownloadControlFile(String path,List<String> pendingList,List<String> finishedList) throws DownloadControlServiceException;

    public boolean addFinishedItemsToDownloadControlFile(String path,List<String> finishedItems) throws DownloadControlServiceException;

    public boolean addFinishedItemToDownloadControlFile(String path,String finishedItem) throws DownloadControlServiceException;

    public boolean addPendingItemsToDownloadControlFile(String path,List<String> pendingItems) throws DownloadControlServiceException;

    public boolean addPendingItemToDownloadControlFile(String path,String pendingItem) throws DownloadControlServiceException;

    public boolean removePendingItemToDownloadControlFile(String path,String pendingItem) throws DownloadControlServiceException;

    public boolean removePendingItemsToDownloadControlFile(String path,List<String> pendingItems) throws DownloadControlServiceException;

    public boolean removeFinishedItemsToDownloadControlFile(String path,List<String> removeItems) throws DownloadControlServiceException;

    public boolean removeFinishedItemToDownloadControlFile(String path,String removeItem) throws DownloadControlServiceException;

    public boolean shouldDownload(String path,String item) throws DownloadControlServiceException;


    public boolean resetDownloadControlFile() throws DownloadControlServiceException;


    public boolean isUpdateAvailable() throws DownloadControlServiceException;


    public List<String> checkForUpdates() throws DownloadControlServiceException;
}
