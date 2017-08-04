/*
 * This file is part of j-hawk
 * Copyright (C) 2012-2013 Manoranjan Sahu, All Rights Reserved.
 *
 * This software is provided under the GNU GPL Version 2. In particular,
 *
 * 1) If you modify a source file, make a comment in it containing your name and the date.
 * 2) If you distribute a modified version, you must do it under the GPL 2.
 * 3) Developers are encouraged but not required to notify the j-hawk maintainers at abeautifulmind98@gmail.com
 * when they make a useful addition. It would be nice if significant contributions could be merged into the main distribution.
 *
 * A full copy of the license can be found in gpl.txt or online at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * END_HEADER
 */
package org.cricket.hawkeye.codegen.fetcher;

import java.lang.reflect.Field;
import org.common.di.ScanMe;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;


/**
 *
 * @author Manoranjan Sahu
 */
@ScanMe(true)
public class CountMethodImpl extends AbstractFetcherMethodImpl {

    @Override
    public boolean fieldImplements(Field field) {
        HQLGenerate hqlg = field.getAnnotation(HQLGenerate.class);
        boolean isImplemented = hqlg.aggregate().count();
        return isImplemented;
    }

   
    
}
