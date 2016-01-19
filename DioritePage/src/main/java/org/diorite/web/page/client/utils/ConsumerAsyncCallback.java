package org.diorite.web.page.client.utils;

import org.diorite.web.page.shared.utils.function.Consumer;

@SuppressWarnings("ClassHasNoToStringMethod")
public class ConsumerAsyncCallback<T> extends SimpleAsyncCallback<T>
{
    private final Consumer<T> consumer;

    public ConsumerAsyncCallback(final Consumer<T> consumer)
    {
        this.consumer = consumer;
    }

    @Override
    public void onSuccess(final T t)
    {
        this.consumer.accept(t);
    }
}
