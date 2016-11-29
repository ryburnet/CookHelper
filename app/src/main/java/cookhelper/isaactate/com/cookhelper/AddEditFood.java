package cookhelper.isaactate.com.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static cookhelper.isaactate.com.cookhelper.FoodList.userFoods;

public class AddEditFood extends AppCompatActivity {

    ListView foodListView;
    ArrayAdapter<String> foodAdapter;

    //Where all foods will be stored
    List<String> userFoodList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_food);

        userFoodList = FoodList.userFoods;

        foodListView = (ListView) findViewById(R.id.foodList);
        foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userFoodList);
        foodListView.setAdapter(foodAdapter);


    }

    public void addFood(View view) {

        EditText editText = (EditText) findViewById(R.id.editText3);
        String foodName = editText.getText().toString();

        if(foodName != null) {
            FoodList.userFoods.add(foodName);
        }

        editText.setText("");

        foodAdapter.notifyDataSetChanged();

    }

}



