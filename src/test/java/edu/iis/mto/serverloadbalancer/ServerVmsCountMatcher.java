package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedAmountOfVms;

    public ServerVmsCountMatcher(int expectedAmountOfVms) {
        this.expectedAmountOfVms = expectedAmountOfVms;
    }

    public static ServerVmsCountMatcher hasAVmsCountOf(int expectedAmountOfVms) {
        return new ServerVmsCountMatcher(expectedAmountOfVms);
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return expectedAmountOfVms==item.countOfVms();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedAmountOfVms);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with vms count of ").appendValue(item.countOfVms());
    }
}
