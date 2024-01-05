package year_2023.day_20;

import lombok.AllArgsConstructor;


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
        for (int i=0; i<buttonPresses; i++) {
            buttonModule.pushButton();
            while (!messageQueue.isEmpty()) {
                Message message = messageQueue.remove();
                message.logMessage();

                if (message.pulse == Pulse.HIGH) {
                    highPulsesSent++;
                } else {
                    lowPulsesSent++;
                }

                CommunicationModule destinationModule = moduleLibrary.get(message.destination);
                if (destinationModule != null) {
                    destinationModule.receiveMessage(message);
                }
            }
            System.out.println("------END OF BUTTON PRESS--------");
        }
        return highPulsesSent * lowPulsesSent;
    }


    @AllArgsConstructor
    static class Message {
        String sender;
        String destination;
        Pulse pulse;


        void logMessage() {
            System.out.println(sender + " --" + pulse.toString() + "--> " + destination);
        }
    }

    enum Pulse {
        HIGH,
        LOW;

    }



}

