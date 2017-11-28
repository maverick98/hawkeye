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

import java.util.List;
import java.util.Map;
import org.cricket.hawkeye.exception.HawkEyeException;

/**
 *
 * @author manoranjan
 */
public interface IEditDistanceAlgorithm {

     public Map<Integer, List<String>> findEditDistanceMap(String searchCriteria, List<String> all) throws HawkEyeException;

     public List<String> findStringsWithMinimumEditDistance(String searchCriteria, List<String> all) throws HawkEyeException;

     public int calculateEditDistance(String str1, String str2) throws HawkEyeException;
}
