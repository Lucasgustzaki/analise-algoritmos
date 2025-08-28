package io.iot;

import br.furb.analise.algoritmos.PersianaNatLight;

public final class BlindNatLightAdapter implements BlindController {

    private final PersianaNatLight blind;

    public BlindNatLightAdapter() {
        blind = new PersianaNatLight();
    }

    @Override
    public void open() {
        blind.abrirPalheta();

        try {
            blind.subirPalheta();
        } catch (Exception e) {
            // ignored
        }
    }

    @Override
    public void close() {
        blind.descerPalheta();

        try {
            blind.fecharPalheta();
        } catch (java.lang.Exception e) {
            // ignored
        }
    }
}
