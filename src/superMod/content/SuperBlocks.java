package superMod.content;

import arc.graphics.Color;
import arc.struct.EnumSet;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.Sounds;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;

public class SuperBlocks extends Blocks {
    public static Block
    // environment
    blightsand, blightsandWall,
    stoneVent, daciteVent, mossyVent, sandVent, darksandVent,
    shallowHydroblight, deepHydroblight,
    // turrets
    blightedScatter, blightedWave, blightedLancer, blightedSalvo, blightedTsunami, blightedFuse, blightedRipple, blightedCyclone, blightedBreach,
    // distribution
    warpDriver,
    // crafting
    phasefoamSynthesizer, inertialPhaseDisentangler,
    // payload
    soldierReassembler, primaryReassemblerModule;

    public static void load() {
        // environment
        blightsand = new Floor("blightsand"){{
            variants = 3;
            speedMultiplier = 0.9f;
        }};
        blightsandWall = new StaticWall("blightsand-wall"){{
            variants = 2;
        }};
        stoneVent = new SteamVent("stone-vent"){{
            parent = blendGroup = Blocks.stone;
            attributes.set(Attribute.steam, 1f);
        }};
        daciteVent = new SteamVent("dacite-vent"){{
            parent = blendGroup = Blocks.dacite;
            attributes.set(Attribute.steam, 1f);
        }};
        mossyVent = new SteamVent("mossy-vent"){{
            parent = blendGroup = Blocks.moss;
            attributes.set(Attribute.steam, 1f);
        }};
        sandVent = new SteamVent("sand-vent"){{
            parent = blendGroup = Blocks.sand;
            attributes.set(Attribute.steam, 1f);
        }};
        darksandVent = new SteamVent("darksand-vent"){{
            parent = blendGroup = Blocks.darksand;
            attributes.set(Attribute.steam, 1f);
        }};
        shallowHydroblight = new Floor("shallow-hydroblight"){{
            speedMultiplier = 0.45f;
            variants = 2;
            status = SuperStatus.contaminated;
            statusDuration = 999999999f;
            liquidDrop = SuperFluids.hydroblight;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.8f;
            supportsOverlay = true;
        }};
        deepHydroblight = new Floor("deep-hydroblight"){{
            speedMultiplier = 0.3f;
            variants = 2;
            status = SuperStatus.contaminated;
            statusDuration = 999999999f;
            drownTime = 180f;
            liquidDrop = SuperFluids.hydroblight;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.8f;
            supportsOverlay = true;
        }};
        // turrets
        // NOTE: blighted turrets are all reskins of vanilla turrets
        blightedScatter = new ItemTurret("blighted-scatter"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 85, Items.lead, 45));
            ammo(
                    Items.scrap, new FlakBulletType(4f, 3){{
                        lifetime = 60f;
                        ammoMultiplier = 5f;
                        shootEffect = Fx.shootSmall;
                        reloadMultiplier = 0.5f;
                        width = 6f;
                        height = 8f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 22f * 1.5f;
                        splashDamageRadius = 24f;
                    }},
                    Items.lead, new FlakBulletType(4.2f, 3){{
                        lifetime = 60f;
                        ammoMultiplier = 4f;
                        shootEffect = Fx.shootSmall;
                        width = 6f;
                        height = 8f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 27f * 1.5f;
                        splashDamageRadius = 15f;
                    }},
                    Items.metaglass, new FlakBulletType(4f, 3){{
                        lifetime = 60f;
                        ammoMultiplier = 5f;
                        shootEffect = Fx.shootSmall;
                        reloadMultiplier = 0.8f;
                        width = 6f;
                        height = 8f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 30f * 1.5f;
                        splashDamageRadius = 20f;
                        fragBullets = 6;
                        fragBullet = new BasicBulletType(3f, 5){{
                            width = 5f;
                            height = 12f;
                            shrinkY = 1f;
                            lifetime = 20f;
                            backColor = Pal.gray;
                            frontColor = Color.white;
                            despawnEffect = Fx.none;
                            collidesGround = false;
                        }};
                    }}
            );

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
                parts.add(new RegionPart("-mid"){{
                    progress = DrawPart.PartProgress.recoil;
                    under = false;
                    moveY = -1.25f;
                }});
            }};

            reload = 18f;
            range = 220f;
            size = 2;
            targetGround = false;

            shoot.shotDelay = 5f;
            shoot.shots = 2;

            recoil = 1f;
            rotateSpeed = 15f;
            inaccuracy = 17f;
            shootCone = 35f;

            scaledHealth = 200;
            shootSound = Sounds.shootSnap;
            coolant = consumeCoolant(0.2f);
            researchCostMultiplier = 0.05f;

            limitRange(2);
        }};
        blightedWave = new LiquidTurret("blighted-wave"){{
            requirements(Category.turret, ItemStack.with(Items.metaglass, 45, Items.lead, 75, Items.copper, 25));
            ammo(
                    Liquids.water, new LiquidBulletType(Liquids.water){{
                        knockback = 0.7f;
                        drag = 0.01f;
                        layer = Layer.bullet - 2f;
                    }},
                    SuperFluids.hydroblight, new LiquidBulletType(SuperFluids.hydroblight){{
                        knockback = 0.7f;
                        drag = 0.01f;
                        layer = Layer.bullet - 2f;
                        status = SuperStatus.contaminated;
                        // effectively infinite, just imagine you need to keep the affected unit on life support
                        statusDuration = 999999999f;
                    }}
            );

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
            }};

            size = 2;
            recoil = 0f;
            reload = 3f;
            inaccuracy = 5f;
            shootCone = 50f;
            liquidCapacity = 10f;
            shootEffect = Fx.shootLiquid;
            range = 110f;
            scaledHealth = 250;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};
        blightedLancer = new PowerTurret("blighted-lancer"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 60, Items.lead, 70, Items.silicon, 60, Items.titanium, 30));
            range = 165f;

            shoot.firstShotDelay = 40f;

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
            }};

            recoil = 2f;
            reload = 80f;
            shake = 2f;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 2;
            scaledHealth = 280;
            targetAir = false;
            moveWhileCharging = false;
            accurateDelay = false;
            shootSound = Sounds.laser;
            coolant = consumeCoolant(0.2f);

            consumePower(6f);

            shootType = new LaserBulletType(140){{
                colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
                chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);

                buildingDamageMultiplier = 0.25f;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 400f;
                collidesAir = false;
                length = 173f;
                ammoMultiplier = 1f;
                pierceCap = 4;
            }};
        }};
        blightedSalvo = new ItemTurret("blighted-salvo"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 100, Items.graphite, 80, Items.titanium, 50));
            ammo(
                    Items.copper,  new BasicBulletType(2.5f, 11){{
                        width = 7f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = 2;
                    }},
                    Items.graphite, new BasicBulletType(3.5f, 20){{
                        width = 9f;
                        height = 12f;
                        reloadMultiplier = 0.6f;
                        ammoMultiplier = 4;
                        lifetime = 60f;
                    }},
                    Items.pyratite, new BasicBulletType(3.2f, 18){{
                        width = 10f;
                        height = 12f;
                        frontColor = Pal.lightishOrange;
                        backColor = Pal.lightOrange;
                        status = StatusEffects.burning;
                        hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);

                        ammoMultiplier = 5;

                        splashDamage = 12f;
                        splashDamageRadius = 22f;

                        makeFire = true;
                        lifetime = 60f;
                    }},
                    Items.silicon, new BasicBulletType(3f, 15, "bullet"){{
                        width = 7f;
                        height = 9f;
                        homingPower = 0.1f;
                        reloadMultiplier = 1.5f;
                        ammoMultiplier = 5;
                        lifetime = 60f;
                    }},
                    Items.thorium, new BasicBulletType(4f, 29, "bullet"){{
                        width = 10f;
                        height = 13f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 4;
                        lifetime = 60f;
                    }}
            );

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
                parts.add(new RegionPart("-side"){{
                    progress = PartProgress.warmup;
                    moveX = 0.6f;
                    moveRot = -15f;
                    mirror = true;
                    layerOffset = 0.001f;
                    moves.add(new PartMove(PartProgress.recoil, 0.5f, -0.5f, -8f));
                }}, new RegionPart("-barrel"){{
                    progress = PartProgress.recoil;
                    moveY = -2.5f;
                }});
            }};

            size = 2;
            range = 190f;
            reload = 31f;
            consumeAmmoOnce = false;
            ammoEjectBack = 3f;
            recoil = 0f;
            shake = 1f;
            shoot.shots = 4;
            shoot.shotDelay = 3f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(0.2f);
        }};
        blightedTsunami = new LiquidTurret("blighted-tsunami"){{
            requirements(Category.turret, ItemStack.with(Items.metaglass, 100, Items.lead, 400, Items.titanium, 250, Items.thorium, 100));
            ammo(
                    Liquids.water, new LiquidBulletType(Liquids.water){{
                        lifetime = 49f;
                        speed = 4f;
                        knockback = 1.7f;
                        puddleSize = 8f;
                        orbSize = 4f;
                        drag = 0.001f;
                        ammoMultiplier = 0.4f;
                        statusDuration = 60f * 4f;
                        damage = 0.2f;
                        layer = Layer.bullet - 2f;
                    }},
                    SuperFluids.hydroblight, new LiquidBulletType(SuperFluids.hydroblight){{
                        lifetime = 49f;
                        speed = 4f;
                        knockback = 1.7f;
                        puddleSize = 8f;
                        orbSize = 4f;
                        drag = 0.001f;
                        ammoMultiplier = 0.4f;
                        status = SuperStatus.contaminated;
                        statusDuration = 999999999f;
                        damage = 0.2f;
                        layer = Layer.bullet - 2f;
                    }}
            );

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
            }};

            size = 3;
            reload = 3f;
            shoot.shots = 2;
            velocityRnd = 0.1f;
            inaccuracy = 4f;
            recoil = 1f;
            shootCone = 45f;
            liquidCapacity = 40f;
            shootEffect = Fx.shootLiquid;
            range = 190f;
            scaledHealth = 250;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};
        blightedFuse = new ItemTurret("blighted-fuse"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 225, Items.graphite, 225, Items.thorium, 100));

            reload = 35f;
            shake = 4f;
            range = 90f;
            recoil = 5f;

            shoot = new ShootSpread(3, 20f);

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
            }};

            shootCone = 30;
            size = 3;
            envEnabled |= Env.space;

            scaledHealth = 220;
            shootSound = Sounds.shotgun;
            coolant = consumeCoolant(0.3f);

            float brange = range + 10f;

            ammo(
                    Items.titanium, new ShrapnelBulletType(){{
                        length = brange;
                        damage = 66f;
                        ammoMultiplier = 4f;
                        width = 17f;
                        reloadMultiplier = 1.3f;
                    }},
                    Items.thorium, new ShrapnelBulletType(){{
                        length = brange;
                        damage = 105f;
                        ammoMultiplier = 5f;
                        toColor = Pal.thoriumPink;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                    }}
            );
        }};
        blightedRipple = new ItemTurret("blighted-ripple"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 150, Items.graphite, 135, Items.titanium, 60));
            ammo(
                    Items.graphite, new ArtilleryBulletType(3f, 20){{
                        knockback = 0.8f;
                        lifetime = 80f;
                        width = height = 11f;
                        collidesTiles = false;
                        splashDamageRadius = 25f * 0.75f;
                        splashDamage = 33f;
                    }},
                    Items.silicon, new ArtilleryBulletType(3f, 20){{
                        knockback = 0.8f;
                        lifetime = 80f;
                        width = height = 11f;
                        collidesTiles = false;
                        splashDamageRadius = 25f * 0.75f;
                        splashDamage = 33f;
                        reloadMultiplier = 1.2f;
                        ammoMultiplier = 3f;
                        homingPower = 0.08f;
                        homingRange = 50f;
                    }},
                    Items.pyratite, new ArtilleryBulletType(3f, 24){{
                        hitEffect = Fx.blastExplosion;
                        knockback = 0.8f;
                        lifetime = 80f;
                        width = height = 13f;
                        collidesTiles = false;
                        splashDamageRadius = 25f * 0.75f;
                        splashDamage = 45f;
                        status = StatusEffects.burning;
                        statusDuration = 60f * 12f;
                        frontColor = Pal.lightishOrange;
                        backColor = Pal.lightOrange;
                        makeFire = true;
                        trailEffect = Fx.incendTrail;
                        ammoMultiplier = 4f;
                    }},
                    Items.blastCompound, new ArtilleryBulletType(2f, 20, "shell"){{
                        hitEffect = Fx.blastExplosion;
                        knockback = 0.8f;
                        lifetime = 80f;
                        width = height = 14f;
                        collidesTiles = false;
                        ammoMultiplier = 4f;
                        splashDamageRadius = 45f * 0.75f;
                        splashDamage = 55f;
                        backColor = Pal.missileYellowBack;
                        frontColor = Pal.missileYellow;

                        status = StatusEffects.blasted;
                    }},
                    Items.plastanium, new ArtilleryBulletType(3.4f, 20, "shell"){{
                        hitEffect = Fx.plasticExplosion;
                        knockback = 1f;
                        lifetime = 80f;
                        width = height = 13f;
                        collidesTiles = false;
                        splashDamageRadius = 35f * 0.75f;
                        splashDamage = 45f;
                        fragBullet = new BasicBulletType(2.5f, 10, "bullet"){{
                            width = 10f;
                            height = 12f;
                            shrinkY = 1f;
                            lifetime = 15f;
                            backColor = Pal.plastaniumBack;
                            frontColor = Pal.plastaniumFront;
                            despawnEffect = Fx.none;
                            collidesAir = false;
                        }};
                        fragBullets = 10;
                        backColor = Pal.plastaniumBack;
                        frontColor = Pal.plastaniumFront;
                    }}
            );

            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
            }};

            targetAir = false;
            size = 3;
            shoot.shots = 4;
            inaccuracy = 12f;
            reload = 60f;
            ammoEjectBack = 5f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 2;
            velocityRnd = 0.2f;
            recoil = 6f;
            shake = 2f;
            range = 290f;
            minRange = 50f;
            coolant = consumeCoolant(0.3f);

            scaledHealth = 130;
            shootSound = Sounds.artillery;
        }};
        blightedCyclone = new ItemTurret("blighted-cyclone"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 200, Items.titanium, 125, Items.plastanium, 80));
            ammo(
                    Items.metaglass, new FlakBulletType(4f, 6){{
                        ammoMultiplier = 2f;
                        shootEffect = Fx.shootSmall;
                        reloadMultiplier = 0.8f;
                        width = 6f;
                        height = 8f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 45f;
                        splashDamageRadius = 25f;
                        fragBullet = new BasicBulletType(3f, 12, "bullet"){{
                            width = 5f;
                            height = 12f;
                            shrinkY = 1f;
                            lifetime = 20f;
                            backColor = Pal.gray;
                            frontColor = Color.white;
                            despawnEffect = Fx.none;
                        }};
                        fragBullets = 4;
                        explodeRange = 20f;
                        collidesGround = true;
                    }},
                    Items.blastCompound, new FlakBulletType(4f, 8){{
                        shootEffect = Fx.shootBig;
                        ammoMultiplier = 5f;
                        splashDamage = 45f;
                        splashDamageRadius = 60f;
                        collidesGround = true;

                        status = StatusEffects.blasted;
                        statusDuration = 60f;
                    }},
                    Items.plastanium, new FlakBulletType(4f, 8){{
                        ammoMultiplier = 4f;
                        splashDamageRadius = 40f;
                        splashDamage = 37.5f;
                        fragBullet = new BasicBulletType(2.5f, 12, "bullet"){{
                            width = 10f;
                            height = 12f;
                            shrinkY = 1f;
                            lifetime = 15f;
                            backColor = Pal.plastaniumBack;
                            frontColor = Pal.plastaniumFront;
                            despawnEffect = Fx.none;
                        }};
                        fragBullets = 6;
                        hitEffect = Fx.plasticExplosion;
                        frontColor = Pal.plastaniumFront;
                        backColor = Pal.plastaniumBack;
                        shootEffect = Fx.shootBig;
                        collidesGround = true;
                        explodeRange = 20f;
                    }},
                    Items.surgeAlloy, new FlakBulletType(4.5f, 13){{
                        ammoMultiplier = 5f;
                        splashDamage = 50f * 1.5f;
                        splashDamageRadius = 38f;
                        lightning = 2;
                        lightningLength = 7;
                        shootEffect = Fx.shootBig;
                        collidesGround = true;
                        explodeRange = 20f;
                    }}
            );
            shootY = 10f;

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        0f, 1f, 0f,
                        3f, 0f, 0f,
                        -3f, 0f, 0f,
                };
            }};

            recoils = 3;
            drawer = new DrawTurret(){{
                basePrefix = "blighted-";
                for(int i = 3; i > 0; i--){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + i){{
                        progress = PartProgress.recoil;
                        recoilIndex = f - 1;
                        under = true;
                        moveY = -2f;
                    }});
                }
            }};

            reload = 8f;
            range = 200f;
            size = 3;
            recoil = 1.5f;
            recoilTime = 10;
            rotateSpeed = 10f;
            inaccuracy = 10f;
            shootCone = 30f;
            shootSound = Sounds.shootSnap;
            coolant = consumeCoolant(0.3f);

            scaledHealth = 145;
            limitRange();
        }};
        blightedBreach = new ItemTurret("blighted-breach"){{
            requirements(Category.turret, ItemStack.with(Items.beryllium, 150, Items.silicon, 150, Items.graphite, 250));

            Effect sfe = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);

            ammo(
                    Items.beryllium, new BasicBulletType(7.5f, 85){{
                        width = 12f;
                        hitSize = 7f;
                        height = 20f;
                        shootEffect = sfe;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1;
                        pierceCap = 2;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Pal.berylShot;
                        frontColor = Color.white;
                        trailWidth = 2.1f;
                        trailLength = 10;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                    }},
                    Items.tungsten, new BasicBulletType(8f, 95){{
                        width = 13f;
                        height = 19f;
                        hitSize = 7f;
                        shootEffect = sfe;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1;
                        reloadMultiplier = 1f;
                        pierceCap = 3;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Pal.tungstenShot;
                        frontColor = Color.white;
                        trailWidth = 2.2f;
                        trailLength = 11;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        rangeChange = 40f;
                        buildingDamageMultiplier = 0.3f;
                    }},
                    Items.copper, new BasicBulletType(10f, 30f){{
                        width = 10f;
                        hitSize = 7f;
                        height = 16f;
                        lifetime = 20f;
                        shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1f;
                        reloadMultiplier = 2f;
                        pierceCap = 2;
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Color.valueOf("efcdb7");
                        frontColor = Color.white;
                        trailWidth = 1.8f;
                        trailLength = 10;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                    }},
                    Items.titanium, new BasicBulletType(40f, 68f){{
                        width = 12f;
                        hitSize = 7f;
                        height = 20f;
                        lifetime = 5f;
                        shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1f;
                        pierceCap = 2;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Color.valueOf("b3c6fb");
                        frontColor = Color.white;
                        trailWidth = 2.1f;
                        trailLength = 200;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                    }}
            );

            coolantMultiplier = 6f;
            shootSound = Sounds.shootAlt;

            targetUnderBlocks = false;
            shake = 1f;
            ammoPerShot = 2;
            drawer = new DrawTurret("reinforced-blighted-");
            shootY = -2;
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            reload = 40f;
            recoil = 2f;
            range = 190;
            shootCone = 3f;
            scaledHealth = 180;
            rotateSpeed = 1.5f;
            researchCostMultiplier = 0.05f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
            limitRange(12f);
        }};
        ((ItemTurret) Blocks.breach).ammoTypes.put(Items.copper, new BasicBulletType(10f, 30f){{
                width = 10f;
                hitSize = 7f;
                height = 16f;
                lifetime = 20f;
                shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                smokeEffect = Fx.shootBigSmoke;
                ammoMultiplier = 1f;
                reloadMultiplier = 2f;
                pierceCap = 2;
                status = StatusEffects.shocked;
                statusDuration = 60f;
                pierce = true;
                pierceBuilding = true;
                hitColor = backColor = trailColor = Color.valueOf("efcdb7");
                frontColor = Color.white;
                trailWidth = 1.8f;
                trailLength = 10;
                hitEffect = despawnEffect = Fx.hitBulletColor;
                buildingDamageMultiplier = 0.3f;
            }}
        );
        ((ItemTurret) Blocks.breach).ammoTypes.put(Items.titanium, new BasicBulletType(40f, 68f){{
                width = 12f;
                hitSize = 7f;
                height = 20f;
                lifetime = 5f;
                shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                smokeEffect = Fx.shootBigSmoke;
                ammoMultiplier = 1f;
                pierceCap = 2;
                pierce = true;
                pierceBuilding = true;
                hitColor = backColor = trailColor = Color.valueOf("b3c6fb");
                frontColor = Color.white;
                trailWidth = 2.1f;
                trailLength = 200;
                hitEffect = despawnEffect = Fx.hitBulletColor;
                buildingDamageMultiplier = 0.3f;
            }}
        );
        // distribution
        warpDriver = new MassDriver("warp-driver"){{
            requirements(Category.distribution, ItemStack.with(Items.silicon, 160, Items.tungsten, 145, Items.plastanium, 30, Items.phaseFabric, 20));
            size = 3;
            health = 560;
            itemCapacity = 200;
            reload = 240f;
            range = 600f;

            consumePower(6f);
            consumeLiquid(SuperFluids.phaseEnergy, 0.2f);
        }};
        // crafting
        phasefoamSynthesizer = new GenericCrafter("phasefoam-synthesizer"){{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 50, Items.tungsten, 40, Items.thorium, 25));
            outputLiquid = new LiquidStack(SuperFluids.phasefoam, 0.1f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(SuperFluids.phasefoam, 1f),
                new DrawRegion("-rutter", 1, true),
                new DrawDefault()
            );
            size = 2;
            health = 260;
            craftTime = 60f;
            hasLiquids = true;
            hasItems = hasPower = false;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.04f;

            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.1f, Liquids.arkycite, 0.2f));
        }};
        inertialPhaseDisentangler = new GenericCrafter("inertial-phase-disentangler"){{
            requirements(Category.crafting, ItemStack.with(Items.graphite, 85, Items.plastanium, 65, Items.phaseFabric, 50));
            outputLiquid = new LiquidStack(SuperFluids.phaseEnergy, 0.1f);
            outputItem = new ItemStack(Items.scrap, 6);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(SuperFluids.phaseEnergy, 1f),
                new DrawSpikes(){{
                    color = Color.valueOf("d3bcf0");
                    stroke = 1f;
                    layers = 2;
                    amount = 8;
                    rotateSpeed = 0.5f;
                    layerSpeed = -0.9f;
                }},
                new DrawMultiWeave(){{
                    glowColor = Color.valueOf("9269ad").a(0.8f);
                }},
                new DrawDefault(),
                new DrawGlowRegion("-glow"){{
                    color = Color.valueOf("b08cde");
                    glowScale = 5f;
                    alpha = 0.5f;
                }}
            );
            size = 3;
            health = 475;
            craftTime = 360f;
            hasItems = hasLiquids = hasPower = true;
            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.06f;

            consumePower(8f);
            consumeItem(Items.phaseFabric, 3);
            consumeLiquid(SuperFluids.phasefoam, 0.2f);
        }};
        // payload
        ((Constructor) Blocks.constructor).filter.set(Seq.with(
            Blocks.tungstenWall,
            Blocks.tungstenWallLarge,
            Blocks.berylliumWallLarge,
            Blocks.carbideWallLarge,
            Blocks.reinforcedSurgeWallLarge,
            Blocks.reinforcedLiquidContainer,
            Blocks.reinforcedContainer,
            Blocks.beamNode
        ));
        ((Constructor) Blocks.largeConstructor).filter.set(Seq.with(
            Blocks.diffuse,
            Blocks.sublimate
        ));
        soldierReassembler = new UnitAssembler("soldier-reassembler"){{
            requirements(Category.units, ItemStack.with(Items.tungsten, 500, Items.silicon, 380, Items.carbide, 120, Items.phaseFabric, 85));
            size = 5;
            areaSize = 13;

            plans.add(
                new AssemblerUnitPlan(SuperUnits.urge, 1200f, PayloadStack.list(UnitTypes.dagger, 3, Blocks.tungstenWall, 6, Blocks.diffuse, 1)),
                new AssemblerUnitPlan(SuperUnits.exhort, 1800f, PayloadStack.list(UnitTypes.mace, 2, Blocks.tungstenWallLarge, 2, Blocks.sublimate, 2))
            );

            consumePower(4f);
            consumeLiquid(SuperFluids.phaseEnergy, 0.2f);
        }};
        primaryReassemblerModule = new UnitAssemblerModule("primary-reassembler-module"){{
            requirements(Category.units, ItemStack.with(Items.lead, 350, Items.tungsten, 235, Items.phaseFabric, 70));
            size = 3;
            tier = 1;

            consumePower(4f);
        }};
    }
}
