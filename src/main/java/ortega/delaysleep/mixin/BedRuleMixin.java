package ortega.delaysleep.mixin;

import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ortega.delaysleep.DelaySleep;

@Mixin(BedRule.class)
public class BedRuleMixin {
	@Inject(method = "canSleep(Lnet/minecraft/world/level/Level;)Z", at = @At("HEAD"), cancellable = true)
	private void delaySleep$beforeCanSleep(Level level, CallbackInfoReturnable<Boolean> info) {
		if (!DelaySleep.isSleepTimeReached(level)) {
			info.setReturnValue(false);
		}
	}
}
