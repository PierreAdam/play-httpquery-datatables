package com.jackson42.play.httpquerydatatables.internal;

/**
 * QueryEntry.
 *
 * @author Pierre Adam
 * @since 21.03.05
 */
public class OperatorQuery extends AQuery {

    /**
     * The Value.
     */
    private final Object value;

    /**
     * Instantiates a new Operator query.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     */
    public OperatorQuery(final String column, final String operator, final Object value) {
        super(column, operator);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.asString(this.value);
    }
}
