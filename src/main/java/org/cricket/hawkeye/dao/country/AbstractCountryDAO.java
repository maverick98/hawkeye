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
package org.cricket.hawkeye.dao.country;

import org.cricket.hawkeye.dao.exception.DAOException;
import java.util.regex.Matcher;
import org.cricket.hawkeye.dao.DataPattern;

/**
 *
 * @author manoranjan
 */
public abstract class AbstractCountryDAO {

    private DataPattern countryPattern;

    public DataPattern getCountryPattern() {
        return countryPattern;
    }

    public void setCountryPattern(DataPattern countryPattern) {
        this.countryPattern = countryPattern;
    }
    
    public AbstractCountryDAO(){
        
    }
    public AbstractCountryDAO(DataPattern countryPattern){
        this.countryPattern = countryPattern;
    }
    
    public boolean find(String countriesHTML) throws DAOException{

        if(countriesHTML == null || countriesHTML.isEmpty()){
            
            return false;
        }
        
        boolean found = false;

        Matcher countryMatcher = this.getCountryPattern().getPattern().matcher(countriesHTML);

        while (countryMatcher.find()) {

            String countryName = countryMatcher.group(2);

            this.countryCallback(countryName);

            found = true;
        }

        return found;
    }

    public abstract void countryCallback(String countryName) throws DAOException;
}
