package solidbeans.com.handla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;

import solidbeans.com.handla.util.RandomStuff;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HandlaAdapter listAdapter;
    private TextView errorMessage;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_handla);
        errorMessage = (TextView) findViewById(R.id.error_message);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listAdapter = new HandlaAdapter();
        recyclerView.setAdapter(listAdapter);
        spinner = (ProgressBar) findViewById(R.id.loading_indicator);
        populateHandlaList();
    }

    private void populateHandlaList() {
        String[] names = new String[30];
        for (int i = 0; i < 30; i++) {
            names[i] = RandomStuff.firstAndLastName();
        }
        listAdapter.setHandlaItems(names);
    }
}
