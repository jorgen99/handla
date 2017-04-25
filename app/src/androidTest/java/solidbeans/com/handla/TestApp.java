package solidbeans.com.handla;

import android.app.Application;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import solidbeans.com.handla.db.DaoMaster;
import solidbeans.com.handla.db.DaoMaster.DevOpenHelper;
import solidbeans.com.handla.db.DaoSession;
import solidbeans.com.handla.db.DbHelper;

public class TestApp extends Application implements DbHelper {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, "handla-test-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        Log.d("leffe", "######################################");
        return daoSession;
    }
}