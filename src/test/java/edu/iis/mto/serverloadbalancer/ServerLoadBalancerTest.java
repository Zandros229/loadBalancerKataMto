package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}
	@Test
	public void balancingServerWithEmptyListOfVms_serverShoulldStaysEmpty(){
		Server theServer=a(server().withCapacity(0));

		balancing(aServerList(theServer), aEmptyListOfVms());

		assertThat(theServer, hasCurentLoadOf(0.0d));
	}

	private Matcher<? super Server> hasCurentLoadOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] aEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServerList(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}


}
