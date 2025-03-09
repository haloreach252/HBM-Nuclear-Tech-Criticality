package com.miniverse.hbm.lib;

import com.miniverse.hbm.entity.projectile.EntityBullet;
import com.miniverse.hbm.entity.projectile.EntityLaserBeam;
import com.miniverse.hbm.entity.projectile.EntityMinerBeam;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

public class ModDamageSource extends DamageSource {

    public static DamageSource nuclearBlast = (new DamageSource("nuclearBlast")).explosion();
    public static DamageSource mudPoisoning = (new DamageSource("mudPoisoning")).bypassArmor();
    public static DamageSource acid = new DamageSource("acid");
    public static DamageSource euthanizedSelf = (new DamageSource("euthanizedSelf")).bypassArmor();
    public static DamageSource euthanizedSelf2 = (new DamageSource("euthanizedSelf2")).bypassArmor();
    public static DamageSource tauBlast = (new DamageSource("tauBlast")).bypassArmor();
    public static DamageSource radiation = (new DamageSource("radiation")).bypassArmor();
    public static DamageSource digamma = (new DamageSource("digamma")).setIsAbsolute().bypassArmor().allowInCreativeMode();
    public static DamageSource suicide = (new DamageSource("suicide")).projectile();
    public static DamageSource teleporter = (new DamageSource("teleporter")).setIsAbsolute();
    public static DamageSource cheater = (new DamageSource("cheater")).setIsAbsolute().bypassArmor().allowInCreativeMode();
    public static DamageSource rubble = (new DamageSource("rubble")).projectile();
    public static DamageSource shrapnel = (new DamageSource("shrapnel")).projectile();
    public static DamageSource blackhole = (new DamageSource("blackhole")).setIsAbsolute().bypassArmor();
    public static DamageSource turbofan = (new DamageSource("blender")).setIsAbsolute().bypassArmor();
    public static DamageSource meteorite = (new DamageSource("meteorite")).setIsAbsolute().bypassArmor();
    public static DamageSource boxcar = (new DamageSource("boxcar")).setIsAbsolute().bypassArmor();
    public static DamageSource boat = (new DamageSource("boat")).setIsAbsolute().bypassArmor();
    public static DamageSource building = (new DamageSource("building")).setIsAbsolute().bypassArmor();
    public static DamageSource taint = (new DamageSource("taint")).setIsAbsolute().bypassArmor();
    public static DamageSource ams = (new DamageSource("ams")).setIsAbsolute().bypassArmor();
    public static DamageSource amsCore = (new DamageSource("amsCore")).setIsAbsolute().bypassArmor();
    public static DamageSource broadcast = (new DamageSource("broadcast")).setIsAbsolute().bypassArmor();
    public static DamageSource bang = (new DamageSource("bang")).setIsAbsolute().bypassArmor();
    public static DamageSource pc = (new DamageSource("pc")).setIsAbsolute().bypassArmor();
    public static DamageSource cloud = (new DamageSource("cloud")).setIsAbsolute().bypassArmor();
    public static DamageSource lead = (new DamageSource("lead")).setIsAbsolute().bypassArmor();
    public static DamageSource enervation = (new DamageSource("enervation")).setIsAbsolute().bypassArmor();
    public static DamageSource electricity = (new DamageSource("electricity")).setIsAbsolute().bypassArmor();
    public static DamageSource exhaust = (new DamageSource("exhaust")).setIsAbsolute().bypassArmor();
    public static DamageSource spikes = (new DamageSource("spikes")).bypassArmor();
    public static DamageSource lunar = (new DamageSource("lunar")).setIsAbsolute().bypassArmor();
    public static DamageSource monoxide = (new DamageSource("monoxide")).setIsAbsolute().bypassArmor();
    public static DamageSource asbestos = (new DamageSource("asbestos")).setIsAbsolute().bypassArmor();
    public static DamageSource blacklung = (new DamageSource("blacklung")).setIsAbsolute().bypassArmor();
    public static DamageSource mku = (new DamageSource("mku")).setIsAbsolute().bypassArmor();
    public static DamageSource vacuum = (new DamageSource("vacuum")).setIsAbsolute().bypassArmor();
    public static DamageSource overdose = (new DamageSource("overdose")).setIsAbsolute().bypassArmor();
    public static DamageSource microwave = (new DamageSource("microwave")).setIsAbsolute().bypassArmor();
    public static DamageSource nitan = (new DamageSource("nitan")).setIsAbsolute().bypassArmor().allowInCreativeMode();

