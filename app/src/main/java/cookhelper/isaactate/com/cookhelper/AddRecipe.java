package cookhelper.isaactate.com.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    public void createARecipe(View view){
//grgdrsg
        EditText titleText = (EditText) findViewById(R.id.recipeTitleBox);
        EditText editDirections = (EditText) findViewById(R.id.recipeInstBox);


        String recipeName = titleText.getText().toString();
        String recipeInst = editDirections.getText().toString();

        Recipe newRecipe = new Recipe();

        RecipeList.userRecipes.add(newRecipe);

        titleText.setText("");
        editDirections.setText("");

        Intent myIntent = new Intent(this, AddEditRecipe.class);
        startActivity(myIntent);

        finish();

    }

    public void toAddFoods(View view){

        Intent myIntent = new Intent(this, SelectFoodsForRecipe.class);
        startActivity(myIntent);

    }

}


