package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        Server lessLoadedServer = null;
        List<Server> capableServers = new ArrayList<>();
        for (Vm vm : vms) {
            for (Server server : servers) {
                if (server.canFit(vm)) {
                    capableServers.add(server);
                }
            }

            lessLoadedServer = setOnLessLoadedServer(capableServers, vm);
            if(lessLoadedServer!=null) {
                lessLoadedServer.addVm(vm);
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
