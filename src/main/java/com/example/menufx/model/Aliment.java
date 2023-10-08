package com.example.menufx.model;



public class Aliment extends MenuElement {

    String frecuency;
    boolean gluten;
    boolean milk;
    boolean nuts;
    boolean egg;
    double calories;
    double carbohydrates;
    double fat;

    /**
     *
     * @param name
     * @param description
     * @param frecuency
     * @param gluten
     * @param milk
     * @param nuts
     * @param egg
     * @param calories
     * @param carbohydrates
     * @param fat
     */

    public Aliment(String name, String description, String frecuency, boolean gluten, boolean milk, boolean nuts, boolean egg, double calories, double carbohydrates, double fat) {
        super(name, description);
        this.frecuency = frecuency;
        this.gluten = gluten;
        this.milk = milk;
        this.nuts = nuts;
        this.egg = egg;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    public String getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(String frecuency) {
        this.frecuency = frecuency;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isNuts() {
        return nuts;
    }

    public void setNuts(boolean nuts) {
        this.nuts = nuts;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return super.toString()+ ";"+frecuency +";" +
                gluten + ";"+
                milk + ";"+
                nuts +";"+
                egg +";"+
                calories +";"+
                carbohydrates +";"+
                fat;

    }
}

