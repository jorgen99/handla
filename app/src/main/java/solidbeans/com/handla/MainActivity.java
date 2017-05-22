package solidbeans.com.handla;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import solidbeans.com.handla.db.Category;
import solidbeans.com.handla.db.CategoryDao;
import solidbeans.com.handla.db.DaoMaster;
import solidbeans.com.handla.db.DaoSession;
import solidbeans.com.handla.db.Item;
import solidbeans.com.handla.db.ItemDao;
import solidbeans.com.handla.db.ItemType;
import solidbeans.com.handla.db.ItemTypeDao;
import solidbeans.com.handla.db.MockDB;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    private static final String logTag = MainActivity.class.getSimpleName();

    private EditText addItem;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private TextView errorMessage;
    private ProgressBar spinner;
    private ItemDao itemDao;
    private Query<Item> itemQuery;

    private DaoSession daoSession;
    private Query<Category> categoryQuery;
    private CategoryDao categoryDao;
    private ItemTypeDao itemTypeDao;
    private Query<ItemType> itemTypeQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dropItemTables();
        daoSession = ((App) getApplication()).getDbComponent().getDaoSession();



        setContentView(R.layout.activity_list);
        setUpView();

        createItemQuery();

        createCategoryQuery();
        populateMockCategories();
        debugLogEntities(categoryQuery, "category");
//
        creteItemTypeQuery();
        populateItemTypes();
        debugLogEntities(itemTypeQuery, "item_type");

        populateItemList();
    }

    private <T> void debugLogEntities(Query<T> query, String type) {
        List<T> list = query.list();
        for (T entity : list) {
            Log.d(logTag, type + ": " + entity);
        }
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
//                .orderAsc(ItemDao.Properties.)
                .build();
    }

    private void setUpView() {
        setUpAddItem();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_handla);
        errorMessage = (TextView) findViewById(R.id.error_message);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemsAdapter = new ItemsAdapter();
        recyclerView.setAdapter(itemsAdapter);
        spinner = (ProgressBar) findViewById(R.id.loading_indicator);
    }

    private void setUpAddItem() {
        addItem = (EditText) findViewById(R.id.add_item);
        addItem.setImeActionLabel(getString(R.string.add_button_text), KeyEvent.KEYCODE_ENTER);
        addItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String text = v.getText().toString();
                    if (!text.equals("")) {
                        Item item = itemFrom(text);
                        itemDao.save(item);
                        itemsAdapter.addItem(item);
                        v.setText("");
                        return true;
                    }
//                } else if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
//                    v.requestFocus();
//                    return true;
                }
                return false;
            }
        });
    }

    private void populateItemList() {
        LinkedList<Item> names = new LinkedList<>(itemQuery.list());
        itemsAdapter.setHandlaItems(names);
    }

    private void dropItemTables() {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(), "handla-db");
        Database db = helper.getWritableDb();
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);
//        CategoryDao.dropTable(db, true);
//        ItemTypeDao.dropTable(db, true);
//        ItemDao.dropTable(db, true);
    }

    @NonNull
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

}
