package solidbeans.com.handla.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Category {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;
    private int ordinal;
    private String hexColor;
    @Generated(hash = 2567257)
    public Category(Long id, String name, int ordinal, String hexColor) {
        this.id = id;
        this.name = name;
        this.ordinal = ordinal;
        this.hexColor = hexColor;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }

    public Category(String name, int ordinal, String hexColor) {
        this.name = name;
        this.ordinal = ordinal;
        this.hexColor = hexColor;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", ordinal=" + ordinal +
                ", hexColor='" + hexColor + '\'' +
                '}';
    }
}
