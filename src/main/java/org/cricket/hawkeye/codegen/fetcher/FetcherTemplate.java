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

import java.util.ArrayList;
import java.util.List;
import org.hawk.codegen.FieldData;
import org.hawk.codegen.FileTemplateJavaBean;
import org.hawk.codegen.IPostProcessor;
import org.hawk.codegen.IntermediateTemplateData;
import org.hawk.util.HawkUtil;

/**
 *
 * @author Manoranjan Sahu
 */
public class FetcherTemplate extends FileTemplateJavaBean implements IPostProcessor {

    @FieldData("#CLAZZ#")
    private String clazz;
    @FieldData("#CLAZZINSTANCE#")
    private String clazzInstance;
    @FieldData("#DATE#")
    private String date;
    @FieldData("#SIZE#")
    private String size;
    @FieldData(value = "#TYPE#", multiple = true, pre = false, post = true)
    private List<String> types = new ArrayList<String>();
    @FieldData(value = "#GETFIELD#", multiple = true, pre = false, post = true)
    private List<String> getFields = new ArrayList<String>();
    @FieldData(value = "#SETFIELD#", multiple = true, pre = false, post = true)
    private List<String> setFields = new ArrayList<String>();
    @FieldData(value = "#FIELDINSTANCE#", multiple = true, pre = false, post = true)
    private List<String> fieldInstance = new ArrayList<String>();
    @FieldData(value = "#FIELD#", multiple = true, pre = false, post = true)
    private List<String> field = new ArrayList<String>();
    @FieldData(value = "#INSIDEFIELD#", multiple = true, pre = false, post = true)
    private List<String> insideField = new ArrayList<String>();
    @FieldData(value = "#INSIDEFIELDINSTANCE#", multiple = true, pre = false, post = true)
    private List<String> insideFieldInstance = new ArrayList<String>();
    @FieldData(value = "#TYPEINSIDEFIELD#", multiple = true, pre = false, post = true)
    private List<String> typeInsideField = new ArrayList<String>();
    @FieldData(value = "#INSIDETYPE#", multiple = true, pre = false, post = true)
    private List<String> insideType = new ArrayList<String>();

    @FieldData("#SHOULD_INCLUDE_MORETHAN_START#")
    private String includeMoreThan;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_MORETHAN_START#")
    private String includeInnerMoreThan;
    @FieldData("#SHOULD_INCLUDE_LESSTHAN_START#")
    private String includeLessThan;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_START#")
    private String includeInnerLessThan;
    @FieldData("#SHOULD_INCLUDE_AFTER_START#")
    private String includeAfter;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_AFTER_START#")
    private String includeInnerAfter;
    @FieldData("#SHOULD_INCLUDE_BEFORE_START#")
    private String includeBefore;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_BEFORE_START#")
    private String includeInnerBefore;
    @FieldData("#SHOULD_INCLUDE_MAX_START#")
    private String includeMax;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_MAX_START#")
    private String includeInnerMax;
    @FieldData("#SHOULD_INCLUDE_MIN_START#")
    private String includeMin;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_MIN_START#")
    private String includeInnerMin;
    @FieldData("#SHOULD_INCLUDE_TOP_START#")
    private String includeTop;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_TOP_START#")
    private String includeInnerTop;
    @FieldData("#SHOULD_INCLUDE_BOTTOM_START#")
    private String includeBottom;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_BOTTOM_START#")
    private String includeInnerBottom;
    @FieldData("#SHOULD_INCLUDE_AVG_START#")
    private String includeAvg;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_AVG_START#")
    private String includeInnerAvg;
    @FieldData("#SHOULD_INCLUDE_SUM_START#")
    private String includeSum;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_SUM_START#")
    private String includeInnerSum;
    @FieldData("#SHOULD_INCLUDE_VARIANCE_START#")
    private String includeVariance;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_VARIANCE_START#")
    private String includeInnerVariance;
    @FieldData("#SHOULD_INCLUDE_STDDEV_START#")
    private String includeStdDev;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_STDDEV_START#")
    private String includeInnerStdDev;
    @FieldData("#SHOULD_INCLUDE_SORT_START#")
    private String includeSort;
    @FieldData("#SHOULD_INCLUDE_INNER_FIELD_SORT_START#")
    private String includeInnerSort;
    @FieldData("#OWNER_OF_LIKE_IMPLEMENTATION_START#")
    private String ownerLikeImpl;

