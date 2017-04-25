package solidbeans.com.handla.db;

import javax.inject.Singleton;

import dagger.Component;
import solidbeans.com.handla.AppModule;

//@Singleton
//@Component(
//        modules = {
//                AppModule.class
//        }
//)
public interface DbHelper {
    DaoSession getDaoSession();
//    void inject(DbHelper dbHelper);
}
