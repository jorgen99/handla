package solidbeans.com.handla;

import android.app.Application;

import solidbeans.com.handla.di.AppModule;
import solidbeans.com.handla.di.DaggerDbComponent;
import solidbeans.com.handla.di.DbComponent;
import solidbeans.com.handla.di.DbModule;

public class App extends Application {

    protected DbComponent dbComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        dbComponent = DaggerDbComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule("handla-db"))
                .build();
    }

    public DbComponent getDbComponent() {
        return dbComponent;
    }


}