package solidbeans.com.handla.db;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ItemType {

    private int id;
    private String name;

    private Category category;

    public ItemType(int id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public ItemType(String name, int categoryId) {
        this.id = id;
        this.name = name;
    }

    public ItemType() {
    }

    public ItemType(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "ItemType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }

}
