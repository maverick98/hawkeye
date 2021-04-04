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
package org.cricket.hawkeye.service.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cricket.hawkeye.service.ICommonService;
import org.cricket.hawkeye.service.file.exception.FileServiceException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author manoranjan
 */
//@Service("fileService")
//@Qualifier("default")
public class FileServiceImpl implements IFileService {

    private static final Logger logger = Logger.getLogger(FileServiceImpl.class);

    @Autowired(required = true)
    //@Qualifier("default")
    ICommonService commonService ;

    public ICommonService getCommonService() {
        return commonService;
    }

   

    

    @Override
    public List<File> findFile(String mainDir, final String fileName) throws FileServiceException {

        if (this.getCommonService().isNullOrEmpty(mainDir) || this.getCommonService().isNullOrEmpty(fileName)) {

            throw new FileServiceException("illegal args");

        }
        final List<File> result = new ArrayList<File>();

        try {
            new FileTraversal() {

                @Override
                public void processFile(final File f) {

                    if (f.getName().endsWith(fileName)) {

                        result.add(f);
                    }


                }

                @Override
                public void processDirectory(File d) {
                    return;
                }
            }.search(new File(mainDir));

        } catch (IOException ex) {

           logger.error("error occurred",ex);

            throw new FileServiceException(ex);
        }

        return result;


    }

    private abstract class FileTraversal {

        public final void search(final File f) throws IOException {

            if (f.isDirectory()) {

                processDirectory(f);

                final File[] childs = f.listFiles();

                for (File child : childs) {

                    search(child);

                }

                return;

            }

            processFile(f);

        }

        public abstract void processDirectory(final File d);

        public abstract void processFile(final File f);
    }

    @Override
    public String readFile(String fileName) throws FileServiceException {

        if(fileName == null || fileName.isEmpty()){
            throw new FileServiceException("invalid fileName");
        }

        BufferedReader bfr = null;
        StringBuilder sb = new StringBuilder();
        try {
            bfr = new BufferedReader(new FileReader(new File(fileName)));
            sb.append(bfr.readLine());

        } catch (Throwable th) {
            throw new FileServiceException("error while writing to file {" + fileName + "}", th);
        } finally {
            try {
                if(bfr != null){
                    bfr.close();
                }
            } catch (IOException ex) {
                throw new FileServiceException("caught io exp while closing bufferreader for the file {" + fileName + "}", ex);
            }
        }
        return sb.toString();
    }

    @Override
    public boolean writeFile(String fileName, String fileData) throws FileServiceException {

        boolean status = false;

        BufferedWriter out = null;

        try {

            if (fileName == null || fileName.isEmpty()) {

                throw new FileServiceException("unable to read file {" + fileName + "}");

            }

            File file = new File(fileName);

            if (!file.exists()) {

                file.createNewFile();

            }

            out = new BufferedWriter(new FileWriter(file));

            out.write(fileData);

        } catch (Throwable th) {

            throw new FileServiceException("error while writing to file {" + fileName + "}", th);

        } finally {

            try {

                if (out != null) {

                    out.close();

                    status = true;

                }

            } catch (IOException ex) {

                throw new FileServiceException("caught io exp while closing bufferwriter for the file {" + fileName + "}", ex);

            }

        }

        return status;

    }
}
