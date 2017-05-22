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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoSession daoSession = ((App) getApplication()).getDbComponent().getDaoSession();

        setContentView(R.layout.activity_list);
        setUpView();
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.dropItemTables(getApplication(), "handla-db");
        itemsAdapter.setShoppingList(shoppingList);
        itemsAdapter.loadItems(daoSession);
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
                        itemsAdapter.addItemFrom(text);
                        v.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

}
