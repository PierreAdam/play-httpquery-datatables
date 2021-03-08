package com.jackson42.play.httpquerydatatables.internal;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * QueryEntry.
 *
 * @author Pierre Adam
 * @since 21.03.05
 */
public class ListOperatorQuery extends AQuery {

    /**
     * The Values.
     */
    private final Object[] values;

    /**
     * Instantiates a new Operator query.
     *
     * @param column   the column
     * @param operator the operator
     * @param values   the values
     */
    public ListOperatorQuery(final String column, final String operator, final Object... values) {
        super(column, operator);
        this.values = values;
    }

    @Override
    public String getValue() {
        return Arrays.stream(this.values).map(this::asString).collect(Collectors.joining(","));
    }
}
