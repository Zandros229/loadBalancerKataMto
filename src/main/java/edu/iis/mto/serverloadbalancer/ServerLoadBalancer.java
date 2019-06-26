package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            addToLessLoaded(servers, vm);
        }

    }

    private void addToLessLoaded(Server[] servers, Vm vm) {
        Server lessLoadedServer;
        lessLoadedServer = findLessLoaded(servers);
        lessLoadedServer.addVm(vm);
    }

    private Server findLessLoaded(Server[] servers) {
        Server lessLoadedServer = null;
        for(Server server:servers){
            if(lessLoadedServer==null||lessLoadedServer.currentLoadPercentage>server.currentLoadPercentage){
                lessLoadedServer=server;
            }
        }
        return lessLoadedServer;
    }
}
