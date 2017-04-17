package solidbeans.com.handla;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class HandlaAdapter extends RecyclerView.Adapter<HandlaAdapter.HandlaAdapterViewHolder> {

    private String[] handlaItems;

    @Override
    public HandlaAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, viewGroup, false);
        return new HandlaAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HandlaAdapterViewHolder handlaAdapterViewHolder, int position) {
        String handlaItem = handlaItems[position];
        handlaAdapterViewHolder.handlaTextView.setText(handlaItem);
    }

    @Override
    public int getItemCount() {
        return handlaItems == null ? 0 : handlaItems.length;
    }

    void setHandlaItems(String[] handlaItems) {
        this.handlaItems = handlaItems;
        notifyDataSetChanged();
    }

    class HandlaAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView handlaTextView;

        HandlaAdapterViewHolder(View itemView) {
            super(itemView);
            handlaTextView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
