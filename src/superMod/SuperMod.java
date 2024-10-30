package superMod;

import mindustry.mod.*;
import superMod.content.*;
import superMod.gen.*;

public class SuperMod extends Mod{
    @Override
    public void loadContent(){
        EntityRegistry.register();
        SuperStatus.load();
        SuperFluids.load();
        SuperBlocks.load();
    }
}
