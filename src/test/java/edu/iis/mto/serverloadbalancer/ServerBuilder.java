package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {
    private int capacity;
    private double initlizeLoad;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server=new Server(capacity);
        if(initlizeLoad>0) {
            int initialVmSize = (int) (initlizeLoad / (double) capacity * 100.0d);
            Vm initialVm = VmBuilder.vm().withSize(initialVmSize).build();
            server.addVm(initialVm);
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withstartingLoadOf(double initializeLoad) {
        this.initlizeLoad = initializeLoad;
        return this;
    }
}
