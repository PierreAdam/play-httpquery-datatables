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

import com.jackson42.play.datatables.entities.Parameters;
import com.jackson42.play.datatables.entities.internal.DataSource;
import com.jackson42.play.datatables.enumerations.OrderEnum;
import com.jackson42.play.datatables.implementation.BasicPayload;
import com.jackson42.play.datatables.implementation.SimplePlayDataTables;
import play.i18n.MessagesApi;

import java.util.function.Supplier;

/**
 * EbeanDataTableQuery.
 *
 * @param <T> the type parameter
 * @author Pierre Adam
 * @since 21.03.05
 */
public class HttpQueryDataTablesLogic<T> extends SimplePlayDataTables<T, HttpQueryProvider<T>, BasicPayload> implements HttpQueryDataTables<T> {

    /**
     * Instantiates a new A play data tables.
     *
     * @param tClass                   the entity class
     * @param messagesApi              the messages api
     * @param initialHttpQuerySupplier the query supplier
     */
    protected HttpQueryDataTablesLogic(final Class<T> tClass, final MessagesApi messagesApi, final Supplier<HttpQueryProvider<T>> initialHttpQuerySupplier) {
        super(tClass, messagesApi, initialHttpQuerySupplier);
    }

    @Override
    protected void setPagination(final HttpQueryProvider<T> provider, final int startElement, final int numberOfElement) {
        final int page = startElement / (Math.max(numberOfElement, 1));
        provider.setPagniation(page, numberOfElement);
    }

    @Override
    protected void fallbackOrderHandler(final HttpQueryProvider<T> provider, final String columnName, final OrderEnum order) {
        provider.orderBy(columnName, order);
    }

    @Override
    protected void fallbackSearchHandler(final HttpQueryProvider<T> provider, final String columnName, final String value) {
    }

    @Override
    protected DataSource<T> dataSourceFromProvider(final HttpQueryProvider<T> provider, final BasicPayload payload) {
        return provider.execute();
    }

    @Override
    protected void preSearchHook(final HttpQueryProvider<T> tHttpQueryProvider, final BasicPayload payload, final Parameters parameters) {
    }

    @Override
    protected void postSearchHook(final HttpQueryProvider<T> tHttpQueryProvider, final BasicPayload payload, final Parameters parameters) {
    }

    @Override
    protected void preOrderHook(final HttpQueryProvider<T> tHttpQueryProvider, final BasicPayload payload, final Parameters parameters) {
    }

    @Override
    protected void postOrderHook(final HttpQueryProvider<T> tHttpQueryProvider, final BasicPayload payload, final Parameters parameters) {
    }

    @Override
    protected BasicPayload getDefaultPayload() {
        return new BasicPayload();
    }
}
