import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.junit.Test;

public class TestCom {

    @Test
    public void run(){
        String ser = "{Passengers:[{id:\"minecraft:creeper\",CustomName:\"\\\"432432\\\"\",CustomNameVisible:1,NoAI:1b,Glowing:1,CanPickUpLoot:1b,Health:23432,Attributes:[{Name:\"generic.maxHealth\",Base:23432},{Name:\"generic.followRange\",Base:500},{Name:\"generic.knockbackResistance\",Base:1f},{Name:\"generic.movementSpeed\",Base:10f}],Fire:648640,Silent:1,Invulnerable:1,ExplosionRadius:50,Fuse:1200,ignited:1,HandDropChances:[2F,2F],HandItems:[{id:\"minecraft:stripped_oak_log\",tag:{display:{Name:32432,Lore:[\"23432432\"]}},Count:64},{id:\"minecraft:dried_kelp\",tag:{display:{Name:23432432,Lore:[\"42323432\"]}},Count:64}],ActiveEffects:[{Id:1,Amplifier:0,Duration:2147483647},{Id:2,Amplifier:0,Duration:2147483647},{Id:3,Amplifier:0,Duration:2147483647},{Id:4,Amplifier:0,Duration:2147483647},{Id:5,Amplifier:0,Duration:2147483647},{Id:9,Amplifier:0,Duration:2147483647},{Id:11,Amplifier:0,Duration:2147483647},{Id:13,Amplifier:0,Duration:2147483647}]}]}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        NBTTagCompound nbt = gson.fromJson(ser, NBTTagCompound.class);
        String s = gson.toJson(nbt);
        System.out.println(s);
    }
}
