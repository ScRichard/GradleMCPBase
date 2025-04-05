package dev.gothaj.utilities.rotation;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;

import java.util.Random;

public class Handler {

    public static boolean isRotating;
    static Minecraft mc;
    public static float serverYaw;
    public static float serverPitch;
    public static float lastServerYaw;
    public static float lastServerPitch;
    private static Random random;
    static FastNoiseLite noise = new FastNoiseLite();

    public static float getOptifineExtraReach() {
        return 7.3838234E-4f;
    }

    public static boolean allowRotation(double offset) {
        BlockPos var3 = new BlockPos(Handler.mc.thePlayer.posX - offset, Handler.mc.thePlayer.posY - 0.5, Handler.mc.thePlayer.posZ - offset);
        BlockPos var4 = new BlockPos(Handler.mc.thePlayer.posX - offset, Handler.mc.thePlayer.posY - 0.5, Handler.mc.thePlayer.posZ + offset);
        BlockPos var5 = new BlockPos(Handler.mc.thePlayer.posX + offset, Handler.mc.thePlayer.posY - 0.5, Handler.mc.thePlayer.posZ + offset);
        BlockPos var6 = new BlockPos(Handler.mc.thePlayer.posX + offset, Handler.mc.thePlayer.posY - 0.5, Handler.mc.thePlayer.posZ - offset);
        return Handler.mc.thePlayer.worldObj.getBlockState(var3).getBlock() == Blocks.air && Handler.mc.thePlayer.worldObj.getBlockState(var4).getBlock() == Blocks.air && Handler.mc.thePlayer.worldObj.getBlockState(var5).getBlock() == Blocks.air && Handler.mc.thePlayer.worldObj.getBlockState(var6).getBlock() == Blocks.air;
    }

    public static float getYawAngleToEntity(Entity entity) {
        return (float)Math.toDegrees(Math.atan2(entity.posZ - Handler.mc.thePlayer.posZ, entity.posX - Handler.mc.thePlayer.posX)) - 90.0f;
    }

    public static double normalizeAngle(double angle) {
        if ((angle %= 360.0) >= 180.0) {
            angle -= 360.0;
        }
        if (angle < -180.0) {
            angle += 360.0;
        }
        return angle;
    }

    public static void resetRotations(float yaw, float pitch, boolean smooth) {
        isRotating = false;
        serverYaw = yaw;
        serverPitch = pitch;
        if(smooth) {
            int speed = 20;
            int existed = mc.thePlayer.ticksExisted * speed;
            serverYaw = updateRotation(lastServerYaw,yaw,noise.GetNoise(existed + 25, existed + 175));
            serverPitch = updateRotation(lastServerPitch,pitch,noise.GetNoise(existed + 25, existed + 175));
        }
    }

    public static float[] getBlockRotations(double x, double y, double z) {
        double xPos = x - Handler.mc.thePlayer.posX + 0.5;
        double zPos = z - Handler.mc.thePlayer.posZ + 0.5;
        double yPos = y - (Handler.mc.thePlayer.posY + (double)Handler.mc.thePlayer.getEyeHeight() - 0.5);
        double calc = MathHelper.sqrt_double(xPos * xPos + zPos * zPos);
        float yaw = (float)(Math.atan2(zPos, xPos) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(yPos, calc) * 180.0 / Math.PI));
        return Handler.mouseFix(yaw, pitch);
    }

    public static Vec3 getBestHitVec(Entity entity) {
        Vec3 positionEyes = Handler.mc.thePlayer.getPositionEyes(1.0f);
        float f11 = entity.getCollisionBorderSize();
        AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox().expand(f11, f11, f11);
        double ex = MathHelper.clamp_double(positionEyes.xCoord, entityBoundingBox.minX, entityBoundingBox.maxX);
        double ey = MathHelper.clamp_double(positionEyes.yCoord, entityBoundingBox.minY, entityBoundingBox.maxY);
        double ez = MathHelper.clamp_double(positionEyes.zCoord, entityBoundingBox.minZ, entityBoundingBox.maxZ);
        return new Vec3(ex, ey, ez);
    }

    public static float[] mouseFix(float yaw, float pitch) {
        float curYaw = serverYaw;
        float curPitch = serverPitch;
        float f = Handler.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float f2 = f * f * f * 8.0f * 0.15f;
        float nYaw = yaw - curYaw;
        nYaw -= nYaw % f2;
        yaw = curYaw + nYaw;
        float nPitch = pitch - curPitch;
        nPitch -= nPitch % f2;
        pitch = curPitch + nPitch;
        pitch = MathHelper.clamp_int((int)pitch, -90, 90);
        return new float[]{yaw, pitch};
    }

    public static float[] fixedRotations(float yaw, float pitch, boolean a3) {
        float sensitivity = Handler.mc.gameSettings.mouseSensitivity;
        if (sensitivity == 0.0f) {
            sensitivity = 0.0070422534f;
        }
        sensitivity = Math.max(0.1f, sensitivity);
        int deltaYaw = (int)((yaw - serverYaw) / (sensitivity / 2.0f));
        int deltaPitch = (int)((pitch - serverPitch) / (sensitivity / 2.0f)) * -1;
        if (a3) {
            deltaYaw = (int)((double)deltaYaw - ((double)deltaYaw % 0.5 + 0.25));
            deltaPitch = (int)((double)deltaPitch - ((double)deltaPitch % 0.5 + 0.25));
        }
        float f = sensitivity * 0.6f + 0.2f;
        float f1 = f * f * f * 8.0f;
        float f2 = (float)deltaYaw * f1;
        float f3 = (float)deltaPitch * f1;
        float endYaw = (float)((double)serverYaw + (double)f2 * 0.15);
        float endPitch = (float)((double)serverPitch - (double)f3 * 0.15);
        return new float[]{endYaw, endPitch};
    }

    public static float updateRotation(float current, float intended, float factor) {
        float var4 = MathHelper.wrapAngleTo180_float(intended - current);
        if (var4 > factor) {
            var4 = factor;
        }
        if (var4 < -factor) {
            var4 = -factor;
        }
        return current + var4;
    }

}
