package solidbeans.com.handla;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import solidbeans.com.handla.db.DaoMaster;
import solidbeans.com.handla.db.DaoMaster.DevOpenHelper;
import solidbeans.com.handla.db.DaoSession;
import solidbeans.com.handla.db.DbHelper;

public class App extends Application implements DbHelper {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, "handla-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}