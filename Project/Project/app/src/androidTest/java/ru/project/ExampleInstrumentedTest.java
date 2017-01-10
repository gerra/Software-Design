package ru.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context appContext;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        assertEquals("ru.project", appContext.getPackageName());
    }

    @Test
    public void ss() {
        Activity activity =
                InstrumentationRegistry.getInstrumentation().startActivitySync(new Intent(appContext, MainActivity.class));
        System.out.println(activity.getClass().getSimpleName());
        assertEquals(activity.getClass(), ExampleInstrumentedTest.class);
    }
}
