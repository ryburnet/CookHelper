package cookhelper.isaactate.com.cookhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Isaac on 2016-11-25.
 */

public class FoodList {


    static List<String> userFoods = new ArrayList<String>();

    public void addItemToFoods(String food){

        userFoods.add(food);

    }

    public List getList (){

        return userFoods;

    }

}
