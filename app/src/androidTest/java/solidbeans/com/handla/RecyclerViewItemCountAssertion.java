package solidbeans.com.handla;

import android.support.test.espresso.NoMatchingViewException;
import android.view.View;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class RecyclerViewItemCountAssertion extends RecyclerViewAssertion {
    private final int expectedCount;

    RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        super.check(view, noViewFoundException);
        assertThat(listAdapter.getItemCount(), is(expectedCount));
    }
}
