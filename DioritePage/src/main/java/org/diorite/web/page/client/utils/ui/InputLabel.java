package org.diorite.web.page.client.utils.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("ClassHasNoToStringMethod")
public class InputLabel extends ButtonBase implements HasName, HasValue<String>, HasChangeHandlers
{
    InputElement inputElem;
    final   LabelElement labelElem;
    private boolean      valueChangeHandlerInitialized;

    public InputLabel()
    {
        this(false);
    }

    /**
     * Creates an input box with no label.
     */
    public InputLabel(final boolean password)
    {
        this(password ? DOM.createInputPassword() : DOM.createInputText());
        this.setStyleName("input-field");
    }

    /**
     * Creates an input box with the specified text label.
     *
     * @param label the check box's label
     */
    public InputLabel(final String label)
    {
        this(label, false);
    }

    public InputLabel(final String label, final boolean password)
    {
        this(password);
        this.setText(label);
    }

    protected InputLabel(final Element elem)
    {
        super(DOM.createDiv());
        this.inputElem = InputElement.as(elem);
        this.labelElem = Document.get().createLabelElement();

        this.getElement().appendChild(this.inputElem);
        this.getElement().appendChild(this.labelElem);

        final String uid = DOM.createUniqueId();
        this.inputElem.setPropertyString("id", uid);
        this.labelElem.setHtmlFor(uid);

        // Accessibility: setting tab index to be 0 by default, ensuring element
        // appears in tab sequence. FocusWidget's setElement method already
        // calls setTabIndex, which is overridden below. However, at the time
        // that this call is made, inputElem has not been created. So, we have
        // to call setTabIndex again, once inputElem has been created.
        this.setTabIndex(0);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler)
    {
        // Is this the first value change handler? If so, time to add handlers
        if (! this.valueChangeHandlerInitialized)
        {
            this.addChangeHandler(event -> ValueChangeEvent.fire(InputLabel.this, InputLabel.this.getValue()));
            this.valueChangeHandlerInitialized = true;
        }
        return this.addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Returns the value property of the input element that backs this widget.
     * This is the value that will be associated with the InputLabel name and
     * submitted to the server if a {@link FormPanel} that holds it is submitted.
     * <p/>
     * This will probably return the same thing as {@link #getValue}, left here for magic reasons.
     */
    public String getFormValue()
    {
        return this.inputElem.getValue();
    }

    @Override
    public String getHTML()
    {
        return this.labelElem.getInnerHTML();
    }

    @Override
    public String getName()
    {
        return this.inputElem.getName();
    }

    @Override
    public int getTabIndex()
    {
        return this.inputElem.getTabIndex();
    }

    @Override
    public String getText()
    {
        return this.labelElem.getInnerText();
    }

    /**
     * Gets the text value of the input element.
     * <p/>
     *
     * @return the value of the input box.
     * Will not return null
     */
    @Override
    public String getValue()
    {
        if (this.isAttached())
        {
            return this.inputElem.getValue();
        }
        else
        {
            return this.inputElem.getDefaultValue();
        }
    }

    @Override
    public boolean isEnabled()
    {
        return ! this.inputElem.isDisabled();
    }

    @Override
    public void setAccessKey(final char key)
    {
        this.inputElem.setAccessKey("" + key);
    }

    @Override
    public void setEnabled(final boolean enabled)
    {
        this.inputElem.setDisabled(! enabled);
        if (enabled)
        {
            this.removeStyleDependentName("disabled");
        }
        else
        {
            this.addStyleDependentName("disabled");
        }
    }

    @Override
    public void setFocus(final boolean focused)
    {
        if (focused)
        {
            this.inputElem.focus();
        }
        else
        {
            this.inputElem.blur();
        }
    }

    /**
     * Set the value property on the input element that backs this widget. This is
     * the value that will be associated with the InputLabel's name and submitted to
     * the server if a {@link FormPanel} that holds it is submitted.
     * <p/>
     * Don't confuse this with {@link #setValue}.
     *
     * @param value
     */
    public void setFormValue(final String value)
    {
        this.inputElem.setAttribute("value", value);
    }

    @Override
    public void setHTML(final String html)
    {
        this.labelElem.setInnerHTML(html);
    }

    @Override
    public void setName(final String name)
    {
        this.inputElem.setName(name);
    }

    @Override
    public void setTabIndex(final int index)
    {
        // Need to guard against call to setTabIndex before inputElem is
        // initialized. This happens because FocusWidget's (a superclass of
        // InputLabel) setElement method calls setTabIndex before inputElem is
        // initialized. See InputLabel's protected constructor for more information.
        if (this.inputElem != null)
        {
            this.inputElem.setTabIndex(index);
        }
    }

    @Override
    public void setText(final String text)
    {
        this.labelElem.setInnerText(text);
    }

    /**
     * Sets the text in the input box.
     * <p/>
     * Note that this <em>does not</em> set the value property of the
     * input element wrapped by this widget. For access to that property, see
     * {@link #setFormValue(String)}
     *
     * @param value the text to set; must not be null
     *
     * @throws IllegalArgumentException if value is null
     */
    @Override
    public void setValue(final String value)
    {
        this.setValue(value, false);
    }

    /**
     * Sets the text in the input box, firing {@link ValueChangeEvent} if
     * appropriate.
     * <p/>
     * Note that this <em>does not</em> set the value property of the
     * input element wrapped by this widget. For access to that property, see
     * {@link #setFormValue(String)}
     *
     * @param value      true the text to set; must not be null
     * @param fireEvents If true, and value has changed, fire a
     *                   {@link ValueChangeEvent}
     *
     * @throws IllegalArgumentException if value is null
     */
    @Override
    public void setValue(final String value, final boolean fireEvents)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("value must not be null");
        }

        final String oldValue = this.getValue();
        this.inputElem.setValue(value);
        this.inputElem.setDefaultValue(value);
        if (value.equals(oldValue))
        {
            return;
        }
        if (fireEvents)
        {
            ValueChangeEvent.fire(this, value);
        }
    }

