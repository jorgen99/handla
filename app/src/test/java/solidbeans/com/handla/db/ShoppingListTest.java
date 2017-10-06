package solidbeans.com.handla.db;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import solidbeans.com.handla.view.list.ListCategory;
import solidbeans.com.handla.view.list.ListItem;
import solidbeans.com.handla.view.list.ListObject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShoppingListTest {

    private FileDb db;

    @Before
    public void setUp() throws Exception {
        db = new FileDb("junit");
    }

    @Test
    public void it_should_add_one_item_and_it_should_have_the_correct_itemtype() throws Exception {
        // Given we have a db with categories and item types
        db.defaultData();

        // When we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Potatis");

        // When we list items we should have one item
        List<Item> items = db.listItems();
        assertThat(items.size(), is(1));
        Item potatoes = items.get(0);
        assertThat(potatoes.getItemType().getName(), is(equalTo("Potatis")));
        assertThat(potatoes.getItemType().getCategory().getName(), is(equalTo("Frukt & Grönt")));
    }

    @Test
    public void updating_an_item_will_not_create_a_duplicate() throws Exception {
        // GIVEN we have a db with categories and item types
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Potatis");
        List<Item> items = db.listItems();
        assertThat(items.size(), is(1));
        Item potatoes = items.get(0);
        int hashCode = potatoes.hashCode();

        // AND we add another item of the same type
        shoppingList.addItemWithName("Potatis");

        // THEN we should get the same Item back
        items = db.listItems();
        assertThat(items.size(), is(1));
        potatoes = items.get(0);
        assertThat(hashCode, is(potatoes.hashCode()));
    }

    @Test
    public void it_should_sort_items_according_to_category_ordinal_and_item_type_name()
            throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Potatis");
        shoppingList.addItemWithName("Glass");
        shoppingList.addItemWithName("Mjölk");

        // THEN it should have Categories and Items
        // in the correct sort order
        assertCatetory(shoppingList, 0, "Frukt & Grönt");
        assertItem(shoppingList, 1, "Potatis");
        assertCatetory(shoppingList, 2, "Mejeri");
        assertItem(shoppingList, 3, "Mjölk");
        assertItem(shoppingList, 4, "Vispgrädde");
        assertCatetory(shoppingList, 5, "Fryst");
        assertItem(shoppingList, 6, "Glass");
    }

    @Test
    public void it_should_add_uncategorized_items_at_the_top() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Något nytt");

        assertCatetory(shoppingList, 0, "Okategoriserad");
        assertItem(shoppingList, 1, "Något nytt");

        assertCatetory(shoppingList, 2, "Mejeri");
        assertItem(shoppingList, 3, "Mjölk");
        assertItem(shoppingList, 4, "Vispgrädde");
    }

    @Test
    public void the_size_should_count_both_categories_and_items() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Något nytt");

        assertThat(shoppingList.size(), is(5));
    }

    @Test
    public void it_should_have_a_done_category_at_the_bottom_of_the_list() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Glass");
        // Mejeri
        //   Mjölk
        //   Vispgrädde
        // Fryst
        //   Glass

        // Toggle Vispgrädde from Mejeri do Avklarade
        shoppingList.toggleItemAt(2);
        // Mejeri
        //   Mjölk
        // Fryst
        //   Glass
        // Avklarade
        //   Vispgrädde
        assertCatetory(shoppingList, 0, "Mejeri");
        assertItem(shoppingList, 1, "Mjölk");

        assertCatetory(shoppingList, 2, "Fryst");
        assertItem(shoppingList, 3, "Glass");

        assertCatetory(shoppingList, 4, "Avklarade");
        assertItem(shoppingList, 5, "Vispgrädde");

        // Toggle Mjölk from Mejeri to Avklarade
        shoppingList.toggleItemAt(1);
        // Fryst
        //   Glass
        // Avklarade
        //   Mjölk
        //   Vispgrädde
        assertCatetory(shoppingList, 0, "Fryst");
        assertItem(shoppingList, 1, "Glass");

        assertCatetory(shoppingList, 2, "Avklarade");
        assertItem(shoppingList, 3, "Mjölk");
        assertItem(shoppingList, 4, "Vispgrädde");

        // Toggle Mjölk from Avklarade to Mejeri
        shoppingList.toggleItemAt(3);
        // Mejeri
        //   Mjölk
        // Fryst
        //   Glass
        // Avklarade
        //   Vispgrädde
        assertCatetory(shoppingList, 0, "Mejeri");
        assertItem(shoppingList, 1, "Mjölk");

        assertCatetory(shoppingList, 2, "Fryst");
        assertItem(shoppingList, 3, "Glass");

        assertCatetory(shoppingList, 4, "Avklarade");
        assertItem(shoppingList, 5, "Vispgrädde");
    }

    @Test
    public void it_should_only_have_one_done_category_at_the_bottom() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Glass");
        // Mejeri
        //   Mjölk
        //   Vispgrädde
        // Fryst
        //   Glass

        // Toggle Vispgrädde from Mejeri do Avklarade
        shoppingList.toggleItemAt(2);
        // Mejeri
        //   Mjölk
        // Fryst
        //   Glass
        // Avklarade
        //   Vispgrädde

        // Toggle Mjölk from Mejeri to Avklarade
        shoppingList.toggleItemAt(1);
        // Fryst
        //   Glass
        // Avklarade
        //   Mjölk
        //   Vispgrädde
        assertCatetory(shoppingList, 0, "Fryst");
        assertItem(shoppingList, 1, "Glass");

        assertCatetory(shoppingList, 2, "Avklarade");
        assertItem(shoppingList, 3, "Mjölk");
        assertItem(shoppingList, 4, "Vispgrädde");
    }

    @Test
    public void it_should_move_unchecked_items_back_to_the_correct_category() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Glass");
        // Mejeri
        //   Mjölk
        //   Vispgrädde
        // Fryst
        //   Glass

        // Toggle Vispgrädde from Mejeri do Avklarade
        shoppingList.toggleItemAt(2);
        // Mejeri
        //   Mjölk
        // Fryst
        //   Glass
        // Avklarade
        //   Vispgrädde

        // Toggle Mjölk from Mejeri to Avklarade
        shoppingList.toggleItemAt(1);
        // Fryst
        //   Glass
        // Avklarade
        //   Mjölk
        //   Vispgrädde

        // Toggle Glass from Fryst to Avklarade
        shoppingList.toggleItemAt(1);
        // Avklarade
        //   Glass
        //   Mjölk
        //   Vispgrädde
        assertCatetory(shoppingList, 0, "Avklarade");
        assertItem(shoppingList, 1, "Glass");
        assertItem(shoppingList, 2, "Mjölk");
        assertItem(shoppingList, 3, "Vispgrädde");
        assertThat(shoppingList.size(), is(4));
    }

    @Test
    public void checking_the_last_one_will_change_heading_to_done() throws Exception {
        // Given we have the default Categories and ItemTypes
        db.defaultData();

        // WHEN we add an item to the current shoppinglist
        ShoppingList shoppingList = db.currentShoppingList();
        shoppingList.addItemWithName("Vispgrädde");
        shoppingList.addItemWithName("Mjölk");
        shoppingList.addItemWithName("Glass");
        // Mejeri
        //   Mjölk
        //   Vispgrädde
        // Fryst
        //   Glass

        // Move Glass from Fryst to Avklarade
        shoppingList.toggleItemAt(4);
        // Mejeri
        //   Mjölk
        //   Vispgrädde
        // Avklarade
        //   Glass

        assertCatetory(shoppingList, 0, "Mejeri");
        assertItem(shoppingList, 1, "Mjölk");
        assertItem(shoppingList, 2, "Vispgrädde");
        assertCatetory(shoppingList, 3, "Avklarade");
        assertItem(shoppingList, 4, "Glass");
        assertThat(shoppingList.size(), is(5));
    }
    private void assertItem(ShoppingList shoppingList, int position, String name) {
        ListItem firstItem = (ListItem) shoppingList.itemAt(position);
        assertThat(firstItem.getItemType().getName(), is(equalTo(name)));
    }

    private void assertCatetory(ShoppingList shoppingList, int position, String name) {
        ListCategory firstCategory = (ListCategory) shoppingList.itemAt(position);
        assertThat(firstCategory.getName(), is(equalTo(name)));
    }
}