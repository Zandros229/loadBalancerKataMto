package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerCountVmsMatcher extends TypeSafeMatcher<Server> {
    private int expetedAmountOfVms;

    public ServerCountVmsMatcher(int expetedAmountOfVms) {
        this.expetedAmountOfVms = expetedAmountOfVms;
    }

    @Override
    protected boolean matchesSafely(Server item) {
        return expetedAmountOfVms == item.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("amount of vms in the server is ").appendValue(item.countVms());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("amount of vms in the server is ").appendValue(expetedAmountOfVms);
    }
}
