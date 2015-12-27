package org.diorite.web.page.client.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Wrapper for AsyncCallback which doesn't require implementing both methods
 */
public abstract class SimpleAsyncCallback<T> implements AsyncCallback<T>
{
    @Override
    public void onFailure(final Throwable throwable)
    {
        // TODO show error page
    }

    @Override
    public void onSuccess(final T t)
    {
    }
}
