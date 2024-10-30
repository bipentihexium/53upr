package superMod.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class SuperFluids {
    public static Liquid
    // gas
    hydroblight, phasefoam, phaseEnergy;

    public static void load() {
        hydroblight = new Liquid("hydroblight"){{
            color = Color.valueOf("ca7ba1");
            gasColor = Color.valueOf("e8b1cc");
            heatCapacity = 0.35f;
            effect = SuperStatus.contaminated;
            boilPoint = 0.6f;
        }};
        phasefoam = new Liquid("phasefoam"){{
            color = gasColor = Color.valueOf("e48a95");
            coolant = false;
            gas = true;
            flammability = 0.1f;
            explosiveness = 0.2f;
        }};
        phaseEnergy = new Liquid("phase-energy"){{
            color = gasColor = Color.valueOf("b08cde");
            coolant = false;
            gas = true;
            flammability = 0.25f;
            explosiveness = 0.25f;
        }};
    }
}
