package com.recipeapp.datahandler;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            String line;
            while ((line = reader.readLine()) != null) {
                //レシピのデータを分解
                String[] data = line.split(",");
                //レシピ名を代入
                String recipeName = data[0];
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
    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer =new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(recipe.getName());

            List<Ingredient> ingredients = recipe.getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                // 最初の要素にはスペースを追加しない
                if (i == 0) {
                    // 最初の材料はそのまま書き込む
                    writer.write("," + ingredients.get(i).getName());
                } else {
                    // 2番目以降の材料にはカンマとスペースを追加
                    writer.write(", " + ingredients.get(i).getName());
                }
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {

        return null;
    }

    
}
