package solidbeans.com.handla.view.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.function.Consumer;

import solidbeans.com.handla.R;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public interface ListObject {

    int CATEGORY_VIEW_TYPE = 0;
    int ITEM_VIEW_TYPE = 1;

    void bind(ViewHolder vh, int position);

    String getName();

    int viewType();

    default void runOnListUpdate(Consumer<ListEvent> listEventConsumer) {
        // Do nothing by default
    }

    static ViewHolder createViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case CATEGORY_VIEW_TYPE:
                view = inflater.inflate(R.layout.category_separator, viewGroup, false);
                return new ListCategory.CategoryViewHolder(view);
            case ITEM_VIEW_TYPE:
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
                return new ListItem.ItemViewHolder(view);
        }
        return null;
    }
}
