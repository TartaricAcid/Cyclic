package com.lothrazar.cyclicmagic.block.tileentity;
import java.util.List;
import com.lothrazar.cyclicmagic.util.Const;
import com.lothrazar.cyclicmagic.util.UtilEntity;
import com.lothrazar.cyclicmagic.util.UtilParticle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityMagnetAnti extends TileEntityBaseMachine implements ITickable {
  private int timer;
  private static final String NBT_TIMER = "Timer";
  public final static int TIMER_FULL = 10;
  public final static int ITEM_VRADIUS = 3;
  public final static int ITEM_HRADIUS = 32;
  public final static float SPEED = 1.437F;
  
  public TileEntityMagnetAnti() {
    this.timer = TIMER_FULL;
  }
  @Override
  public void readFromNBT(NBTTagCompound tagCompound) {
    super.readFromNBT(tagCompound);
    timer = tagCompound.getInteger(NBT_TIMER);
  }
  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
    tagCompound.setInteger(NBT_TIMER, timer);
    return super.writeToNBT(tagCompound);
  }
  public boolean isBurning() {
    return this.timer > 0 && this.timer < TIMER_FULL;
  }
  @Override
  public void update() {
    boolean trigger = false;
    timer -= this.getSpeed();
    if (timer <= 0) {
      timer = TIMER_FULL;
      trigger = true;
    }
    // center of the block
    double x = this.getPos().getX() + 0.5;
    double y = this.getPos().getY() - 0.5;
    double z = this.getPos().getZ() + 0.5;
    if (trigger) {
      AxisAlignedBB range = UtilEntity.makeBoundingBox(x, y, z, ITEM_HRADIUS, ITEM_VRADIUS);
      List<EntityLivingBase> nonPlayer = UtilEntity.getLivingHostile(this.getWorld(), range);
      UtilEntity.pullEntityList(x, y, z, false, nonPlayer,SPEED,SPEED);
      for(EntityLivingBase living : nonPlayer){
        if(living.isCreatureType(EnumCreatureType.MONSTER, false)){
          living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,10*Const.TICKS_PER_SEC, 1));
          living.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,10*Const.TICKS_PER_SEC, 4));
        }
      }
      timer = TIMER_FULL;//harvest worked!
      spawnParticles();
    }
  }
  protected void spawnParticles() {
    if (this.getWorld().isRemote) {
      double x = this.getPos().getX() + 0.5; //center of the block;
      double y = this.getPos().getY() + 0.5;
      double z = this.getPos().getZ() + 0.5;
      UtilParticle.spawnParticle(this.getWorld(), EnumParticleTypes.CRIT_MAGIC, x, y, z);
      UtilParticle.spawnParticle(this.getWorld(), EnumParticleTypes.CRIT_MAGIC, x, y + 1, z);
      UtilParticle.spawnParticle(this.getWorld(), EnumParticleTypes.CRIT_MAGIC, x, y + 2, z);
      UtilParticle.spawnParticle(this.getWorld(), EnumParticleTypes.CRIT_MAGIC, x, y + 3, z);
    }
  }
  private int getSpeed() {
    return 1;
  }
}
