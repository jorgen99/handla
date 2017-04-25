package solidbeans.com.handla.di;

import javax.inject.Singleton;

import dagger.Component;
import solidbeans.com.handla.db.DaoSession;

@Singleton
@Component(modules = {AppModule.class, DbModule.class})
public interface DbComponent {
    DaoSession getDaoSession();
}
