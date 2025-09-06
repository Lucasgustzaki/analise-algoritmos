package io.iot;

import br.furb.analise.algoritmos.PersianaNatLight;

public final class BlindNatLightAdapter implements BlindController {

    private final PersianaNatLight blind;

    public BlindNatLightAdapter() {
        blind = new PersianaNatLight();
    }

    @Override
    public boolean isOpen() {
        return blind.estaPalhetaAberta() && blind.estaPalhetaErguida();
    }

    @Override
    public void open() {
        blind.abrirPalheta();

        try {
            blind.subirPalheta();
        } catch (Exception e) {
            // ignored: precondition already checked
        }
    }

    @Override
    public void close() {
        blind.descerPalheta();

        try {
            blind.fecharPalheta();
        } catch (java.lang.Exception e) {
            // ignored: precondition already checked
        }
    }
}
