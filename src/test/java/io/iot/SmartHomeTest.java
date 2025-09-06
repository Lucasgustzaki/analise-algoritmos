package io.iot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmartHomeTest {

    private SmartHome home;

    @BeforeEach
    void setUp() {
        home = new SmartHome();
    }

    @Test
    void onNighModeACsAreTurnedOff() {
        ACBaumnAdapter baumn = new ACBaumnAdapter();
        ACGellaKazaAdapter gellaKaza = new ACGellaKazaAdapter();

        gellaKaza.on();
        baumn.on();

        home.addAC(gellaKaza, baumn);

        home.enableNightMode();

        assertFalse(gellaKaza.isOn());
        assertFalse(baumn.isOn());
    }

    @Test
    void onNightModeBlindsAreClosed() {
        BlindSolariusAdapter solarius = new BlindSolariusAdapter();
        BlindNatLightAdapter natLight = new BlindNatLightAdapter();

        natLight.open();
        solarius.open();

        home.addBlind(natLight, solarius);

        home.enableNightMode();

        assertFalse(natLight.isOpen());
        assertFalse(solarius.isOpen());
    }

    @Test
    void onNightModeLampsAreTurnedOff() {
        LampController phellipes = new LampPhellipesAdapter();
        LampController shoyuMi = new LampShoyuMiAdapter();

        phellipes.on();
        shoyuMi.on();

        home.addLamp(phellipes, shoyuMi);

        home.enableNightMode();

        assertFalse(phellipes.isOn());
        assertFalse(shoyuMi.isOn());
    }
    
    @Test
    void onDayModeLightsAreTurnedOn() {
        LampController phellipes = new LampPhellipesAdapter();
        LampController shoyuMi = new LampShoyuMiAdapter();

        home.addLamp(phellipes, shoyuMi);

        home.enableDayMode();

        assertTrue(phellipes.isOn());
        assertTrue(shoyuMi.isOn());
    }

    @Test
    void onDayModeACsAreTurnedOn() {
        ACBaumnAdapter baumn = new ACBaumnAdapter();
        ACGellaKazaAdapter gellaKaza = new ACGellaKazaAdapter();

        home.addAC(gellaKaza, baumn);

        home.enableDayMode();

        assertTrue(gellaKaza.isOn());
        assertTrue(baumn.isOn());
    }

    @Test
    void onDayModeACsTemperatureIsSetTo25() {
        ACBaumnAdapter baumn = new ACBaumnAdapter();
        ACGellaKazaAdapter gellaKaza = new ACGellaKazaAdapter();

        home.addAC(gellaKaza, baumn);

        home.enableDayMode();

        assertTrue(gellaKaza.getTemperature().isDefault());
        assertTrue(baumn.getTemperature().isDefault());
    }

    @Test
    void onDayModeBlindsAreOpen() {
        BlindSolariusAdapter solarius = new BlindSolariusAdapter();
        BlindNatLightAdapter natLight = new BlindNatLightAdapter();

        home.addBlind(natLight, solarius);

        home.enableDayMode();

        assertTrue(natLight.isOpen());
        assertTrue(solarius.isOpen());
    }
}
