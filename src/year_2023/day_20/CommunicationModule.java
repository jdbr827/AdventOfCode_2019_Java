package year_2023.day_20;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Queue;

@AllArgsConstructor
public abstract class CommunicationModule {

    String name;

    @Getter
    String[] destinationModules;

    Queue<Day20.Message> messageQueue;

    void sendPulse(Day20.Pulse pulse) {
        for (String module : getDestinationModules()) {
            messageQueue.add(new Day20.Message(name, module, pulse));
        }

    }

    void receivePulse(Day20.Pulse pulse) {
        if (pulse == Day20.Pulse.HIGH) {
            receiveHighPulse();
        } else {
            receiveLowPulse();
        }

    }


    protected abstract void receiveHighPulse();

    protected abstract void receiveLowPulse();
}

class FlipFlopModule extends CommunicationModule {

    public FlipFlopModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    boolean isOn = false;


    @Override
    public void receiveHighPulse() {
        // do nothing;
    }

    @Override
    public void receiveLowPulse() {
        isOn = !isOn;
        sendPulse(isOn ? Day20.Pulse.HIGH : Day20.Pulse.LOW);

    }
}

class ConjunctionModule extends CommunicationModule {

    public ConjunctionModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    @Override
    protected void receiveHighPulse() {

    }

    @Override
    protected void receiveLowPulse() {

    }
}


class BroadcasterModule extends CommunicationModule {

    public BroadcasterModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    @Override
    protected void receiveHighPulse() {
        sendPulse(Day20.Pulse.HIGH);

    }

    @Override
    protected void receiveLowPulse() {
        sendPulse(Day20.Pulse.LOW);

    }
}

class ButtonModule extends CommunicationModule {

    public ButtonModule(Queue<Day20.Message> messageQueue) {
        super("button", new String[]{"broadcaster"}, messageQueue);
    }

    void pushButton() {
        sendPulse(Day20.Pulse.LOW);
    }

    @Override
    protected void receiveHighPulse() {

    }

    @Override
    protected void receiveLowPulse() {

    }
}
