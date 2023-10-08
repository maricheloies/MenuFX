package com.example.menufx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Menu{
    LocalDate date;

    List<MenuElement> elements;

    public Menu(LocalDate date) {
        this.date = date;
        elements=new ArrayList<MenuElement>();
    }


    public LocalDate getDate() {
        return date;
    }

    public void addElement(MenuElement m)
    {
        elements.add(m);
    }

    public List<MenuElement> getElements() {
        return elements;
    }

    public void setElements(List<MenuElement> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return date+";"+ elements.stream().map(e -> e.toString()).collect(Collectors.joining(";"));

    }
    public boolean isGluten()
    {
        return elements.stream().filter(e ->e.isGluten()).count()>0;
    }
    public boolean isMilk()
    {
        return elements.stream().filter(e ->e.isMilk()).count()>0;
    }
    public boolean isNuts()
    {
        return elements.stream().filter(e ->e.isNuts()).count()>0;
    }
    public boolean isEgg()
    {
        return elements.stream().filter(e ->e.isEgg()).count()>0;
    }
    public double getCalories()
    {
        return elements.stream().mapToDouble(p-> p.getCalories()).sum();
    }
    public  double getCarbohydrates()
    {
        return elements.stream().mapToDouble(p-> p.getCarbohydrates()).sum();
    }
    public  double getFat()
    {
        return elements.stream().mapToDouble(p-> p.getFat()).sum();
    }

}


