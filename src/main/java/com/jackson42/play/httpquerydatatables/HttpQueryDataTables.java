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

import com.jackson42.play.datatables.implementation.BasicPayload;
import com.jackson42.play.datatables.interfaces.PlayDataTables;
import play.i18n.MessagesApi;

import java.util.function.Supplier;

/**
 * EbeanDataTablesQuery.
 *
 * @param <T> the type parameter
 * @author Pierre Adam
 * @since 21.03.05
 */
public interface HttpQueryDataTables<T> extends PlayDataTables<T, HttpQueryProvider<T>, BasicPayload> {

    /**
     * Instantiate a new EbeanDataTables.
     *
     * @param <T>                      the type parameter
     * @param tClass                   the tClass
     * @param messagesApi              the messages api
     * @param initialHttpQuerySupplier the initial http query supplier
     * @return the ebean data tables
     */
    static <T> HttpQueryDataTables<T> create(final Class<T> tClass, final MessagesApi messagesApi, final Supplier<HttpQueryProvider<T>> initialHttpQuerySupplier) {
        return new HttpQueryDataTablesLogic<T>(tClass, messagesApi, initialHttpQuerySupplier);
    }
}
