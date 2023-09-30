package org.by1337.bairdrop.migrator.command;

public class CmdMigrator {
    public static String adapt(String cmd) {
        if (cmd.startsWith("[BLOCK_SET_OPEN]")) {
            return "[BLOCK_LID_TOGGLE] OPEN";
        } else if (cmd.startsWith("[BLOCK_SET_CLOSE]")) {
            return "[BLOCK_LID_TOGGLE] CLOSE";
        } else if (cmd.startsWith("[ACTIVATE]")) {
            return "[ACTIVATE] true";
        } else if (cmd.startsWith("[CALL-")) {
            return "[INVOKE] " + cmd.replace("[CALL-", "").replace("]", "");
        } else if (cmd.startsWith("[EFFECT_START-")) {
            String[] args = cmd.split("-");
            String id = args[2].replace("]", "");
            return String.format("[EFFECT_START] %s %s", args[1], id);
        } else if (cmd.startsWith("[EFFECT_STOP-")) {
            String[] args = cmd.split("-");
            String id = args[1].replace("]", "");
            return String.format("[EFFECT_STOP] %s", id);
        } else if (cmd.startsWith("[NEAR-PLAYERS=")) {
            String radius = cmd.split("=")[1].split("]")[0];
            String listener = cmd
                    .replace(String.format("[NEAR-PLAYERS=%s] {CALL-", radius), "")
                    .replace("}", "");
            return String.format("[NEAR_PLAYERS] %s %s", radius, listener);
        } else if (cmd.startsWith("[PLAYER-CLOSE-INVENTORY]")) {
            return "[PLAYER_CLOSE_INVENTORY]";
        } else if (cmd.startsWith("[RUN_JS=")) {
            return String.format("[RUN_JS] %s", cmd.split("RUN_JS=")[1].split("]")[0]);
        } else if (cmd.startsWith("[SCHEMATICS_PASTE-")) {
            return String.format("[SCHEMATICS_PASTE] %s", cmd.replace("[SCHEMATICS_PASTE-", "").replace("]", ""));
        } else if (cmd.startsWith("[SET_BLOCK_FACE_")) {
            return String.format("[SET_BLOCK_FACE] %s", cmd.replace("[SET_BLOCK_FACE_", "").replace("]", ""));

        } else if (cmd.startsWith("[SET_TIME_END-")) {
            return String.format("[SET_TIME_END] %s", cmd.replace("[SET_TIME_END-", "").replace("]", ""));

        } else if (cmd.startsWith("[SET_TIME_START-")) {
            return String.format("[SET_TIME_START] %s", cmd.replace("[SET_TIME_START-", "").replace("]", ""));

        } else if (cmd.startsWith("[SET_TIME_UNLOCK-")) {
            return String.format("[SET_TIME_UNLOCK] %s", cmd.replace("[SET_TIME_UNLOCK-", "").replace("]", ""));

        } else if (cmd.startsWith("[SET_MATERIAL_")) {
            return String.format("[SET_MATERIAL] %s",
                    cmd.split("\\[SET_MATERIAL_")[1].split("]")[0]
            ) + (cmd.contains("-offsets") ? " offsets" : "");
        }
        return cmd;
    }
}
