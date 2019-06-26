package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public static CurrentLoadPercentageMatcher hasCurentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }


    @Override
    protected boolean matchesSafely(Server server) {
        return doublesAreEqual(server.currentLoadPercentage, this.expectedLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with load percentage of ").appendValue(item.currentLoadPercentage);

    }

    private boolean doublesAreEqual(double currentLoadPercentage, double d2) {
        return d2 == currentLoadPercentage
                || Math.abs(d2 - currentLoadPercentage)< 0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
    }
}
