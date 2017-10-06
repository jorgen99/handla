package solidbeans.com.handla.db;

import android.support.annotation.NonNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Category implements Comparable<Category> {

    public static final Category UNCATEGORIZED
            = new Category("Okategoriserad", 0, "#999999");

    private String name;
    private int ordinal;
    private String hexColor;

    public Category(String name, int ordinal, String hexColor) {
        this.name = name;
        this.ordinal = ordinal;
        this.hexColor = hexColor;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOrdinal() {
        return this.ordinal;
    }
    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
    public String getHexColor() {
        return this.hexColor;
    }
    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", ordinal=" + ordinal +
                ", hexColor='" + hexColor + '\'' +
                '}';
    }

    public int compareTo(@NonNull Category o) {
        return this.getOrdinal() - o.getOrdinal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
