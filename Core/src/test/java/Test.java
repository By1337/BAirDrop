import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.by1337.bairdrop.util.Lang;
import org.junit.Assert;

public class Test {


    @org.junit.Test
    public void  a(){
        Lang lang = new Lang();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(lang);
        Lang l = gson.fromJson(s, Lang.class);
        System.out.println(s);
    }
}
