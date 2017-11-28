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
import org.common.di.AppContainer;
import org.commons.reflection.ClazzUtil;
import org.cricket.hawkeye.codegen.SourceVO;
import org.cricket.hawkeye.values.exception.HawkEyeCodeTemplateException;
import org.hawk.logger.HawkLogger;

/**
 *
 * @author Manoranjan Sahu
 */
public class FetcherMethodImplFactory {

    private static final HawkLogger logger = HawkLogger.getLogger(FetcherMethodImplFactory.class.getName());

    public static List<IFetcherMethodImpl> create(SourceVO sourceVO) throws HawkEyeCodeTemplateException {
        List<IFetcherMethodImpl> fetcherMethodImpls = new ArrayList<IFetcherMethodImpl>();
        List<Class> fetcherMethodClasses = AppContainer.getInstance().getSubTypesOf(IFetcherMethodImpl.class);
        if (fetcherMethodClasses != null) {
            for (Class fetcherMethodClazz : fetcherMethodClasses) {
                try {
                    IFetcherMethodImpl dummyFetcherMethodImpl = ClazzUtil.instantiate(fetcherMethodClazz, IFetcherMethodImpl.class);
                    IFetcherMethodImpl fetcherMethodImpl = dummyFetcherMethodImpl.create(sourceVO);
                        fetcherMethodImpls.add(fetcherMethodImpl);
                } catch (Exception ex) {
                    logger.error(ex);
                    throw new HawkEyeCodeTemplateException(ex);
                }
            }
        }
        return fetcherMethodImpls;
    }
}
