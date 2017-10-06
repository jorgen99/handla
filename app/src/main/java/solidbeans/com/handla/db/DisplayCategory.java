package solidbeans.com.handla.db;

import android.graphics.Color;

public class DisplayCategory {

    public static final DisplayCategory DONE = new DisplayCategory("Avklarade", "#EEFFEE");

    private String name;
    private String hexColor;

    public DisplayCategory(String name, String hexColor) {
        this.name = name;
        this.hexColor = hexColor;
    }

    public DisplayCategory(Category category) {
        this.name = category.getName();
        this.hexColor = category.getHexColor();
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return Color.parseColor(hexColor);
    }

    @Override
    public String toString() {
        return "DisplayCategory{" +
                "name='" + name + '\'' +
                ", hexColor='" + hexColor + '\'' +
                '}';
    }
}
