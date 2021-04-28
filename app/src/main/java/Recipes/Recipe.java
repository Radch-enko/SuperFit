package Recipes;

import android.graphics.drawable.Drawable;

public class Recipe {
    private String title, kcal, info, type;
    private Drawable img;
    public Recipe(String title, String kcal, String info, Drawable img, String type){
        this.title = title;
        this.kcal = kcal;
        this.info = info;
        this.img = img;
        this.type = type;
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

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
