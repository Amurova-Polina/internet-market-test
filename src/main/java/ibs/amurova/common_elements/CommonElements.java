package ibs.amurova.common_elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CommonElements {

    //DIV
    public static SelenideElement divText(String text) {
        return $x("//div[contains(text(), '" + text + "')]").as("Элемент с текстом \"" + text + "\"");
    }

    public static SelenideElement divClass(String className) {
        return $x("//div[contains(@class, '" + className + "')]").as("Элемент с аттрибутом class \"" + className + "\"");
    }

    public static SelenideElement divDataName(String text) {
        return $x("//div[contains(@data-name, '" + text + "')]").as("Элемент с аттрибутом data-name \"" + text + "\"");
    }

    //BUTTON
    public static SelenideElement buttonText(String text) {
        return $x("//button[contains(text(), '" + text + "')]").as("Кнопка с текстом \"" + text + "\"");
    }

    public static SelenideElement buttonClass(String className) {
        return $x("//button[contains(@class, '" + className + "')]").as("Кнопка с аттрибутом \"" + className + "\"");
    }

    //INPUT
    public static SelenideElement inputClass(String className) {
        return $x("//input[contains(@class, '" + className + "')]").as("Поле ввода с аттрибутом class \"" + className + "\"");
    }

    public static SelenideElement inputId(String id) {
        return $x("//input[contains(@id, '" + id + "')]").as("Инпут");
    }

    //ANCHOR
    public static SelenideElement aClass(String className) {
        return $x("//a[contains(@class, '" + className + "')]").as("Элемент с аттрибутом class \"" + className + "\"");
    }

    public static SelenideElement aText(String text) {
        return $x("//a[contains(text(), '" + text + "')]").as("Элемент с текстом \"" + text + "\"");
    }

    //LI
    public static SelenideElement liClass(String className) {
        return $x("//li[contains(@class, '" + className + "')]").as("Элемент с аттрибутом class \"" + className + "\"");
    }


    //H
    public static SelenideElement h1Class(String className) {
        return $x("//h1[contains(@class, '" + className + "')]").as("Заголовок");
    }

    public static SelenideElement h2Text(String text) {
        return $x("//h2[text() = '" + text + "']").as("Элемент с текстом \"" + text + "\"");
    }

}
