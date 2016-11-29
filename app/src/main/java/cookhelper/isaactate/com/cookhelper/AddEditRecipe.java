package cookhelper.isaactate.com.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddEditRecipe extends AppCompatActivity {

    ListView recipeListView;
    ArrayAdapter<String> recipeAdapter;

    //Where all foods will be stored
    List<Recipe> userRecipeList;
    List<String> stringList;


    @Override
    public void onResume(){

        super.onResume();
        recipeAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ON CREATE");
        setContentView(R.layout.activity_recipe_list);

        userRecipeList = RecipeList.userRecipes;
        stringList = new ArrayList<String>();

        int i;
        for(i=0; i<userRecipeList.size(); i++){
            stringList.add(userRecipeList.get(i).getRecipeTitle());
        }

        recipeListView = (ListView) findViewById(R.id.listView2);
        recipeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        recipeListView.setAdapter(recipeAdapter);
    }

    public void toRecipeAdd2 (View view){

        Intent myIntent = new Intent(this, AddRecipe.class);
        startActivity(myIntent);
        finish();

    }

}
