package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;

    private List<Vm> vms=new ArrayList<>();

    public Server(int capacity) {

        this.capacity = capacity;
    }


    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public void addVm(Vm vm) {
        this.currentLoadPercentage += getCurrentLoadPercentage(vm);
        vms.add(vm);
    }

    private double getCurrentLoadPercentage(Vm vm) {
        return (double) vm.getSize() / (double) this.capacity * MAXIMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return this.currentLoadPercentage + (double) vm.getSize() / (double)this.capacity * MAXIMUM_LOAD <= MAXIMUM_LOAD;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }
}
