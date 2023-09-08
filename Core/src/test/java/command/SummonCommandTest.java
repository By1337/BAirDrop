package command;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.by1337.bairdrop.airdrop.command.impl.SummonCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummonCommandTest {

    @Test
    public void run() {
        String cmd = "[SUMMON] creeper 11 12.5 13 {CustomName:\"\\\"123\\\"\",CustomNameVisible:1,NoAI:1b,Attributes:[{Name:\"generic.followRange\",Base:172},{Name:\"generic.knockbackResistance\",Base:0.23f},{Name:\"generic.movementSpeed\",Base:2.5f}]}";
        SummonCommand.SummonCommandArgs args = new SummonCommand.SummonCommandArgs();
        args.parse(cmd);

        Assert.assertEquals(args.getX(), 11.0, 0);
        Assert.assertEquals(args.getY(), 12.5, 0);
        Assert.assertEquals(args.getZ(), 13.0, 0);
        Assert.assertEquals(args.getNbt(), "{CustomName:\"\\\"123\\\"\",CustomNameVisible:1,NoAI:1b,Attributes:[{Name:\"generic.followRange\",Base:172},{Name:\"generic.knockbackResistance\",Base:0.23f},{Name:\"generic.movementSpeed\",Base:2.5f}]}");
        Assert.assertEquals(args.getEntityType(), "creeper");

        cmd = "[SUMMON] slime -912.3 872.01 -123.5 {Size:3,Passengers:[{id:\"minecraft:wither_skeleton\",CustomName:\"\\\"123we we r rwer wer wer ew rew \\\"\",CustomNameVisible:1,NoAI:1b,Glowing:1,NoGravity:1b,FallFlying:1,ArmorItems:[{},{},{id:\"minecraft:elytra\",Count:1},{id:\"minecraft:glass\",Count:1,tag:{display:{Name:12321,Lore:['{\"text\":\"21312 3\"}',\"213\",\"213\",\"213\",\"312\",\"213\",\"123\"]}}}],Health:12312,Attributes:[{Name:\"generic.maxHealth\",Base:12312},{Name:\"generic.followRange\",Base:500},{Name:\"generic.knockbackResistance\",Base:1f},{Name:\"generic.movementSpeed\",Base:10f},{Name:\"generic.attackDamage\",Base:21321}],Fire:426420,Silent:1,Invulnerable:1,HandItems:[{id:\"minecraft:dark_oak_log\",tag:{display:{Name:2321321,Lore:['{\"text\":\"123 21\"}','{\"text\":\"213\\\\\\\\21\\\\\\\\3\\\\\\\\21\\\\\\\\123\"}',\"3\",\"4\",\"324\",\"32\"]}},Count:64},{id:\"minecraft:acacia_log\",tag:{display:{Name:'{\"text\":\"21312 123 12 \"}',Lore:['{\"text\":\"231 12 312\"}']}},Count:64}],HandDropChances:[0.51F,0.84F],ActiveEffects:[{Id:3,Amplifier:0,Duration:2147483647},{Id:4,Amplifier:0,Duration:2147483647},{Id:5,Amplifier:0,Duration:2500},{Id:6,Amplifier:0,Duration:2147483647},{Id:7,Amplifier:0,Duration:2147483647}]}]}";
        args = new SummonCommand.SummonCommandArgs();
        args.parse(cmd);

        Assert.assertEquals(args.getX(), -912.3, 0);
        Assert.assertEquals(args.getY(), 872.01, 0);
        Assert.assertEquals(args.getZ(), -123.5, 0);
        Assert.assertEquals(args.getNbt(), "{Size:3,Passengers:[{id:\"minecraft:wither_skeleton\",CustomName:\"\\\"123we we r rwer wer wer ew rew \\\"\",CustomNameVisible:1,NoAI:1b,Glowing:1,NoGravity:1b,FallFlying:1,ArmorItems:[{},{},{id:\"minecraft:elytra\",Count:1},{id:\"minecraft:glass\",Count:1,tag:{display:{Name:12321,Lore:['{\"text\":\"21312 3\"}',\"213\",\"213\",\"213\",\"312\",\"213\",\"123\"]}}}],Health:12312,Attributes:[{Name:\"generic.maxHealth\",Base:12312},{Name:\"generic.followRange\",Base:500},{Name:\"generic.knockbackResistance\",Base:1f},{Name:\"generic.movementSpeed\",Base:10f},{Name:\"generic.attackDamage\",Base:21321}],Fire:426420,Silent:1,Invulnerable:1,HandItems:[{id:\"minecraft:dark_oak_log\",tag:{display:{Name:2321321,Lore:['{\"text\":\"123 21\"}','{\"text\":\"213\\\\\\\\21\\\\\\\\3\\\\\\\\21\\\\\\\\123\"}',\"3\",\"4\",\"324\",\"32\"]}},Count:64},{id:\"minecraft:acacia_log\",tag:{display:{Name:'{\"text\":\"21312 123 12 \"}',Lore:['{\"text\":\"231 12 312\"}']}},Count:64}],HandDropChances:[0.51F,0.84F],ActiveEffects:[{Id:3,Amplifier:0,Duration:2147483647},{Id:4,Amplifier:0,Duration:2147483647},{Id:5,Amplifier:0,Duration:2500},{Id:6,Amplifier:0,Duration:2147483647},{Id:7,Amplifier:0,Duration:2147483647}]}]}");
        Assert.assertEquals(args.getEntityType(), "slime");

        cmd = "[SUMMON] slime -912.3 872.01 -123.5";
        args = new SummonCommand.SummonCommandArgs();
        args.parse(cmd);

        Assert.assertEquals(args.getX(), -912.3, 0);
        Assert.assertEquals(args.getY(), 872.01, 0);
        Assert.assertEquals(args.getZ(), -123.5, 0);
        Assert.assertNull(args.getNbt());
        Assert.assertEquals(args.getEntityType(), "slime");

    }

}
