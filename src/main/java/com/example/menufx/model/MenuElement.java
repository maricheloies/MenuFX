package com.example.menufx.model;

import java.util.Objects;

public abstract class MenuElement {
    protected String name;
    protected String description;

    public MenuElement() {
    }

    public MenuElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuElement)) return false;
        MenuElement that = (MenuElement) o;
        return getName().equals(that.getName()) && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return name + ";"+ description;
    }

    public abstract double getCalories();
    public abstract double getCarbohydrates();
    public abstract double getFat();
    public abstract boolean isGluten();
    public abstract boolean isMilk();
    public abstract boolean isNuts();
    public abstract boolean isEgg();
}

