package com.template.ui.atf.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.testmonkeys.maui.core.browser.Browser;
import org.testmonkeys.maui.core.factory.PageFactory;
import org.testmonkeys.maui.core.factory.PageScanner;

@Getter
@AllArgsConstructor
public class UiEnvironment {
    Browser browser;
    PageScanner pageScanner;
    PageFactory pageFactory;
}