package superMod.content;

import arc.graphics.Color;
import mindustry.type.Weather;
import mindustry.type.weather.RainWeather;
import mindustry.world.meta.Attribute;

public class SuperWeathers {
    public static Weather
    blightedRain;

    public static void load(){
        blightedRain = new RainWeather("blighted-rain"){{
            attrs.set(Attribute.light, -0.2f);
            attrs.set(Attribute.water, 0.15f);
            attrs.set(Attribute.spores, 0.3f);
            status = SuperStatus.contaminated;
            statusDuration = 10f;
            sound = SuperSounds.rainhowl;
            soundVol = 0.25f;

            yspeed = 2f;
            xspeed = 6.5f;
            density = 800f;
            stroke = 1f;
            sizeMin = 12f;
            sizeMax = 48f;
            liquid = SuperFluids.hydroblight;
            color = Color.valueOf("85637a");
        }};
    }
}
