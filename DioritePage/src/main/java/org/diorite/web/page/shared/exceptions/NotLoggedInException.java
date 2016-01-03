package org.diorite.web.page.shared.exceptions;

public class NotLoggedInException extends Exception
{
    public NotLoggedInException()
    {
    }

    public NotLoggedInException(final String message)
    {
        super(message);
    }

    public NotLoggedInException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public NotLoggedInException(final Throwable cause)
    {
        super(cause);
    }
}
