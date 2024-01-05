package year_2023.day_20;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@RequiredArgsConstructor
public class Day20 {
    Queue<Message> messageQueue = new LinkedList<>();
    final Map<String, CommunicationModule> moduleLibrary;


    public Number getPulseProduct(int buttonPresses) {
        return 0;
    }


    @AllArgsConstructor
    static class Message {
        String destination;
        Pulse pulse;
    }

    enum Pulse {
        HIGH_PULSE,
        LOW_PULSE
    }



}

