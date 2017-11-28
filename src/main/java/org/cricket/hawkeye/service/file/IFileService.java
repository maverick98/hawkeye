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

import java.io.File;
import java.util.List;
import org.cricket.hawkeye.service.file.exception.FileServiceException;

/**
 *
 * @author manoranjan
 */
public interface IFileService {

     public  List<File> findFile(String mainDir, final String fileName) throws FileServiceException;

     public  String readFile(String fileName) throws FileServiceException;

     public  boolean writeFile(String fileName, String fileData) throws FileServiceException;
     
}
