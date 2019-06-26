package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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

    @Test
    public void balancingServerWithOneSlot_withOneSlotVms_fillsServerWithIt(){
        Server theServer=a(server().withCapacity(1));

        Vm theVm=a(vm().withSize(1));

        balancing(aServerList(theServer), aVmsList(theVm));

        assertThat(theServer, hasCurentLoadOf(100.0d));
        assertThat("server should contains the vm", theServer.contains(theVm));
    }

    private Vm[] aVmsList(Vm... vms) {
	    return vms;
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

	private <T> T a(Builder<T> builder){
	    return builder.build();
    }





}
