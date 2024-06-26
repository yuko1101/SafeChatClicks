package io.github.yuko1101.safechatclicks.mixin;

import io.github.yuko1101.safechatclicks.SafeChatClicks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
abstract class ChatScreenMixin extends Screen {
    @Shadow @Nullable protected abstract Style getTextStyleAt(double x, double y);

    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;getTextStyleAt(DD)Lnet/minecraft/text/Style;", shift = At.Shift.AFTER))
    private void renderMixin(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var style = getTextStyleAt(mouseX, mouseY);
        if (style != null && style.getClickEvent() != null && style.getHoverEvent() == null) {
            var text = SafeChatClicks.getActionText(style);
            if (text != null) {
                context.drawOrderedTooltip(textRenderer, textRenderer.wrapLines(text, 210), mouseX, mouseY);
            }
        }
    }
}
