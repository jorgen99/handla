package solidbeans.com.handla;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListAdapterViewHolder> {

    private LinkedList<String> handlaItems;

    @Override
    public ListAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, viewGroup, false);
        return new ListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterViewHolder listAdapterViewHolder, int position) {
        String handlaItem = handlaItems.get(position);
        listAdapterViewHolder.handlaTextView.setText(handlaItem);
    }

    @Override
    public int getItemCount() {
        return handlaItems == null ? 0 : handlaItems.size();
    }

    void setHandlaItems(LinkedList<String> handlaItems) {
        this.handlaItems = handlaItems;
        notifyDataSetChanged();
    }

    void addItem(String item) {
        this.handlaItems.addFirst(item);
        notifyDataSetChanged();
    }

    class ListAdapterViewHolder extends RecyclerView.ViewHolder {
        final TextView handlaTextView;

        ListAdapterViewHolder(View itemView) {
            super(itemView);
            handlaTextView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
