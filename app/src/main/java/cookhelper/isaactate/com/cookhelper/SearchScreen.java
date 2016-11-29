package cookhelper.isaactate.com.cookhelper;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class SearchScreen extends AppCompatActivity {

    ListView foodListView;
    ArrayAdapter<String> foodAdapter;

    List<String> userFoodList;
    List<String> searchedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        userFoodList = FoodList.userFoods;
        searchedItems = new ArrayList<String>();

        foodListView = (ListView) findViewById(R.id.listView5);
        foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userFoodList);
        foodListView.setAdapter(foodAdapter);

        System.out.println(FoodList.userFoods.toString());

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);

                if (searchedItems.contains(name) == true) {

                    for (int i = 0; i < searchedItems.size(); i++) {
                        String tempName = searchedItems.get(i);
                        if (tempName.equals(name)) {
                            searchedItems.remove(i);
                            foodListView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                } else {
                    searchedItems.add(name);
                    foodListView.getChildAt(position).setBackgroundColor(Color.GREEN);
                }

                System.out.println(searchedItems);

            }
        });


    }


    public void toRecipeList(View view) {
        Intent myIntent = new Intent(this, RecipeScreen.class);
        startActivity(myIntent);
    }

}
