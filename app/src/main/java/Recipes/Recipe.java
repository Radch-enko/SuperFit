package Recipes;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String title, info;
    private ArrayList<String> type, ingredientLines;
    private String kcal;
    private String img;
    private String protein, fat, carbs;


    // формат для числовых значений БЖУ и ккал
    DecimalFormat format = new DecimalFormat("#0");


    public Recipe(String title, String kcal, String protein, String fat, String carbs, String img, JSONArray type, JSONArray ingredientLines) throws JSONException {
        this.title = title;
        this.kcal = format.format(Double.valueOf(kcal)) + " kcal";
        this.img = img;
        this.type = new ArrayList<String>();
        for(int i = 0; i < type.length(); i++){
            this.type.add(type.getString(i));
        }

        this.ingredientLines = new ArrayList<>();
        for(int i = 0; i < ingredientLines.length(); i++){
            this.ingredientLines.add(ingredientLines.getString(i));
        }

        this.protein = format.format(Double.valueOf(protein));
        this.fat = format.format(Double.valueOf(fat));
        this.carbs = format.format(Double.valueOf(carbs));

        this.info = this.protein + "g protein • " + this.fat + "g fat • " + this.carbs + "g carbs";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = format.format(kcal);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String protein, String fat, String carbs) {
        this.info = protein + " • " + fat + " • " + carbs;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "title: " + title + " kcal: " + kcal + "protein: " + protein + " fat: " + fat
                + " carbs: " + carbs + " type: " + type + " ingredients: " + ingredientLines;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = format.format(protein);
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = format.format(fat);
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = format.format(carbs);
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }
}
