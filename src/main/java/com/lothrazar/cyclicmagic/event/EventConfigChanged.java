package com.lothrazar.cyclicmagic.event;
import com.lothrazar.cyclicmagic.registry.ConfigRegistry;
import com.lothrazar.cyclicmagic.util.Const;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventConfigChanged {
  @SubscribeEvent
  public void onConfigChanged(OnConfigChangedEvent event) {
    if (event.getModID().equals(Const.MODID)) {
      ConfigRegistry.syncAllConfig();
    }
  }
}
