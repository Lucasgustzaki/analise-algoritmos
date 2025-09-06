package io.iot;

import br.furb.analise.algoritmos.LampadaPhellipes;

public final class LampPhellipesAdapter implements LampController {

    private static final Luminosity MAX = Luminosity.of(100);
    private static final Luminosity MIN = Luminosity.of(0);

    private final LampadaPhellipes lamp;

    public LampPhellipesAdapter() {
        lamp = new LampadaPhellipes();
    }

    @Override
    public boolean isOn() {
        return lamp.getIntensidade() > 0;
    }

    @Override
    public void on() {
        lamp.setIntensidade(MAX.get());
    }

    @Override
    public void off() {
        lamp.setIntensidade(MIN.get());
    }
}
