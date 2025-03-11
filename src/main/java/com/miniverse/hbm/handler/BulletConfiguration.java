package com.miniverse.hbm.handler;

import java.util.List;

import com.miniverse.hbm.entity.projectile.EntityBulletBaseNT;
import com.miniverse.hbm.entity.projectile.EntityBulletBaseNT.*;
import com.miniverse.hbm.handler.guncfg.BulletConfigFactory;
import com.miniverse.hbm.inventory.RecipesCommon.ComparableStack;
import com.miniverse.hbm.lib.ModDamageSource;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.particle.SpentCasing;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.ChatFormatting;

/**
 * This class holds all configuration parameters for bullets used by the mod.
 * It stores data such as ammunition consumption, velocity, damage bounds, behaviors, effects, and visual style.
 */
public class BulletConfiguration implements Cloneable {

    // What item this specific configuration consumes.
    public ComparableStack ammo;
    // How many ammo units one item restores.
    public int ammoCount = 1;
    // How fast the bullet is (in sanics per second, or sps).
    public float velocity;
    // Spread of bullets in gaussian range.
    public float spread;
    // Weapon durability reduced (centered around 10).
    public int wear;
    // Greatest amount of pellets created each shot.
    public int bulletsMin;
    // Least amount of pellets created each shot.
    public int bulletsMax;

    // Damage bounds.
    public float dmgMin;
    public float dmgMax;
    public float headshotMult = 1.0F;

    // Acceleration towards negative Y.
    public double gravity;
    // Max age in ticks before despawning.
    public int maxAge;

    // Whether the projectile should be able to bounce off blocks.
    public boolean doesRicochet;
    // The maximum angle at which the projectile should bounce.
    public double ricochetAngle;
    // Lower bound ricochet chance (below R angle).
    public int LBRC;
    // Higher bound ricochet chance (above R angle).
    public int HBRC;
    // How much of the initial velocity is kept after bouncing.
    public double bounceMod;
    // How many ticks until the projectile can hurt the shooter.
    public int selfDamageDelay = 5;

    // Whether or not the bullet should penetrate mobs.
    public boolean doesPenetrate;
    // Disables collisions with blocks entirely.
    public boolean isSpectral;
    // Whether or not the bullet should break glass.
    public boolean doesBreakGlass;
    // Bullets still call the impact function when hitting blocks but do not get destroyed.
    public boolean liveAfterImpact;

    // Creates a "muzzle flash" and a ton of smoke with every projectile spawned.
    public boolean blackPowder = false;

    // Bullet effects.
    public List<MobEffectInstance> effects;
    public int incendiary;
    public int emp;
    public boolean blockDamage = true;
    public float explosive;
    public double jolt;
    public int rainbow;
    public int nuke;
    public int shrapnel;
    public int chlorine;
    public int leadChance;
    public int caustic;
    public boolean destroysBlocks;
    public boolean instakill;

    // Bullet behavior interfaces.
    public IBulletHurtBehaviorNT bntHurt;
    public IBulletHitBehaviorNT bntHit;
    public IBulletRicochetBehaviorNT bntRicochet;
    public IBulletImpactBehaviorNT bntImpact;
    public IBulletUpdateBehaviorNT bntUpdate;

    // Appearance.
    public int style;
    // Additional appearance data, i.e. particle effects.
    public int trail;
    // Ricochet sound type.
    public int plink;
    // Vanilla particle FX.
    public String vPFX = "";
    public SpentCasing spentCasing;

    // Energy projectiles.
    // Power consumed per shot.
    public int dischargePerShot;
    // Unlocalised firing mode name.
    public String modeName;
    // Firing mode text colour.
    public ChatFormatting chatColour = ChatFormatting.WHITE;
    // Firing rate.
    public int firingRate;

    public String damageType = ModDamageSource.s_bullet;
    public boolean dmgProj = true;
    public boolean dmgFire = false;
    public boolean dmgExplosion = false;
    public boolean dmgBypass = false;

