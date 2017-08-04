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
package org.cricket.hawkeye.dao.player;

import java.util.regex.Matcher;
import org.common.di.AppContainer;
import org.cricket.hawkeye.dao.DataPattern;
import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.dao.exception.DAOException;


/**
 *
 * @author manoranjan
 */
public abstract class AbstractPlayerDAO {

      public ICricDataDAO getCricWebSiteDAO() {
        return AppContainer.getInstance().getBean("cricWebSiteDAO", ICricDataDAO.class);
    }
   

    
    public DataPattern getPlayerPattern() throws DAOException{
        return this.getCricWebSiteDAO().getPlayerPattern();
    }
  
    public boolean find(String countryDetails) throws DAOException {

        if (countryDetails == null || countryDetails.isEmpty()) {

            return false;
        }

        boolean found = false;


        Matcher playerMatcher = this.getPlayerPattern().getPattern().matcher(countryDetails);

        while (playerMatcher.find()) {

            String playerName = playerMatcher.group(2);

            this.playerCallback(playerName);

            found = true;

        }

        return found;
    }

    public abstract void playerCallback(String playerName) throws DAOException;
}