    // Unlike other widgets the InputLabel sinks on its inputElement, not
    // its wrapper
    @Override
    public void sinkEvents(final int eventBitsToAdd)
    {
        if (this.isOrWasAttached())
        {
            Event.sinkEvents(this.inputElem, eventBitsToAdd | Event.getEventsSunk(this.inputElem));
        }
        else
        {
            super.sinkEvents(eventBitsToAdd);
        }
    }


    /**
     * <b>Affected Elements:</b>
     * <ul>
     * <li>-label = label next to the input box.</li>
     * </ul>
     *
     * @see UIObject#onEnsureDebugId(String)
     */
    @Override
    protected void onEnsureDebugId(final String baseID)
    {
        super.onEnsureDebugId(baseID);
        ensureDebugId(this.labelElem, baseID, "label");
        ensureDebugId(this.inputElem, baseID, "input");
        this.labelElem.setHtmlFor(this.inputElem.getId());
    }

    /**
     * This method is called when a widget is attached to the browser's document.
     * onAttach needs special handling for the InputLabel case. Must still call
     * {@link Widget#onAttach()} to preserve the <code>onAttach</code> contract.
     */
    @Override
    protected void onLoad()
    {
        this.setEventListener(this.inputElem, this);
    }

    /**
     * This method is called when a widget is detached from the browser's
     * document. Overridden because of IE bug that throws away checked state and
     * in order to clear the event listener off of the <code>inputElem</code>.
     */
    @Override
    protected void onUnload()
    {
        // Clear out the inputElem's event listener (breaking the circular
        // reference between it and the widget).
        this.setEventListener(this.asOld(this.inputElem), null);
        this.setValue(this.getValue());
    }

    /**
     * Replace the current input element with a new one. Preserves
     * all state except for the name property, for nasty reasons
     * related to radio button grouping. (See implementation of
     * {@link RadioButton#setName}.)
     *
     * @param elem the new input element
     */
    protected void replaceInputElement(final Element elem)
    {
        final InputElement newInputElem = InputElement.as(elem);
        // Collect information we need to set
        final int tabIndex = this.getTabIndex();
        final String checked = this.getValue();
        final boolean enabled = this.isEnabled();
        final String formValue = this.getFormValue();
        final String uid = this.inputElem.getId();
        final String accessKey = this.inputElem.getAccessKey();
        final int sunkEvents = Event.getEventsSunk(this.inputElem);

        // Clear out the old input element
        this.setEventListener(this.asOld(this.inputElem), null);

        this.getElement().replaceChild(newInputElem, this.inputElem);

        // Sink events on the new element
        Event.sinkEvents(elem, Event.getEventsSunk(this.inputElem));
        Event.sinkEvents(this.inputElem, 0);
        this.inputElem = newInputElem;

        // Setup the new element
        Event.sinkEvents(this.inputElem, sunkEvents);
        this.inputElem.setId(uid);
        if (! accessKey.isEmpty())
        {
            this.inputElem.setAccessKey(accessKey);
        }
        this.setTabIndex(tabIndex);
        this.setValue(checked);
        this.setEnabled(enabled);
        this.setFormValue(formValue);

        // Set the event listener
        if (this.isAttached())
        {
            this.setEventListener(this.asOld(this.inputElem), this);
        }
    }

    private Element asOld(final com.google.gwt.dom.client.Element elem)
    {
        return elem.cast();
    }

    private void setEventListener(final com.google.gwt.dom.client.Element e, final EventListener listener)
    {
        DOM.setEventListener(this.asOld(e), listener);
    }

    @Override
    public HandlerRegistration addChangeHandler(final ChangeHandler handler)
    {
        return this.addDomHandler(handler, ChangeEvent.getType());
    }
}