    public static final String s_bullet = "revolverBullet";
    public static final String s_emplacer = "chopperBullet";
    public static final String s_tau = "tau";
    public static final String s_combineball = "cmb";
    public static final String s_zomg_prefix = "subAtomic";
    public static final String s_euthanized = "euthanized";
    public static final String s_emp = "electrified";
    public static final String s_flamethrower = "flamethrower";
    public static final String s_immolator = "plasma";
    public static final String s_cryolator = "ice";
    public static final String s_laser = "laser";
    public static final String s_boil = "boil";
    public static final String s_acid = "acidPlayer";

    public ModDamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }

    public static DamageSource causeBulletDamage(EntityBullet bullet, Entity hit) {
        return (new IndirectEntityDamageSource(s_bullet, bullet, hit)).projectile();
    }

    public static DamageSource causeBulletDamage(Entity shooter, Entity bullet) {
        return (new IndirectEntityDamageSource(s_bullet, shooter, bullet)).projectile();
    }

    public static DamageSource causeDisplacementDamage(Entity shooter, Entity hit) {
        return (new IndirectEntityDamageSource(s_emplacer, shooter, hit)).projectile();
    }

    public static DamageSource causeTauDamage(Entity shooter, Entity hit) {
        return (new IndirectEntityDamageSource(s_tau, shooter, hit)).projectile().bypassArmor();
    }

    public static DamageSource causeCombineDamage(Entity shooter, Entity hit) {
        return (new IndirectEntityDamageSource(s_combineball, shooter, hit)).projectile().bypassArmor();
    }

    public static DamageSource causeSubatomicDamage(Entity shooter, Entity hit) {
        return (new IndirectEntityDamageSource(s_zomg_prefix + (shooter.level().random.nextInt(5) + 1), shooter, hit))
                .projectile().bypassArmor();
    }

    public static DamageSource euthanized(Entity shooter, Entity hit) {
        return (new IndirectEntityDamageSource(s_euthanized, shooter, hit)).bypassArmor();
    }

    public static DamageSource causeLaserDamage(EntityLaserBeam laser, Entity hit) {
        return (new IndirectEntityDamageSource(s_laser, laser, hit)).bypassArmor();
    }

    public static DamageSource causeLaserDamage(EntityMinerBeam minerBeam, Entity hit) {
        return (new IndirectEntityDamageSource(s_laser, minerBeam, hit)).bypassArmor();
    }

    public static boolean getIsBullet(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_bullet);
        }
        return false;
    }

    public static boolean getIsEmplacer(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_emplacer);
        }
        return false;
    }

    public static boolean getIsTau(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_tau);
        }
        return false;
    }

    public static boolean getIsPoison(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_euthanized);
        }
        return false;
    }

    public static boolean getIsCmb(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_combineball);
        }
        return false;
    }

    public static boolean getIsSubatomic(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            String s = ((IndirectEntityDamageSource) source).msgId;
            return s.startsWith(s_zomg_prefix);
        }
        return false;
    }

    public static boolean getIsDischarge(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_emp);
        }
        return false;
    }

    public static boolean getIsFire(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_flamethrower);
        }
        return false;
    }

    public static boolean getIsPlasma(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_immolator);
        }
        return false;
    }

    public static boolean getIsLiquidNitrogen(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_cryolator);
        }
        return false;
    }

    public static boolean getIsLaser(DamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) source).msgId.equals(s_laser);
        }
        return false;
    }
}
