package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public ServerLoadPercentageMatcher(double expetedLoadPercentage) {
        this.expectedLoadPercentage = expetedLoadPercentage;
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return expectedLoadPercentage == item.currentLoadPercentage || Math.abs(expectedLoadPercentage-item.currentLoadPercentage)<=0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server has percentage of load ").appendValue(expectedLoadPercentage);
    }
}
