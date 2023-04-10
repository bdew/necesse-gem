package net.bdew.necesse.gem;

import necesse.engine.GameLog;
import necesse.engine.events.worldGeneration.GeneratedCaveOresEvent;
import necesse.engine.registries.ObjectRegistry;

public class OreGeneration {
    public static void generateOres(GeneratedCaveOresEvent ev) {
        GameLog.debug.println(String.format("Generating ores in %s (%s)", ev.level.getIdentifier().stringID, ev.level.biome.getStringID()));

        GemMod.settings.oreGen.forEach(gen -> {
            if (ev.level.biome.getStringID().equals(gen.biome) && ev.caveGeneration.rockObject == ObjectRegistry.getObjectID(gen.baseRock)) {
                GameLog.debug.println("Generating ore: " + gen);
                ev.caveGeneration.generateOreVeins(gen.veinsPerChunk, gen.minVeinSize, gen.maxVeinSize, ObjectRegistry.getObjectID(gen.ore));
            }
        });

        GameLog.debug.println(String.format("Finished ores in %s (%s)", ev.level.getIdentifier().stringID, ev.level.biome.getStringID()));
    }
}
