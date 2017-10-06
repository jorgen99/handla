package solidbeans.com.handla.cucumber.runner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.MonitoringInstrumentation;

import org.junit.runner.RunWith;

import cucumber.api.android.CucumberInstrumentationCore;
import solidbeans.com.handla.TestApp;

/**
 * Used in build.gradle/testInstrumentationRunner to run Cucumber tests
 * 'testInstrumentationRunner' variable in build.gradle has to point to this package
 * This class must be in a different package than the glue code
 * (this class is in '...cucumber.runner' and glue is in '...cucumber.steps')
 */
public class CucumberTestRunner extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore instrumentationCore
            = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        instrumentationCore.create(bundle);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        waitForIdleSync();
        instrumentationCore.start();
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApp.class.getName(), context);
    }

}