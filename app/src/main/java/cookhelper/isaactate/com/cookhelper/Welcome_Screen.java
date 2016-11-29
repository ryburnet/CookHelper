package cookhelper.isaactate.com.cookhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
    }

    public void toSearchScreen (View view){

        Intent myIntent = new Intent(this, SearchScreen.class);
        startActivity(myIntent);
    }

    public void toRecentRecipe (View view){

        Intent myIntent = new Intent(this, RecipeScreen.class);
        startActivity(myIntent);
    }

    public void toAddFood (View view){

        Intent myIntent = new Intent(this, AddEditFood.class);
        startActivity(myIntent);
    }

    public void toAddRecipe (View view){

        Intent myIntent = new Intent(this, AddEditRecipe.class);
        startActivity(myIntent);
    }

    public void toSettings (View view){

        Intent myIntent = new Intent(this, SettingsScreen.class);
        startActivity(myIntent);
    }
}
