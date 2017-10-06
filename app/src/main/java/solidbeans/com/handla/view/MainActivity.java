package solidbeans.com.handla.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import solidbeans.com.handla.App;
import solidbeans.com.handla.R;
import solidbeans.com.handla.db.Db;
import solidbeans.com.handla.db.ShoppingList;
import solidbeans.com.handla.view.list.ItemsAdapter;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText addItem;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    @SuppressWarnings("unused")
    private TextView errorMessage;
    @SuppressWarnings("unused")
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Db db = ((App) getApplication()).getDb();

        setContentView(R.layout.activity_list);
        setUpView();
        ShoppingList shoppingList = db.currentShoppingList();
        itemsAdapter.setShoppingList(shoppingList);
        itemsAdapter.notifyDataSetChanged();
    }

    private void setUpView() {
        setUpAddItem();
        errorMessage = findViewById(R.id.error_message);

        recyclerView = findViewById(R.id.recyclerview_handla);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemsAdapter = new ItemsAdapter();
        recyclerView.setAdapter(itemsAdapter);
        spinner = findViewById(R.id.loading_indicator);
    }

    private void setUpAddItem() {
        addItem = findViewById(R.id.add_item);
        addItem.setImeActionLabel(getString(R.string.add_button_text), KeyEvent.KEYCODE_ENTER);
        addItem.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                String text = v.getText().toString();
                if (!text.equals("")) {
                    itemsAdapter.addItemFrom(text);
                    v.setText("");
                    return true;
                }
            }
            return false;
        });
    }

}
