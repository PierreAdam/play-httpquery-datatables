package com.jackson42.play.httpquerydatatables.internal;

/**
 * QueryEntry.
 *
 * @author Pierre Adam
 * @since 21.03.05
 */
public class StringOperatorQuery extends AQuery {

    /**
     * The Value.
     */
    private final String value;

    /**
     * Instantiates a new Operator query.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     */
    public StringOperatorQuery(final String column, final String operator, final String value) {
        super(column, operator);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value.toString();
    }
}
