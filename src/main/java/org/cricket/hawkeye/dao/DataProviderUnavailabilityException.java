/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.dao;

import org.cricket.hawkeye.dao.exception.DAOException;

/**
 *
 * @author manosahu
 */
public class DataProviderUnavailabilityException extends DAOException{

    /**
     * Creates a new instance of <code>DAOException</code> without detail message.
     */
    public DataProviderUnavailabilityException() {
    }

    /**
     * Constructs an instance of <code>DAOException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DataProviderUnavailabilityException(Object msg) {
        super(msg.toString());
    }

    /**
     * Constructs an instance of <code>DAOException</code> with the specified detail message.
     * @param t Throwable.
     */
    public DataProviderUnavailabilityException(Throwable t) {
        super(t);
    }

    /**
     * Constructs an instance of <code>DAOException</code> with the specified detail message and throws the Exception.
     * @param t msg the detail message.
     * @param t Throwable exception to be thrown.
     */
    public DataProviderUnavailabilityException(Object msg, Throwable t) {
        super(msg.toString(), t);
    }
}
