package sakastudio.screen_shake;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.parallel.ParIterableLike;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class ShakeCommand extends CommandBase {

    @Override
    public String getName() {
        return "shake";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "shake";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        //プレイヤーリストの取得
        List<EntityPlayerMP> list = server.getPlayerList().getPlayers();

        //エンティティIDからターゲットエンティティを取得
        for (EntityPlayerMP item: list) {
            //ターゲットのプレイヤーである
            EntityPlayer p = (EntityPlayer)item;
            if(args[0].equals(p.getName())){
                PacketHandler.INSTANCE.sendTo(new PacketClientSendKey(Integer.parseInt(args[1])),item);
                break;
            }
        }
    }
}
