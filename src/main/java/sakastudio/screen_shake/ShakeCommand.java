package sakastudio.screen_shake;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.lang3.math.NumberUtils;

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
        // 引数不足
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString("/shake <player> <seconds>"));
            return;
        }

        //ターゲットのプレイヤーリストの取得
        List<EntityPlayerMP> list = getPlayers(server, sender, args[0]);

        // 秒数
        float sec = args.length < 2 ? 0 : NumberUtils.toFloat(args[1]);

        //エンティティIDからターゲットエンティティを取得
        for (EntityPlayerMP item : list) {
            PacketHandler.INSTANCE.sendTo(new SPacketShake(sec), item);
        }
    }
}
