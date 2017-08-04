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
package org.cricket.hawkeye.string;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.cricket.hawkeye.exception.HawkEyeException;
import org.springframework.stereotype.Service;
import org.cricket.hawkeye.string.exception.StringServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author manoranjan
 */
//@Service("stringService")
//@Qualifier("default")
public class StringServiceImpl implements IStringService {

    private static final Logger logger = Logger.getLogger(StringServiceImpl.class);

    @Autowired(required = true)
    //@Qualifier("default")
    private IEditDistanceAlgorithm editDistanceAlgorithm;

    public IEditDistanceAlgorithm getEditDistanceAlgorithm() {
        return editDistanceAlgorithm;
    }

    public StringServiceImpl(){
        
    }
    public StringServiceImpl(IEditDistanceAlgorithm editDistanceAlgorithm){

            this.editDistanceAlgorithm = editDistanceAlgorithm;
    }

    @Override
    public boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

  

    
    @Override
    public Map<Integer,List<String>> findEditDistanceMap(String searchCriteria , List<String> all) throws StringServiceException{

        if(this.isNullOrEmpty(searchCriteria) || all == null || all.isEmpty()){

            throw new StringServiceException("illegal args");
        }

        Map<Integer,List<String>> result = null;

        try {

            result = this.getEditDistanceAlgorithm().findEditDistanceMap(searchCriteria, all);

        } catch (HawkEyeException ex) {

            throw new StringServiceException(ex);
        }

        return result;
    }

    @Override
    public List<String> findStringsWithMinimumEditDistance(String searchCriteria, List<String> all) throws StringServiceException {


        if(this.isNullOrEmpty(searchCriteria) || all == null || all.isEmpty()){

            throw new StringServiceException("illegal args");
        }

        List<String> result = null;

        try {

            result = this.getEditDistanceAlgorithm().findStringsWithMinimumEditDistance(searchCriteria, all);

        } catch (HawkEyeException ex) {

            throw new StringServiceException(ex);
        }

        return result;
    }



    @Override
    public int calculateEditDistance(String str1, String str2) throws StringServiceException {

        int distance = -1;
        
        try {
            
            distance =  this.getEditDistanceAlgorithm().calculateEditDistance(str1, str2);

        } catch (HawkEyeException ex) {

            throw new StringServiceException(ex);
        }

        return distance;
    }




    
}