    public String getOwnerLikeImpl() {
        return ownerLikeImpl;
    }

    public void setOwnerLikeImpl(String ownerLikeImpl) {
        this.ownerLikeImpl = ownerLikeImpl;
    }

    public String getIncludeMoreThan() {
        return includeMoreThan;
    }

    public void setIncludeMoreThan(String includeMoreThan) {
        this.includeMoreThan = includeMoreThan;
    }

    public String getIncludeInnerMoreThan() {
        return includeInnerMoreThan;
    }

    public void setIncludeInnerMoreThan(String includeInnerMoreThan) {
        this.includeInnerMoreThan = includeInnerMoreThan;
    }

    public String getIncludeLessThan() {
        return includeLessThan;
    }

    public void setIncludeLessThan(String includeLessThan) {
        this.includeLessThan = includeLessThan;
    }

    public String getIncludeInnerLessThan() {
        return includeInnerLessThan;
    }

    public void setIncludeInnerLessThan(String includeInnerLessThan) {
        this.includeInnerLessThan = includeInnerLessThan;
    }

    public String getIncludeAfter() {
        return includeAfter;
    }

    public void setIncludeAfter(String includeAfter) {
        this.includeAfter = includeAfter;
    }

    public String getIncludeInnerAfter() {
        return includeInnerAfter;
    }

    public void setIncludeInnerAfter(String includeInnerAfter) {
        this.includeInnerAfter = includeInnerAfter;
    }

    public String getIncludeBefore() {
        return includeBefore;
    }

    public void setIncludeBefore(String includeBefore) {
        this.includeBefore = includeBefore;
    }

    public String getIncludeInnerBefore() {
        return includeInnerBefore;
    }

    public void setIncludeInnerBefore(String includeInnerBefore) {
        this.includeInnerBefore = includeInnerBefore;
    }

    public String getIncludeMax() {
        return includeMax;
    }

    public void setIncludeMax(String includeMax) {
        this.includeMax = includeMax;
    }

    public String getIncludeInnerMax() {
        return includeInnerMax;
    }

    public void setIncludeInnerMax(String includeInnerMax) {
        this.includeInnerMax = includeInnerMax;
    }

    public String getIncludeMin() {
        return includeMin;
    }

    public void setIncludeMin(String includeMin) {
        this.includeMin = includeMin;
    }

    public String getIncludeInnerMin() {
        return includeInnerMin;
    }

    public void setIncludeInnerMin(String includeInnerMin) {
        this.includeInnerMin = includeInnerMin;
    }

    public String getIncludeTop() {
        return includeTop;
    }

    public void setIncludeTop(String includeTop) {
        this.includeTop = includeTop;
    }

    public String getIncludeInnerTop() {
        return includeInnerTop;
    }

    public void setIncludeInnerTop(String includeInnerTop) {
        this.includeInnerTop = includeInnerTop;
    }

    public String getIncludeBottom() {
        return includeBottom;
    }

    public void setIncludeBottom(String includeBottom) {
        this.includeBottom = includeBottom;
    }

    public String getIncludeInnerBottom() {
        return includeInnerBottom;
    }

    public void setIncludeInnerBottom(String includeInnerBottom) {
        this.includeInnerBottom = includeInnerBottom;
    }

    public String getIncludeAvg() {
        return includeAvg;
    }

    public void setIncludeAvg(String includeAvg) {
        this.includeAvg = includeAvg;
    }

    public String getIncludeInnerAvg() {
        return includeInnerAvg;
    }

    public void setIncludeInnerAvg(String includeInnerAvg) {
        this.includeInnerAvg = includeInnerAvg;
    }

    public String getIncludeSum() {
        return includeSum;
    }

