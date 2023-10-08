package com.example.menufx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dish extends MenuElement {
    List<Ingredient> ingredients;

    public Dish(String name, String description) {
        super(name, description);
        this.ingredients = new ArrayList<>();
    }
    public void addIngredient(Ingredient i)
    {
        ingredients.add(i);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void removeIngredient(Ingredient i)
    {
        ingredients.remove(i);
    }
    public double getCalories()
    {
        return ingredients.stream().mapToDouble(p-> p.getCalories()).sum();
    }
    public double getCarbohydrates()
    {
        return ingredients.stream().mapToDouble(p-> p.getCarbohydrates()).sum();
    }
    public double getFat()
    {
        return ingredients.stream().mapToDouble(p-> p.getFat()).sum();
    }

    @Override
    public String toString() {
        return super.toString() + ";"+ ingredients.stream().map(e -> e.toString()).collect(Collectors.joining(";"));

    }

    public boolean isGluten()
    {
        return ingredients.stream().anyMatch(e->e.aliment.isGluten());
    }
    public boolean isMilk()
    {
        return ingredients.stream().filter(e ->e.getAliment().isMilk()).count()>0;
    }
    public boolean isNuts()
    {
        return ingredients.stream().filter(e ->e.getAliment().isNuts()).count()>0;
    }
    public boolean isEgg()
    {
        return ingredients.stream().filter(e ->e.getAliment().isEgg()).count()>0;
    }



}

