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
 * http:www.gnu.org/licenses/gpl.txt
 *
 * END_HEADER
 */
package org.cricket.hawkeye.codegen;

import org.cricket.hawkeye.codegen.table.TableTemplate;
import org.cricket.hawkeye.codegen.entity.EntityTemplate;
import org.cricket.hawkeye.codegen.fetcher.FetcherTemplate;
import java.util.ArrayList;
import java.util.List;
import org.hawk.codegen.CodeGenConfig;
import org.common.di.AppContainer;
import org.cricket.hawkeye.values.country.Country;
import org.cricket.hawkeye.values.ground.Ground;
import org.cricket.hawkeye.values.inning.Inning;
import org.cricket.hawkeye.values.player.Player;
import org.hawk.config.HawkConfigHelper;
import org.hawk.codegen.FileTemplateJavaBean;
import org.hawk.codegen.ITemplateService;

/**
 *
 * @author Manoranjan Sahu
 */
public class NGCogegenerator {

    public static void main(String args[]) {
        NGCogegenerator ngc = new NGCogegenerator();
        ngc.generate();
                
    }

    public void generate() {
        try {
            HawkConfigHelper.configure();
               
            System.out.println("-----------");
            ICodeTemplateProvider codeTemplateProvider = new DefaultCodeTemplateProvider();
            List<FileTemplateJavaBean> fileTemplateJavaBeans = new ArrayList<FileTemplateJavaBean>();
            
            Player player = new Player();
            EntityTemplate playerEntityTemplate = codeTemplateProvider.createEntityTemplate(player);
            TableTemplate playerTableTemplate = codeTemplateProvider.createTableTemplate(player);
            FetcherTemplate playerFetcherTemplate = codeTemplateProvider.createFetcherTemplate(player);
            fileTemplateJavaBeans.add(playerEntityTemplate);
            fileTemplateJavaBeans.add(playerTableTemplate);
            fileTemplateJavaBeans.add(playerFetcherTemplate);

            Ground ground = new Ground();
            EntityTemplate groundEntityTemplate = codeTemplateProvider.createEntityTemplate(ground);
            TableTemplate groundTableTemplate = codeTemplateProvider.createTableTemplate(ground);
            FetcherTemplate groundFetcherTemplate = codeTemplateProvider.createFetcherTemplate(ground);
            fileTemplateJavaBeans.add(groundEntityTemplate);
            fileTemplateJavaBeans.add(groundTableTemplate);
            fileTemplateJavaBeans.add(groundFetcherTemplate);

            Country country = new Country();
            EntityTemplate countryEntityTemplate = codeTemplateProvider.createEntityTemplate(country);
            TableTemplate countryTableTemplate = codeTemplateProvider.createTableTemplate(country);
            FetcherTemplate countryFetcherTemplate = codeTemplateProvider.createFetcherTemplate(country);
            fileTemplateJavaBeans.add(countryEntityTemplate);
            fileTemplateJavaBeans.add(countryTableTemplate);
            fileTemplateJavaBeans.add(countryFetcherTemplate);

            Inning inning = new Inning();
            EntityTemplate inningEntityTemplate = codeTemplateProvider.createEntityTemplate(inning);
            TableTemplate inningTableTemplate = codeTemplateProvider.createTableTemplate(inning);

            FetcherTemplate inningFetcherTemplate = codeTemplateProvider.createFetcherTemplate(inning);
            fileTemplateJavaBeans.add(inningEntityTemplate);
            fileTemplateJavaBeans.add(inningTableTemplate);
            fileTemplateJavaBeans.add(inningFetcherTemplate);
            
            this.createJavaCode(fileTemplateJavaBeans);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createJavaCode(List<FileTemplateJavaBean> fileTemplateJavaBeans) throws Exception{

        for (FileTemplateJavaBean fileTemplateJavaBean : fileTemplateJavaBeans) {
            ITemplateService templateService = AppContainer.getInstance().getBean(ITemplateService.class);

            templateService.toFile(fileTemplateJavaBean);
        }



    }
}
