package io.iot;

import br.furb.analise.algoritmos.PersianaSolarius;

public final class BlindSolariusAdapter implements BlindController {

    private final PersianaSolarius blind;

    public BlindSolariusAdapter() {
        blind = new PersianaSolarius();
    }

    @Override
    public void open() {
        blind.subirPersiana();
    }

    @Override
    public void close() {
        blind.descerPersiana();
    }
}