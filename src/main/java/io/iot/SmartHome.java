package io.iot;

import java.util.HashSet;
import java.util.Set;

public final class SmartHome {

    private final Set<ACController> acs;
    private final Set<LampController> lamps;
    private final Set<BlindController> blinds;

    public SmartHome() {
        this.acs = new HashSet<>();
        this.lamps = new HashSet<>();
        this.blinds = new HashSet<>();
    }

    public void addAC(final ACController ac) {
        acs.add(ac);
    }

    public void addLamp(final LampController lamp) {
        lamps.add(lamp);
    }

    public void addBlind(final BlindController blind) {
        blinds.add(blind);
    }

    public void nightMode() {
        // TODO
    }

    public void dayMode() {
        // TODO
    }
}
