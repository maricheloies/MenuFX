package com.example.menufx.utils;

import com.example.menufx.model.*;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileUtils {

    public static List<MenuElement> loadElements() {
        List<MenuElement> list = new ArrayList<>();
        //System.out.println(Paths.get(".").toAbsolutePath());

        if (Files.exists(Paths.get("./src/main", "aliments.txt")) &&
                Files.exists(Paths.get("./src/main", "dishes.txt"))) {

            try (Stream<String> myStream = Files.lines(
                    Paths.get("./src/main", "aliments.txt"));
                 Stream<String> myDishes = Files.lines(
                         Paths.get("./src/main", "dishes.txt"));) {
                list = myStream.map(line -> {
                    String[] parts = line.split(";");
                    return new Aliment(parts[0], parts[1], parts[2],
                            Boolean.parseBoolean(parts[3]),
                            Boolean.parseBoolean(parts[4]),
                            Boolean.parseBoolean(parts[5]),
                            Boolean.parseBoolean(parts[6]),
                            Double.parseDouble(parts[7]),
                            Double.parseDouble(parts[8]),
                            Double.parseDouble(parts[9]));
                }).collect(Collectors.toList());

                list.addAll(myDishes.map(line -> {
                    String[] parts = line.split(";");
                    Dish myDish = new Dish(parts[0], parts[1]);
                    for (int i = 2; i < parts.length; i += 11) {
                        myDish.addIngredient(new Ingredient(Double.parseDouble(parts[i]),
                                new Aliment(parts[i + 1], parts[i + 2], parts[i + 3],
                                        Boolean.parseBoolean(parts[i + 4]),
                                        Boolean.parseBoolean(parts[i + 5]),
                                        Boolean.parseBoolean(parts[i + 6]),
                                        Boolean.parseBoolean(parts[i + 7]),
                                        Double.parseDouble(parts[i + 8]),
                                        Double.parseDouble(parts[i + 9]),
                                        Double.parseDouble(parts[i + 10]))));

                    }
                    return myDish;
                }).collect(Collectors.toList()));

            } catch (IOException e) {
                System.err.println("Error reading files");
            }

        }
        return list;
    }

    public static void storeElements(List<MenuElement> list) {
        try (PrintWriter printA = new PrintWriter(new
                FileWriter(Paths.get("./src/main", "aliments.txt").toAbsolutePath().toString()));
             PrintWriter printD = new PrintWriter(new
                     FileWriter(Paths.get("./src/main", "dishes.txt").toAbsolutePath().toString()));
        ) {
            list.stream().filter(p -> p instanceof Aliment).forEach(printA::println); //se llama a toString de Aliment
            list.stream().filter(p -> p instanceof Dish).forEach(printD::println);  //se llama a toString de Dish

        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
    }

    public static void storeAliment(Aliment a)
    {
        try (PrintWriter printA = new PrintWriter(new
                FileWriter(Paths.get("./src/main", "aliments.txt").toAbsolutePath().toString(),true)))
        {
           printA.println(a);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
    }

    public static void storeDish(Dish d)
    {
        try (PrintWriter printD = new PrintWriter(new
                FileWriter(Paths.get("./src/main", "dishes.txt").toAbsolutePath().toString(),true)))
        {
            printD.println(d);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
    }

    public static void storeMenu(Menu m) {

        try (PrintWriter print = new PrintWriter(new FileWriter(
                Paths.get("./src/main", m.getDate().toString() + ".menu")
                        .toAbsolutePath().toString()));
        ) {
            print.println(m); //toString de Menu definido para escribir correctamente el fichero

        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }

    }
}
