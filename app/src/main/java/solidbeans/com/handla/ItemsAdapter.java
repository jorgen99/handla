package solidbeans.com.handla;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.LinkedList;

import solidbeans.com.handla.db.Item;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private LinkedList<Item> handlaItems;

    void addItem(Item item) {
        this.handlaItems.addFirst(item);
        notifyDataSetChanged();
    }

    Item itemAt(int position) {
        return handlaItems.get(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        Item item = handlaItems.get(position);
        viewHolder.itemText.setText(item.getItemType().getName());
        viewHolder.itemQuantity.setText(item.quantity());
        viewHolder.checkBox.setChecked(item.isChecked());
    }

    @Override
    public int getItemCount() {
        return handlaItems == null ? 0 : handlaItems.size();
    }

    void setHandlaItems(LinkedList<Item> handlaItems) {
        this.handlaItems = handlaItems;
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView itemText;
        final TextView itemQuantity;
        final CheckBox checkBox;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.item_text);
            itemQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
            checkBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
        }
    }
}
