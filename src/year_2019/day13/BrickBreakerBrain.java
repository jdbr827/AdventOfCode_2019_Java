package year_2019.day13;


import org.testng.internal.collections.Pair;
import year_2019.IntCodeComputer.IntCodeAPI;

import java.awt.*;
import java.util.Optional;

public class BrickBreakerBrain extends IntCodeAPI {
    private boolean useAutopilot = false;
    Joystick joystick;

    public BrickBreakerBrain(long[] tape) {
        super(tape);
    }

    public BrickBreakerBrain(long[] tape, Joystick joystick) {
        super(tape, joystick.joystickInputs);
        this.joystick = joystick;
    }

    Optional<Pair<Point, Integer>> getNextOutput() throws Exception {
        Optional<Long> optX = waitForOutputOptional(() -> {
            if (isUsingAutopilot()) {
                joystick.doNextJoystickInput();
            }
        });
        if (optX.isPresent()) {
            int x = optX.get().intValue();
            int y = waitForOutputKnown().intValue();
            int obj_id = waitForOutputKnown().intValue();
            return Optional.of(new Pair<>(new Point(x, y), obj_id));
        }
        return Optional.empty();
    }

    public boolean isUsingAutopilot() {
        return useAutopilot;
    }

    public void setUseAutopilot(boolean useAutopilot) {
        this.useAutopilot = useAutopilot;
    }
}
