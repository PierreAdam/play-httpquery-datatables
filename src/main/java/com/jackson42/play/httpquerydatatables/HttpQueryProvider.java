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

package com.jackson42.play.httpquerydatatables;

import com.jackson42.play.datatables.entities.internal.DataSource;
import com.jackson42.play.datatables.enumerations.OrderEnum;
import com.jackson42.play.httpquerydatatables.internal.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * HttpQueryProvider.
 *
 * @param <T> the type parameter
 * @author Pierre Adam
 * @since 21.03.05
 */
public class HttpQueryProvider<T> {

    /**
     * The Page.
     */
    private int page;

    /**
     * The Per page.
     */
    private int perPage;

    /**
     * The Queries.
     */
    private final List<QueryComponent> queries;

    /**
     * The Fetch function.
     */
    private final Function<HttpQueryProvider<T>, DataSource<T>> fetchFunction;

    /**
     * Instantiates a new Http query provider.
     *
     * @param fetchFunction the fetch function
     */
    public HttpQueryProvider(final Function<HttpQueryProvider<T>, DataSource<T>> fetchFunction) {
        this.fetchFunction = fetchFunction;
        this.queries = new ArrayList<>();
    }

    /**
     * Sets pagniation.
     *
     * @param page    the page
     * @param perPage the per page
     */
    public void setPagniation(final int page, final int perPage) {
        this.page = Math.max(page, 1);
        this.perPage = Math.max(perPage, 1);
    }

    /**
     * Eq http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> eq(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "eq", value));
        return this;
    }

    /**
     * Ne http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> ne(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "ne", value));
        return this;
    }

    /**
     * Gt http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> gt(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "gt", value));
        return this;
    }

    /**
     * Gte http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> gte(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "gte", value));
        return this;
    }

    /**
     * Lt http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> lt(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "lt", value));
        return this;
    }

    /**
     * Lte http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> lte(final String field, final Object value) {
        this.queries.add(new OperatorQuery(field, "lte", value));
        return this;
    }

    /**
     * Like http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> like(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "like", value));
        return this;
    }

    /**
     * Ilike http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> ilike(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "ilike", value));
        return this;
    }

    /**
     * Contains http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> contains(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "contains", value));
        return this;
    }

    /**
     * Icontains http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> icontains(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "icontains", value));
        return this;
    }

    /**
     * Startswith http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> startsWith(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "startswith", value));
        return this;
    }

    /**
     * Istartswith http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> istartsWith(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "istartswith", value));
        return this;
    }

    /**
     * Endswith http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> endsWith(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "endswith", value));
        return this;
    }

    /**
     * Iendswith http query provider.
     *
     * @param field the field
     * @param value the value
     * @return the http query provider
     */
    public HttpQueryProvider<T> iendsWith(final String field, final String value) {
        this.queries.add(new StringOperatorQuery(field, "iendswith", value));
        return this;
    }

    /**
     * In http query provider.
     *
     * @param field  the field
     * @param values the values
     * @return the http query provider
     */
    public HttpQueryProvider<T> in(final String field, final Object... values) {
        this.queries.add(new ListOperatorQuery(field, "in", values));
        return this;
    }

    /**
     * Notin http query provider.
     *
     * @param field  the field
     * @param values the values
     * @return the http query provider
     */
    public HttpQueryProvider<T> notIn(final String field, final Object... values) {
        this.queries.add(new ListOperatorQuery(field, "notin", values));
        return this;
    }

    /**
     * Between http query provider.
     *
     * @param field the field
     * @param start the start
     * @param end   the end
     * @return the http query provider
     */
    public HttpQueryProvider<T> between(final String field, final Object start, final Object end) {
        this.queries.add(new ListOperatorQuery(field, "between", start, end));
        return this;
    }

    /**
     * Isempty http query provider.
     *
     * @param field the field
     * @return the http query provider
     */
    public HttpQueryProvider<T> isEmpty(final String field) {
        this.queries.add(new NoValueOperatorQuery(field, "isempty"));
        return this;
    }

    /**
     * Isnotempty http query provider.
     *
     * @param field the field
     * @return the http query provider
     */
    public HttpQueryProvider<T> isNotEmpty(final String field) {
        this.queries.add(new NoValueOperatorQuery(field, "isnotempty"));
        return this;
    }

    /**
     * Isnull http query provider.
     *
     * @param field the field
     * @return the http query provider
     */
    public HttpQueryProvider<T> isNull(final String field) {
        this.queries.add(new NoValueOperatorQuery(field, "isnull"));
        return this;
    }

    /**
     * Isnotnull http query provider.
     *
     * @param field the field
     * @return the http query provider
     */
    public HttpQueryProvider<T> isNotNull(final String field) {
        this.queries.add(new NoValueOperatorQuery(field, "isnotnull"));
        return this;
    }

    /**
     * Orderby http query provider.
     *
     * @param field the field
     * @param order the order
     * @return the http query provider
     */
    public HttpQueryProvider<T> orderBy(final String field, final OrderEnum order) {
        this.queries.add(new StringOperatorQuery(field, "orderby", order.name()));
        return this;
    }

    /**
     * Execute the query to fetch the DataSource.
     * Should not be called externally from the library.
     *
     * @return the list
     */
    public DataSource<T> execute() {
        return this.fetchFunction.apply(this);
    }

    /**
     * Return the arguments as a Map of Key, Value.
     *
     * @return the arguments as a Map
     */
    public Map<String, String> forgeArguments() {
        final Map<String, String> data = new HashMap<>();

        for (final QueryComponent query : this.queries) {
            data.put(query.getKey(), query.getValue());
        }

        return data;
    }

    /**
     * Adds the argument to the URL given as parameters and return the transformed URL.
     *
     * @param url the url
     * @return the string
     */
    public String addArgumentsToUrl(final String url) {
        final StringBuilder urlBuilder = new StringBuilder(url);

        this.forgeArguments().forEach((key, value) -> {
            urlBuilder.append(urlBuilder.toString().contains("?") ? "&" : "?");
            urlBuilder.append(key);
            urlBuilder.append("=");
            urlBuilder.append(value);
        });

        return urlBuilder.toString();
    }

    /**
     * Gets the asked page.
     *
     * @return the page
     */
    public int getPage() {
        return this.page;
    }

    /**
     * Gets the asked number of item per page.
     *
     * @return the per page
     */
    public int getPerPage() {
        return this.perPage;
    }

    /**
     * Gets the first item index to retrieve from the data.
     * The first item is to be included in the result.
     *
     * @return the start item
     */
    public int getFirstItemIdx() {
        return (this.page - 1) * this.perPage;
    }

    /**
     * Gets the last time index to retrieve from the data.
     * The last item is to be excluded from the result.
     *
     * @return the end item idx
     */
    public int getLastItemIdx() {
        return (this.page) * this.perPage;
    }
}
