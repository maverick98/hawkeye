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

package org.cricket.hawkeye.values.exception;

import org.cricket.hawkeye.exception.HawkEyeException;

/**
 *
 * @author Manoranjan Sahu
 */
public class HawkEyeCodeTemplateException extends HawkEyeException {

    
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>HawkEyeException</code> without detail message.
     */
    public HawkEyeCodeTemplateException() {
    }

    /**
     * Constructs an instance of <code>HawkEyeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public HawkEyeCodeTemplateException(Object msg) {
        super(msg.toString());
    }

    /**
     * Constructs an instance of <code>HawkEyeException</code> with the specified detail message.
     * @param t Throwable.
     */
    public HawkEyeCodeTemplateException(Throwable t) {
        super(t);
    }

    /**
     * Constructs an instance of <code>HawkEyeException</code> with the specified detail message and throws the Exception.
     * @param t msg the detail message.
     * @param t Throwable exception to be thrown.
     */
    public HawkEyeCodeTemplateException(Object msg, Throwable t) {
        super(msg.toString(), t);
    }

    
}

