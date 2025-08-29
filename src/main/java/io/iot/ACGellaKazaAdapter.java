package io.iot;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;

public final class ACGellaKazaAdapter implements ACController {

    private final ArCondicionadoGellaKaza ac;

    public ACGellaKazaAdapter() {
        ac = new ArCondicionadoGellaKaza();
    }

    @Override
    public boolean isOn() {
        return ac.estaLigado();
    }

    @Override
    public void on() {
        ac.ativar();
    }

    @Override
    public void off() {
        ac.desativar();
    }

    @Override
    public void increaseTemperature() {
        try {
            ac.aumentarTemperatura();
        } catch (Exception e) {
            throw new MaxTemperatureReached();
        }
    }

    @Override
    public void decreaseTemperature() {
        try {
            ac.diminuirTemperatura();
        } catch (Exception e) {
            throw new MinTemperatureReached();
        }
    }

    @Override
    public void setTemperature(Temperature temperature) {
        if (temp() == temperature.get()) {
            return;
        }

        if (temp() < temperature.get()) {
            while (temp() < temperature.get()) {
                increaseTemperature();
            }
        } else {
            while (temp() > temperature.get()) {
                decreaseTemperature();
            }
        }
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.of(ac.getTemperatura());
    }

    private int temp() {
        return ac.getTemperatura();
    }
}
