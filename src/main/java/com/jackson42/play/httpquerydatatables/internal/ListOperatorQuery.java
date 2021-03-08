/*
 * MIT License
 *
 * Copyright (c) 2021 Pierre Adam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
