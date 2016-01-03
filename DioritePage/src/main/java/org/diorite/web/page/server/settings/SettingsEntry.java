package org.diorite.web.page.server.settings;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "settings")
public final class SettingsEntry
{
    @Id
    private String name;
    private Type   type;
    private String value;

    public SettingsEntry()
    {
    }

    public SettingsEntry(final String name, final Type type, final Object value)
    {
        this.name = name;
        this.type = type;
        this.value = this.type.serialize(value);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Type getType()
    {
        return this.type;
    }

    public void setType(final Type type)
    {
        this.type = type;
    }

    public <T> T getValue()
    {
        //noinspection unchecked
        return (T) this.type.deserialize(this.value);
    }

    public void setValue(final Object value)
    {
        this.value = this.type.serialize(value);
    }

    public enum Type
    {
        INTEGER
                {
                    @Override
                    Object deserialize(final String string)
                    {
                        return Integer.parseInt(string);
                    }
                },
        BOOLEAN
                {
                    @Override
                    Object deserialize(final String string)
                    {
                        return Boolean.parseBoolean(string);
                    }
                },
        STRING;

        String serialize(final Object object)
        {
            return object.toString();
        }

        Object deserialize(final String string)
        {
            return string;
        }
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("name", this.name).append("type", this.type).append("value", this.value).toString();
    }
}
