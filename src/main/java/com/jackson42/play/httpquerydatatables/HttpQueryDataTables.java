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
