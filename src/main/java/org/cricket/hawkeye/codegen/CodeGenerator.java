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
package org.cricket.hawkeye.codegen;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.commons.file.FileUtil;
import org.cricket.hawkeye.codegen.annotation.Entity;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import static org.cricket.hawkeye.codegen.HawkEyeCopyRightInfo.*;


/**
 * Refactor me or use ANTLR/StringTemplate
 * @author manoranjan
 */
public class CodeGenerator {

    private static final Logger logger = Logger.getLogger(CodeGenerator.class);
    private static final Pattern CLAZZPATTERN = Pattern.compile("[\\w|\\.]*\\.(\\w+)");
    private static final String regex = "//\\s*for\\s*\\(\\s*(\\w+)\\s*:\\s*([@|\\w]+)\\s*\\)\\s*\\n\\s*//\\s*\\@START";
    private static final Pattern FORLOOPPATTERN = Pattern.compile(regex);

    private static boolean parseClazz(Map<String, String> map, String clazzStr) throws Exception {


        Matcher m = CLAZZPATTERN.matcher(clazzStr);

        if (m.matches()) {

            String CLAZZ = m.group(1);
            String cLAZZ = toggle(CLAZZ);
            map.put("@CLAZZ", CLAZZ);
            map.put("@cLAZZ", cLAZZ);
        }
        return true;
    }

    private static Map<String, String> generateCodeMap(String clazzStr) throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        Date DATE = new Date();
        
        map.put("@DATE", DATE.toString());

        parseClazz(map, clazzStr);


         
        map.put("@SHOULD_INCLUDE_COUNT_START#"  , "");
        map.put("@SHOULD_INCLUDE_COUNT_END#"  , "");
                    

        parseForLoop(map, clazzStr);

