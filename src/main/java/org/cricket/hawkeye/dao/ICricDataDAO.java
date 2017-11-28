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

import java.util.List;
import java.util.Map;
import org.cricket.hawkeye.dao.exception.DAOException;

/**
 *
 * @author manoranjan
 */
public interface ICricDataDAO {

    public String findCountrysDir() throws DAOException;

    public String findCountrysPath() throws DAOException;

    public String findCountrysHTML() throws DAOException;


    public String findCountryDir(String countryName) throws DAOException;

    public String findCountryPath(String countryName) throws DAOException;

    public String findCountryHTML(String countrName) throws DAOException;

    public String findPlayerPath(String countryName,String playerName) throws DAOException;

    public String findPlayerHTML(String countryName,String playerName) throws DAOException;

    public List<String> findCountrys() throws DAOException;

    public List<String> findPlayers() throws DAOException;

    public List<String> findPlayers(String countryName) throws DAOException;

    public Map<Integer,List<String>> searchPlayersByApproxStringMatching(String searchCriteria) throws DAOException;

    public List<String> searchPlayersByBestApproxStringMatching(String searchCriteria) throws DAOException;


    public Map<Integer,List<String>> searchCountriesByApproxStringMatching(String searchCriteria) throws DAOException;

    public List<String> searchCountriesByBestApproxStringMatching(String searchCriteria) throws DAOException;
    
    public DataPattern getCountryPattern() throws DAOException;
    
    public DataPattern getPlayerPattern() throws DAOException;
    
   
    
    
}
