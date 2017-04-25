package solidbeans.com.handla;

import solidbeans.com.handla.di.AppModule;
import solidbeans.com.handla.di.DaggerDbComponent;
import solidbeans.com.handla.di.DbModule;

public class TestApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        dbComponent = DaggerDbComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule("handla-test-db"))
                .build();

    }

}