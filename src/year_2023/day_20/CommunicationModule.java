package year_2023.day_20;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Queue;

@AllArgsConstructor
public abstract class CommunicationModule {


    @Getter
    String[] destinationModules;

    Queue<Day20.Message> messageQueue;

    void sendPulse(Day20.Pulse pulse) {
        for (String module : getDestinationModules()) {
            messageQueue.add(new Day20.Message(module, pulse));
        }

    }


    abstract void receiveHighPulse();

    abstract void receiveLowPulse();
}

class FlipFlopModule extends CommunicationModule {

    public FlipFlopModule(String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(destinationModules, messageQueue);
    }

    @Override
    public void sendPulse(Day20.Pulse pulse) {

    }

    @Override
    public void receiveHighPulse() {

    }

    @Override
    public void receiveLowPulse() {

    }
}

class ConjunctionModule extends CommunicationModule {

    public ConjunctionModule(String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(destinationModules, messageQueue);
    }

    @Override
    void receiveHighPulse() {

    }

    @Override
    void receiveLowPulse() {

    }
}


class BroadcasterModule extends CommunicationModule {

    public BroadcasterModule(String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(destinationModules, messageQueue);
    }

    @Override
    void receiveHighPulse() {

    }

    @Override
    void receiveLowPulse() {

    }
}

class ButtonModule extends CommunicationModule {

    public ButtonModule(Queue<Day20.Message> messageQueue) {
        super(new String[]{"broadcaster"}, messageQueue);
    }

    void pushButton() {
        sendPulse(Day20.Pulse.LOW_PULSE);
    }

    @Override
    void receiveHighPulse() {

    }

    @Override
    void receiveLowPulse() {

    }
}
