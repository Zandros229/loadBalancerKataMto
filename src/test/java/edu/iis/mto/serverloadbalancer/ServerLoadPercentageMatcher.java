package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public ServerLoadPercentageMatcher(double expetedLoadPercentage) {
        this.expectedLoadPercentage = expetedLoadPercentage;
    }
    public static ServerLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new ServerLoadPercentageMatcher(expectedLoadPercentage);
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return areDoublesEqual(this.expectedLoadPercentage, item.currentLoadPercentage);
    }

    private boolean areDoublesEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2) <= EPSILON;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server has percentage of load ").appendValue(expectedLoadPercentage);
    }
}
