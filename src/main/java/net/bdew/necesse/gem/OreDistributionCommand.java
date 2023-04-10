package net.bdew.necesse.gem;

import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.localization.message.GameMessageBuilder;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.registries.ObjectRegistry;
import necesse.gfx.GameColor;
import necesse.level.gameObject.GameObject;
import necesse.level.gameObject.RockOreObject;
import necesse.level.maps.Level;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OreDistributionCommand extends ModularChatCommand {

    public OreDistributionCommand() {
        super("oredistribution", "Calculate ore distribution", PermissionLevel.ADMIN, false);
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog commandLog) {
        Level level = serverClient.getLevel();

        Map<GameObject, Integer> objMap = StreamSupport.stream(ObjectRegistry.getObjects().spliterator(), false)
                .filter(x -> x instanceof RockOreObject)
                .collect(Collectors.toMap(Function.identity(), x -> 0));

        int total = 0;

        for (int x = 0; x < level.width; x++) {
            for (int y = 0; y < level.height; y++) {
                GameObject obj = level.getObject(x, y);
                if (objMap.containsKey(obj)) {
                    objMap.put(obj, objMap.get(obj) + 1);
                    total++;
                }
            }
        }

        int finalTotal = total;

        serverClient.sendChatMessage(new LocalMessage("misc", "oredistribution_header",
                "level", String.format("%s%s%s", GameColor.YELLOW.getColorCode(), level.getIdentifier().stringID, GameColor.NO_COLOR.getColorCode()),
                "width", String.format("%s%d%s", GameColor.CYAN.getColorCode(), level.width, GameColor.NO_COLOR.getColorCode()),
                "height", String.format("%s%d%s", GameColor.CYAN.getColorCode(), level.height, GameColor.NO_COLOR.getColorCode())
        ));

        objMap.entrySet()
                .stream()
                .filter(x -> x.getValue() > 0)
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(x -> {
                    GameMessageBuilder builder = new GameMessageBuilder();
                    builder.append(GameColor.YELLOW.getColorCode());
                    builder.append(x.getKey().getLocalization());
                    builder.append(GameColor.NO_COLOR.getColorCode());
                    builder.append(String.format(": %s%.3f%%%s (%d)",
                            GameColor.CYAN.getColorCode(),
                            100f * x.getValue() / finalTotal,
                            GameColor.NO_COLOR.getColorCode(),
                            x.getValue()
                    ));
                    serverClient.sendChatMessage(builder);
                });
    }
}
