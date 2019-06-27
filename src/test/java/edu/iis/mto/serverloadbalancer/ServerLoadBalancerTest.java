package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadPercentageMatcher.hasCurrentLoadOf;
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
    public void balancingServer_noVm_ServerStaysEmpty(){
	    Server theServer=a(server().withCapacity(10));

	    balance(listOfServers(theServer), emptyListOfVms());

	    assertThat(theServer, hasCurrentLoadOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
        Server theServer=a(server().withCapacity(1));

        Vm theVm=a(vm().withSize(1));

        balance(listOfServers(theServer), listOfVms(theVm));

        assertThat(theServer, hasCurrentLoadOf(100.0d));
        assertThat("teh server contains the Vm ", theServer.contains(theVm));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent(){
        Server theServer=a(server().withCapacity(10));

        Vm theVm=a(vm().withSize(1));

        balance(listOfServers(theServer), listOfVms(theVm));

        assertThat(theServer, hasCurrentLoadOf(10.0d));
        assertThat("teh server contains the Vm ", theServer.contains(theVm));
    }

    @Test
    public void balancingAServerWithEnoughRoom_getsFilledWithAllVms(){
        Server theServer=a(server().withCapacity(10));

        Vm vm1=a(vm().withSize(1));
        Vm vm2=a(vm().withSize(1));


        balance(listOfServers(theServer), listOfVms(vm1,vm2));

        assertThat(theServer, hasAmountOfVms(2));
        assertThat("teh server contains the Vm ", theServer.contains(vm1));
        assertThat("teh server contains the Vm ", theServer.contains(vm2));
    }

    private Matcher<? super Server> hasAmountOfVms(int expetedAmountOfVms) {
        return new ServerCountVmsMatcher(expetedAmountOfVms);
    }

    private Vm[] listOfVms(Vm... vms) {
        return vms;
    }





    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers,vms);
    }

    private Vm[] emptyListOfVms() {
        return new Vm[0];
    }

    private Server[] listOfServers(Server... servers) {
        return servers;
    }

    private <T> T a(Builder<T> builder){
	    return builder.build();
    }




}
