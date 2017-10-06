package solidbeans.com.handla;

import android.app.Application;
import android.util.Log;

import solidbeans.com.handla.db.Db;
import solidbeans.com.handla.db.FileDb;

public class App extends Application {

    private Db db;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App", "onCreate");
        db = new FileDb("real");
        db.defaultData();
    }

    public Db getDb() {
        return db;
    }


}