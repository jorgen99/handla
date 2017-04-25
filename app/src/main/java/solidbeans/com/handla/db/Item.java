package solidbeans.com.handla.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Item {

    @Id(autoincrement = true)
    private Long id;
    private String text;
    private int quantity;
    private String quantityType;
    private boolean checked = false;

    @Generated(hash = 1470900980)
    public Item() {
    }

    @Generated(hash = 863483346)
    public Item(Long id, String text, int quantity, String quantityType,
            boolean checked) {
        this.id = id;
        this.text = text;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.checked = checked;
    }

    public Item(String text, int quantity, String quantityType) {
        this.text = text;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public String quantity() {
        return quantity + " " + quantityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return this.checked;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", quantity=" + quantity +
                ", quantityType='" + quantityType + '\'' +
                ", checked=" + checked +
                '}';
    }
}
