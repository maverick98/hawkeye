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
package org.cricket.hawkeye.codegen.entity;

import java.util.ArrayList;
import java.util.List;
import org.hawk.codegen.FieldData;
import org.hawk.codegen.FileTemplateJavaBean;

/**
 *
 * @author Manoranjan Sahu
 */
public class EntityTemplate extends FileTemplateJavaBean {

    @FieldData("#CLAZZ#")
    private String clazz;
    @FieldData("#DATE#")
    private String date;
    @FieldData("#ENTITY#")
    private String entity;
    @FieldData("#SIZE#")
    private String size;
    @FieldData(value = "#TYPE#", multiple = true, pre = false, post = true)
    private List<String> types = new ArrayList<String>();
    @FieldData(value = "#GETFIELD#", multiple = true, pre = false, post = true)
    private List<String> getFields = new ArrayList<String>();
    @FieldData(value = "#SETFIELD#", multiple = true, pre = false, post = true)
    private List<String> setFields = new ArrayList<String>();

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

    public List<String> getGetFields() {
        return getFields;
    }

    public void setGetFields(List<String> getFields) {
        this.getFields = getFields;
    }

    public List<String> getSetFields() {
        return setFields;
    }

    public void setSetFields(List<String> setFields) {
        this.setFields = setFields;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
