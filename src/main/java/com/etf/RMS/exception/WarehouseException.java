package com.etf.RMS.exception;

/**
 *
 * @author Dusan
 */
public class WarehouseException extends Exception{
    
    public WarehouseException(String message) {
        super(message);
    }

    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
