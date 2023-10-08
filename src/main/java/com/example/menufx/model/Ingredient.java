package com.example.menufx.model;

import java.util.Objects;

public class Ingredient {
    double quantity;
    Aliment aliment;


    public Ingredient( double quantity,Aliment aliment) {
        this.aliment = aliment;
        this.quantity = quantity;
    }

    public String getName()
    {
        return aliment.getName();
    }

    public String getDescription()
    {
        return aliment.getDescription();
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getAliment().equals(that.getAliment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAliment());
    }

    @Override
    public String toString() {
        return quantity +";" + aliment ;

    }

    public double getCalories(){
        return aliment.getCalories()*quantity;
    }

    public double getCarbohydrates()
    {
        return aliment.getCarbohydrates()*quantity;
    }

    public double getFat()
    {
        return aliment.getFat()*quantity;
    }
}