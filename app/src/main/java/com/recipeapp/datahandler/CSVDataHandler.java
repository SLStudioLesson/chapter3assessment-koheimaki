package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

public class CSVDataHandler implements DataHandler{
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {

        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        //filePathのファイルの読み込み
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                //レシピのデータを分解
                String[] data = line.split(",");
                //レシピ名を代入
                String recipeName = data[0]();
                //材料名を取り出す
                ArrayList<Ingredient> ingredients = new ArrayList<>();

                for (int i = 1; i < data.length; i++) {
                    ingredients.add(new Ingredient(data[i]));
                }

                Recipe recipe = new Recipe(recipeName, ingredients);
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {

        return null;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {

        
    }
    
}
