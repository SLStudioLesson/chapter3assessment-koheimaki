import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            DataHandler dataHandler;
            //ユーザーから値を受け取り2だったらJSONをインスタンスを作成それ以外はCSVD
            if (choice.equals("2")) {
                dataHandler = new JSONDataHandler();
            } else {
                dataHandler = new CSVDataHandler();
            }

            RecipeUI recipeui = new RecipeUI(dataHandler);
            recipeui.displayMenu();
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}