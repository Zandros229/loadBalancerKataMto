package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        Server lessLoadedServer = null;
        List<Server> capableServers = new ArrayList<>();
        for (Vm vm : vms) {
            addToCapableLessLoadedServer(servers, capableServers, vm);
        }
    }

    private void addToCapableLessLoadedServer(Server[] servers, List<Server> capableServers, Vm vm) {
        Server lessLoadedServer;
        findServersWithEnoughCapacity(servers, vm);

        lessLoadedServer = setOnLessLoadedServer(capableServers, vm);
        if(lessLoadedServer!=null) {
            lessLoadedServer.addVm(vm);
        }
    }

    private void findServersWithEnoughCapacity(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<>();
        for (Server server : servers) {
            if (server.canFit(vm)) {
                capableServers.add(server);
            }
        }
    }

    private Server setOnLessLoadedServer(List<Server> servers, Vm vm) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if (lessLoadedServer == null || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage) {
                lessLoadedServer = server;

            }
        }
        return lessLoadedServer;
    }
}
