package sakastudio.screen_shake;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPacketShake implements IMessage {

    public float shakeSec;

    @Override
    public void fromBytes(ByteBuf buf) {
        shakeSec = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(shakeSec);
    }

    public SPacketShake(float num) {
        shakeSec = num;
    }
    public SPacketShake() {
    }


    public static class Handler implements IMessageHandler<SPacketShake, IMessage> {
        @Override
        public IMessage onMessage(SPacketShake message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(SPacketShake message, MessageContext ctx) {
            Shaking.INSTANCE.startShake(message.shakeSec);
        }
    }
}
