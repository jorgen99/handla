package solidbeans.com.handla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    private EditText addItem;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private TextView errorMessage;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setUpAddItem();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_handla);
        errorMessage = (TextView) findViewById(R.id.error_message);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        spinner = (ProgressBar) findViewById(R.id.loading_indicator);
        populateItemList();
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
                        listAdapter.addItem(text);
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
        LinkedList<String> names = new LinkedList<>();
        listAdapter.setHandlaItems(names);
    }
}
