package solidbeans.com.handla.di;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import solidbeans.com.handla.db.DaoMaster;
import solidbeans.com.handla.db.DaoSession;

@Module
public class DbModule {

    private String dbName;

    public DbModule(String dbName) {
        this.dbName = dbName;
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession(Application application) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(application, dbName);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }
}
