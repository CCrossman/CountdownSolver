package com.crossman;

import java.util.Objects;

public class Element {
    private final Integer number;
    private final Operator operator;

    private Element(Integer number, Operator operator) {
        this.number = number;
        this.operator = operator;
    }

    public Integer getNumber() {
        return number;
    }

    public Operator getOperator() {
        return operator;
    }

    public boolean isNumber() {
        return number != null;
    }

    public boolean isOperator() {
        return operator != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(number, element.number) && Objects.equals(operator, element.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, operator);
    }

    @Override
    public String toString() {
        return "Element{" +
                "number=" + number +
                ", operator='" + operator + '\'' +
                '}';
    }

    public static Element ofNumber(Integer number) {
        return new Element(number, null);
    }

    public static Element ofOperator(Operator operator) {
        return new Element(null, operator);
    }
}
