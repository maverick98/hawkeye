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


package org.cricket.hawkeye.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author manoranjan
 */
public interface ICommonService {

    public  List<String> listify(String input, String separator);

    public  String stringify(List<String> input, String separator);

    public  boolean saveProperties(Properties props, String file);

    public  Properties loadProperties(String file);

    public  Properties loadProperties(File file);

    public  boolean isNullOrEmpty(String input);

    public String getTabPath();

}
