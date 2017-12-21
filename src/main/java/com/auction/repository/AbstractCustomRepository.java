package com.auction.repository;

import java.util.List;

/**
 * Created by Helga on 11.12.2017.
 */
public interface AbstractCustomRepository<T> {
    /**
     * Abstract method for get list of pages
     *
     * @param t
     * @param pageid
     * @param total
     * @return
     */
    List<T> getByPage(T t, int pageid, int total);
}
