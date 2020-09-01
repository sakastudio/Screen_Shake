package sakastudio.screen_shake;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Vector2f;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SideOnly(Side.CLIENT)
public class Shaking {
    public static final Shaking INSTANCE = new Shaking();

    private Shaking() {
    }

    private Vector2f velocity = new Vector2f();
    private Vector2f position = new Vector2f();

    public void pulse() {
        Random r = ThreadLocalRandom.current();
        float x = r.nextFloat() * 2f - 1f;
        float y = r.nextFloat() * 2f - 1f;
        Vector2f force = new Vector2f(x, y);
        force.scale(3f);
        velocity.add(force);
    }

    private void shake() {
        Random r = ThreadLocalRandom.current();
        if (r.nextFloat() < .1f) {
            boolean bShakeEnabled = (System.currentTimeMillis() < waitTime);
            if (bShakeEnabled) {
                pulse();
            }
        }
        position.add(velocity);
        velocity.sub(new Vector2f(position.x * .5f, position.y * .5f));
        velocity.scale(.9f);
    }

    private long waitTime;

    public void startShake(float time) {
        if (time > 0) {
            waitTime = System.currentTimeMillis() + (int) (time * 1000);
        } else {
            pulse();
        }
    }

    public void onUpdate() {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Vector2f p = new Vector2f(position);
        shake();
        p.negate();
        p.add(position);
        player.rotationYaw += p.x;
        player.rotationPitch += p.y;
    }
}
