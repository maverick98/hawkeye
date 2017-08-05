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
package org.cricket.hawkeye.codegen;

import org.cricket.hawkeye.codegen.fetcher.IFetcherMethodImpl;
import org.cricket.hawkeye.codegen.fetcher.FetcherMethodImplFactory;
import org.cricket.hawkeye.codegen.table.TableTemplate;
import org.cricket.hawkeye.codegen.entity.EntityTemplate;
import org.cricket.hawkeye.codegen.fetcher.FetcherTemplate;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import org.common.di.AppContainer;
import org.commons.reflection.ClazzUtil;
import org.commons.string.StringUtil;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.values.exception.HawkEyeCodeTemplateException;
import org.hawk.logger.HawkLogger;
import org.hawk.codegen.ITemplateService;
import org.hawk.codegen.TemplateServiceImpl;

/**
 *
 * @author Manoranjan Sahu
 */
public class DefaultCodeTemplateProvider implements ICodeTemplateProvider {

    private static final HawkLogger logger = HawkLogger.getLogger(DefaultCodeTemplateProvider.class.getName());

    @Override
    public EntityTemplate createEntityTemplate(SourceVO sourceVO) throws HawkEyeCodeTemplateException {

        EntityTemplate entityTemplate = new EntityTemplate();
        entityTemplate.setInputFile("template\\Entity.template");

        String outputClazz = sourceVO.getEntityOutputJavaFile();
        entityTemplate.setErrorFile("error.log");
        entityTemplate.setOutputFile(outputClazz);
        Class clazz = sourceVO.getClass();

        entityTemplate.setClazz(clazz.getSimpleName());
        entityTemplate.setEntity(clazz.getName());
        entityTemplate.setDate(new Date().toString());

        entityTemplate.validate();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(HQLGenerate.class)) {

                String fIELD = field.getName();

                String GETFIELD = ClazzUtil.getGetterMethod(fIELD);
                String SETFIELD = ClazzUtil.getSetterMethod(fIELD);
                entityTemplate.getGetFields().add(GETFIELD);
                entityTemplate.getSetFields().add(SETFIELD);
                Object obj = null;
                try {

                    obj = ClazzUtil.invoke(sourceVO, GETFIELD, new Class[]{}, new Object[]{});
                } catch (Exception ex) {
                    throw new HawkEyeCodeTemplateException(ex);
                }
                if (obj != null) {
                    String TYPE = obj.getClass().getName();

                    entityTemplate.getTypes().add(TYPE);
                } else {
                    throw new HawkEyeCodeTemplateException("could not find #TYPE#");
                }

            }
        }
        entityTemplate.setSize(String.valueOf(entityTemplate.getGetFields().size()));

        return entityTemplate;

    }

    @Override
    public TableTemplate createTableTemplate(SourceVO sourceVO) throws HawkEyeCodeTemplateException {
        TableTemplate tableTemplate = new TableTemplate();
        tableTemplate.setInputFile("template\\Table.template");
        String outputClazz = sourceVO.getTableOutputJavaFile();
        tableTemplate.setErrorFile("error.log");
        tableTemplate.setOutputFile(outputClazz);
        Class clazz = sourceVO.getClass();
        tableTemplate.setClazz(clazz.getSimpleName());
        tableTemplate.setDate(new Date().toString());
        tableTemplate.setClazzInstance(StringUtil.toggle(tableTemplate.getClazz()));
        return tableTemplate;
    }

    @Override
    public FetcherTemplate createFetcherTemplate(SourceVO sourceVO) throws HawkEyeCodeTemplateException {
        FetcherTemplate fetcherTemplate = new FetcherTemplate();
        fetcherTemplate.setInputFile("template\\Fetcher.template");
        fetcherTemplate.setOutputFile(sourceVO.getFetcherOutputJavaFile());
        fetcherTemplate.setErrorFile("error.log");
        fetcherTemplate.setDate(new Date().toString());
        Class clazz = sourceVO.getClass();
        fetcherTemplate.setClazz(clazz.getSimpleName());

        List<IFetcherMethodImpl> impls = FetcherMethodImplFactory.create(sourceVO);
        if (impls != null) {
            for (IFetcherMethodImpl fetcherMethodImpl : impls) {
                ITemplateService templateService = (ITemplateService)AppContainer.getInstance().getBean(ITemplateService.class);
                try {
                    templateService.toFile(fetcherMethodImpl.getFetcherTemplate());
                } catch (Exception ex) {
                    logger.error(ex);
                    throw new HawkEyeCodeTemplateException(ex);
                }
            }
        }

        return fetcherTemplate;
    }
}
