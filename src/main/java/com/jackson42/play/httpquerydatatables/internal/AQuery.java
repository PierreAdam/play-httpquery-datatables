package com.jackson42.play.httpquerydatatables.internal;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * AQuery.
 *
 * @author Pierre Adam
 * @since 21.03.05
 */
public abstract class AQuery implements QueryComponent {

    /**
     * The Column.
     */
    protected final String column;

    /**
     * The Operator.
     */
    protected final String operator;

    /**
     * Instantiates a new A query.
     *
     * @param column   the column
     * @param operator the operator
     */
    public AQuery(final String column, final String operator) {
        this.column = column;
        this.operator = operator;
    }

    @Override
    public String getKey() {
        return String.format("%s__%s", this.column, this.operator);
    }

    /**
     * Convert an object to string.
     *
     * @param object the object
     * @return the string
     */
    protected String asString(final Object object) {
        if (object == null) {
            return "null";
        } else if (object instanceof String) {
            return ((String) object);
        } else if (object instanceof Boolean) {
            return ((Boolean) object) ? "true" : "false";
        } else if (object instanceof DateTime) {
            return (((DateTime) object).withZone(DateTimeZone.UTC).toString("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }
        return object.toString();
    }
}
