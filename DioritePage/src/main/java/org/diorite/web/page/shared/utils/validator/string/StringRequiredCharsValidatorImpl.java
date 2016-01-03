package org.diorite.web.page.shared.utils.validator.string;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StringRequiredCharsValidatorImpl implements StringRequiredCharsValidator
{
    private final char[] chars;

    public StringRequiredCharsValidatorImpl(final char[] chars)
    {
        Validate.notNull(chars, "Chars can't be null!");
        this.chars = chars;
        Arrays.sort(this.chars);
    }

    @Override
    public char[] getChars()
    {
        return this.chars.clone();
    }

    @Override
    public boolean validate(final String s) throws RuntimeException
    {
        if (s == null)
        {
            return false;
        }
        for (final char c : s.toCharArray())
        {
            if (Arrays.binarySearch(this.chars, c) < 0)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass()))
        {
            return false;
        }

        final StringRequiredCharsValidatorImpl that = (StringRequiredCharsValidatorImpl) o;

        return Arrays.equals(this.chars, that.chars);

    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(this.chars);
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("chars", this.chars).toString();
    }
}
