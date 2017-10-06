package solidbeans.com.handla.view.list;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import solidbeans.com.handla.db.ShoppingList;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class ItemsAdapter extends RecyclerView.Adapter<ViewHolder> {

    @SuppressWarnings("unused")
    public static final String TAG = ItemsAdapter.class.getSimpleName();

    private ShoppingList shoppingList;
    private RecyclerView recyclerView;

    @Override
    public int getItemViewType(int position) {
        ListObject o = shoppingList.itemAt(position);
        return o.viewType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return ListObject.createViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        ListObject listObject = shoppingList.itemAt(position);
        listObject.bind(viewHolder, position);
        listObject.runOnListUpdate(listEvent -> {
            int evenPosition = listEvent.getPosition();
            shoppingList.toggleItemAt(evenPosition);
            notifyIfNotUpdatingView(new Handler());
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }


    @Override
    public int getItemCount() {
        return shoppingList == null ? 0 : shoppingList.size();
    }


    private void notifyIfNotUpdatingView(final Handler handler) {
        handler.post(() -> {
            if (recyclerView.isComputingLayout()) {
                notifyIfNotUpdatingView(handler);
            } else {
                notifyDataSetChanged();
            }
        });
    }

    public void addItemFrom(String text) {
        shoppingList.addItemWithName(text);
        notifyDataSetChanged();
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
