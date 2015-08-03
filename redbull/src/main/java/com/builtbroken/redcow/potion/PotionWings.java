package com.builtbroken.redcow.potion;

import com.builtbroken.redcow.RedCow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.awt.*;

/**
 * Created by Cow Pi on 7/31/2015.
 */
public class PotionWings extends Potion
{
    public PotionWings(int id)
    {
        super(id, false, new Color(1f, 1f, 1f, 0F).getRGB());
        setPotionName(RedCow.PREFIX + "wings");
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
        if (entity instanceof EntityPlayerMP)
        {
            PotionEffect effect = entity.getActivePotionEffect(RedCow.potionRedBull);
            if (effect.getDuration() > 1)
            {
                if(!((EntityPlayer) entity).capabilities.allowFlying)
                {
                    ((EntityPlayer) entity).capabilities.allowFlying = true;
                    ((EntityPlayerMP) entity).playerNetServerHandler.sendPacket(new S39PacketPlayerAbilities(((EntityPlayerMP) entity).capabilities));
                }
            }
            else
            {
                ((EntityPlayer) entity).capabilities.allowFlying = false;
                ((EntityPlayer) entity).capabilities.isFlying = false;
                ((EntityPlayer) entity).fallDistance = 0.0F;
                ((EntityPlayerMP) entity).playerNetServerHandler.sendPacket(new S39PacketPlayerAbilities(((EntityPlayerMP) entity).capabilities));
            }
        }
    }

    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_)
    {
        return true;
    }
}