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
package org.cricket.hawkeye.service.persistence;


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.jackson.map.ObjectMapper;
import org.cricket.hawkeye.codegen.constant.DismissalType;
import org.cricket.hawkeye.dao.ICricDataDAO;
import org.cricket.hawkeye.dao.exception.DAOException;
import org.cricket.hawkeye.db.Countrys;
import org.cricket.hawkeye.db.Ground;
import org.cricket.hawkeye.db.Grounds;
import org.cricket.hawkeye.db.Inning;
import org.cricket.hawkeye.db.Innings;
import org.cricket.hawkeye.db.Player;
import org.cricket.hawkeye.db.Players;
import org.cricket.hawkeye.service.persistence.exception.PersistenceServiceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author manoranjan
 */
public class CricDataPersistenceServiceImpl implements ICricDataPersistenceService {

    private static final Pattern inningsPattern = Pattern.compile("<caption>Innings by innings list</caption>");
    private static final Pattern p1 = Pattern.compile("\\s*<tr\\s*class=\"data1\">");
    private static final Pattern p2 = Pattern.compile(">([\\d|\\s|\\w|\\.|#|\\-|*|\\(|\\)|']*)<?/?[a|b]?>?</td>");
    @Autowired(required = true)
    // @Qualifier("default")
    ICricDataDAO cricOfflineDAO;

    public ICricDataDAO getCricOfflineDAO() {
        return cricOfflineDAO;
    }
    private  ObjectMapper objectMapper;
    public CricDataPersistenceServiceImpl(){
        objectMapper = new ObjectMapper();
       
    }
    
    private void createJson(Object object) {
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean persist() throws PersistenceServiceException {

        List<String> countries;
        Players playersTab = Players.getInstance();
        Innings inningsTab = Innings.getInstance();
        Grounds groundsTab = Grounds.getInstance();
        Countrys countrysTab = Countrys.getInstance();

        if (playersTab.getAll() != null && inningsTab.getAll() != null && groundsTab.getAll() != null && countrysTab.getAll() != null) {
            ;//return false;
        }

        try {
            countries = this.getCricOfflineDAO().findCountrys();
            System.out.println(countries);
            for (String country : countries) {

                try {
                    List<String> playersOfThisCountry = this.getCricOfflineDAO().findPlayers(country);
                    for (String player : playersOfThisCountry) {

                        Player playerEntity = new Player();
                        playerEntity.setName(player);
                        String playerStats = this.getCricOfflineDAO().findPlayerHTML(country, player);
                        try {
                            Set<org.cricket.hawkeye.db.Inning> innings = createInnings(playerStats, playerEntity);
                            //System.out.println(innings);
                            playerEntity.setInnings(innings);
                            playersTab.insert(playerEntity);

                            for (Inning inning : playerEntity.getInnings()) {
                                //System.out.println("adding {"+inning+"}");
                                inningsTab.insert(inning);
                                createJson(inning);
                                groundsTab.insert(inning.getGround());
                                countrysTab.insert(inning.getOpposition());

                            }

                            System.out.println("SUCCESS{" + player + "}");
                        } catch (Throwable th) {
                            System.out.println("FAILURE{" + player + "}");
                            //th.printStackTrace();
                        }

                        //System.out.println(playerStats);
                    }
                } catch (DAOException ex) {
                    ex.printStackTrace();
                    continue;
                }
            }
        } catch (DAOException ex) {
            ex.printStackTrace();

            throw new PersistenceServiceException();
        }

        playersTab.flush();
        inningsTab.flush();
        groundsTab.flush();
        countrysTab.flush();

        return true;
    }

    private static Set<org.cricket.hawkeye.db.Inning> createInnings(String text, Player player) {

        Matcher m = inningsPattern.matcher(text);
        String start = null;

        while (m.find()) {
            start = m.group();

        }
        String end = "</table>";

        int i1 = text.indexOf(start);

        int i = text.indexOf("<tbody>", i1);

        int j = text.indexOf(end, i);
        String innings = text.substring(i, j);

        Matcher m2 = p1.matcher(innings);

        int x = 0;

        int count = 0;
        Set<org.cricket.hawkeye.db.Inning> result = new TreeSet<org.cricket.hawkeye.db.Inning>();

        while (m2.find()) {
            String start1 = m2.group();

            x = innings.indexOf(start1, x);

            int y = innings.indexOf("</tr>", x);
            String part = innings.substring(x + start1.length(), y);

            Matcher m3 = p2.matcher(part);

            int k = 0;
            org.cricket.hawkeye.db.Inning inning = new org.cricket.hawkeye.db.Inning();
            inning.setPlayer(player);
            org.cricket.hawkeye.db.Country country = null;

            while (m3.find()) {
                String td = m3.group(1);

                switch (k) {

                    case 0:
                        int runs = 0;

                        if (td.endsWith("DNB")) {
                            inning.setBatted(false);
                            inning.setWasOut(false);

                        } else if (td.endsWith("*")) {
                            runs = Integer.parseInt(td.substring(0, td.length() - 1));

                            inning.setRuns(runs);
                            inning.setWasOut(false);

                        } else {
                            inning.setRuns(Integer.parseInt(td));

                        }
                        break;

                    case 1:

                        inning.setMins(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 2:
                        inning.setBallFaced(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 3:
                        inning.setFours(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 4:
                        inning.setSixes(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 5:
                        inning.setStrikeRate(td.equals("-") ? 0 : Double.parseDouble(td));

                        break;

                    case 6:
                        inning.setPositions(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 7:
                        inning.setDismissalType(td.equals("-") ? new DismissalType() : new DismissalType(td));

                        break;

                    case 8:
                        inning.setInnings(td.equals("-") ? 0 : Integer.parseInt(td));

                        break;

                    case 9:

                        break;

                    case 10:
                        country = new org.cricket.hawkeye.db.Country();
                        country.setName(td);

                        inning.setOpposition(country);

                        break;

                    case 11:
                        Ground ground = new org.cricket.hawkeye.db.Ground();
                        System.out.println(td);
                        if (td == null || td.isEmpty()) {
                            System.out.println("empty ground for " + inning.getPlayer() + " of country " + inning.getOpposition());
                        }
                        ground.setName(td);
                        inning.setGround(ground);

                        break;

                    case 12:
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

                        try {

                            inning.setStartDate(sdf.parse(td));

                        } catch (ParseException ex) {
                            System.out.println("Error occurred while paring date");
                            ex.printStackTrace();

                        }
                        break;

                    case 13:
                        int odi = Integer.parseInt(td.substring(td.indexOf("#") + 1).trim());
                        inning.setOdi(odi);

                        break;

                }

                k++;

            }
            x++;
            count++;

            result.add(inning);

        }

        return result;

    }
}
