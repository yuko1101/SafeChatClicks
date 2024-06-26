package io.github.yuko1101.safechatclicks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public class SafeChatClicks implements ModInitializer {
    @Override
    public void onInitialize() {

    }

    @Nullable
    public static Text getActionText(Style style) {
        var clickEvent = style.getClickEvent();
        if (clickEvent == null) {
            return null;
        }

        String actionMessage = switch (clickEvent.getAction()) {
            case OPEN_URL, OPEN_FILE -> "Opens";
            case RUN_COMMAND -> "Runs";
            case SUGGEST_COMMAND -> "Suggests";
            case COPY_TO_CLIPBOARD -> "Copies";
            case CHANGE_PAGE -> throw new UnsupportedOperationException("CHANGE_PAGE is not supported");
        };

        var text = Text.literal(actionMessage + " ").setStyle(Style.EMPTY.withColor(Formatting.GRAY));
        text.append(Text.literal(clickEvent.getValue()).setStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
        text.append(Text.literal(" on click.").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));

        return text;
    }
}
