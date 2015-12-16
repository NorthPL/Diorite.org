package org.diorite.web.page.shared.exceptions;

public class InvalidCredientalsException extends Exception
{
    public InvalidCredientalsException()
    {
    }

    public InvalidCredientalsException(final String message)
    {
        super(message);
    }

    public InvalidCredientalsException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public InvalidCredientalsException(final Throwable cause)
    {
        super(cause);
    }
}
