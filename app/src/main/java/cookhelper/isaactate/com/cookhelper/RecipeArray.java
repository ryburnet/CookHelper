package cookhelper.isaactate.com.cookhelper;

/**
 * Created by Rhino on 2016-11-28.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecipeArray extends ArrayAdapter<Recipe>  {
    /**/
    private final Context context;
    private final ArrayList<Recipe> recipes;

    public RecipeArray(Context context, ArrayList<Recipe> values) {
        super(context, R.layout.activity_recipe_layout, values);
        this.context = context;
        this.recipes = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting Recipe
        Recipe currentRecipe = recipes.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_recipe_layout, parent, false);
        TextView recipeName = (TextView) rowView.findViewById(R.id.recipeTitle);
        TextView recipeType = (TextView) rowView.findViewById(R.id.typeName);
        TextView recipeCategory = (TextView) rowView.findViewById(R.id.categoryName);
        ImageView recipeImage = (ImageView) rowView.findViewById(R.id.recipeImage);

        //Placing content into recipe List Item
        recipeName.setText(currentRecipe.getRecipeTitle());
        recipeType.setText((currentRecipe.getTypeName()));
        recipeCategory.setText((currentRecipe.getCategoryName()));

        //TODO: Perform decision regarding image selection for recipe prior to setting an image
        /*recipeImage.setImageResource(R.drawable.ic_logo_empty);*/

        return rowView;
    }
}

