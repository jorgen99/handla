package solidbeans.com.handla.db;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileDbTest {

    private FileDb db;

    @Before
    public void setUp() throws Exception {
        db = new FileDb("junit");
    }

    @Test
    public void the_db_should_be_empty() {
        assertThat(db.listCategories().isEmpty(), is(true));
        assertThat(db.listItemTypes().isEmpty(), is(true));
        assertThat(db.listItems().isEmpty(), is(true));
    }

    @Test
    public void the_db_should_be_prepopulated() {
        db.defaultData();
        assertThat(db.listCategories().size(), is(15));
        assertThat(db.listItemTypes().size(), is(480));
        assertThat(db.listItems().isEmpty(), is(true));
    }

    @Test
    public void it_should_find_item_types_by_name() throws Throwable {
        db.defaultData();
        ItemType cabbage = db.itemTypeWithName("Vitkål").orElseThrow(() -> new RuntimeException("No cabbage found"));
        assertThat(cabbage.getId(), is(455));
        assertThat(cabbage.getName(), is(equalTo("Vitkål")));
        assertThat(cabbage.getCategory().getName(), is(equalTo("Frukt & Grönt")));
    }

    @Test
    public void it_should_delete_all_data() throws Exception {
        db.defaultData();
        db.dropAllData();

        assertThat(db.listCategories().isEmpty(), is(true));
        assertThat(db.listItemTypes().isEmpty(), is(true));
        assertThat(db.listItems().isEmpty(), is(true));
    }



}