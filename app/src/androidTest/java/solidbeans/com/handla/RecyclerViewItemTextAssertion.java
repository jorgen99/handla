package solidbeans.com.handla;

import android.support.test.espresso.NoMatchingViewException;
import android.view.View;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        assertThat(listAdapter.itemAt(position), is(expectedText));
    }
}
