package superMod;

import mindustry.mod.*;
import superMod.content.*;
import superMod.gen.*;

public class SuperMod extends Mod{
    @Override
    public void loadContent(){
        EntityRegistry.register();
        SuperSounds.load();
        SuperStatus.load();
        SuperFluids.load();
        SuperWeathers.load();
        SuperUnits.load();
        SuperBlocks.load();
    }
}
