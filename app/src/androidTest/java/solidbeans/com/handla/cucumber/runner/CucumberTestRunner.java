package solidbeans.com.handla.cucumber.runner;

import android.os.Bundle;


import cucumber.api.android.CucumberInstrumentationCore;

/**
 * Used in build.gradle/testInstrumentationRunner to run Cucumber tests
 * 'testInstrumentationRunner' variable in build.gradle has to point to this package
 * This class must be in a different package than the glue code
 * (this class is in '...cucumber.runner' and glue is in '...cucumber.steps')
 */
public class CucumberTestRunner extends android.support.test.runner.AndroidJUnitRunner {

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        instrumentationCore.create(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}