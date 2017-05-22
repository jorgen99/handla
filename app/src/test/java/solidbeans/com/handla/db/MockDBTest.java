package solidbeans.com.handla.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.test.DbTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import javax.inject.Inject;

import solidbeans.com.handla.MainActivity;
import solidbeans.com.handla.di.AppModule;
import solidbeans.com.handla.di.DaggerDbComponent;
import solidbeans.com.handla.di.DbComponent;
import solidbeans.com.handla.di.DbModule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@Ignore
public class MockDBTest {

    private DaoSession daoSession;
    private CategoryDao categoryDao;
    private Query<Category> categoryQuery;
    private ItemTypeDao itemTypeDao;
    private Query<ItemType> itemTypeQuery;

    @Before
    public void setUp() throws Exception {
        Context context = mock(MainActivity.class);
//        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "unit-test");
//        Database db = helper.getWritableDb();
        daoSession = DaoMaster.newDevSession(context, "unit-test");

        categoryDao = this.daoSession.getCategoryDao();
        categoryQuery = categoryDao.queryBuilder()
                .orderAsc(CategoryDao.Properties.Name)
                .build();
        itemTypeDao = this.daoSession.getItemTypeDao();
        itemTypeQuery = itemTypeDao.queryBuilder()
                .orderAsc(ItemTypeDao.Properties.Name)
                .build();
    }

    @Test
    public void it_should_create_a_category_from_a_semicolon_separated_string() throws Exception {
        String category = "5;Kryddor & Olja;#0665AF";
        Category c = MockDB.createCategory(category);
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

        List<Category> categories = MockDB.createCategoryList(cs);

        assertThat(categories.size(), is(4));
        assertThat(categories.get(0).getName(), is(equalTo("Okategoriserad")));
        for (Category category : categories) {
            assertThat(category.getHexColor().length(), is(7));
        }
    }

    @Test
    public void it_should_create_the_big_list_of_categories() throws Exception {
        List<Category> categories = MockDB.constructCategories(categoryDao);
        List<ItemType> itemTypes = MockDB.constructItemTypes(categories, itemTypeDao);

        assertThat(itemTypes.size(), is(480));
        assertThat(itemTypes.get(0).getName(), is("9V batteri"));
        assertThat(itemTypes.get(0).getCategory().getName(), is("Godis & Kassan"));

        assertThat(itemTypes.get(itemTypes.size() - 1).getName(), is("Örtsalt"));

    }
}