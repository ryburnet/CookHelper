package cookhelper.isaactate.com.cookhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isaac on 2016-11-25.
 */

public class Recipe {

    private String recipeTitle;
    private String recipeText;
    private String categoryName;
    private String typeName;
    private ArrayList<String> ingredients = new ArrayList<>();

    public Recipe(String name,ArrayList<String> ingredients, String category, String type, String description)
    {
        this.recipeTitle = name;
        this.categoryName = category;
        this.typeName = type;
        this.recipeText = description;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public void setRecipeTitle(String recipeTitle){
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeTitle() { return recipeTitle; }

    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public String getCategoryName() {return  categoryName;}

    public void setTypeName(String typeName) {this.typeName = typeName; }

    public String getTypeName() {return  typeName;}

    public void addIngredient(String ingredient) {ingredients.add(ingredient);}

    public String getText() {return recipeText;}

    public void  setText(String text) {this.recipeText = text;}
}
