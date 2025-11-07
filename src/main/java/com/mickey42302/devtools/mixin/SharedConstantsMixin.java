package com.mickey42302.devtools.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SharedConstants.class})
public abstract class SharedConstantsMixin {
    @Shadow
    @Mutable
    public static boolean IS_RUNNING_IN_IDE;

    // For unknown reasons, the "IS_RUNNING_IN_IDE" flag does not enable most of the developer commands on Forge.
    // This workaround fixes the problem.
    @Final
    @Shadow
    @Mutable
    public static boolean DEBUG_DEV_COMMANDS;

    @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
    private static void devtools$clinit(CallbackInfo ci) {
        IS_RUNNING_IN_IDE = true;
        DEBUG_DEV_COMMANDS = true;
    }

}
