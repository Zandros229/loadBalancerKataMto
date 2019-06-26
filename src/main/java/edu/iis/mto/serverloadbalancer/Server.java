package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoadPecentage;
	private int capacity;
	private List<Vm> vms = new ArrayList<Vm>();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public void addVm(Vm vm) {
		this.vms.add(vm);
		this.currentLoadPecentage += getLoaded(vm);
	}

    private double getLoaded(Vm vm) {
        return (double) vm.size / (double) this.capacity * MAXIMUM_LOAD;
    }

    public int vmsCount() {
		return vms.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean canFit(Vm vm) {		
		return currentLoadPecentage + (getLoaded(vm)) <= MAXIMUM_LOAD ;
	}

}
