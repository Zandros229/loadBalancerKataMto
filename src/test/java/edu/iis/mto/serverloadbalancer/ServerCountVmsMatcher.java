package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerCountVmsMatcher extends TypeSafeMatcher<Server> {
    private int expectedAmountOfVms;

    public ServerCountVmsMatcher(int expectedAmountOfVacs) {
        this.expectedAmountOfVms = expectedAmountOfVacs;
    }

    public static ServerCountVmsMatcher hasAmountOfVms(int expectedAmountOfVms) {
        return new ServerCountVmsMatcher(expectedAmountOfVms);
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return expectedAmountOfVms == item.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("amount of vms in the server is ").appendValue(item.countVms());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("amount of vms in the server is ").appendValue(expectedAmountOfVms);
    }
}
