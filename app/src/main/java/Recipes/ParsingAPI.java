package Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ParsingAPI {
    String data = "";
    ArrayList<Recipe> outList = new ArrayList<>();
    public ArrayList<Recipe> parse(String url) throws IOException, JSONException {
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsonObject = new JSONObject(data);
            System.out.println("JSON: " + jsonObject.toString());

            JSONArray recipes = (JSONArray) jsonObject.getJSONArray("hits");

            System.out.println("Количество рецептов: " + recipes.length() );

            for (int i = 0; i < recipes.length(); i++){
                JSONObject curRecipeWrap = recipes.getJSONObject(i);
                JSONObject curRecipe = curRecipeWrap.getJSONObject("recipe");

                outList.add( new Recipe(
                        curRecipe.getString("label"),
                        curRecipe.getString("calories"),
                        curRecipe.getJSONArray("digest").getJSONObject(3).getString("total"),
                        curRecipe.getJSONArray("digest").getJSONObject(1).getString("total"),
                        curRecipe.getJSONArray("digest").getJSONObject(2).getString("total"),
                        curRecipe.getString("image"),
                        curRecipe.getJSONArray("dietLabels"),
                        curRecipe.getJSONArray("ingredientLines")));

                System.out.println(outList.get(i).toString());
            }

            return outList;

        }else{
            System.out.println("Ошибка подключения HTTPS");
        }


        return null;
    }
}
