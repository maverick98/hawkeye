/*
 * This file is part of j-hawk
 * CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.
 *
 */
package org.cricket.hawkeye.service.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.cricket.hawkeye.service.url.exception.URLServiceException;

/**
 *
 * @author manoranjan
 */
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
