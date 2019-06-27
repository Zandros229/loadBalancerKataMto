package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerCountVmsMatcher extends TypeSafeMatcher<Server> {
    private int expectedAmountOfVms;

    public ServerCountVmsMatcher(int expectedAmountOfVms) {
        this.expectedAmountOfVms = expectedAmountOfVms;
    }

    public static ServerCountVmsMatcher hasVmsCount(int expectedAmountOfVms) {
        return new ServerCountVmsMatcher(expectedAmountOfVms);
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return expectedAmountOfVms==item.countVms();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server count vms of ").appendValue(expectedAmountOfVms);
    }
}
