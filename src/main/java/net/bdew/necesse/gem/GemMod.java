package net.bdew.necesse.gem;

import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.commands.CommandsManager;
import necesse.engine.events.worldGeneration.GeneratedCaveOresEvent;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import net.bdew.necesse.gem.settings.GemModSettings;

import java.awt.*;

@ModEntry
public class GemMod {
    public static GemModSettings settings;

    public ModSettings initSettings() {
        settings = new GemModSettings();
        return settings;
    }

    public void init() {
        RegistryHelper.registerGem("emerald", 20f);
        RegistryHelper.registerGem("sapphire", 30f);
        RegistryHelper.registerGem("ruby", 40f);
        RegistryHelper.registerGem("diamond", 50f);

        RegistryHelper.registerOre("tin", 1.5f);

        RegistryHelper.registerBar("bronze", 1.5f);

        RegistryHelper.registerOreVariants("emerald", new Color(10, 169, 73), 1, "gem", 1, 1);
        RegistryHelper.registerOreVariants("sapphire", new Color(10, 15, 247), 1, "gem", 1, 1);
        RegistryHelper.registerOreVariants("ruby", new Color(248, 37, 1), 1, "gem", 1, 1);
        RegistryHelper.registerOreVariants("diamond", new Color(91, 201, 212), 1, "gem", 1, 1);

        RegistryHelper.registerOreVariants("tin", new Color(172, 186, 199), 0, "ore", 1, 3);

        GameEvents.addListener(GeneratedCaveOresEvent.class, new GameEventListener<GeneratedCaveOresEvent>() {
            @Override
            public void onEvent(GeneratedCaveOresEvent ev) {
                OreGeneration.generateOres(ev);
            }
        });
    }

    public void initResources() {
    }

    public void postInit() {
        CommandsManager.registerServerCommand(new OreDistributionCommand());

        Recipes.registerModRecipe(new Recipe(
                "bronzebar",
                2,
                RecipeTechRegistry.FORGE,
                new Ingredient[]{
                        new Ingredient("copperore", 3),
                        new Ingredient("tinore", 1)
                }
        ));
    }

}
