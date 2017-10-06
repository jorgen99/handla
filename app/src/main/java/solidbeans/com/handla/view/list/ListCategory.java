package solidbeans.com.handla.view.list;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import solidbeans.com.handla.R;
import solidbeans.com.handla.db.DisplayCategory;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class ListCategory implements ListObject {

    private static final String TAG = ListObject.class.getSimpleName();

    private String name;

    ListCategory(DisplayCategory category) {
        this.name = category.getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int viewType() {
        return CATEGORY_VIEW_TYPE;
    }

    @Override
    public void bind(ViewHolder viewHolder, int position) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder) viewHolder;
        categoryViewHolder.categoryText.setText(name);
        if(!DisplayCategory.DONE.getName().equals(name)) {
            categoryViewHolder.clearButton.setVisibility(View.GONE);
        } else {
            categoryViewHolder.clearButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String toString() {
        return "ListCategory@" + hashCode() + "{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListCategory that = (ListCategory) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    static class CategoryViewHolder extends ViewHolder {
        final TextView categoryText;
        final Button clearButton;

        CategoryViewHolder(View categoryView) {
            super(categoryView);
            categoryText = categoryView.findViewById(R.id.category_text);
            categoryText.setBackgroundColor(Color.LTGRAY);
            clearButton = categoryView.findViewById(R.id.clear_done);
        }


    }
}