    // Bullet styles.
    public static final int STYLE_NONE = -1;
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_PISTOL = 1;
    public static final int STYLE_FLECHETTE = 2;
    public static final int STYLE_PELLET = 3;
    public static final int STYLE_BOLT = 4;
    public static final int STYLE_FOLLY = 5;
    public static final int STYLE_ROCKET = 6;
    public static final int STYLE_STINGER = 7;
    public static final int STYLE_NUKE = 8;
    public static final int STYLE_MIRV = 9;
    public static final int STYLE_GRENADE = 10;
    public static final int STYLE_BF = 11;
    public static final int STYLE_ORB = 12;
    public static final int STYLE_METEOR = 13;
    public static final int STYLE_APDS = 14;
    public static final int STYLE_BLADE = 15;
    public static final int STYLE_BARREL = 16;
    public static final int STYLE_TAU = 17;
    public static final int STYLE_LEADBURSTER = 18;

    // Ricochet sound types.
    public static final int PLINK_NONE = 0;
    public static final int PLINK_BULLET = 1;
    public static final int PLINK_GRENADE = 2;
    public static final int PLINK_ENERGY = 3;
    public static final int PLINK_SING = 4;

    // Bolt styles.
    public static final int BOLT_LACUNAE = 0;
    public static final int BOLT_NIGHTMARE = 1;
    public static final int BOLT_LASER = 2;
    public static final int BOLT_ZOMG = 3;
    public static final int BOLT_WORM = 4;
    public static final int BOLT_GLASS_CYAN = 5;
    public static final int BOLT_GLASS_BLUE = 6;

    public BulletConfiguration setToBolt(int trail) {
        this.style = STYLE_BOLT;
        this.trail = trail;
        return this;
    }

    public BulletConfiguration setToFire(int duration) {
        this.incendiary = duration;
        return this;
    }

    public BulletConfiguration setHeadshot(float mult) {
        this.headshotMult = mult;
        return this;
    }

    public BulletConfiguration setToGuided() {
        this.bntUpdate = BulletConfigFactory.getLaserSteering();
        this.doesRicochet = false;
        return this;
    }

    public BulletConfiguration getChlorophyte() {
        this.bntUpdate = BulletConfigFactory.getHomingBehavior(30, 180);
        this.bntHurt = BulletConfigFactory.getPenHomingBehavior();
        this.dmgMin *= 2F;
        this.dmgMax *= 2F;
        this.wear *= 0.5;
        this.velocity *= 0.3;
        this.doesRicochet = false;
        this.doesPenetrate = true;
        this.vPFX = "greendust";

        if(this.spentCasing != null) {
            int[] colors = this.spentCasing.getColors();
            this.spentCasing = this.spentCasing.clone();
            if(colors != null && colors.length > 0) {
                int[] colorClone = new int[colors.length];
                for(int i = 0; i < colors.length; i++) {
                    colorClone[i] = colors[i];
                }
                colorClone[colorClone.length - 1] = 0x659750; // Standard chlorophyte coloring.
                this.spentCasing.setColor(colorClone).register(this.spentCasing.getName() + "Cl");
            }
        }

        return this;
    }

    public BulletConfiguration setToHoming(ItemStack ammo) {
        this.ammo = new ComparableStack(ammo);
        return getChlorophyte();
    }

    public BulletConfiguration accuracyMod(float mod) {
        this.spread *= mod;
        return this;
    }

    /**
     * Returns a DamageSource for the bullet based on this configuration.
     * The damage source can be indirect (if a shooter is provided) or direct.
     */
    public DamageSource getDamage(EntityBulletBaseNT bullet, LivingEntity shooter) {
        DamageSource dmg;
        String unloc = damageType;
        if(unloc.equals(ModDamageSource.s_zomg_prefix))
            unloc += (bullet.level().getRandom().nextInt(5) + 1); // Append a random pain indicator.

        if(shooter != null)
            dmg = new IndirectEntityDamageSource(unloc, bullet, shooter);
        else
            dmg = new DamageSource(unloc);

        if(this.dmgProj)
            dmg = dmg.setProjectile();
        if(this.dmgFire)
            dmg = dmg.setIsFire();
        if(this.dmgExplosion)
            dmg = dmg.setExplosion();
        if(this.dmgBypass)
            dmg = dmg.bypassArmor();

        return dmg;
    }

    @Override
    public BulletConfiguration clone() {
        try {
            return (BulletConfiguration) super.clone();
        } catch(CloneNotSupportedException e) {
            HBMNuclearTechCriticality.logger.catching(e);
            return new BulletConfiguration();
        }
    }
}
