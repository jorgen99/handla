package solidbeans.com.handla.view.list;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.function.Consumer;

import solidbeans.com.handla.R;
import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.DisplayCategory;
import solidbeans.com.handla.db.Item;
import solidbeans.com.handla.db.ItemType;

public class ListItem implements ListObject {

    @SuppressWarnings("unused")
    private static final String TAG = ListItem.class.getSimpleName();

    private String name;
    private int color;
    private int quantity;
    private String quantityType;
    private boolean checked = false;
    private String category;
    private ItemType itemType;
    private Consumer<ListEvent> listEventConsumer;

    public ListItem(Item item) {
        itemType = item.getItemType();
        this.name = itemType.getName();
        Category category = itemType.getCategory();
        this.color = Color.parseColor(category.getHexColor());
        this.quantity =item.getQuantity();
        this.quantityType = item.getQuantityType();
        this.checked = item.isChecked();
        this.category = itemType.getCategory().getName();
    }

    @Override
    public void bind(RecyclerView.ViewHolder vh, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) vh;
        TextView itemText = itemViewHolder.itemText;
        itemText.setText(name);
        itemViewHolder.itemQuantity.setText(quantity());
        itemViewHolder.marginColor.setBackgroundColor(color);
        CheckBox checkBox = itemViewHolder.checkBox;
        checkBox.setChecked(this.checked);
        checkBox.setOnCheckedChangeListener((view, isChecked) -> {
            int adapterPosition = itemViewHolder.getAdapterPosition();
            if(checkBox.isChecked()) {
                itemText.setPaintFlags(itemText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                int strikeOff = ~ Paint.STRIKE_THRU_TEXT_FLAG;
                itemText.setPaintFlags(itemText.getPaintFlags() & strikeOff);
            }

            listEventConsumer.accept(new ListEvent(adapterPosition));
        });

    }

    public ListCategory listCategory() {
        return new ListCategory(displayCategory());
    }

    @Override
    public void runOnListUpdate(Consumer<ListEvent> listEventConsumer) {
        this.listEventConsumer = listEventConsumer;
    }

    private DisplayCategory displayCategory() {
        if (checked) {
            return DisplayCategory.DONE;
        }
        Category cat = getItemType().getCategory();
        return new DisplayCategory(cat);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int viewType() {
        return ITEM_VIEW_TYPE;
    }

    private String quantity() {
        return quantity + " " + quantityType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItem listItem = (ListItem) o;

        return name.equals(listItem.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "ListItem@" + hashCode() + "{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", quantity=" + quantity +
                ", quantityType='" + quantityType + '\'' +
                ", checked=" + checked +
                ", category='" + category + '\'' +
                ", itemType=" + (itemType == null ? "null" : itemType.getName()) +
                '}';
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView itemText;
        final TextView itemQuantity;
        final CheckBox checkBox;
        final View marginColor;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_text);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            checkBox = itemView.findViewById(R.id.item_checkbox);
            marginColor = itemView.findViewById(R.id.item_margin_color);
        }
    }
}
