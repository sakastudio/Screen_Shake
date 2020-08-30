package sakastudio.screen_shake;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.sound.sampled.LineUnavailableException;

public class Shacking {

    static void shake() {
        EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().player;
        Random r = new Random();
        double x = (r.nextDouble() - 0.5) * 2;
        double z = (r.nextDouble() - 0.5) * 2;
        //player.move(MoverType.SELF, x/ 100D, 0, z / 100D);
        player.rotationYaw -= x;
        player.rotationPitch -= z;
        //player.posX -= z;
    }
    static long waitTime;
    @SideOnly(Side.CLIENT)
    public static void StartShacke(int time){
        waitTime = System.currentTimeMillis() + time * 1000;
    }
    @SideOnly(Side.CLIENT)
    public  static void onUpdate(){
        long startTime = System.currentTimeMillis();
        if(System.currentTimeMillis() < waitTime){
            shake();
        }
    }
}
