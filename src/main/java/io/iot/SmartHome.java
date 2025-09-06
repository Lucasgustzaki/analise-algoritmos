package io.iot;

import java.util.HashSet;
import java.util.List;
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

    public void addAC(final ACController... ac) {
        acs.addAll(List.of(ac));
    }

    public void addLamp(final LampController... lamp) {
        lamps.addAll(List.of(lamp));
    }

    public void addBlind(final BlindController... blinds) {
        this.blinds.addAll(List.of(blinds));
    }

    public void enableNightMode() {
        acs.forEach(ACController::off);
        lamps.forEach(LampController::off);
        blinds.forEach(BlindController::close);
    }

    public void enableDayMode() {
        blinds.forEach(BlindController::open);
        lamps.forEach(LampController::on);
        acs.forEach(ACController::on);
        acs.forEach(ac -> ac.setTemperature(Temperature.getDefault()));
    }
}
