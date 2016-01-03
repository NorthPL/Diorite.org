package org.diorite.web.page.shared.utils.validator.string;

/**
 * Validator interface for String required chars check.
 */
public interface StringRequiredCharsValidator extends StringCustomValidator<StringRequiredCharsValidator>
{
    /**
     * Returns array of chars which must be included in String.
     *
     * @return array of chars which must be included in String.
     */
    char[] getChars();

    /**
     * Creates a new instance of StringRequiredCharsValidator
     *
     * @param chars array of required chars
     *
     * @return new instance of StringRequiredCharsValidator
     */
    static StringRequiredCharsValidator create(final char... chars)
    {
        return new StringRequiredCharsValidatorImpl(chars);
    }
}
