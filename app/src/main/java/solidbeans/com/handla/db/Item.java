package solidbeans.com.handla.db;

@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class Item {

    private int id;

    private ItemType itemType;

    private int quantity;
    private String quantityType;
    private boolean checked = false;

    public Item() {
    }

    public Item(int id, long itemTypeId, int quantity, String quantityType, boolean checked) {
        this.id = id;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.checked = checked;
    }

    public String quantity() {
        return quantity + " " + quantityType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", quantity=" + quantity +
                ", quantityType='" + quantityType + '\'' +
                ", checked=" + checked +
                '}';
    }

    public void toggle() {
        checked = !checked;
    }
}
