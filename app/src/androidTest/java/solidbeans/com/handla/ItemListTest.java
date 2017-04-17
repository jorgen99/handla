package solidbeans.com.handla;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ItemListTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void it_should_add_one_item_to_the_list() throws Exception {
        onView(withId(R.id.add_item))
                .perform(typeText("Tomatoes\n"), closeSoftKeyboard());
        onView(withId(R.id.recyclerview_handla))
                .check(new RecyclerViewItemCountAssertion(1));
        onView(withId(R.id.recyclerview_handla))
                .check(new RecyclerViewItemTextAssertion(0, "Tomatoes"));
    }

    @Test
    public void it_should_add_items_to_the_beginning_of_the_list() throws Exception {
        onView(withId(R.id.add_item))
                .perform(typeText("Tomatoes\n"), closeSoftKeyboard());

        onView(withId(R.id.add_item))
                .perform(typeText("Bananas\n"), closeSoftKeyboard());

        onView(withId(R.id.add_item))
                .perform(typeText("Loaf of bread\n"), closeSoftKeyboard());

        ViewInteraction onRecycleView = onView(withId(R.id.recyclerview_handla));
        onRecycleView.check(new RecyclerViewItemCountAssertion(3));
        onRecycleView.check(new RecyclerViewItemTextAssertion(0, "Loaf of bread"));
        onRecycleView.check(new RecyclerViewItemTextAssertion(1, "Bananas"));
        onRecycleView.check(new RecyclerViewItemTextAssertion(2, "Tomatoes"));
    }


}
