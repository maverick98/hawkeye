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
package org.cricket.hawkeye.codegen.table;

import org.hawk.codegen.FieldData;
import org.hawk.codegen.FileTemplateJavaBean;

/**
 *
 * @author Manoranjan Sahu
 */
public class TableTemplate extends FileTemplateJavaBean{

    @FieldData("#CLAZZ#")
    private String clazz;
    @FieldData("#DATE#")
    private String date;
    @FieldData("#CLAZZINSTANCE#")
    private String clazzInstance;

    public String getClazzInstance() {
        return clazzInstance;
    }

    public void setClazzInstance(String clazzInstance) {
        this.clazzInstance = clazzInstance;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
