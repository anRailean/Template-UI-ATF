package com.template.ui.atf.enhancement;

import org.testmonkeys.maui.core.elements.actions.ExecuteJSAction;
import org.testmonkeys.maui.pageobjects.elements.AbstractComponent;

public class EnhanceWebElement extends AbstractComponent {

    public void clickWithJs() {
        ExecuteJSAction action = new ExecuteJSAction(this, "arguments[0].click();");
        action.execute();
    }
}
