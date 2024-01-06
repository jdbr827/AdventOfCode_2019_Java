package year_2023.day_20;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public abstract class CommunicationModule {

    @NonNull  String name;

    @Getter
    @NonNull  String[] destinationModules;

    @NonNull  Queue<Day20.Message> messageQueue;

    Day20.Pulse lastPulseSent = Day20.Pulse.LOW;

    void sendPulse(Day20.Pulse pulse) {
        messageQueue.add(new Day20.Message(this, pulse));
    }

    void addInputModule(CommunicationModule module) {

    }

    protected abstract void receiveMessage(Day20.Message message);

}

class FlipFlopModule extends CommunicationModule {

    public FlipFlopModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }


    @Override
    protected void receiveMessage(Day20.Message message) {
        if (message.pulse == Day20.Pulse.LOW) {
            sendPulse(lastPulseSent == Day20.Pulse.HIGH ? Day20.Pulse.LOW : Day20.Pulse.HIGH);
        }

    }
}

class ConjunctionModule extends CommunicationModule {

    List<CommunicationModule> rememberedModules = new ArrayList<>();
    public ConjunctionModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    @Override
    void addInputModule(CommunicationModule module) {
        rememberedModules.add(module);
    }



    @Override
    protected void receiveMessage(Day20.Message message) {
        if (rememberedModules.stream().allMatch(module -> module.lastPulseSent == Day20.Pulse.HIGH)) {
            sendPulse(Day20.Pulse.LOW);
        } else {
            sendPulse(Day20.Pulse.HIGH);
        }
    }
}


class BroadcasterModule extends CommunicationModule {

    public BroadcasterModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }


    @Override
    protected void receiveMessage(Day20.Message message) {
        sendPulse(message.pulse);
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
    protected void receiveMessage(Day20.Message message) {
        // never happens
    }
}
