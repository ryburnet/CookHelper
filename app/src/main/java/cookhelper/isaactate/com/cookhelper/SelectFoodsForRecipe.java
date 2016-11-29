package cookhelper.isaactate.com.cookhelper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectFoodsForRecipe extends AppCompatActivity {


    ListView foodListView;
    ArrayAdapter<Ingredient> foodAdapter;
    List<String> selectedItems;


    //Where all foods will be stored
    List<Ingredient> userFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_foods_for_recipe);

        userFoodList = FoodList.getIngedientList();
        selectedItems = new ArrayList<String>();

        foodListView = (ListView) findViewById(R.id.stupidList);
        foodAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, userFoodList);
        foodListView.setAdapter(foodAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);

                if (selectedItems.contains(name) == true) {

                    for (int i = 0; i < selectedItems.size(); i++) {
                        String tempName = selectedItems.get(i);
                        if (tempName.equals(name)) {
                            selectedItems.remove(i);
                            foodListView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                } else {

                    selectedItems.add(name);
                    foodListView.getChildAt(position).setBackgroundColor(Color.GREEN);
                }

                System.out.println(selectedItems);

            }
        });

    }

    public void goBack(View view){




        super.onBackPressed();



    }

}
