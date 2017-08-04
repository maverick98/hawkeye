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
package org.cricket.hawkeye.service.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.cricket.hawkeye.service.url.exception.URLServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author manoranjan
 */
//@Service("urlService")
//@Qualifier("default")
public class URLServiceImpl implements IURLService {

    @Override
    public String fetch(String urlStr) throws URLServiceException {
        
        StringBuilder  sb = new StringBuilder();
        boolean done = false;
        while (!done) {
            BufferedReader in = null;
           

            try {
                URL url = new URL(urlStr);
                URLConnection cricinfoPlayerConn = url.openConnection();
                
                in = new BufferedReader(new InputStreamReader(cricinfoPlayerConn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                done = true;

            } catch (Throwable th) {
                
                done =false;
                System.out.println("unable to fetch from the url {" + urlStr + "}"+th);

                System.out.println("fetching data from  {"+urlStr+"} one more time...");
                throw new URLServiceException(th);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    throw new URLServiceException("caught io exp while closing bufferreader for the url {" + urlStr + "}", ex);
                }
            }
        }
       
        return sb.toString();
    }
}
