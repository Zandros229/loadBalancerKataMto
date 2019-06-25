package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatch extends TypeSafeMatcher<Server> {
    private int expectedVmsCount;

    public ServerVmsCountMatch(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    public static ServerVmsCountMatch hasAVmsCountOf(int expectedVmsCount) {
        return new ServerVmsCountMatch(expectedVmsCount);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedVmsCount==server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with vms count of ").appendValue(item.countVms());
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedVmsCount);
    }
}
