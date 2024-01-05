package year_2023.day_20;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    void addInputModule(String moduleName) {

    }

    protected abstract void receiveMessage(Day20.Message message);

}

class FlipFlopModule extends CommunicationModule {

    public FlipFlopModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    boolean isOn = false;


    @Override
    protected void receiveMessage(Day20.Message message) {
        if (message.pulse == Day20.Pulse.LOW) {
            isOn = !isOn;
            sendPulse(isOn ? Day20.Pulse.HIGH : Day20.Pulse.LOW);
        }

    }
}

class ConjunctionModule extends CommunicationModule {

    Map<String, Day20.Pulse> rememberedModules = new HashMap<>();
    public ConjunctionModule(String name, String[] destinationModules, Queue<Day20.Message> messageQueue) {
        super(name, destinationModules, messageQueue);
    }

    @Override
    void addInputModule(String moduleName) {
        rememberedModules.put(moduleName, Day20.Pulse.LOW);
    }



    @Override
    protected void receiveMessage(Day20.Message message) {
        rememberedModules.put(message.sender, message.pulse);
        if (rememberedModules.values().stream().allMatch(pulse -> pulse == Day20.Pulse.HIGH)) {
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
