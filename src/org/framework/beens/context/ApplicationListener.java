package org.framework.beens.context;

public interface ApplicationListener<E> {
    void onApplicationEvent(E event);
}
