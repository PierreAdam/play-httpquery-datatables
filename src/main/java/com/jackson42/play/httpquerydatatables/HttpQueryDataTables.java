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

import com.jackson42.play.datatables.implementations.BasicPayload;
import com.jackson42.play.datatables.interfaces.PlayDataTables;
import play.i18n.MessagesApi;

import java.util.function.Supplier;

/**
 * HttpQueryDataTables.
 *
 * @param <T> the type parameter
 * @param <P> the type parameter
 * @author Pierre Adam
 * @since 21.03.05
 */
public interface HttpQueryDataTables<T, P extends HttpQueryProvider<T>> extends PlayDataTables<T, P, BasicPayload> {

    /**
     * Create http query data tables.
     *
     * @param <T>                      the type parameter
     * @param <P>                      the type parameter
     * @param tClass                   the t class
     * @param messagesApi              the messages api
     * @param initialHttpQuerySupplier the initial http query supplier
     * @return the http query data tables
     */
    static <T, P extends HttpQueryProvider<T>> HttpQueryDataTables<T, P> create(final Class<T> tClass,
                                                                                final MessagesApi messagesApi,
                                                                                final Supplier<P> initialHttpQuerySupplier) {
        return new HttpQueryDataTablesLogic<>(tClass, messagesApi, initialHttpQuerySupplier);
    }
}
