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
