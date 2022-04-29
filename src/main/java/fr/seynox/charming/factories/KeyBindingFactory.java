package fr.seynox.charming.factories;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import static fr.seynox.charming.CharmingMod.LOGGER;
import static fr.seynox.charming.CharmingMod.MOD_ID;

public class KeyBindingFactory {

    private KeyBindingFactory() {}

    private static final String KEYBINDING_NAME_BASE = "key.%s.".formatted(MOD_ID);
    private static final String CATEGORY_NAME_BASE = "category.%s.".formatted(MOD_ID);

    public static KeyBinding createAndRegister(String keybindingName, int keyCode, String categoryName) {
        LOGGER.debug("Creating keybinding : {}", keybindingName);

        String keybindingTranslationKey = KEYBINDING_NAME_BASE + keybindingName;
        String categoryTranslationKey = CATEGORY_NAME_BASE + categoryName;

        KeyBinding keyBinding = new KeyBinding(
                keybindingTranslationKey,
                InputUtil.Type.KEYSYM,
                keyCode,
                categoryTranslationKey
        );

        return KeyBindingHelper.registerKeyBinding(keyBinding);
    }

}
