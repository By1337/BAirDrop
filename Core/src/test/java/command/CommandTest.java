package command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.effect.test.EffectCircle;

import org.by1337.bairdrop.lang.Lang;
import org.by1337.bairdrop.menu.property.property.PropertyColor;
import org.junit.Test;


public class CommandTest {


    @Test
    public void testCommandExecution()  {

        Lang.initLocal();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().create();
        System.out.println(gson.toJson(Lang.getLang()));

//        EffectCircle effectCircle = new EffectCircle(10, 10);
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting().create();
//        String s = gson.toJson(effectCircle);
//        System.out.println(s);
//        EffectCircle effectCircle1 = gson.fromJson(s, EffectCircle.class);
//         s = gson.toJson(effectCircle1);
//        System.out.println(s);
    }


}