        return map;
    }

    public static void generate(String clazzStr) throws Exception {

        Map<String, String> map = generateCodeMap(clazzStr);

        String fetcherJavaFilePath = "./src/main/java/org/cricket/hawkeye/db/" + map.get("@CLAZZ") + "Fetcher.java";
        parseTemplate("Fetcher.template", map, fetcherJavaFilePath);




    }

    private static boolean parseForLoop(Map<String, String> map, String clazzStr) throws Exception {
        Class clazz = Class.forName(clazzStr);
        Object instance = clazz.newInstance();

        boolean ownsLikeImpl = ((Entity)clazz.getAnnotation(Entity.class)).ownLikeImpl();
        int i = 0;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(HQLGenerate.class)) {

                String fIELD = field.getName();
                
                String FIELD = toggle(fIELD);
                String GETFIELD = "get" + FIELD;
                String SETFIELD = "set" + FIELD;
                Method method = clazz.getDeclaredMethod(GETFIELD, new Class[]{});
                Object obj = method.invoke(instance, new Object[]{});
                String TYPE = obj.getClass().getName();
                map.put("@fIELD#" + i, fIELD);
                map.put("@FIELD#" + i, FIELD);
                map.put("@GETFIELD#" + i, GETFIELD);
                map.put("@SETFIELD#" + i, SETFIELD);
                map.put("@TYPE#" + i, TYPE);
                map.put("@ENTITY", clazzStr);
                String insideFieldName = field.getAnnotation(HQLGenerate.class).field();

                boolean isInsideFieldReqd = insideFieldName != null && !insideFieldName.isEmpty();
                if (isInsideFieldReqd) {
                    String toggledInsideFieldName = toggle(insideFieldName);
                    map.put("@INSIDEFIELD#" + i, toggledInsideFieldName);
                    map.put("@TYPEINSIDEFIELD#" + i, FIELD + toggledInsideFieldName);
                    map.put("@iNSIDEFIELD#" + i, insideFieldName);
                    Class insideFieldClazz = obj.getClass().getField(insideFieldName).getType();
                    map.put("@INSIDETYPE#" + i, insideFieldClazz.getName());
                }

                if (field.getAnnotation(HQLGenerate.class).comparision().equal()) {
                    map.put("@SHOULD_INCLUDE_EQUAL_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_EQUAL_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_EQUAL_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_EQUAL_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_EQUAL_END#" + i, " */");
                }

                if (field.getAnnotation(HQLGenerate.class).comparision().moreThan()) {
                    map.put("@SHOULD_INCLUDE_MORETHAN_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_MORETHAN_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_MORETHAN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_MORETHAN_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MORETHAN_END#" + i, " */");
                }

                if (field.getAnnotation(HQLGenerate.class).comparision().lessThan()) {
                    map.put("@SHOULD_INCLUDE_LESSTHAN_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_LESSTHAN_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_LESSTHAN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_LESSTHAN_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_LESSTHAN_END#" + i, " */");
                }

                if (field.getAnnotation(HQLGenerate.class).comparision().after()) {
                    map.put("@SHOULD_INCLUDE_AFTER_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_AFTER_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_AFTER_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_AFTER_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_AFTER_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).comparision().before()) {
                    map.put("@SHOULD_INCLUDE_BEFORE_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_BEFORE_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_BEFORE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_BEFORE_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_BEFORE_END#" + i, " */");
                }

                if (field.getAnnotation(HQLGenerate.class).aggregate().max()) {
                    map.put("@SHOULD_INCLUDE_MAX_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_MAX_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_MAX_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_MAX_END#" + i,  "*/");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MAX_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().min()) {
                    map.put("@SHOULD_INCLUDE_MIN_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_MIN_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_MIN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_MIN_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_MIN_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().top()) {
                    map.put("@SHOULD_INCLUDE_TOP_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_TOP_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_TOP_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_TOP_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_TOP_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().bottom()) {
                    map.put("@SHOULD_INCLUDE_BOTTOM_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_BOTTOM_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_BOTTOM_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_BOTTOM_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_BOTTOM_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().avg()) {
                    map.put("@SHOULD_INCLUDE_AVG_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_AVG_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_AVG_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_AVG_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_AVG_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().sum()) {
                    map.put("@SHOULD_INCLUDE_SUM_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_SUM_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_SUM_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_SUM_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_SUM_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().variance()) {
                    map.put("@SHOULD_INCLUDE_VARIANCE_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_VARIANCE_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_VARIANCE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_VARIANCE_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_VARIANCE_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).aggregate().stddev()) {
                    map.put("@SHOULD_INCLUDE_STDDEV_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_STDDEV_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_STDDEV_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_STDDEV_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_STDDEV_END#" + i, " */");
                }
                if (field.getAnnotation(HQLGenerate.class).comparision().like()) {
                    map.put("@SHOULD_INCLUDE_LIKE_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_LIKE_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_END#" + i, " */");
                    }
                    if(ownsLikeImpl){
                        map.put("@NOT_OWNER_OF_LIKE_IMPLEMENTATION_START#"+i,"/*");
                        map.put("@NOT_OWNER_OF_LIKE_IMPLEMENTATION_END#"+i,"*/");
                        map.put("@OWNER_OF_LIKE_IMPLEMENTATION_START#"+i,"");
                        map.put("@OWNER_OF_LIKE_IMPLEMENTATION_END#"+i,"");
                    }else{
                        map.put("@NOT_OWNER_OF_LIKE_IMPLEMENTATION_START#"+i,"");
                        map.put("@NOT_OWNER_OF_LIKE_IMPLEMENTATION_END#"+i,"");
                        map.put("@OWNER_OF_LIKE_IMPLEMENTATION_START#"+i,"/*");
                        map.put("@OWNER_OF_LIKE_IMPLEMENTATION_END#"+i,"*/");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_LIKE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_LIKE_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_LIKE_END#" + i, " */");
                }


                if (field.getAnnotation(HQLGenerate.class).comparision().sort()) {
                    map.put("@SHOULD_INCLUDE_SORT_START#" + i, "");
                    map.put("@SHOULD_INCLUDE_SORT_END#" + i, "");
                    if (isInsideFieldReqd) {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_START#" + i, "");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_END#" + i, "");
                    } else {
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_START#" + i, "/* ");
                        map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_END#" + i, " */");
                    }
                } else {
                    map.put("@SHOULD_INCLUDE_SORT_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_SORT_END#" + i, " */");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_START#" + i, "/* ");
                    map.put("@SHOULD_INCLUDE_INNER_FIELD_SORT_END#" + i, " */");
                }
               
                i++;
            }
        }
        map.put("@SIZE", i + "");
        return true;
    }

    private static boolean parseTemplate(String str, Map<String, String> map, String file) throws Exception {

        String template = readTemplate(str);

        Matcher m = FORLOOPPATTERN.matcher(template);

        String pre = "";
        String post = "";
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String tmp = m.group();
            String iStr = m.group(1);
            String sizeKey = m.group(2);

            int x = template.indexOf(tmp) + tmp.length();

            int y = template.indexOf("//@STOP", x);
            pre = template.substring(0, x);
            String method = template.substring(x, y);
            post = template.substring(y + 7);
            int size = Integer.parseInt(map.get(sizeKey));

            sb.append(pre);

            for (int i = 0; i < size; i++) {
                String test = method.replaceAll("#" + iStr, "#" + i);


                sb.append(test);
            }

            sb.append(post);

            template = sb.toString();
            sb = new StringBuilder();


        }

        template = createJavaCode(template, map, file);

        return true;


    }

    private static String createJavaCode(String template, Map<String, String> map, String filePath) throws Exception {

        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.contains("#")) {
                key = key + " ";

            }
            //System.out.println("replacing key {"+key+"} with value {"+value+"}");

            template = template.replaceAll(key, value);
        }
        template = removeComment(template);
        String copyRight = COPYRIGHT.replaceAll("@DATE", map.get("@DATE"));
        FileUtil.writeFile(filePath, copyRight + template);
        return template;
    }

    private static String readTemplate(String templateFile) throws Exception {
        return FileUtil.readFile(templateFile);
    }

    public static String toggle(String str) {
        String part = str.substring(1);
        String tmp = str.charAt(0) + "";
        if (tmp.equals(tmp.toLowerCase())) {
            tmp = tmp.toUpperCase();
        } else {
            tmp = tmp.toLowerCase();
        }
        return tmp + part;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("generate");
        String clazzStr = "org.cricket.hawkeye.values.player.Player";
        CodeGenerator.generate(clazzStr);

        clazzStr = "org.cricket.hawkeye.values.country.Country";
        CodeGenerator.generate(clazzStr);

        clazzStr = "org.cricket.hawkeye.values.ground.Ground";
        CodeGenerator.generate(clazzStr);

        clazzStr = "org.cricket.hawkeye.values.inning.Inning";
        CodeGenerator.generate(clazzStr);
    }

    public static String removeComment(String javaCodeStr) throws Exception {

        int ch;

        boolean slashCommentFound = false;
        boolean starCommentFound = false;
        boolean firstSlashFound = false;
        boolean firstStarFound = false;
        boolean closingStarFound = false;
        boolean startDoubleQuoteFound = false;
        int lastChar;

        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new StringReader(javaCodeStr));

        while ((ch = reader.read()) != -1) {

            lastChar = ch;

            if (ch == '\"') {
                if (startDoubleQuoteFound == false) {
                    startDoubleQuoteFound = true;
                } else if (startDoubleQuoteFound == true) {
                    startDoubleQuoteFound = false;
                }
            }

            if (startDoubleQuoteFound
                    && (starCommentFound == true || slashCommentFound == true)) {
                continue;
            }
            if (ch == '/') {
                if (starCommentFound == true && closingStarFound == false) {
                    continue;
                }
                if (closingStarFound && starCommentFound == true) {
                    starCommentFound = false;
                    closingStarFound = false;
                    firstStarFound = false;
                    continue;
                } else if (firstSlashFound && slashCommentFound == false
                        && starCommentFound == false) {
                    slashCommentFound = true;
                    firstSlashFound = false;
                    continue;
                } else if (slashCommentFound == false
                        && starCommentFound == false
                        && startDoubleQuoteFound == false) {
                    firstSlashFound = true;
                    continue;
                }
            }
            if (ch == '*') {
                if (starCommentFound) {
                    closingStarFound = true;
                    continue;
                }
                if (firstSlashFound && starCommentFound == false) {
                    starCommentFound = true;
                    firstSlashFound = false;
                    continue;
                } else if (firstStarFound == false
                        && starCommentFound == true) {
                    firstStarFound = true;
                    continue;
                }
            }
            if (ch == '\n') {
                if (slashCommentFound) {
                    slashCommentFound = false;
                    firstStarFound = false;
                    firstSlashFound = false;
                    starCommentFound = false;
                    sb.append((char) ch);
                    continue;
                }
            }

            if (starCommentFound == true && closingStarFound == false) {
                continue;
            }

            if (ch != '/' && ch != '*') {
                if (closingStarFound) {
                    sb.append((char) lastChar);
                }

                closingStarFound = false;
                firstSlashFound = false;
                firstStarFound = false;
                closingStarFound = false;

            }

            if (slashCommentFound == false && starCommentFound == false) {
                sb.append((char) ch);
            }
        }

        reader.close();

        return sb.toString();

    }
}
