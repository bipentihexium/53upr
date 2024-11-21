package superMod.content;

import arc.audio.Sound;
import mindustry.Vars;

public class SuperSounds {
    public static Sound rainhowl;
    public static void load() {
        rainhowl = Vars.tree.loadSound("rainhowl");
    }
}
