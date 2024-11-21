package superMod.content;

import arc.graphics.Color;
import ent.anno.Annotations;
import mindustry.content.Fx;
import mindustry.entities.abilities.RegenAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HaloPart;
import mindustry.entities.pattern.*;
import mindustry.gen.MechUnit;
import mindustry.gen.Mechc;
import mindustry.gen.Sounds;
import mindustry.gen.Unitc;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class SuperUnits {
    // mech
    public static @Annotations.EntityDef({Unitc.class, Mechc.class}) UnitType urge, exhort;

    public static void load(){
        // mech
        urge = new UnitType("urge"){{
            constructor = MechUnit::create;

            outlineColor = Pal.darkOutline;
            speed = 0.5f;
            hitSize = 9f;
            health = 250;
            armor = 5f;

            weapons.add(new Weapon("supermod-urge-weapon"){{
                reload = 83.3334f;
                x = 4f;
                y = -0.25f;
                top = false;
                mirror = true;
                ejectEffect = Fx.casing1;

                shoot = new ShootSpread(8, 2f);

                bullet = new BasicBulletType(5f, 40){{
                    width = 8f;
                    height = 10f;
                    lifetime = 20f;
                    knockback = 3f;
                    trailWidth = 1.5f;
                    trailLength = 6;
                    shootSound = Sounds.artillery;

                    shootEffect = Fx.shootBig;
                }};
            }});
            weapons.add(new Weapon("supermod-urge-ability"){{
                reload = 900f;
                display = false;
                mirror = false;
                rotate = false;
                shootCone = 15f;
                shootSound = Sounds.bolt;
                minWarmup = 0.9f;
                shootWarmupSpeed = 0.05f;
                x = 0f;
                y = 0f;
                ejectEffect = Fx.none;

                parts.add(new HaloPart(){{
                    hollow = true;
                    x = 0f;
                    y = 0f;
                    shapes = 4;
                    sides = 3;
                    radius = 7f;
                    radiusTo = 7f;
                    stroke = -1f;
                    strokeTo = 2f;
                    rotateSpeed = 0f;
                    color = Color.valueOf("ff6565");
                    layer = Layer.effect;
                    haloRadius = 18.5f;
                    haloRadiusTo = 18.5f;
                    haloRotateSpeed = 2f;
                }});
                parts.add(new HaloPart(){{
                    hollow = true;
                    x = 0f;
                    y = 0f;
                    shapes = 1;
                    sides = 8;
                    haloRadius = 0f;
                    haloRadiusTo = 0f;
                    radius = 16f;
                    radiusTo = 16f;
                    stroke = -1f;
                    strokeTo = 2f;
                    rotateSpeed = 2f;
                    haloRotation = 22.5f;
                    color = Color.valueOf("ff6565");
                    layer = Layer.effect;
                }});

                bullet = new BasicBulletType(0f, 0){{
                    width = 0f;
                    height = 0f;
                    recoil = -30f;

                    smokeEffect = despawnEffect = hitEffect = Fx.none;
                    hitSound = Sounds.none;
                }};
            }});
        }};
        exhort = new UnitType("exhort"){{
            constructor = MechUnit::create;

            outlineColor = Pal.darkOutline;
            speed = 0.5f;
            hitSize = 11f;
            health = 780;
            armor = 8f;

            abilities.add(new RegenAbility(){{
                amount = 0.166667f;
            }});

            weapons.add(new Weapon("supermod-exhort-weapon"){{
                top = false;
                alternate = false;
                x = 5.25f;
                y = 1.25f;
                shootY = 3f;
                recoil = 1.5f;

                shootSound = Sounds.torch;

                continuous = alwaysContinuous = true;

                bullet = new ContinuousFlameBulletType(){{
                    damage = 6f;
                    length = 80f;
                    knockback = 0.4f;
                    pierceCap = 1;
                    lightStroke = 24f;
                    width = 2f;
                    buildingDamageMultiplier = 0.45f;

                    continuous = alwaysContinuous = true;

                    colors = new Color[]{
                        Color.valueOf("bb5151").a(0.55f),
                        Color.valueOf("ff6565").a(0.7f),
                        Color.valueOf("d06b53").a(0.8f),
                        Color.valueOf("ffa665"),
                        Color.white
                    };
                    lightColor = hitColor = flareColor = Color.valueOf("ff6565");
                    flareWidth = 3f;
                    flareLength = 12f;
                    flareRotSpeed = 1f;
                }};
            }});
        }};
    }
}
