package solidbeans.com.handla.db;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.DbLineParser;
import solidbeans.com.handla.db.ItemType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DbLineParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_it_should_create_a_category_from_a_semicolon_separated_string() throws Exception {
        String category = "5;Kryddor & Olja;#0665AF";
        Category c = DbLineParser.createCategory(category);
        assertThat(c.getName(), is(equalTo("Kryddor & Olja")));
        assertThat(c.getHexColor(), is(equalTo("#0665AF")));
        assertThat(c.getOrdinal(), is(5));
    }

    @Test
    public void it_should_create_a_category_list() throws Exception {
        String cs = "0;Okategoriserad;#999999\n" +
                "1;Happy Vegan;#C9D8C5\n" +
                "2;Rollands;#86D0A2\n" +
                "3;Fiskbilen;#697B8A";

        List<Category> categories = DbLineParser.createCategoryList(cs);

        assertThat(categories.size(), is(4));
        assertThat(categories.get(0).getName(), is(equalTo("Okategoriserad")));
        for (Category category : categories) {
            assertThat(category.getHexColor().length(), is(7));
        }
    }

    @Test
    public void it_should_create_the_big_list_of_categories() throws Exception {
        List<Category> categories = DbLineParser.constructCategories();
        List<ItemType> itemTypes = DbLineParser.constructItemTypes(categories);

        assertThat(itemTypes.size(), is(480));
        assertThat(itemTypes.get(0).getName(), is("9V batteri"));
        assertThat(itemTypes.get(0).getCategory().getName(), is("Godis & Kassan"));

        assertThat(itemTypes.get(itemTypes.size() - 1).getName(), is("Örtsalt"));

    }
}