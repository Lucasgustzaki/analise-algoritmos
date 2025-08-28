package io.iot;

import br.furb.analise.algoritmos.LampadaShoyuMi;

public final class LampShoyuMiAdapter implements LampController {

    private final LampadaShoyuMi lamp;

    public LampShoyuMiAdapter() {
        lamp = new LampadaShoyuMi();
    }

    @Override
    public void on() {
        lamp.ligar();
    }

    @Override
    public void off() {
        lamp.desligar();
    }
}
