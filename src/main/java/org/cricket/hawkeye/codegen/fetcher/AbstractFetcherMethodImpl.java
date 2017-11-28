/*
 * This file is part of j-hawk
 * Copyright (C) BigBang<->BigCrunch Manoranjan Sahu, All Rights Reserved.
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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.commons.reflection.ClazzUtil;
import org.commons.string.StringUtil;
import org.cricket.hawkeye.codegen.SourceVO;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.values.exception.HawkEyeCodeTemplateException;
import org.hawk.logger.HawkLogger;

/**
 *
 * @author Manoranjan Sahu
 */
public abstract class AbstractFetcherMethodImpl implements IFetcherMethodImpl {

    private static final HawkLogger logger = HawkLogger.getLogger(AbstractFetcherMethodImpl.class.getName());
    protected static final String ENABLED = "1";
    protected static final String DISABLED = "0";
    protected Field field;
    protected HQLGenerate hqlGenerated;
    protected FetcherTemplate fetcherTemplate;
    protected boolean implemented;
    protected SourceVO sourceVO;
    
    @Override
    public  boolean isInsideType(){
        
        return false;
    }

    @Override
    public SourceVO getSourceVO() {
        return sourceVO;
    }

    public void setSourceVO(SourceVO sourceVO) {
        this.sourceVO = sourceVO;
    }

    public AbstractFetcherMethodImpl(boolean implemented) {
        this.implemented = implemented;
    }

    public AbstractFetcherMethodImpl() {
    }

    public HQLGenerate getHqlGenerated() {
        return hqlGenerated;
    }

    public void setHqlGenerated(HQLGenerate hqlGenerated) {
        this.hqlGenerated = hqlGenerated;
    }

    public boolean isImplemented() {
        return implemented;
    }

    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
    }

    @Override
    public FetcherTemplate getFetcherTemplate() {
        return fetcherTemplate;
    }

    public void setFetcherTemplate(FetcherTemplate fetcherTemplate) {
        this.fetcherTemplate = fetcherTemplate;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    protected String toggle(String str) {
        String part = str.substring(1);
        String tmp = String.valueOf(str.charAt(0));
        if (tmp.equals(tmp.toLowerCase())) {
            tmp = tmp.toUpperCase();
        } else {
            tmp = tmp.toLowerCase();
        }
        return tmp + part;
    }

    @Override
    public IFetcherMethodImpl create(SourceVO sourceVO) throws HawkEyeCodeTemplateException {

        this.setSourceVO(sourceVO);
        FetcherTemplate fetcherTempl = new FetcherTemplate();
        this.setFetcherTemplate(fetcherTempl);
        fetcherTempl.setDate(new Date().toString());
        this.populate(sourceVO);
        this.setIOFiles();
        return this;
    }

    @Override
    public boolean populate(SourceVO sourceVO) throws HawkEyeCodeTemplateException {
        FetcherTemplate fetcherTempl = this.getFetcherTemplate();

        Class clazz = sourceVO.getClass();
        int i = 0;
        for (Field declField : clazz.getDeclaredFields()) {

            if (declField.isAnnotationPresent(HQLGenerate.class)) {
                boolean isImplemented = this.fieldImplements(declField);
                if (isImplemented) {
                    this.setFieldData(declField);
                    i++;
                }
            }
        }
        fetcherTempl.setSize(String.valueOf(i));
        return true;
    }

    public abstract boolean fieldImplements(Field field);

    private boolean setFieldData(Field field) throws HawkEyeCodeTemplateException {
        FetcherTemplate fetcherTempl = this.getFetcherTemplate();
        Class clazz = this.getSourceVO().getClass();
        fetcherTempl.setClazz(clazz.getSimpleName());
        fetcherTempl.setClazzInstance(StringUtil.toggle(fetcherTempl.getClazz()));

        String fIELD = field.getName();

        String FIELD = StringUtil.toggle(fIELD);
        String GETFIELD = "get" + FIELD;

        fetcherTempl.getFieldInstance().add(fIELD);
        fetcherTempl.getField().add(FIELD);
        fetcherTempl.getGetFields().add(GETFIELD);
        String insideFieldName = field.getAnnotation(HQLGenerate.class).field();
        boolean isInsideFieldDefined = this.isInsideType();
        if (isInsideFieldDefined) {
            String toggledInsideFieldName = toggle(insideFieldName);
            fetcherTempl.getInsideField().add(toggledInsideFieldName);

            fetcherTempl.getTypeInsideField().add(FIELD + toggledInsideFieldName);

            fetcherTempl.getInsideFieldInstance().add(insideFieldName);
        }

        Object obj = null;
        try {

            obj = ClazzUtil.invoke(this.getSourceVO(), GETFIELD, new Class[]{}, new Object[]{});
        } catch (Exception ex) {
            throw new HawkEyeCodeTemplateException(ex);
        }
        if (obj != null) {
            String TYPE = obj.getClass().getName();
            fetcherTempl.getTypes().add(TYPE);
            if (isInsideFieldDefined) {
                Class insideFieldClazz = null;
                try {
                    insideFieldClazz = obj.getClass().getField(insideFieldName).getType();
                } catch (NoSuchFieldException ex) {
                    logger.error(ex);
                } catch (SecurityException ex) {
                    logger.error(ex);
                }
                if (insideFieldClazz != null) {
                    fetcherTempl.getInsideType().add(insideFieldClazz.getName());
                }
            }
        } else {
            throw new HawkEyeCodeTemplateException("error in getting TYPE");
        }
        return true;


    }
    
    public boolean setIOFiles(){
        FetcherTemplate fetcherTempl = this.getFetcherTemplate();
        fetcherTempl.setInputFile(this.getDefaultInputFile());
        fetcherTempl.setOutputFile(this.getDefaultOutputFile());
        this.touchFiles(fetcherTempl.getInputFile());
        fetcherTempl.setErrorFile(this.getDefaultErrorFile());
        this.touchFiles(fetcherTempl.getErrorFile());
        return true;
    }
    private boolean touchFiles(String file){
        try {
            FileUtils.touch(new File(file));
        } catch (IOException ex) {
            logger.error("Unable to touch  {"+file+"}",ex);
        }
        return true;
    }
    protected String getDefaultInputFile(){
        StringBuilder inputFile = new StringBuilder();
        inputFile.append("template");
        inputFile.append("\\");
        inputFile.append(this.getClass().getSimpleName());
        inputFile.append(".");
        inputFile.append("template");
        return inputFile.toString();
    }
    protected String getDefaultOutputFile(){
        StringBuilder outputFile = new StringBuilder(this.getSourceVO().getClass().getSimpleName());
        outputFile.append("\\");
        outputFile.append(this.getClass().getSimpleName());
        outputFile.append(".");
        outputFile.append("out");
        return outputFile.toString();
    }
    protected String getDefaultErrorFile(){
        StringBuilder errorFile = new StringBuilder(this.getSourceVO().getClass().getSimpleName());
        errorFile.append("\\");
        errorFile.append(this.getClass().getSimpleName());
        errorFile.append(".");
        errorFile.append("err");
        return errorFile.toString();
    }
}
