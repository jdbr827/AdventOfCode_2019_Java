package year_2019.day23;

import java.util.List;
import java.util.stream.Collectors;

public class NATComputer extends Thread {
    Packet lastReceivedPacket;
    NICNetwork network;

    NATComputer(NICNetwork network) {
        this.network = network;
    }


    public void receivePacket(Packet packet) {
        lastReceivedPacket = packet;
    }

    public void run() {
        try {
            executeProgram();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeProgram() throws InterruptedException {
        while (true) {
            List<Integer> running = network.nicComputers.stream().filter(nicComputer -> !nicComputer.isIdling).map(nicComputer -> nicComputer.address).collect(Collectors.toList());
            while (!running.isEmpty()) {
                //System.out.println(running);
                running = network.nicComputers.stream().filter(nicComputer -> !nicComputer.isIdling).map(nicComputer -> nicComputer.address).collect(Collectors.toList());
            }
            System.out.println(lastReceivedPacket.Y);
            network.sendPacket(0L, lastReceivedPacket);
        }
    }


}
