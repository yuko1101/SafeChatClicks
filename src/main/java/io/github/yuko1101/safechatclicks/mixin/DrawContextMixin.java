package io.github.yuko1101.safechatclicks.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.yuko1101.safechatclicks.SafeChatClicks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DrawContext.class)
public class DrawContextMixin {
    @ModifyArg(method = "drawHoverEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;wrapLines(Lnet/minecraft/text/StringVisitable;I)Ljava/util/List;"))
    private StringVisitable drawHoverEventMixin(StringVisitable text, @Local(argsOnly = true) Style style) {
        return Text.empty()
                .append(((Text) text))
                .append(Text.literal("\n\n"))
                .append(SafeChatClicks.getActionText(style));
    }
}
