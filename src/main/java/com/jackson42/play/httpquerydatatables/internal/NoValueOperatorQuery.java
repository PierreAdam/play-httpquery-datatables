package com.jackson42.play.httpquerydatatables.internal;

/**
 * QueryEntry.
 *
 * @author Pierre Adam
 * @since 21.03.05
 */
public class NoValueOperatorQuery extends AQuery {

    /**
     * Instantiates a new No value operator query.
     *
     * @param column   the column
     * @param operator the operator
     */
    public NoValueOperatorQuery(final String column, final String operator) {
        super(column, operator);
    }

    @Override
    public String getValue() {
        return "";
    }
}
