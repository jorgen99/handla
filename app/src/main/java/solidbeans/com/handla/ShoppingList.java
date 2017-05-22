package solidbeans.com.handla;

import android.app.Application;
import android.util.Log;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.CategoryDao;
import solidbeans.com.handla.db.DaoMaster;
import solidbeans.com.handla.db.DaoSession;
import solidbeans.com.handla.db.Item;
import solidbeans.com.handla.db.ItemDao;
import solidbeans.com.handla.db.ItemList;
import solidbeans.com.handla.db.ItemType;
import solidbeans.com.handla.db.ItemTypeDao;
import solidbeans.com.handla.db.MockDB;

class ShoppingList {

    private static final String TAG = ShoppingList.class.getSimpleName();

    private ItemList handlaItems;

    private ItemDao itemDao;
    private Query<Item> itemQuery;

    private DaoSession daoSession;
    private Query<Category> categoryQuery;
    private CategoryDao categoryDao;
    private ItemTypeDao itemTypeDao;
    private Query<ItemType> itemTypeQuery;

    void init(DaoSession daoSession) {
        this.daoSession = daoSession;

        createCategoryQuery();
        populateMockCategories();
        debugLogEntities(categoryQuery, "category");

        creteItemTypeQuery();
        populateItemTypes();
        debugLogEntities(itemTypeQuery, "item_type");

        createItemQuery();
        populateItemList();

    }

    void addItemFrom(String text) {
        Item item = itemFrom(text);
        itemDao.save(item);
        handlaItems.add(item);
    }

    void dropItemTables(Application application, String dbName) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(application, dbName);
        Database db = helper.getWritableDb();
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);
    }

    Item itemAt(int position) {
        return handlaItems.itemAt(position);
    }

    int size() {
        return handlaItems.size();
    }

    private Item itemFrom(String text) {
        Item item = new Item();
        ItemType itemType = itemTypeDao.queryBuilder()
                .where(ItemTypeDao.Properties.Name.eq(text))
                .orderAsc(ItemTypeDao.Properties.Name)
                .unique();

        if(itemType == null) {
            itemType = new ItemType();
            itemType.setName(text);
            Category uncategorized = categoryDao.queryBuilder()
                    .where(CategoryDao.Properties.Ordinal.eq(0))
                    .build()
                    .unique();
            itemType.setCategory(uncategorized);
            itemTypeDao.save(itemType);
        }
        item.setItemType(itemType);

        String type = randomQuantityType();
        item.setQuantityType(type);
        int quantity = new Random().nextInt(9) + 1;
        if(type.equals("g")) {
            quantity *= 100;
        }
        item.setQuantity(quantity);
        item.setChecked(false);
        return item;
    }

    private String randomQuantityType() {
        List<String> types = Arrays.asList("st", "g", "kg", "paket", "burk", "påse");
        return types.get(new Random().nextInt(types.size()));
    }

    private void populateItemList() {
        ItemList gemensamma = new ItemList(null, "Gemensamma");
        daoSession.getItemListDao().save(gemensamma);
        this.handlaItems = gemensamma;
    }

    private void createCategoryQuery() {
        categoryDao = daoSession.getCategoryDao();
        categoryQuery = categoryDao.queryBuilder()
                .orderAsc(CategoryDao.Properties.Name)
                .build();
    }

    private void populateMockCategories() {
        categoryDao.deleteAll();
        MockDB.constructCategories(categoryDao);
    }

    private void populateItemTypes() {
        itemTypeDao.deleteAll();
        MockDB.constructItemTypes(categoryQuery.list(), itemTypeDao);
    }

    private void creteItemTypeQuery() {
        itemTypeDao = daoSession.getItemTypeDao();
        itemTypeQuery = itemTypeDao.queryBuilder()
                .orderAsc(ItemTypeDao.Properties.Name)
                .build();
    }

    private void createItemQuery() {
        itemDao = daoSession.getItemDao();
        itemQuery = itemDao.queryBuilder()
                .build();
    }

    private <T> void debugLogEntities(Query<T> query, String type) {
        List<T> list = query.list();
        for (T entity : list) {
            Log.d(TAG, type + ": " + entity);
        }
    }
}
