package sakastudio.screen_shake;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientSendKey implements IMessage {

    public int shakeSec;

    @Override
    public void fromBytes(ByteBuf buf) {
        shakeSec = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(shakeSec);
    }

    public PacketClientSendKey(int num) {
        shakeSec = num;
    }
    public PacketClientSendKey() {
    }


    public static class Handler implements IMessageHandler<PacketClientSendKey, IMessage> {
        @Override
        public IMessage onMessage(PacketClientSendKey message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketClientSendKey message, MessageContext ctx) {
            Shacking.StartShacke(message.shakeSec);
        }
    }
}
