package cookhelper.isaactate.com.cookhelper;

import android.app.ListActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Isaac on 2016-11-25.
 */

public class FoodList extends ListActivity{


    public static List<Entry> userFoods;
    private static IngredientDataSource ingredientDB;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ingredientDB = new IngredientDataSource(this);
        ingredientDB.open();

        userFoods = new ArrayList<Entry>();

        try {
            userFoods = ingredientDB.getAllEntries();
        }
        catch(IOException i){}
        catch(ClassNotFoundException c){}
    }


    public void checkRecipeIngredientInput(String input) throws IOException{
        String[] split = input.split(", ");
        for(int i = 0; i < split.length; i++){
            if(!checkInDB(split[i])){
                ingredientDB.addToDB(new Ingredient(split[i]));
            }
        }
    }

    public void removeIngredient(Entry entry){
        ingredientDB.deleteEntry(entry);
    }


    public static void addItemToFoods(String food) throws IOException{
        ingredientDB.addToDB(new Ingredient(food));
    }

    public static List<Ingredient> getIngedientList (){
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();

        for(int i = 0; i < userFoods.size(); i++){
            ingredientList.add((Ingredient)userFoods.get(i).getValue());
        }

        return ingredientList;
    }

    private boolean checkInDB(String s){
        boolean check = false;
        for(int i = 0; i < userFoods.size(); i++){
            if(userFoods.get(i).getValue().equals(s)){
                return true;
            }
        }
        return check;
    }

}
