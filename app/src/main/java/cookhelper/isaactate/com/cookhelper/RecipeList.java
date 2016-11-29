package cookhelper.isaactate.com.cookhelper;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isaac on 2016-11-25.
 */

public class RecipeList extends ListActivity {

    private List<Entry> userRecipes;
    private RecipeDataSource recipeDB;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        recipeDB = new RecipeDataSource(this);
        recipeDB.open();

        userRecipes = new ArrayList<Entry>();

        try {
            userRecipes = recipeDB.getAllEntries();
        }
        catch(IOException i){}
        catch(ClassNotFoundException c){}
    }


    public List<Entry> getRecipes(){
        return userRecipes;
    }
    public void addRecipe(Recipe recipe)throws IOException{
        recipeDB.addToDB(recipe);
    }

}
