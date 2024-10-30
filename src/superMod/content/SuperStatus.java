package superMod.content;

import arc.struct.ObjectSet;
import mindustry.content.StatusEffects;
import mindustry.type.StatusEffect;

public class SuperStatus {
    public static StatusEffect
    cloaked, contaminated;

    public static void load() {
        cloaked = new StatusEffect("cloaked"){{
            opposites = new ObjectSet<>();
            // does it NEED to be this awful
            opposites.add(StatusEffects.wet);
            opposites.add(StatusEffects.burning);
            opposites.add(StatusEffects.tarred);
            opposites.add(StatusEffects.melting);
            opposites.add(StatusEffects.shocked);
        }};
        contaminated = new StatusEffect("contaminated"){{
            opposites = new ObjectSet<>();
            opposites.add(StatusEffects.melting);
            affinities = new ObjectSet<>();
            affinities.add(StatusEffects.wet);

            speedMultiplier = 0.8f;
            healthMultiplier = 0.8f;
        }};
    }
}
