package solidbeans.com.handla;

import android.support.test.espresso.NoMatchingViewException;
import android.view.View;

import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.Item;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RecyclerViewItemTextAssertion extends RecyclerViewAssertion {
    private final String expectedText;
    private int position;
    private Class type;

    public RecyclerViewItemTextAssertion(int position, String expectedText, Class type) {
        this.expectedText = expectedText;
        this.position = position;
        this.type = type;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        super.check(view, noViewFoundException);
        Object o = itemsAdapter.itemAt(position);
        assertThat(o, isA(type));
        if(o instanceof Item) {
            Item item = (Item) o;
            assertThat(item.getItemType().getName(), is(expectedText));
        } else {
            Category c = (Category) o;
            assertThat(c.getName(), is(expectedText));
        }
    }
}
