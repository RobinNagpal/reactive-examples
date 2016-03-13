package com.echofex.futures.java8_old;

/**
 * Created by rnagpal2 on 3/13/2016.
 */
@FunctionalInterface
public interface CompletableFuture<T>  {
    Object apply(T t) throws Exception;
}
