package sakastudio.screen_shake;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = Screen_Shake.MOD_ID,
        name = Screen_Shake.MOD_NAME,
        version = Screen_Shake.VERSION
)
public class Screen_Shake {

    public static final String MOD_ID = "screenshake";
    public static final String MOD_NAME = "Screen_Shake";
    public static final String VERSION = "1.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Screen_Shake INSTANCE;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderWorldLastEvent e)
    {
        Shaking.INSTANCE.onUpdate();
    }

    @Mod.EventHandler
    //この関数でMODファイル自体をイベントの発火先にする。
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        PacketHandler.registerMessages(MOD_ID);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new ShakeCommand());
    }
}
