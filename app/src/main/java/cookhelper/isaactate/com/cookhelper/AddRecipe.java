package cookhelper.isaactate.com.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    public void createARecipe(View view){
        EditText titleText = (EditText) findViewById(R.id.recipeTitleBox);
        EditText editDirections = (EditText) findViewById(R.id.recipeInstrBox);
        EditText ingredientsText = (EditText) findViewById(R.id.ingredientEntryBox);

        Spinner catiSpinner = (Spinner) findViewById(R.id.spinner5);
        Spinner typeSpinner = (Spinner) findViewById(R.id.spinner4);

        String[] cats = new String[]{"Canadian", "Indian", "Thai", "Chinese", "Japanese", "Italian"};
        String[] types = new String[]{"Breakfast","Lunch","Dinner"};

        ArrayAdapter<String> catAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cats);
        ArrayAdapter<String> typesAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types);

        catiSpinner.setAdapter(catAdapt);
        typeSpinner.setAdapter(typesAdapt);

        String recipeName = titleText.getText().toString();
        String recipeInst = editDirections.getText().toString();

        String selectType = typeSpinner.getSelectedItem().toString();
        String selectCat = typeSpinner.getSelectedItem().toString();

        ArrayList<String> recipeIngredients = new ArrayList<>();
        recipeIngredients.add("carrot");
        recipeIngredients.add("beer");

        /*Add ingredients that were enter*/

        Recipe newRecipe = new Recipe(recipeName, recipeIngredients ,selectCat, selectType, recipeInst);

        /*Add recipe to database*/

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


