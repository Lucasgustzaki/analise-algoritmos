package io.iot;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;

public final class ACBaumnAdapter implements ACController {

    private static final int INTERVAL = 5;

    private final Temperature temp;
    private final ArCondicionadoVentoBaumn ac;

    public ACBaumnAdapter() {
        temp = Temperature.getDefault();

        ac = new ArCondicionadoVentoBaumn();
    }

    @Override
    public void on() {
        ac.ligar();
    }

    @Override
    public void off() {
        ac.desligar();
    }

    @Override
    public void increaseTemperature() {
        if (!isOn()) {
            throw new DeviceOff();
        }

        try {
            ac.definirTemperatura(temp.increaseBy(INTERVAL).get());
        } catch (Exception e) {
            throw new MaxTemperatureReached();
        }
    }

    @Override
    public void decreaseTemperature() {
        if (!isOn()) {
            throw new DeviceOff();
        }

        try {
            ac.definirTemperatura(temp.decreaseBy(INTERVAL).get());
        } catch (Exception e) {
            throw new MinTemperatureReached();
        }
    }

    @Override
    public void setTemperature(Temperature temperature) {
        if (!isOn()) {
            throw new DeviceOff();
        }

        try {
            ac.definirTemperatura(temperature.get());
        } catch (Exception e) {
            // TODO
        }
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.of(ac.getTemperatura());
    }

    @Override
    public boolean isOn() {
        return ac.estaLigado();
    }
}
