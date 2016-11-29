package cookhelper.isaactate.com.cookhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cookhelper.isaactate.com.cookhelper.RecipeSQLiteHelper.TABLE_RECIPES;

/**
 * Created by Jimmy on 11/29/2016.
 */

public class IngredientDataSource {
    private SQLiteDatabase database;
    private RecipeSQLiteHelper dbHelper;
    private String[] allColumns = { RecipeSQLiteHelper.COLUMN_ID,
            RecipeSQLiteHelper.COLUMN_RECIPE };

    public IngredientDataSource(Context context) {
        dbHelper = new RecipeSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Entry addToDB(Ingredient ingredient) throws IOException {
        ContentValues values = new ContentValues();
        values.put(RecipeSQLiteHelper.COLUMN_RECIPE, encodeToString(ingredient));
        long insertID = database.insert(TABLE_RECIPES, null, values);
        Entry entry = new Entry(insertID, ingredient);
        return entry;
    }
    public void deleteEntry(Entry entry){
        long id = (long)entry.getKey();
        database.delete(TABLE_RECIPES, RecipeSQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Entry> getAllEntries() throws IOException, ClassNotFoundException{
        List<Entry> entries = new ArrayList<Entry>();
        Cursor cursor = database.query(TABLE_RECIPES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            long id = cursor.getLong(0);
            Entry entry = new Entry(id,decodeFromString(cursor.getString(1)));
            entries.add(entry);
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }

    public List<Entry> queryDB(String query) throws IOException, ClassNotFoundException{
        List<Entry> results = new ArrayList<Entry>();
        List<Entry> allEntries = new ArrayList<Entry>();
        Cursor cursor = database.query(TABLE_RECIPES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            long id = cursor.getLong(0);
            Entry entry = new Entry(id,decodeFromString(cursor.getString(1)));
            allEntries.add(entry);
            cursor.moveToNext();
        }
        cursor.close();



        System.out.println(checkRecipeSearch((Recipe)allEntries.get(0).getValue(),query));


        return results;
    }

    public List<String> andQueries(String q){
        String[] split = q.split(" ");
        List<String> and = new ArrayList<String>();
        System.out.println("split: " + Arrays.toString(split));


        for(int i = 0; i < split.length; i++){
            if(split[i].toUpperCase().equals("AND")){
                try{



                }
                catch(ArrayIndexOutOfBoundsException e){}
            }
        }

        System.out.println(Arrays.toString(and.toArray()));

        return and;
    }
    public List<String> orQueries(String q){
        String[] split = q.split(" ");
        List<String> or = new ArrayList<String>();
        System.out.println("split: " + Arrays.toString(split));


        for(int i = 0; i < split.length; i++){
            if(split[i].toUpperCase().equals("OR")){
                try{



                }
                catch(ArrayIndexOutOfBoundsException e){}
            }
        }

        System.out.println(Arrays.toString(or.toArray()));

        return or;
    }

    public List<String> notQueries(String q){
        String[] split = q.split(" ");
        List<String> not = new ArrayList<String>();
        System.out.println("split: " + Arrays.toString(split));


        for(int i = 0; i < split.length; i++){
            if(split[i].toUpperCase().equals("NOT")){
                try{
                    not.add(split[i + 1]);
                }
                catch(ArrayIndexOutOfBoundsException e){}
            }
        }

        System.out.println(Arrays.toString(not.toArray()));

        return not;
    }


    private boolean checkRecipeSearch(Recipe recipe, String search){ //check if recipe matches search criteria
        System.out.println(">>>>>>>>>>>>>>>>>>>> start of checkRecipeSearch");
        String newSearch = "";
        String[] split = search.split(" ");
        for(int i = 0; i < split.length; i++){
            if(!split[i].toUpperCase().equals("AND") && !split[i].toUpperCase().equals("OR") && !split[i].toUpperCase().equals("NOT")){
                if(ingredientInRecipe(split[i],recipe.getIngredients())){
                    newSearch += "T ";
                }
                else{
                    newSearch += "F ";
                }
            }
            else{
                newSearch += split[i] + " " ;
            }
        }
        System.out.println("175:" + newSearch);
        split = newSearch.split(" ");
        newSearch = "";
        for(int i = 0; i < split.length; i++){
            if(split[i].toUpperCase().equals("NOT")){
                try{
                    if(split[i+1].toUpperCase().equals("T")){
                        newSearch += "F ";
                    }
                    else{
                        newSearch += "T ";
                    }
                    i++;
                }
                catch(ArrayIndexOutOfBoundsException e){}
            }
            else{
                newSearch += split[i] + " ";
            }
        }
        System.out.println("195:" + newSearch);

        while(newSearch.split(" ").length > 1){
            split = newSearch.split(" ");
            newSearch = "";

            for(int i = 0; i < split.length; i++){
                if(split[i].toUpperCase().equals("NOT")){
                    try{
                        if(split[i+1].toUpperCase().equals("T")){
                            newSearch += "F ";
                        }
                        else{
                            newSearch += "T ";
                        }
                        i++;
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                }
                else if(split[i].toUpperCase().equals("AND")){
                    try{
                        if(split[i-1].equals("T") && split[i+1].equals("T")){
                            newSearch += "T ";
                        }
                        else{
                            newSearch += "F ";
                        }
                        i++;
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                }
                else if(split[i].equals("OR")){
                    try{
                        if(split[i-1].equals("T") || split[i+1].equals("T")){
                            newSearch += "T ";
                        }
                        else{
                            newSearch += "F ";
                        }
                        i++;
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                }
            }
            System.out.println("218:" + newSearch);
        }
        split = newSearch.split(" ");
        //newSearch = split.toString();

        System.out.println(">>>>>>>>>>>>>>>>>>>> end of checkRecipeSearch");
        System.out.println("newSearch:" + newSearch);
        return (newSearch.equals("T "));
    }


    public boolean ingredientInRecipe(String query, List<String> ingredients){
        System.out.println(">>>>>>>>>>>>>>>>>>>> start of ingredientInRecipe");
        boolean inList = false;
        for(int i = 0; i < ingredients.size(); i++){
            if(ingredients.get(i).equals(query)){
                return true;
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>> end of ingredientInRecipe");
        return inList;
    }

    public String encodeToString(Serializable o) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.encodeToString(baos.toByteArray(),0);
    }
    public Object decodeFromString(String s) throws IOException, ClassNotFoundException{
        byte [] data = Base64.decode(s,0);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }







}
