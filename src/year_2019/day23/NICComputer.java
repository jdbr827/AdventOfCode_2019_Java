package year_2019.day23;

import year_2019.IntCodeComputer.IntCodeAPI;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NICComputer extends Thread {
    int address;
    IntCodeAPI brain;
    public Queue<Packet> incomingPacketQueue;
    NICNetwork network;

    NICComputer(int address, IntCodeAPI brain, NICNetwork network) {
        this.incomingPacketQueue = new LinkedBlockingQueue<>();
        this.brain = brain;
        this.address = address;
        this.network = network;
    }



    public void run() {
        try {
            executeProgram();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeProgram() throws InterruptedException {
        brain.sendInput((long) address);
        brain.startProgram();
        Optional<Long> outVal;
        while ((outVal = brain.waitForOutputOptional(() -> {
            //System.out.println(address + " requested input, but queue is empty");
            brain.sendInput(-1L);
        })).isPresent()) {
            Long destinationAddress = outVal.get();
            Long X = brain.waitForOutputKnown();
            Long Y = brain.waitForOutputKnown();
            network.sendPacket(destinationAddress, new Packet(X, Y));
        }
    }
}
