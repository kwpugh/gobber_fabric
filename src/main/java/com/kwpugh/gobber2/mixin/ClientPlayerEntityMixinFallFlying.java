package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.ItemInit;
import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixinFallFlying extends AbstractClientPlayerEntity 
{
  @Shadow
  public final ClientPlayNetworkHandler networkHandler;
  @Shadow
  protected final MinecraftClient client;
  @Shadow
  public Input input;
  @Shadow
  private int ticksToNextAutojump;

  public ClientPlayerEntityMixinFallFlying(MinecraftClient client, ClientWorld world, GameProfile profile,
      ClientPlayNetworkHandler networkHandler) 
  {
    super(world, profile);
    this.networkHandler = networkHandler;
    this.client = client;
  }

  @Inject(method = "Lnet/minecraft/client/network/ClientPlayerEntity;tickMovement()V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
  public void gobberTickMovement(CallbackInfo info) 
  {
    ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
    if (itemStack.getItem() == ItemInit.GOBBER2_CHESTPLATE_END && this.checkFallFlying()) 
    {
      this.networkHandler.sendPacket(new ClientCommandC2SPacket(this, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
    }
  }

}