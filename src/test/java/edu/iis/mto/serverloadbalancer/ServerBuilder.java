package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {
    private int capacity;
    private double initialLoadOf;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server=new Server(capacity);

        if(initialLoadOf>0){
            int initialSizeOfVm=(int)(initialLoadOf/ 100.0d *capacity);
            Vm initialVm=VmBuilder.vm().withSize(initialSizeOfVm).build();
            server.addVm(initialVm);
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withInitialLoadOf(double initialLoadOf) {
        this.initialLoadOf = initialLoadOf;
        return this;
    }
}
