package command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.by1337.api.world.BlockPosition;
import org.by1337.bairdrop.effect.test.EffectCircle;

import org.by1337.bairdrop.lang.Lang;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.bairdrop.menu.property.property.PropertyColor;
import org.junit.Test;


public class CommandTest {


    @Test
    public void testCommandExecution()  {

        Lang.initLocal();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().create();
      //  System.out.println(gson.toJson(Lang.getLang()));

        GeneratorSetting setting = new GeneratorSetting();
        setting.center.x = 12312;
        setting.center.z = 411;
        setting.hasBlock.add(new BlockPosition(0, 1, 0));
        setting.hasBlock.add(new BlockPosition(0, 1, 1));

        System.out.println(gson.toJson(setting));
    }


}
