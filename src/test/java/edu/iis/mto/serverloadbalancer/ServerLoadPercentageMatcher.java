package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public ServerLoadPercentageMatcher(double expetedLoadPercentage) {
        this.expectedLoadPercentage = expetedLoadPercentage;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("server has percentage of load ").appendValue(item.getCurrentLoadPercentage());
    }

    public static ServerLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new ServerLoadPercentageMatcher(expectedLoadPercentage);
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return areDoublesEqual(this.expectedLoadPercentage, item.getCurrentLoadPercentage());
    }

    private boolean areDoublesEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2) <= EPSILON;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server has percentage of load ").appendValue(expectedLoadPercentage);
    }
}
