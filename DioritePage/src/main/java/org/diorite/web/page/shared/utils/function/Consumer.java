package org.diorite.web.page.shared.utils.function;

@FunctionalInterface
public interface Consumer<T>
{
    void accept(T t);
}
