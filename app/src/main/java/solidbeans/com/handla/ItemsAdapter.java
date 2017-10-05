package solidbeans.com.handla;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.DaoSession;
import solidbeans.com.handla.db.Item;

import static android.support.v7.widget.RecyclerView.*;

class ItemsAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static final int CATEGORY_VIEW_TYPE = 0;
    public static final int ITEM_VIEW_TYPE = 1;

    @SuppressWarnings("unused")
    public static final String TAG = ItemsAdapter.class.getSimpleName();

    private ShoppingList shoppingList;

    @Override
    public int getItemViewType(int position) {
        Object o = shoppingList.itemAt(position);
        if(o instanceof Category) {
            return CATEGORY_VIEW_TYPE;
        } else if(o instanceof Item) {
            return ITEM_VIEW_TYPE;
        } else {
            return -1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case CATEGORY_VIEW_TYPE:
                view = inflater.inflate(R.layout.category_separator, viewGroup, false);
                return new CategoryViewHolder(view);
            case ITEM_VIEW_TYPE:
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
                return new ItemViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        Object o = shoppingList.itemAt(position);
        if(o instanceof Item) {
            Item item = (Item) o;
            ItemViewHolder itemViewHolder = (ItemViewHolder) vh;
            itemViewHolder.itemText.setText(item.getItemType().getName());
            itemViewHolder.itemQuantity.setText(item.quantity());
            itemViewHolder.checkBox.setChecked(item.isChecked());
            Category category = item.getItemType().getCategory();
            String hexColor = category.getHexColor();
            int color = Color.parseColor(hexColor);
            itemViewHolder.marginColor.setBackgroundColor(color);
        } else if (o instanceof Category) {
            Category category = (Category)o;
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder)vh;
            categoryViewHolder.categoryText.setText(category.getName());
            String hexColor = category.getHexColor();
            int color = Color.parseColor(hexColor);
            categoryViewHolder.marginColor.setBackgroundColor(color);
        }
//        Log.d(TAG, "color is: " + hexColor + ", for category: " + category.getName());
    }

    @Override
    public int getItemCount() {
        return shoppingList == null ? 0 : shoppingList.size();
    }

    void loadItems(DaoSession daoSession) {
        shoppingList.init(daoSession);
        notifyDataSetChanged();
    }

    void addItemFrom(String text) {
        shoppingList.addItemFrom(text);
        notifyDataSetChanged();
    }

    Object itemAt(int position) {
        return shoppingList.itemAt(position);
    }

    void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    static class ItemViewHolder extends ViewHolder {
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

    static class CategoryViewHolder extends ViewHolder {
        final TextView categoryText;
        final View marginColor;

        CategoryViewHolder(View categoryView) {
            super(categoryView);
            categoryText = categoryView.findViewById(R.id.category_text);
            marginColor = categoryView.findViewById(R.id.category_margin_color);
        }
    }
}
