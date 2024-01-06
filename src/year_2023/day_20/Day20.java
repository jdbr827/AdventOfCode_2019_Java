package year_2023.day_20;

import lombok.AllArgsConstructor;


import java.util.Arrays;
import java.util.Map;
import java.util.Queue;

@AllArgsConstructor
public class Day20 {
    final Queue<Message> messageQueue;
    final Map<String, CommunicationModule> moduleLibrary;


    public Number getPulseProduct(int buttonPresses) {
        ButtonModule buttonModule = (ButtonModule) moduleLibrary.get("button");
        int highPulsesSent = 0;
        int lowPulsesSent = 0;
        for (int i = 0; i < buttonPresses; i++) {
            buttonModule.pushButton();
            while (!messageQueue.isEmpty()) {
                Message message = messageQueue.remove();

                if (message.pulse == Pulse.HIGH) {
                    highPulsesSent += message.sender.destinationModules.length;
                } else {
                    lowPulsesSent += message.sender.destinationModules.length;
                }

                message.sender.lastPulseSent = message.pulse;


                for (String destination : message.sender.destinationModules) {
                    //System.out.println(message.sender.name + " --" + message.pulse.toString() + "--> " + destination);

                    if (destination.equals("rx") && message.pulse == Pulse.LOW) {
                        return buttonPresses;
                    }

                    CommunicationModule destinationModule = moduleLibrary.get(destination);
                    if (destinationModule != null) {
                        destinationModule.receiveMessage(message);
                    }
                }
            }
            //System.out.println("------END OF BUTTON PRESS--------");
        }
        return highPulsesSent * lowPulsesSent;
    }

    public int fewestPressesToSendLowToRx() {
        ButtonModule buttonModule = (ButtonModule) moduleLibrary.get("button");
        int buttonPresses = 0;
        while (true) {
            buttonPresses++;
            buttonModule.pushButton();
            while (!messageQueue.isEmpty()) {
                Message message = messageQueue.remove();

                message.sender.lastPulseSent = message.pulse;
                for (String destination : message.sender.destinationModules) {
                    //System.out.println(message.sender.name + " --" + message.pulse.toString() + "--> " + destination);

                    if (destination.equals("rx") && message.pulse == Pulse.LOW) {
                        return buttonPresses;
                    }

                    CommunicationModule destinationModule = moduleLibrary.get(destination);
                    if (destinationModule != null) {
                        destinationModule.receiveMessage(message);
                    }
                }
            }
            //System.out.println("------END OF BUTTON PRESS--------");
        }

    }


    @AllArgsConstructor
    static class Message {
        CommunicationModule sender;
        Pulse pulse;


    }

    enum Pulse {
        HIGH,
        LOW;

    }


}

