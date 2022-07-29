package com.logistic.exceptions;

import java.util.Date;

public class ExceptionResponse {

    /**
     * timestamp.
     */
    private Date timestamp;
    /**
     * message.
     */
    private String message;
    /**
     * details.
     */
    private String details;

    /**
     * constructor.
     *
     * @param timestamp timestamp
     * @param message message
     * @param details details
     */
    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * getter timestamp.
     *
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * getter message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter details.
     *
     * @return details
     */
    public String getDetails() {
        return details;
    }

}
