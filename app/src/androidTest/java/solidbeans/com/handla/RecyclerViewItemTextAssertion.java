package solidbeans.com.handla;

import android.support.test.espresso.NoMatchingViewException;
import android.view.View;

import solidbeans.com.handla.db.Item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RecyclerViewItemTextAssertion extends RecyclerViewAssertion {
    private final String expectedText;
    private int position;

    public RecyclerViewItemTextAssertion(int position, String expectedText) {
        this.expectedText = expectedText;
        this.position = position;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        super.check(view, noViewFoundException);
        Object o = itemsAdapter.itemAt(position);
        if(o instanceof Item) {
            Item item = (Item) o;
            assertThat(item.getItemType().getName(), is(expectedText));
        } else {
            fail("Not an item");
        }
    }
}
