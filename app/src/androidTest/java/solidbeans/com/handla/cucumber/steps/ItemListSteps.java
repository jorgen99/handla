package solidbeans.com.handla.cucumber.steps;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import solidbeans.com.handla.view.MainActivity;
import solidbeans.com.handla.R;
import solidbeans.com.handla.RecyclerViewItemCountAssertion;
import solidbeans.com.handla.RecyclerViewItemTextAssertion;
import solidbeans.com.handla.TestApp;
import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.Db;
import solidbeans.com.handla.db.Item;
import solidbeans.com.handla.util.ActivityFinisher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ItemListSteps  {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule
            = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        TestApp app = (TestApp) InstrumentationRegistry.getTargetContext()
                .getApplicationContext();
        Db db = app.getDb();
        db.dropAllData();
    }

    /**
     * All the clean up of application's data and state after each scenario must happen here
     * The last call of this method should always be the call to parent's tear down method
     */
    @After
    public void tearDown() throws Exception {
        ActivityFinisher.finishOpenActivities(); // Required for testing App with multiple activities
    }


    @Given("^I'm looking at the shoppinglist$")
    public void lookingAtList() {
        mainActivityRule.launchActivity(new Intent());
    }

    @When("^I enter the text \"([^\"]*)\" and hit return$")
    public void enterText(String text) {
        onView(ViewMatchers.withId(R.id.add_item))
                .perform(typeText(text + "\n"), closeSoftKeyboard());
    }

    @Then("^I should see (\\d+) items in the list$")
    public void number_of_items_in_the_list(int noOfItems) {
        onView(withId(R.id.recyclerview_handla))
                .check(new RecyclerViewItemCountAssertion(noOfItems));
    }

    @And("^item number (\\d+) should be an item of type \"([^\"]*)\"$")
    public void item_number_n_should_read(int itemNo, String text) {
        onView(withId(R.id.recyclerview_handla))
                .check(new RecyclerViewItemTextAssertion(itemNo - 1, text, Item.class));
    }

    @And("^item number (\\d+) should be a category header named \"([^\"]*)\"$")
    public void itemNumberShouldBeACategoryHeaderNamed(int itemNo, String name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        onView(withId(R.id.recyclerview_handla))
                .check(new RecyclerViewItemTextAssertion(itemNo - 1, name, Category.class));
    }
}
