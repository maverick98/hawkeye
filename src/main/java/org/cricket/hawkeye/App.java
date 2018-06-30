package org.cricket.hawkeye;

import org.common.di.AppContainer;
import org.cricket.hawkeye.config.HawkEyeConfigHelper;
import org.cricket.hawkeye.context.HawkEyeExecutionContext;
import org.cricket.hawkeye.context.IHawkEyeExecutionContext;
import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.db.Inning;
import org.cricket.hawkeye.service.download.ICricDataDownloadService;
import org.cricket.hawkeye.service.persistence.ICricDataPersistenceService;

/**
 * Hello world!
 *
 */
public class App {

    public static IHawkEyeExecutionContext getHawkEyeExecutionContext() {
        return AppContainer.getInstance().getBean(IHawkEyeExecutionContext.class);
    }

    public static void main(String[] args) throws Exception {
        HawkEyeConfigHelper.getInstance().configureSpringBeans();
        HawkEyeExecutionContext context = new HawkEyeExecutionContext();
        context.setType(HawkEyeExecutionContext.Type.MAIN);
        
        context.setRootPath(".");
        getHawkEyeExecutionContext().setExecutionContext(context);
        HawkEyeConfigHelper.getInstance().configure();

        ICricDataDownloadService cricDataDownloadService = (ICricDataDownloadService) AppContainer.getInstance().getBean(ICricDataDownloadService.class);
        cricDataDownloadService.downloadAll();

        ICricDataPersistenceService cricDataPersistenceService =  AppContainer.getInstance().getBean(ICricDataPersistenceService.class);
        //System.out.println(cricDataPersistenceService);
        System.out.println(cricDataPersistenceService.persist());
          ICricDataDAO cricDAO = (ICricDataDAO) AppContainer.getInstance().getBean("cricOfflineDAO");
           System.out.println( cricDAO.findPlayers() );
    }

    private static void show(Inning[] innings) {
        for (Inning inning : innings) {

            System.out.println(inning);
        }

        System.out.println("-------");
    }
}
