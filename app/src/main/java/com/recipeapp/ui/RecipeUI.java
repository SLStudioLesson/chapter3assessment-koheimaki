package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.*;
import com.recipeapp.model.*;;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    private void displayRecipes	() throws IOException {
        //CSVDataHandlerをインスタンスを作成
        DataHandler dataHandler = new CSVDataHandler();
        //レシピリストの作成
        List<Recipe> recipes = new ArrayList<>();
        try {
            //レシピリストにレシピのデータを入れる
            recipes = dataHandler.readData();

            if (recipes.isEmpty()){
                System.out.println("No recipes available.");//レシピが空の場合表示
            } else {
                System.out.println();
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                //レシピリストからレシピを取り出す
                for (Recipe recipe : recipes) {
                    //レシピの名前を取得、出力
                    System.out.println("Recipe Name: " + recipe.getName());
                    //材料名を入れ込むリストを作成
                    List<String> ingredients = new ArrayList<>();
                    //レシピの中のIngredientsから材料名を取得する
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        ingredients.add(ingredient.getName());
                    }
                    //材料名を出力するための編集
                    String ingredientString = String.join(",", ingredients);
                    System.out.println("Main Ingredients: " + ingredientString);
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addNewRecipe() throws IOException {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient:");
                String ingredientName = reader.readLine();

                if (ingredientName.equals("done")){
                    break;
                }

                ingredients.add(new Ingredient(ingredientName));

            }

            System.out.println("Recipe added successfully.");

            Recipe addRecipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(addRecipe);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
}
