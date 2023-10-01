package command;

import org.by1337.bairdrop.airdrop.command.airdrop.impl.*;

import org.by1337.bairdrop.migrator.command.CmdMigrator;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.lang.Lang;
import org.junit.Assert;
import org.junit.Test;


public class CommandTest {

    @Test
    public void test() throws CommandException {
        Lang.loadIfNull();
    }

    @Test
    public void testSummonCommand() throws CommandException {
        String cmd = "[SUMMON] creeper 11 12 13 {CustomName:\"\\\"123\\\"\",CustomNameVisible:1,NoAI:1b,Attributes:[{Name:\"generic.followRange\",Base:172},{Name:\"generic.knockbackResistance\",Base:0.23f},{Name:\"generic.movementSpeed\",Base:2.5f}]}";
        new SummonCommand().testCommand(cmd);

    }

    @Test
    public void testSoundAll() throws CommandException {
       String cmd = "[SOUND_ALL] AMBIENT_CRIMSON_FOREST_LOOP 1 1";
       new SoundAllCommand().testCommand(cmd);
    }

    @Test
    public void testSetTimeUnlock() throws CommandException {
        String old = "[SET_TIME_UNLOCK-90]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SET_TIME_UNLOCK] 90");
        new SetTimeUnlockCommand().testCommand("[SET_TIME_UNLOCK] 90");
    }

    @Test
    public void testSetTimeStart() throws CommandException {
        String old = "[SET_TIME_START-90]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SET_TIME_START] 90");
        new SetTimeStartCommand().testCommand("[SET_TIME_START] 90");
    }

    @Test
    public void testSetTimeEnd() throws CommandException {
        String old = "[SET_TIME_END-90]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SET_TIME_END] 90");
        new SetTimeEndCommand().testCommand("[SET_TIME_END] 90");
    }

    @Test
    public void testSetMaterial() throws CommandException {
        String old = "[SET_MATERIAL_DIRT]-offsets";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SET_MATERIAL] DIRT offsets");
        new SetMaterialCommand().testCommand("[SET_MATERIAL] DIRT offsets");
    }
    @Test
    public void testSetBlockFace() throws CommandException {
        String old = "[SET_BLOCK_FACE_NORTH]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SET_BLOCK_FACE] NORTH");
        new SetBlockFaceCommand().testCommand("[SET_BLOCK_FACE] NORTH");
    }

    @Test(expected = CommandException.class)
    public void testSetBlockFaceBadCmd() throws CommandException {
        new SetBlockFaceCommand().testCommand("[SET_BLOCK_FACE] 1NORTH");
    }

    @Test
    public void testSchem() throws CommandException {
        String old = "[SCHEMATICS_PASTE-id]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[SCHEMATICS_PASTE] id");
        new SchematicsPasteCommand().testCommand("[SCHEMATICS_PASTE] id");
    }

    @Test
    public void testRunJs() throws CommandException {
        String old = "[RUN_JS=Fire.js] param(player=player)";
        Assert.assertEquals(CmdMigrator.adapt(old), "[RUN_JS] Fire.js");
        new RunJsCommand().testCommand("[RUN_JS] Fire.js");
    }

    @Test
    public void testNearPlayers() throws CommandException {
        String old = "[NEAR-PLAYERS=10] {CALL-testListener}";
        Assert.assertEquals(CmdMigrator.adapt(old), "[NEAR_PLAYERS] 10 testListener");
        new NearPlayersCommand().testCommand("[NEAR_PLAYERS] 10 testListener");
    }

    @Test(expected = CommandException.class)
    public void testNearPlayersBadCommand() throws CommandException {
        new NearPlayersCommand().testCommand("[NEAR_PLAYERS] a123 testListener");
    }

    @Test
    public void testEffectStop() throws CommandException {
        String old = "[EFFECT_STOP-id]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[EFFECT_STOP] id");
        new EffectStopCommand().testCommand("[EFFECT_STOP] id");
    }

    @Test
    public void testEffectStart() throws CommandException {
        String old = "[EFFECT_START-Name-randomId]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[EFFECT_START] Name randomId");
        new EffectStartCommand().testCommand("[EFFECT_START] Name randomId");
    }

    @Test
    public void testInvokeCommand() throws CommandException {
        String old = "[CALL-test]";
        Assert.assertEquals(CmdMigrator.adapt(old), "[INVOKE] test");
        new InvokeListenerCommand().testCommand("[INVOKE] test");
    }

    @Test
    public void actionbarAllCommandTest() throws CommandException {
        ActionbarAllCommand command = new ActionbarAllCommand();
        command.testCommand("[ACTIONBAR_ALL] test message test message test message test message    ");
    }

    @Test
    public void blockLidToggle() throws CommandException {
        String cmd = CmdMigrator.adapt("[BLOCK_SET_OPEN]");
        BlockLidToggle blockLidToggle = new BlockLidToggle();

        Assert.assertEquals(cmd, "[BLOCK_LID_TOGGLE] OPEN");
        blockLidToggle.testCommand(cmd);

        cmd = CmdMigrator.adapt("[BLOCK_SET_CLOSE] ");
        Assert.assertEquals(cmd, "[BLOCK_LID_TOGGLE] CLOSE");
        blockLidToggle.testCommand(cmd);

    }

    @Test(expected = CommandException.class)
    public void blockLidToggleBadCommand() throws CommandException {
        BlockLidToggle blockLidToggle = new BlockLidToggle();
        blockLidToggle.testCommand("[BLOCK_LID_TOGGLE] 12341");
    }

}
