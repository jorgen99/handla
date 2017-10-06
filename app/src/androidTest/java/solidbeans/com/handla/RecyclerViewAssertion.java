package solidbeans.com.handla;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import solidbeans.com.handla.view.list.ItemsAdapter;

abstract class RecyclerViewAssertion implements ViewAssertion {
    ItemsAdapter itemsAdapter;

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        itemsAdapter = (ItemsAdapter) recyclerView.getAdapter();
    }
}
