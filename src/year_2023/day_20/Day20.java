package year_2023.day_20;

import java.util.Queue;

public class Day20 {
    Queue<Message> messageQueue;

    public Day20(String fileName) {

    }

    public Number getPulseProduct(int buttonPresses) {
        return 0;
    }


    class Message {
        CommunicationModule destination;
        Pulse pulse;

    }

    private enum Pulse {
        HIGH_PULSE,
        LOW_PULSE
    }

    private interface CommunicationModule {

        void sendPulse(Pulse pulse);

        void receiveHighPulse();

        void receiveLowPulse();
    }
}
