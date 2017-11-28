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
package org.cricket.hawkeye.string;

import org.cricket.hawkeye.exception.HawkEyeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author manoranjan
 */
//@Component("editDistanceAlgorithm")
//@Qualifier("default")
public class LevenshteinDistanceAlgorithm extends AbstractEditDistanceAlgorithm {

    @Override
    public int calculateEditDistance(String str1, String str2) throws HawkEyeException {

        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {

            throw new HawkEyeException("illegal args");
        }

        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {

            distance[i][0] = i;

        }

        for (int j = 0; j <= str2.length(); j++) {

            distance[0][j] = j;

        }

        for (int i = 1; i <= str1.length(); i++) {

            for (int j = 1; j <= str2.length(); j++) {

                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]
                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                        : 1));
            }
        }

        return distance[str1.length()][str2.length()];


    }

    private int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
