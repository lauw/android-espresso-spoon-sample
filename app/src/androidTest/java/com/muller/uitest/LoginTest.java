package com.muller.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.squareup.spoon.Spoon;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.closeSoftKeyboard;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
/** Taking screenshots is disabled due to a (bug?) in lollipop / spoon, where spoon does not have permission to grab screenshots of of the device
 ** more info: https://github.com/square/spoon/issues/189 **/
public class LoginTest extends ActivityInstrumentationTestCase2<MainActivity> {
	public LoginTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getActivity();
	}

	public void testLogin_Success() {
		//Spoon.screenshot(getActivity(), "beforeLogin");

		onView(withId(R.id.login_txtUsername)).perform(typeText("howdy"), closeSoftKeyboard());
		sleep(300);

		onView(withId(R.id.login_txtPassword)).perform(typeText("hello"), closeSoftKeyboard());
		sleep(300);

		onView(withId(R.id.login_btnSubmit)).perform(click());

		onView(withId(R.id.login_txtResult)).check(matches(withText(R.string.login_successful)));
		//Spoon.screenshot(getActivity(), "afterLogin");
	}

	public void testLogin_Error() {
		//Spoon.screenshot(getActivity(), "beforeLogin");

		onView(withId(R.id.login_txtUsername)).perform(typeText("howdie"), closeSoftKeyboard());
		sleep(300);

		onView(withId(R.id.login_txtPassword)).perform(typeText("hell0"), closeSoftKeyboard());
		sleep(300);

		onView(withId(R.id.login_btnSubmit)).perform(click());

		onView(withId(R.id.login_txtResult)).check(matches(withText(R.string.login_error)));
		//Spoon.screenshot(getActivity(), "afterLogin");
	}

	/** Usually you will not have to use sleep, this is due to the action 'closeSoftKeyboard', which requires some time  **/
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}