    public void setIncludeSum(String includeSum) {
        this.includeSum = includeSum;
    }

    public String getIncludeInnerSum() {
        return includeInnerSum;
    }

    public void setIncludeInnerSum(String includeInnerSum) {
        this.includeInnerSum = includeInnerSum;
    }

    public String getIncludeVariance() {
        return includeVariance;
    }

    public void setIncludeVariance(String includeVariance) {
        this.includeVariance = includeVariance;
    }

    public String getIncludeInnerVariance() {
        return includeInnerVariance;
    }

    public void setIncludeInnerVariance(String includeInnerVariance) {
        this.includeInnerVariance = includeInnerVariance;
    }

    public String getIncludeStdDev() {
        return includeStdDev;
    }

    public void setIncludeStdDev(String includeStdDev) {
        this.includeStdDev = includeStdDev;
    }

    public String getIncludeInnerStdDev() {
        return includeInnerStdDev;
    }

    public void setIncludeInnerStdDev(String includeInnerStdDev) {
        this.includeInnerStdDev = includeInnerStdDev;
    }

    public String getIncludeSort() {
        return includeSort;
    }

    public void setIncludeSort(String includeSort) {
        this.includeSort = includeSort;
    }

    public String getIncludeInnerSort() {
        return includeInnerSort;
    }

    public void setIncludeInnerSort(String includeInnerSort) {
        this.includeInnerSort = includeInnerSort;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
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

    public List<String> getFieldInstance() {
        return fieldInstance;
    }

    public void setFieldInstance(List<String> fieldInstance) {
        this.fieldInstance = fieldInstance;
    }

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public List<String> getInsideField() {
        return insideField;
    }

    public void setInsideField(List<String> insideField) {
        this.insideField = insideField;
    }

    public List<String> getInsideFieldInstance() {
        return insideFieldInstance;
    }

    public void setInsideFieldInstance(List<String> insideFieldInstance) {
        this.insideFieldInstance = insideFieldInstance;
    }

    public List<String> getTypeInsideField() {
        return typeInsideField;
    }

    public void setTypeInsideField(List<String> typeInsideField) {
        this.typeInsideField = typeInsideField;
    }

    public List<String> getInsideType() {
        return insideType;
    }

    public void setInsideType(List<String> insideType) {
        this.insideType = insideType;
    }

    @Override
    public boolean processMultipleDataPost(IntermediateTemplateData intermediateTemplateData) {

        String input = intermediateTemplateData.getTemplateData();

        String startLineRegEx = this.getStartEndRegExInternal(START, intermediateTemplateData.getIndex(), true);
        String endLineRegEx = this.getStartEndRegExInternal(END, intermediateTemplateData.getIndex(), false);

        String result = HawkUtil.replace(input, startLineRegEx, endLineRegEx, intermediateTemplateData.getKey(), intermediateTemplateData.getValue());
        intermediateTemplateData.setTemplateData(result);

        return true;

    }

    @Override
    public boolean handleMultipleDataPostProcessFinished(IntermediateTemplateData intermediateTemplateData) {
        String templateData = intermediateTemplateData.getTemplateData();
        for (int i = 0; i <= intermediateTemplateData.getIndex(); i++) {
            String startLineRegEx = this.getStartEndRegExInternal(START, i, true);
            String endLineRegEx = this.getStartEndRegExInternal(END, i, false);

            templateData = templateData.replaceAll(startLineRegEx, "");
            templateData = templateData.replaceAll(endLineRegEx, "");
        }
        intermediateTemplateData.setTemplateData(templateData);
        return true;
    }

    private String getStartEndRegExInternal(String input, int i, boolean isStart) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(i);
        sb.append(isStart ? START : END);
        return sb.toString();
    }

    @Override
    public boolean handlePostProcessFinished(IntermediateTemplateData intermediateTemplateData) {
        return true;
    }

    @Override
    public boolean processSingleDataPost(IntermediateTemplateData intermediateTemplateData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private static final String START = "START";
    private static final String END = "END";
}
