package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.Listeners.CraftItem;
import org.by1337.bairdrop.Listeners.InteractListener;
import org.by1337.bairdrop.Listeners.PlayerJoin;
import org.by1337.bairdrop.command.Commands;
import org.by1337.bairdrop.command.Completer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static org.by1337.bairdrop.BAirDrop.getInstance;


public class Manager {
    private String string;//licenseKey
    private static final Integer[] num = new Integer[8];
    private static final String[] strings = new String[18];
    private static final Object[][] array2 = new Object[30][2];
    private static final Object[][] array3 = new Object[10][2];
    private static final Object[][] array4 = new Object[13][2];
    private static final Object[][] array5 = new Object[12][2];
    private static final Object[][] array6 = new Object[14][2];//679
    private static final Object[][] array10 = new Object[9][2]; //4315
    private static final Object[][] array7 = new Object[12][2]; //565
    private static final Object[][] array8 = new Object[5][2]; //614
    private static final Object[][] array9 = new Object[14][2]; //2557
    private static final Object[][] array11 = new Object[34][2]; //4657 By1337̵̨̡̛͖̰͉̰͖̰͍̯̼̼̞̣̬̿͒̀͌͊͒͋̃́̿̑ͥ̑̈́̔̌͂̈́ͬ̕͢͢͢(); []==============================[]
    private static final Object[][] array12 = new Object[21][2]; //2712 By1337̸̵͔̙̥̳̥͈͇̬͚̹̦̥̰̳̟ͫ̓́͌̉́͆͂̇ͩͪͩͤ̈́ͣ͆̈́͑ͨͮ̚͟͢(); Соединение с сервером
    private static final Object[][] array13 = new Object[4][2]; //4337 By1337̵̡̛̤̥̥̬̠͕̪̯̥̤̞͇ͦ̽͗̑̑ͬ̄͋ͤ̆͗̿̾ͭ̎ͫ̈́̔ͥ̕͝͡ͅͅ͏(); true
    private static final Object[][] array14 = new Object[22][2]; //2197 By1337̸̴̧̛̬͉̼̙̦͔̻̼͍̳̲̀̽́ͯ̇ͫ̃̂͛̅̌ͯ̂͑̏̐͊̂͗̐͟͡͡ͅͅ(); §cЛицензия не валидна!
    private static final Object[][] array15 = new Object[18][2]; //1001 By1337̢̛̘̟͕̪̞̮̳͇͖̘̞̭͓̼̤̩ͭ̌ͬ̏ͦ̊̽ͧ̊̎̋ͭ͐̔͑͗̽ͧ̆ͬ͆̂(); §cПлагин выключен!
    private static final Object[][] array16 = new Object[5][2]; //2794 By1337̵̧͕̞̻̣͖̠̥͈̙̤̫̺͉̯̥ͬͪ̌ͨ̏̌͋̂ͤͣͣ̇ͮͮ͗ͨ̾̈́ͭ͑̕͟͡(); false
    private static final Object[][] array17 = new Object[25][2]; //1084 By1337̭̟̞͙͉̱̹̭͓̭̘̖̺̘̬̌ͮ̑ͬ́ͯ͊ͮ̔ͬͩ͆͐̋̿͌ͥ̆̌͂̐ͪͩ͂̽(); &cОшибка сервера лицензий
    private static final Object[][] array18 = new Object[32][2]; //3623 By1337̶̵̢̹͍̣̺̗̱̠̳̣͐ͧ̀̓̊̃ͪ̋̓̀̏ͨ̔̏̅ͥ̂͋ͥ̂̅̀͆ͤ͜͢͝͝(); &cОтсутствует лицензионный ключ!
    private static final Object[][] array19 = new Object[18][2]; //213 By1337̶̛̛̫̟̭͕̫ͤ̾ͬͧ͑̽̓̈ͣ͂̚͘͘͝͞͏̵̵̻͚͎͇ͩ̄̾̔̋̀̄̈́̍͠(); &aЛицензия валидна
    private static final Object[][] array20 = new Object[32][2]; //1014 By1337̴̨͍̱̝̮̣͇͕̞̮͉͖͍͍̮̬̬̰͓̩̽ͣ̌͂̓̒̓̽̽͌͋̽̅̍ͯ̀̾̿͝(); &cНеправильный ответ от сервера!
    private static final Object[][] array21 = new Object[20][2]; //4415 By1337͚͉̘̍̾̽̓͗͑̍͌͟͏̛͙͖͔̥͖͔̙̤̻̗͍͖̖̤͂̾́͋̓̓̋ͮͯ͝͞͞(); &cНеправильный ключ!
    private static final Object[][] array22 = new Object[23][2]; //2698 By1337̷̶̵̢̹̼͚̲̥̣̘͈̯͎͑̈͌̄͌͛ͩ̌ͤ̾͌͊ͮ̒ͥ̉͆ͧ̈̾͘͜͢͜ͅͅ(); &cIP адрес не валидный!
    private static final Object[][] array23 = new Object[22][2]; //1640 By1337̴̵̴̵̢͓̱̲̞̱͓̥͚͊̓̉̑̈͋̐̊ͯ̏̉̊̇̒̈́ͥ͊̎ͩͥ̎̇͟͠͡ͅͅ(); &cНеправильный плагин!
    private static final Object[][] array24 = new Object[15][2]; //2674 By1337̵̡̧͓̺͔̼͍̤͎̝̹̐̀̽́͗̎͑̇̐ͬ̔͌ͣͫ͘͝ͅ͏̸̷̫̩̓ͬͫ̔́ͨ(); &cКлюч устарел!
    private static final Object[][] array25 = new Object[20][2]; //3754 By1337̧̤̫̭̞̫̻̦̹̹̹̻̞̺̭̘̤̞̗̘̗̿̈͌̐́̾ͤͣͧ͗ͤͤ͌̾ͥͥ͘͠͠(); &cНеизвестная ошибка
    private static final Object[][] array26 = new Object[4][2]; //1754 By1337̶̴̧̨̼͙̻͙̝̝̯͉͍̫̖͍ͫ̈́͗̓ͯͧͯ̃̀̌́͆̍͛̕͏̷͓̞͌̂̓̄ͣ(); ?v1=
    private static final Object[][] array27 = new Object[4][2]; //3648 By1337̷̢̹̫̝̙̻̯̳̠́̈̈́ͦ̆̅̎̀̅͒͏̸̦̮̺̥̱̘͍͉́͆̄̋͐̒̽̀ͬ͞(); &v2=
    private static final Object[][] array28 = new Object[4][2]; //3948 By1337̶̷̶̴̵̴̧̧̨͉̠̬̣̹͚̭̳̐̓ͯͧ͗̄̈́̄̔̏̉̽ͪ̂̎̉̀͌ͨͭ͘̚͜(); &pl=

    public String manager(String s) {//loadAndRegister
        this.string = s;
        Message.logger(strings[0]);
        Message.logger(strings[1]);
        new Metrics(BAirDrop.getInstance(), 17870);
        int vt = isValidationType();
        try {
            if (vt == num[5]) {
                Message.logger(strings[8]);
                Message.logger(strings[0]);
                BAirDrop.len = generateRandomBinaryNumber(10);
                BAirDrop.info[0] = generateRandomBinaryNumber(12);
                BAirDrop.info[1] = generateRandomBinaryNumber(4);
                BAirDrop.info[2] = generateRandomBinaryNumber(6);
                BAirDrop.info[3] = generateRandomBinaryNumber(8);
                BAirDrop.info[4] = generateRandomBinaryNumber(15);
                BAirDrop.info[5] = generateRandomBinaryNumber(10);
                BAirDrop.info[6] = generateRandomBinaryNumber(20);
                BAirDrop.getiConfig().LoadConfiguration();
                BAirDrop.getInstance().getCommand("bairdrop").setExecutor(new Commands());
                BAirDrop.getInstance().getCommand("bairdrop").setTabCompleter(new Completer());
                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new InteractListener(), BAirDrop.getInstance());
                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(BAirDrop.summoner, BAirDrop.getInstance());
                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.getInstance());
                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.getInstance());
                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(BAirDrop.compass, BAirDrop.getInstance());
                return strings[2];
            } else {
                Message.logger(strings[3]);
                Message.logger(infoCode(vt));
                Message.logger(strings[4]);
                Message.logger(strings[0]);
                BAirDrop.len = generateRandomBinaryNumber(11);
                BAirDrop.info[0] = generateRandomBinaryNumber(13);
                BAirDrop.info[1] = generateRandomBinaryNumber(5);
                BAirDrop.info[2] = generateRandomBinaryNumber(3);
                BAirDrop.info[3] = generateRandomBinaryNumber(5);
                BAirDrop.info[4] = generateRandomBinaryNumber(14);
                BAirDrop.info[5] = generateRandomBinaryNumber(13);
                BAirDrop.info[6] = generateRandomBinaryNumber(9);
            }
        } catch (Exception error) {
            Message.logger(strings[3]);
            Message.logger(infoCode(vt));
            Message.logger(strings[4]);
            Message.logger(strings[0]);
            BAirDrop.len = generateRandomBinaryNumber(4);
            BAirDrop.info[0] = generateRandomBinaryNumber(10);
            BAirDrop.info[1] = generateRandomBinaryNumber(3);
            BAirDrop.info[2] = generateRandomBinaryNumber(4);
            BAirDrop.info[3] = generateRandomBinaryNumber(7);
            BAirDrop.info[4] = generateRandomBinaryNumber(12);
            BAirDrop.info[5] = generateRandomBinaryNumber(8);
            BAirDrop.info[6] = generateRandomBinaryNumber(4);
        }
        Bukkit.getScheduler().cancelTasks(BAirDrop.getInstance());
        Bukkit.getPluginManager().disablePlugin(BAirDrop.getInstance());
        return strings[5];
    }


    private static int shift = 0;

    private static int hashCode(String str) {
        int result = 0;
        for (byte b : str.getBytes()) {
            result += b * (shift == 0 ? 34 : shift);
        }
        if (shift == 0)
            shift = result;
        return result;
    }

    private static String decrypt(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-384").digest(key.getBytes(StandardCharsets.UTF_8)), 8), "Blowfish");

            Cipher des = Cipher.getInstance("Blowfish");
            des.init(Cipher.DECRYPT_MODE, keySpec);

            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);

        } catch (IllegalArgumentException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException |
                 NoSuchAlgorithmException | InvalidKeyException e) {
            return obj;
        }
        // return null;
    }

    public static String sObf(String s, int offset) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int codePoint = Character.codePointAt(Character.toString(c), 0);
            sb.append(Character.toChars(codePoint + offset));
        }
        return sb.toString();
    }

    private String infoCode(int code) {
        if (code == num[0]) return strings[6]; //"&cОшибка сервера лицензий"; //PAGE_ERROR
        if (code == num[7]) return strings[7];//"&cОтсутствует лицензионный ключ!"; //нет_ключа
        if (code == num[5]) return strings[8]; //"&aЛицензия валидна"; //VALID
        if (code == num[6]) return strings[9];//"&cНеправильный ответ от сервера!"; //WRONG_RESPONSE
        if (code == num[1]) return strings[10];//"&cНеправильный ключ!"; //KEY_NOT_FOUND
        if (code == num[2]) return strings[11];//"&cIP адрес не валидный!"; //NOT_VALID_IP
        if (code == num[3]) return strings[12];//"&cНеправильный плагин!"; //INVALID_PLUGIN
        if (code == num[4]) return strings[13];//"&cКлюч устарел!"; //KEY_OUTDATED
        return strings[14];
    }

    private static Object get(Object[][] arr, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length + arr[0].length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < arr.length && i - j < arr[0].length) {
                    Object obj = arr[j][(i - j)];
                    i++;
                    Object key = arr[j][i - j];
                    key = sObf((String) key, offset);
                    sb.append(decrypt((String) obj, (String) key));
                }
            }
        }
        return (Object) sb.toString();
    }

    static {
        hashCode(UUID.randomUUID().toString());
        By1337̴̧̺͎̥͍͇̩̗͍̬̻̙̱͖͂ͦ̓̎͊͐͂̀̒̆͗̄ͬ̓ͫ̊ͣ̄̓́̀́͢͢͟();
        By1337̴̷̢̩̗̭͙̥̥ͥͬ̀̏ͩͩ͑̕̚͏̨͈̫̲̪̹̙̩̖̽ͭ͛̓̀̐̀̀̕͜͠͞();
        By1337̶̷̨̡̮͉̹͕͈̫̞̞̲͖̖̗̝̥̞̱̱̖̩̦̓̽ͬ͗̍̆̒̍ͯ̍ͦ̅͟͟͞ͅ();
        By1337̴̶̡̡̠̳̲̭̱̳̜̠̜̖̞̻͇̟̫͓͔͑̽ͣͤ̿̆̀͐̂̓̿̂̐̅̌́͐ͣͅ();
        By1337̸̨̳̟̼̝̟͕̞̼͓̤̠͙̖̤̝̘̮̥͖̅͛̾̄́̓ͫͨͨ͊̆ͭ͘̚͘̕͜͡͠();
        By1337̴̧̲̱̮̮̮̙̫͈͖͓̳͓̮̲̔ͦ̓ͦ̈́̎̏̇͛ͨͬ̀ͯ͂̇͋́ͬ̕͜͟͠͞͝();
        By1337̝̤̰̟̠̱͈̝͖̲ͮ̿͑ͮ́ͮ̄̏̅̒ͥ́ͧ̊̈́̌̊͏̘͓͔͍͌ͥ̄̆ͤͨ̽͞();
        By1337̡̪̯̘̱͍͕̪͎̤̯͙͔͎̰͖̼̀̋̎ͭ̿ͮ̔ͧ̅̄ͬͥ̀ͮ̏͒ͩͯ̽͠͞ͅͅ();
        By1337̵̨̡̛͖̰͉̰͖̰͍̯̼̼̞̣̬̿͒̀͌͊͒͋̃́̿̑ͥ̑̈́̔̌͂̈́ͬ̕͢͢͢();
        By1337̸̵͔̙̥̳̥͈͇̬͚̹̦̥̰̳̟ͫ̓́͌̉́͆͂̇ͩͪͩͤ̈́ͣ͆̈́͑ͨͮ̚͟͢();
        By1337̵̡̛̤̥̥̬̠͕̪̯̥̤̞͇ͦ̽͗̑̑ͬ̄͋ͤ̆͗̿̾ͭ̎ͫ̈́̔ͥ̕͝͡ͅͅ͏();
        By1337̸̴̧̛̬͉̼̙̦͔̻̼͍̳̲̀̽́ͯ̇ͫ̃̂͛̅̌ͯ̂͑̏̐͊̂͗̐͟͡͡ͅͅ();
        By1337̢̛̘̟͕̪̞̮̳͇͖̘̞̭͓̼̤̩ͭ̌ͬ̏ͦ̊̽ͧ̊̎̋ͭ͐̔͑͗̽ͧ̆ͬ͆̂();
        By1337̵̧͕̞̻̣͖̠̥͈̙̤̫̺͉̯̥ͬͪ̌ͨ̏̌͋̂ͤͣͣ̇ͮͮ͗ͨ̾̈́ͭ͑̕͟͡();
        By1337̭̟̞͙͉̱̹̭͓̭̘̖̺̘̬̌ͮ̑ͬ́ͯ͊ͮ̔ͬͩ͆͐̋̿͌ͥ̆̌͂̐ͪͩ͂̽();
        By1337̶̵̢̹͍̣̺̗̱̠̳̣͐ͧ̀̓̊̃ͪ̋̓̀̏ͨ̔̏̅ͥ̂͋ͥ̂̅̀͆ͤ͜͢͝͝();
        By1337̶̛̛̫̟̭͕̫ͤ̾ͬͧ͑̽̓̈ͣ͂̚͘͘͝͞͏̵̵̻͚͎͇ͩ̄̾̔̋̀̄̈́̍͠();
        By1337̴̨͍̱̝̮̣͇͕̞̮͉͖͍͍̮̬̬̰͓̩̽ͣ̌͂̓̒̓̽̽͌͋̽̅̍ͯ̀̾̿͝();
        By1337͚͉̘̍̾̽̓͗͑̍͌͟͏̛͙͖͔̥͖͔̙̤̻̗͍͖̖̤͂̾́͋̓̓̋ͮͯ͝͞͞();
        By1337̷̶̵̢̹̼͚̲̥̣̘͈̯͎͑̈͌̄͌͛ͩ̌ͤ̾͌͊ͮ̒ͥ̉͆ͧ̈̾͘͜͢͜ͅͅ();
        By1337̴̵̴̵̢͓̱̲̞̱͓̥͚͊̓̉̑̈͋̐̊ͯ̏̉̊̇̒̈́ͥ͊̎ͩͥ̎̇͟͠͡ͅͅ();
        By1337̵̡̧͓̺͔̼͍̤͎̝̹̐̀̽́͗̎͑̇̐ͬ̔͌ͣͫ͘͝ͅ͏̸̷̫̩̓ͬͫ̔́ͨ();
        By1337̧̤̫̭̞̫̻̦̹̹̹̻̞̺̭̘̤̞̗̘̗̿̈͌̐́̾ͤͣͧ͗ͤͤ͌̾ͥͥ͘͠͠();
        By1337̶̴̧̨̼͙̻͙̝̝̯͉͍̫̖͍ͫ̈́͗̓ͯͧͯ̃̀̌́͆̍͛̕͏̷͓̞͌̂̓̄ͣ();
        By1337̷̢̹̫̝̙̻̯̳̠́̈̈́ͦ̆̅̎̀̅͒͏̸̦̮̺̥̱̘͍͉́͆̄̋͐̒̽̀ͬ͞();
        By1337̶̷̶̴̵̴̧̧̨͉̠̬̣̹͚̭̳̐̓ͯͧ͗̄̈́̄̔̏̉̽ͪ̂̎̉̀͌ͨͭ͘̚͜();

        num[0] = hashCode((String) get(array3, -778)); //PAGE_ERROR
        num[1] = hashCode((String) get(array4, -666)); //KEY_NOT_FOUND
        num[2] = hashCode((String) get(array5, -464)); //NOT_VALID_IP
        num[3] = hashCode((String) get(array6, -679)); //INVALID_PLUGIN
        num[4] = hashCode((String) get(array7, -565)); //KEY_OUTDATED
        num[5] = hashCode((String) get(array8, -614)); //VALID
        num[6] = hashCode((String) get(array9, -2557)); //WRONG_RESPONSE
        num[7] = hashCode((String) get(array10, -4315)); //нет_ключа

//        strings[0] = (String) get(array11, -4657); //[]==============================[]
//        strings[1] = (String) get(array12, -2712); //Соединение с сервером
//        strings[2] = (String) get(array13, -4337); //true
//        strings[3] = (String) get(array14, -2197); //§cЛицензия не валидна!
//        strings[4] = (String) get(array15, -1001); //§cПлагин выключен!
//        strings[5] = (String) get(array16, -2794); //false
//        strings[6] = (String) get(array17, -1084); //&cОшибка сервера лицензий
//        strings[7] = (String) get(array18, -3623); //&cОтсутствует лицензионный ключ!
//        strings[8] = (String) get(array19, -213); //&aЛицензия валидна
//        strings[9] = (String) get(array20, -1014); //&cНеправильный ответ от сервера!
//        strings[10] = (String) get(array22, -2698); //&cНеправильный ключ!
//
//        strings[11] = (String) get(array11, -4657); //&cIP адрес не валидный!
//        strings[12] = (String) get(array11, -4657); //&cНеправильный плагин!
//        strings[13] = (String) get(array11, -4657); //&cКлюч устарел!
//        strings[14] = (String) get(array11, -4657); //&cНеизвестная ошибка
//        strings[15] = (String) get(array11, -4657); //?v1=
//        strings[16] = (String) get(array11, -4657); //&v2=
//        strings[17] = (String) get(array11, -4657); //&pl=

        strings[0] = (String) get(array11, -4657);
        strings[1] = (String) get(array12, -2712);
        strings[2] = (String) get(array13, -4337);
        strings[3] = (String) get(array14, -2197);
        strings[4] = (String) get(array15, -1001);
        strings[5] = (String) get(array16, -2794);
        strings[6] = (String) get(array17, -1084);
        strings[7] = (String) get(array18, -3623);
        strings[8] = (String) get(array19, -213);
        strings[9] = (String) get(array20, -1014);
        strings[10] = (String) get(array21, -4415);
        strings[11] = (String) get(array22, -2698);
        strings[12] = (String) get(array23, -1640);
        strings[13] = (String) get(array24, -2674);
        strings[14] = (String) get(array25, -3754);
        strings[15] = (String) get(array26, -1754);
        strings[16] = (String) get(array27, -3648);
        strings[17] = (String) get(array28, -3948);
    }

    private String builder(String v1, String v2) {
        StringBuilder urlBuilder = new StringBuilder(getGet());
        urlBuilder.append(strings[15].toLowerCase()).append(v1);
        urlBuilder.append(strings[16].toLowerCase()).append(v2);
        urlBuilder.append(strings[17].toLowerCase()).append(getInstance().getName());
        return builderToString(urlBuilder);
    }

    private String builderToString(StringBuilder sb) {
        return sb.toString();
    }

    private String requestServer(String v1, String v2) throws IOException {//requestServer
        URL url = new URL(builder(v1, v2));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(5000);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return builderToString(response);
        }
    }

    private int isValidationType() {//isValidationType
        String rand = toBinary(UUID.randomUUID().toString());
        String sKey = toBinary("2APZ5JCR2nuIapCO7eT04knQ");
        String key = toBinary(string);
        try {
            String response = requestServer(xor(rand, sKey), xor(rand, key));
            int hash = hashCode(response);

            if (response.startsWith("<")) {
                return num[0]; //PAGE_ERROR
            }
            if (hash == num[1]) {//KEY_NOT_FOUND
                if (key.length() == 0)
                    return num[7];
                return num[1]; //ключ не валидный
            }
            if (hash == num[2]) //NOT_VALID_IP
                return num[2];
            if (hash == num[3]) //INVALID_PLUGIN
                return num[3];
            if (hash == num[4]) //KEY_OUTDATED
                return num[4];

            String respRand = xor(xor(response, key), sKey);
            if (rand.startsWith(respRand))
                return num[5]; //VALID
            else
                return num[6]; //WRONG_RESPONSE

        } catch (IOException e) {
            return num[0]; //PAGE_ERROR
        }
    }

    private static int generateRandomBinaryNumber(int length) {
        if (length > 0b10100)
            length = 0b10100;
        Random random = new Random();
        StringBuilder binaryNumber = new StringBuilder();
        binaryNumber.append("1");
        for (int i = 0; i < length - 2; i++) {
            int bit = random.nextInt(2);
            binaryNumber.append(bit);
        }
        binaryNumber.append("1");
        return Integer.parseInt(binaryNumber.toString(), 2);
    }

    private String toBinary(String s) {//toBinary
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    private static String xor(String s1, String s2) {//xor
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
                result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
            return result.toString();
        } catch (NumberFormatException e) {
            return "<";
        }
    }

    private static String enc(String string) {//encryption
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while (n < string.length()) {
            stringBuilder.append((char) (string.charAt(n) ^ 0x76));
            ++n;
        }
        return stringBuilder.toString();
    }
    //    public String manager(String s) {//loadAndRegister
    //        this.string = s;
    //        Message.logger(strings[0]);
    //        Message.logger(strings[1]);
    //        int vt = isIs();
    //        try {
    //            if (vt == num[5]) {
    //                Message.logger(strings[8]);
    //                Message.logger(strings[0]);
    //                BAirDrop.len = generateRandomBinaryNumber(10);
    //                BAirDrop.info[0] = generateRandomBinaryNumber(12);
    //                BAirDrop.info[1] = generateRandomBinaryNumber(4);
    //                BAirDrop.info[2] = generateRandomBinaryNumber(6);
    //                BAirDrop.info[3] = generateRandomBinaryNumber(8);
    //                BAirDrop.info[4] = generateRandomBinaryNumber(15);
    //                BAirDrop.info[5] = generateRandomBinaryNumber(10);
    //                BAirDrop.info[6] = generateRandomBinaryNumber(20);
    //                BConfig.LoadConfiguration();
    //                new Metrics(BAirDrop.getInstance(), 17870);
    //                BAirDrop.getInstance().getCommand("bairdrop").setExecutor(new Commands());
    //                BAirDrop.getInstance().getCommand("bairdrop").setTabCompleter(new Completer());
    //
    //                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new InteractListener(), BAirDrop.getInstance());
    //                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(BAirDrop.summoner, BAirDrop.getInstance());
    //                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.getInstance());
    //                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.getInstance());
    //                BAirDrop.getInstance().getServer().getPluginManager().registerEvents(BAirDrop.compass, BAirDrop.getInstance());
    //                return strings[2];
    //            } else {
    //                Message.logger(strings[3]);
    //                Message.logger(infoCode(vt));
    //                Message.logger(strings[4]);
    //                Message.logger(strings[0]);
    //                BAirDrop.len = generateRandomBinaryNumber(3);
    //                BAirDrop.info[0] = generateRandomBinaryNumber(19);
    //                BAirDrop.info[1] = generateRandomBinaryNumber(3);
    //                BAirDrop.info[2] = generateRandomBinaryNumber(1);
    //                BAirDrop.info[3] = generateRandomBinaryNumber(6);
    //                BAirDrop.info[4] = generateRandomBinaryNumber(14);
    //                BAirDrop.info[5] = generateRandomBinaryNumber(12);
    //                BAirDrop.info[6] = generateRandomBinaryNumber(5);
    //            }
    //        } catch (Exception error) {
    //            Message.logger(strings[3]);
    //            Message.logger(infoCode(vt));
    //            Message.logger(strings[4]);
    //            Message.logger(strings[0]);
    //            BAirDrop.len = generateRandomBinaryNumber(5);
    //            BAirDrop.info[0] = generateRandomBinaryNumber(7);
    //            BAirDrop.info[1] = generateRandomBinaryNumber(8);
    //            BAirDrop.info[2] = generateRandomBinaryNumber(4);
    //            BAirDrop.info[3] = generateRandomBinaryNumber(3);
    //            BAirDrop.info[4] = generateRandomBinaryNumber(12);
    //            BAirDrop.info[5] = generateRandomBinaryNumber(4);
    //            BAirDrop.info[6] = generateRandomBinaryNumber(6);
    //        }
    //        Bukkit.getScheduler().cancelTasks(BAirDrop.getInstance());
    //        Bukkit.getPluginManager().disablePlugin(BAirDrop.getInstance());
    //        return strings[5];
    //    }

    private String getGet() {//getVerifyUrl
        // return "http://by1337.space/verify.php";
        int var0 = Integer.parseInt("111011000", 2);
        int Vvar1961845913 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));//6074
        switch (Vvar1961845913) {
            case 1514533906:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1800060780:
                throw null;
            case 6074://6074
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1730465680:
                throw null;
            case 129202360:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 1452589267:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 748696725:
                throw null;
        }

        By1337̧͉̰͖̠̱̘̟͉̗̹̖̲̦̬͍̯͕̺̜̠ͨ̽̂̎ͯͤͮ̊̓̅̓̃ͫ̐ͥ̀͟͟͟();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array2.length + array2[0].length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < array2.length && i - j < array2[0].length) {
                    Object obj = array2[j][(i - j)];
                    i++;
                    Object key = array2[j][i - j];
                    key = sObf((String) key, var0);
                    sb.append(decrypt((String) obj, (String) key));
                }
            }
        }
        return sb.toString();

    }//http://by1337.space/verify.php

    private void By1337̧͉̰͖̠̱̘̟͉̗̹̖̲̦̬͍̯͕̺̜̠ͨ̽̂̎ͯͤͮ̊̓̅̓̃ͫ̐ͥ̀͟͟͟() {
        array2[0][0] = "25clTVAjs24=";
        array2[0][1] = "ؔؑؕؕؒ؏ؽ،؉ؿ؎ؕؑ؉ؐؕـف؉ؕؓؐ،؉ؓ؍،ؽ؍؍ؽؔؕفؕؒ";
        array2[1][0] = "rP/yepX/QC8=";
        array2[1][1] = "ؕ؎ؑؒؐ؎؏؎؉؏ؔؓـ؉ؐؔؕؾ؉ؔؑفؓ؉؎ـ؎ؾؑؽـؑقؕؐؿ";
        array2[2][0] = "wOofj/LeInw=";
        array2[2][1] = "ؑؓـؕؓؕؓ؍؉قؒؽف؉ؐؔ؎ؿ؉ؔؿ؎ف؉ؿؐؕؕ،ؽ؏ؽـؓ؏ؕ";
        array2[3][0] = "EyiX0aUjNCw=";
        array2[3][1] = "ؓقؽؽ؍ؕؽف؉ؽقؐؕ؉ؐ؍؍،؉ؕؿف؍؉ؽؓؿففـقؐ؍؍؏؏";
        array2[4][0] = "fBy0U/H/Ncw=";
        array2[4][1] = "ؽؔ؎ؐقففـ؉ؕؐـ،؉ؐؿــ؉ؕؕ؎ؽ؉ؒؽؐؽ،؍ؾؑـؽقؓ";
        array2[5][0] = "nbJslDW3jJA=";
        array2[5][1] = "ؕ؍ؔؔؿؐؐؾ؉ؓؒ؏؍؉ؐؑؕؽ؉ؔؒؑؐ؉ؕفؑؿؓؾؒؐقؽؒـ";
        array2[6][0] = "XL8LaqOdaVk=";
        array2[6][1] = "ؾؕؑ؎ؑ؎ؕـ؉ؒـؒف؉ؐؓؿؑ؉ؾؽق؍؉ؽ؎فؑؒؾقؕؑ،؏ؑ";
        array2[7][0] = "xtO7v9jHwRM=";
        array2[7][1] = "؏ؕ؍ؽؽؑـ؏؉ؿؕؒـ؉ؐ؏ؓـ؉ؾؓؾؽ؉،ؾؾفؐ؏ق؎ؓ،ؓؔ";
        array2[8][0] = "TxeTFEGPCZI=";
        array2[8][1] = "ؑق؏ؽ؏؍ؾ؍؉ق،ـ،؉ؐؓؕ،؉ؕؒفؑ؉قؔؾ؎ق؎ؓؾؒ؎؍،";
        array2[9][0] = "R5osg/1IG2A=";
        array2[9][1] = "ؑؾؒؒققؾؕ؉قؒفؾ؉ؐ؍ؐق؉ؕؔؐ؎؉؏ؕـؒؾ؍قؑؑققؒ";
        array2[10][0] = "8pB7bOUsIqw=";
        array2[10][1] = "ؾؿؒؿؑ؍،ؾ؉ؑقف؏؉ؐؕؾؿ؉ؾؿف؍؉ؿؑؔؐق؍ؓ،ؾ؍ؐؔ";
        array2[11][0] = "G76pWYeff9w=";
        array2[11][1] = "؎،ؔ؎قؐ؎؍؉؍،ؑؕ؉ؐؑؾؾ؉ؽؔ،،؉؍ؐؿؕـؔؔ؏ـفؿؔ";
        By1337̵̴̧̞͍̥͙̗̠̰̰͙̠̫̗̞̥̰̱́ͭ̎͒̍͐ͯ̂̋̀͆ͮ͂̑͟͟͜͡͡͡͝();
    }

    private void By1337̵̴̧̞͍̥͙̗̠̰̰͙̠̫̗̞̥̰̱́ͭ̎͒̍͐ͯ̂̋̀͆ͮ͂̑͟͟͜͡͡͡͝() {
        array2[12][0] = "HgW8pAZBJT0=";
        array2[12][1] = "ؑؓفؽ؍ؒؿ؎؉فؕؒؔ؉ؐفؿؓ؉ؾـؿق؉فؔ،ؒؔؒؒؕـ؍ؽؑ";
        array2[13][0] = "WNksJmNHdSo=";
        array2[13][1] = "ؿؑؾ؏قؽقؒ؉ؾ؏ؽ؏؉ؐ،ؕؐ؉ؽـقؔ؉ؑؐؑ،؏ف؍؏ؐؽقق";
        array2[14][0] = "gtgDBUATw8o=";
        array2[14][1] = "ؓؓؒؔقؾ؍ف؉ؾؾؒف؉ؐ؏ـ؎؉ؽؓؾؾ؉ـؽؾؕؕ؍ؾؑؓفؽؓ";
        array2[15][0] = "4CgxPZqK1WE=";
        array2[15][1] = "ؔؔ؏؍ؑؐؔ،؉ؾؿؕـ؉ؐ؎،ؽ؉ؽ؍ؽق؉ـؓ؎؎ؽؐ،ؑؑ،؎ؕ";
        array2[16][0] = "ba5Otid71o8=";
        array2[16][1] = "ؒؕؽؔؾؓؾف؉؏ؓ؎؏؉ؐؔؐؐ؉ؾ؍ؒؐ؉ؔ؍ق،ؔ؎؍ؿ؏،؍؍";
        array2[17][0] = "NYsG0XabrmE=";
        array2[17][1] = "ؐؔـفـؐ؏ؕ؉،؎ؾؐ؉ؐؒؓ؍؉ؽ،ؒؐ؉ـ؏؎ؑ؏ـؕ؍ؓـ؎،";
        array2[18][0] = "Eqn9Vnq2FQA=";
        array2[18][1] = "ـؑ؍،قؔ؎ؕ؉ؕ،ؓؿ؉ؐ؏ؕؕ؉ؔ،ــ؉ـف؏ؒؐـؽؾقؔؕؾ";
        array2[19][0] = "T286RjEsx7w=";
        array2[19][1] = "؎؎ؾـ؏ؓؽؒ؉ؾؑؽف؉ؐ؎ؑف؉ؾؐـؐ؉ـؓؓؿؿقؿ،قؑقؐ";
        array2[20][0] = "87jPYfOg2WE=";
        array2[20][1] = "ؐؕؾؾؑقؕؐ؉ؔؽ؏؎؉ؐ؏،ؐ؉ؾؓ؏؏؉ؐؕ؏فؑ؍ؔؒؿؐؐ؎";
        array2[21][0] = "z6PWHnRpicg=";
        array2[21][1] = "ؒؒؒؿ؎ؔ؍ق؉؎؍؎ـ؉ؐؓ؏ؕ؉ؕق؎؏؉ؔؔ؏ؐؕ؍ؕ؏؏ؿؾؿ";
        array2[22][0] = "hHrpDzOEmTo=";
        array2[22][1] = "ق؏ق،ؐؿـف؉فؾؔؔ؉ؐؾؕؔ؉ؔـؿف؉ؿؑؕؒ؎؏ـ،قؒؐؽ";
        By1337̡̛͖̲̗̮͖͙͔̟̯͇̗̩̖̟͑̿͑͗̒́ͣ̊͛ͣ̃̄͒ͦͤͦ͋̐ͪ͛͜͟͞͞();
    }

    private void By1337̡̛͖̲̗̮͖͙͔̟̯͇̗̩̖̟͑̿͑͗̒́ͣ̊͛ͣ̃̄͒ͦͤͦ͋̐ͪ͛͜͟͞͞() {
        array2[23][0] = "Piyq5jjjRLQ=";
        array2[23][1] = "قؾؐـؽؔؽؐ؉ؿؐؕف؉ؐؑ؍،؉ؔ؏فؔ؉ؓق؎ؔـقؓ؍ؑ؏ــ";
        array2[24][0] = "/E+NuG5Lq/s=";
        array2[24][1] = "ؑ؍ؾؔؑؒؐؓ؉ؕ؏؎ؑ؉ؐؐ؎ؑ؉ؔ،ؑف؉ؔؿؔـ؎ؓؕؾ،ؑ؏ؽ";
        array2[25][0] = "uG5UVkIfEso=";
        array2[25][1] = "ؑؓؒؔؑؿؕؒ؉فؐقؒ؉ؐؔؑق؉ؕ؍ؿؿ؉ؽؐ؏ؾؾـؐقؓؓؽؾ";
        array2[26][0] = "FyKXC+OlaR8=";
        array2[26][1] = "ؕقؕؒؑ،ـق؉؏فؽ،؉ؐقؐـ؉ؽ؎،ؽ؉ؒؕؑؽؐ؍ؓ؍ؔ؍ؿف";
        array2[27][0] = "wAL9FL4ULJg=";
        array2[27][1] = "ؐ؍ؔ؏ؒؾ،ؓ؉،ف؏ؑ؉ؐؾ؏ق؉ؽفؕـ؉؏؏؎فف؍ؾؽؐ؍؏ؓ";
        array2[28][0] = "qZ/7T5WURdo=";
        array2[28][1] = "؎ؔ؏؏؎ؽؿـ؉ؾقؓؕ؉ؐ،؎ق؉ؽؽـؓ؉ؾؑ؍؏،،ـ؏؏ؽفؔ";
        array2[29][0] = "nLTwSpzR5V0=";
        array2[29][1] = "ؓؕقؓ؍ؿ،ؾ؉ؾؐـؿ؉ؐؐؐؓ؉ؕ؏ؒؔ؉ف؍ؓ،؎قؓؐؔؐؽق";
    }

    //pagge error
    private static void By1337̴̧̺͎̥͍͇̩̗͍̬̻̙̱͖͂ͦ̓̎͊͐͂̀̒̆͗̄ͬ̓ͫ̊ͣ̄̓́̀́͢͢͟() {
        array3[0][0] = "6JYUNml7ZU0=";
        array3[0][1] = "̓̀͂Ͱ̷̷̷̷̺̻̺̽ͭ̾ͮͭͭͯ̾̿̾͂̿ͯ̾̽ͫ̀ͫͮͫͯ̽ͮ̀Ͱ";
        array3[1][0] = "eWz+HZ/DHVc=";
        array3[1][1] = "ͫ̿Ͱ̷̷̺̺̼ͮ̓ͮ͂ͬ̿̓̾Ͱ̷ͭͬ͂Ͱ̷̻̻̼̻ͫͬ͂͂̾̓ͮͫͬ";
        array3[2][0] = "TCC60tkFJAE=";
        array3[2][1] = "̷̷̷̷̺̼̻̻̺̼̓ͭ̓́͂͂̾ͫ̾ͮ̀̓ͯ́̽̀Ͱ̺̻̺ͫ͂ͬͯ̀̾";
        By1337̵̢̢̡̨̗͎̝̪̭̺̩̬̻̤̹̱̳͈͆̎͆̉̄ͭ̇͗ͭͭ̈́̄̈́̈́ͭ̊͘͢͢͞͠();
    }

    private static void By1337̵̢̢̡̨̗͎̝̪̭̺̩̬̻̤̹̱̳͈͆̎͆̉̄ͭ̇͗ͭͭ̈́̄̈́̈́ͭ̊͘͢͢͞͠() {
        array3[3][0] = "hEs8kcwl19U=";
        array3[3][1] = "̼̽ͭ̀Ͱ̷̷̷̷̺̺̺̼ͬͫͯ̀ͫ͂̾ͫͫͭ͂́ͬ͂ͭͫͭͫ̽̿ͯͫͰ";
        array3[4][0] = "vrkJghQuv24=";
        array3[4][1] = "̷̷̷̷̺̼̺̻̼̻̺̼̺̀͂ͮͯ̿̾ͭ̾ͬ̓͂́̓̀ͬ̓ͫ̓ͬͭͭͫͯ";
        By1337̸̶̻̱̘̦̮͖͈̭͇̙̬̝̗̲̜̉͗̅̆ͬͨ̒ͧ͆̂ͮ̈́͂ͭͬ̃͑͟͟͜͡͝͡();
    }

    private static void By1337̸̶̻̱̘̦̮͖͈̭͇̙̬̝̗̲̜̉͗̅̆ͬͨ̒ͧ͆̂ͮ̈́͂ͭͬ̃͑͟͟͜͡͝͡() {
        array3[5][0] = "6mvxKH/iPOI=";
        array3[5][1] = "̀̽ͬ͂ͯ̽̀Ͱ̷̷̷̷̺ͮ̀ͮ̀̾̀ͮ̓ͫͫ̿ͬͬ̾ͭ̾ͯͮ̓̓̀ͭͰ";
        array3[6][0] = "rlxGMOq/Sbs=";
        array3[6][1] = "̷̷̺̼͂ͯͬͫͮ̀̓̾̀ͮ̾Ͱ́Ͱ̷̷̓ͯͬͬͫͯ͂̾ͯͮͯͰ̻ͯ̿́";
        By1337̴̢̨̧̝̙̗͍̺͚̗͚̖̬͈̯͎̫͙̖̻̇̀̌ͭͣ̓̽́ͤͯ͆͊͋͗̈͂̕͢͢();
    }

    private static void By1337̴̢̨̧̝̙̗͍̺͚̗͚̖̬͈̯͎̫͙̖̻̇̀̌ͭͣ̓̽́ͤͯ͆͊͋͗̈͂̕͢͢() {
        array3[7][0] = "Q502k9di3tQ=";
        array3[7][1] = "̺̿́́Ͱ̷̷̷̷̺̻̼̻̺̻̺̓ͭ̾ͮ̓͂̾̾͂͂ͫͭͫ̓̿ͭͯ̀ͮͯ";
        array3[8][0] = "c2txBD/D9pU=";
        array3[8][1] = "͂ͯ́ͮͬ̽ͫͰ̷ͬͰ̷̷̻ͬͭ̾̾ͮ͂Ͱ̷̺͂ͯ̓́́̽ͮͫ̀ͭͭͫ̓";
        By1337̷̴̵̧̢̨͕̗̪̙̫͓̦̘̙͖̊͑ͧ͗ͯ̋ͯ͑͑̅̄ͨ̈́ͮͥͦ̽ͦ͆ͥͬ̄̚͢();
    }

    private static void By1337̷̴̵̧̢̨͕̗̪̙̫͓̦̘̙͖̊͑ͧ͗ͯ̋ͯ͑͑̅̄ͨ̈́ͮͥͦ̽ͦ͆ͥͬ̄̚͢() {
        array3[9][0] = "rcwvF4IIW60=";
        array3[9][1] = "̾̽ͭ̾Ͱ̷̷̷̷̺̼̻̺ͫ̽ͬ͂ͭ́̾ͬͯ́͂̀̿̀̽̽̽ͮ́ͯ́ͰͰ";
    }
    ////////////////////////////////////////////////

    //key not found
    private static void By1337̴̷̢̩̗̭͙̥̥ͥͬ̀̏ͩͩ͑̕̚͏̨͈̫̲̪̹̙̩̖̽ͭ͛̓̀̐̀̀̕͜͠͞() {
        array4[0][0] = "hXs9sUnk/d4=";
        array4[0][1] = "ː˿˽ˏː˒ˋˊˇˍˌ˻ˎˇˎː˒ˎˇ˻ˑˌˏˇ˒ː̀˼ˋ˿ˏ˼˽˾ː˻";
        array4[1][0] = "h/iz5Tf/Iac=";
        array4[1][1] = "ˌ˾˼˾ːː˻ˑˇˑ˾˽˓ˇˎ˼˼˒ˇ˒ː˼˓ˇ˽˻˿ˑ˓˾ˎ˻̀˾ˌ˽";
        array4[2][0] = "4DdrZoooYYw=";
        array4[2][1] = "ˌˏ̀˼˿ˍː˒ˇ˾ˍˎ˻ˇˎ˼ˏˌˇ˒ˏ˽˓ˇˍ˒ˍˋˊˑː˒ːˊˑ̀";
        By1337̵̸̡̧̢̛̛̗̮̳̣͎̬̲̼̟̮̩͙̬̉̍ͭͭ̉́͐̓͑̏͛ͦ͆̏̈́ͩ͐͘͟͢();
    }

    private static void By1337̵̸̡̧̢̛̛̗̮̳̣͎̬̲̼̟̮̩͙̬̉̍ͭͭ̉́͐̓͑̏͛ͦ͆̏̈́ͩ͐͘͟͢() {
        array4[3][0] = "WjwTxSYY/xg=";
        array4[3][1] = "ˏ˼˿˓˽ˍˑˊˇˎˋ˓ˊˇˎː˒˾ˇ˻̀ˍ˓ˇ˿˼˼̀ː˿ˎ˼ˑː˿ˊ";
        array4[4][0] = "sPBoW1vYqVo=";
        array4[4][1] = "ˑˌˑˋ˼ˑˑ˒ˇ˓˽ˊ˾ˇˎ˼ːˏˇ˻˿ˑ˻ˇ˿˻ˏ˼ˍˎ˒ˎˊˊˑ˼";
        By1337̵̧̢̛̪͇̟̘̗̰̮̮̣̤͉͍̹̖̺ͮͧ̂̄̽̈ͮ̿̀͊͊̈̀̓̚̚͟͟͠͝ͅ();
    }

    private static void By1337̵̧̢̛̪͇̟̘̗̰̮̮̣̤͉͍̹̖̺ͮͧ̂̄̽̈ͮ̿̀͊͊̈̀̓̚̚͟͟͠͝ͅ() {
        array4[5][0] = "I/AM7YYrht4=";
        array4[5][1] = "ˊ˿ːˍ˿˽˽ˊˇˍːːˊˇˎˍ̀ˋˇ˒ː̀ˋˇ̀˾ˋˋˎˍ˾˼˓ˎ˒ˌ";
        array4[6][0] = "3Nofs6C6WoM=";
        array4[6][1] = "ˊˏ˼˽˼ˌ˻ˍˇˌˋˌˊˇˎ˓˼ˎˇ˒˿˽ːˇˑˍ˓˓ː˽˼˒ˋˍˑˎ";
        By1337̹͓͍̱̘̯̠̳̆ͬ̌̈̋ͤ̓ͥ̾̓ͥͪ͢͝͏̶͍̙̳̗̺͖͇̮̿̈́́̓͘͝͡ͅ();
    }

    private static void By1337̹͓͍̱̘̯̠̳̆ͬ̌̈̋ͤ̓ͥ̾̓ͥͪ͢͝͏̶͍̙̳̗̺͖͇̮̿̈́́̓͘͝͡ͅ() {
        array4[7][0] = "ZVvXOnwZR7o=";
        array4[7][1] = "ˌˍ˿˽˓ˎˎˋˇ˽˒ˍ˽ˇˎː˻˾ˇ˒ˋ˽̀ˇ˿˓̀˻ˑ˓˿˒ˑˊ˓˾";
        array4[8][0] = "voKtqo19F14=";
        array4[8][1] = "ˎˊː˾ˎ̀˓ˑˇˊˍ̀ˋˇˎˌ˒˿ˇ˻ˊ˻ˊˇ˽ˌ˻ː˼˾ˎ˾ˑ˒ˌˎ";
        By1337̴̸̛͈̤̤̜͚̻̣̗̩̆̄̇ͤ͌̍̅̉̔ͬͧ͑͆́̉́̎͊̄ͩ̕̚͘͜͢͡͠ͅ();
    }

    private static void By1337̴̸̛͈̤̤̜͚̻̣̗̩̆̄̇ͤ͌̍̅̉̔ͬͧ͑͆́̉́̎͊̄ͩ̕̚͘͜͢͡͠ͅ() {
        array4[9][0] = "DUZHMlC9Kn4=";
        array4[9][1] = "˼ˋ˾˒ˎˎ˿˾ˇˑ̀˻̀ˇˎː˾̀ˇ˼̀ˋˍˇ˾̀ˊ˓˼˼ː˓ˌˎ˿˼";
        array4[10][0] = "YdZkIAA2Mq0=";
        array4[10][1] = "˼˽˻˻ˎ˾ˑ˽ˇ˽ˏ̀ˊˇˎˑˌ˿ˇ˼ˑˏ˽ˇˍˊˑ̀˓̀ˊ˼˼˒˓̀";
        By1337̷̴͓̺͇̜̰̣̤̠̭̭̗̩̥̼́̑͑͊̓ͩ̒̿ͪ͆ͤ̆̀̐ͤ̊̒͘̚̚̚͟͟͝();
    }

    private static void By1337̷̴͓̺͇̜̰̣̤̠̭̭̗̩̥̼́̑͑͊̓ͩ̒̿ͪ͆ͤ̆̀̐ͤ̊̒͘̚̚̚͟͟͝() {
        array4[11][0] = "0vteAFUCw5s=";
        array4[11][1] = "˻ː˽ˍˏ̀ˌ˼ˇ˻˼̀˽ˇˎˋ̀˾ˇ˼˓ˎˏˇˎ˽˻ˌˍˏ˾ˍ˻˻˼˒";
        array4[12][0] = "bYxVeWUupsI=";
        array4[12][1] = "ˋˏ˾ˍˍˏ˒˓ˇˑːˍ˒ˇˎˋˌ˿ˇ˓˿ːˋˇˎ˿ˍ˓ˌ˽ˑˌ˽ˊːˑ";
        By1337̵̶̴̶̹̖͎͎̥̟̩̝̫ͧ͂̍̒̀͛̇ͭ̒͘͜͝͏̵͖̺̲͉͔̱̞ͩ͆ͯ͐̅ͅ();
    }

    private static void By1337̵̶̴̶̹̖͎͎̥̟̩̝̫ͧ͂̍̒̀͛̇ͭ̒͘͜͝͏̵͖̺̲͉͔̱̞ͩ͆ͯ͐̅ͅ() {
    }
    ////////////////////

    //invalid ip
    private static void By1337̶̷̨̡̮͉̹͕͈̫̞̞̲͖̖̗̝̥̞̱̱̖̩̦̓̽ͬ͗̍̆̒̍ͯ̍ͦ̅͟͟͞ͅ() {
        array5[0][0] = "o65clt8CZBU=";
        array5[0][1] = "ȆȀȄȆȀȶȴȴǽȁȈȅȉǽȄȶȆȀǽȲȅȀȉǽȆȳȄȆȶȶȆȁȴȁȶȀ";
        array5[1][0] = "CogFhHZbztg=";
        array5[1][1] = "ȱȵȳȱȈȅȂȄǽȂȵȅȲǽȄȁȃȵǽȲȵȳȇǽȂȅȂȳȄȀȁȈȵȁȵȉ";
        array5[2][0] = "z9Me3TvZ8MQ=";
        array5[2][1] = "ȱȄȃȱȀȳȉȂǽȂȂȇȵǽȄȁȅȁǽȲȅȇȁǽȆȃȲȱȃȀȁȱȵȵȁȆ";
        By1337̧̼̟̼̤̰͚͍̖̘̯̮̭̖̮̩̄ͧ͋̂̈̇̀̿̒͂͆̏̑̑̅̄́̈̍̍͜͟͡͝();
    }

    private static void By1337̧̼̟̼̤̰͚͍̖̘̯̮̭̖̮̩̄ͧ͋̂̈̇̀̿̒͂͆̏̑̑̅̄́̈̍̍͜͟͡͝() {
        array5[3][0] = "TucQGzXjS4k=";
        array5[3][1] = "ȅȃȵȂȀȶȅȉǽȈȉȴȂǽȄȃȄȴǽȈȴȃȴǽȉȆȇȃȶȶȵȄȃȅȲȲ";
        array5[4][0] = "1uIbUu+GuKQ=";
        array5[4][1] = "ȈȁȃȱȇȶȄȴǽȱȄȂȱǽȄȶȂȄǽȱȶȈȶǽȆȶȆȀȈȃȃȳȶȲȵȅ";
        By1337̶̴̶̶̨̡̺̞̻̭̝͖̩̺̻͙ͩ̈̂̉̌ͫ̓͐̈ͭ̈́̈́̒̀̌̆̏ͧ̚̚͜͡͞͞();
    }

    private static void By1337̶̴̶̶̨̡̺̞̻̭̝͖̩̺̻͙ͩ̈̂̉̌ͫ̓͐̈ͭ̈́̈́̒̀̌̆̏ͧ̚̚͜͡͞͞() {
        array5[5][0] = "jTT/PHb1U70=";
        array5[5][1] = "ȳȶȶȳȁȈȴȃǽȀȱȈȱǽȄȳȇȴǽȲȱȵȵǽȳȵȱȇȃȉȂȆȇȉȱȃ";
        array5[6][0] = "pH4wpLE8TO4=";
        array5[6][1] = "ȀȇȲȄȃȄȈȵǽȂȃȴȲǽȄȃȶȇǽȈȲȂȵǽȇȵȄȅȄȄȀȈȈȁȁȵ";
        By1337̸̸̸̨̩͇͚̬̮̩̗̹͕̥̝̩̖͈̏̿̒̔̈̏̈ͭ̿̔ͧ̒ͫ̉̂͒͟͜͝͞͞͞();
    }

    private static void By1337̸̸̸̨̩͇͚̬̮̩̗̹͕̥̝̩̖͈̏̿̒̔̈̏̈ͭ̿̔ͧ̒ͫ̉̂͒͟͜͝͞͞͞() {
        array5[7][0] = "Q+6wLcokdOM=";
        array5[7][1] = "ȇȳȉȈȀȳȂȶǽȴȵȈȃǽȄȁȶȁǽȈȄȈȄǽȲȳȆȴȉȅȆȈȂȃȈȅ";
        array5[8][0] = "lCwAlDZPeeY=";
        array5[8][1] = "ȃȃȀȵȂȵȱȀǽȉȆȵȉǽȄȵȳȶǽȲȉȱȶǽȵȉȱȉȇȉȉȄȲȇȲȱ";
        By1337̴̶̨̛͚̱̤̤̭̠̘̜̲̹̲̞͕̻̦̦̄̽ͣ̓̓ͯ͋͗͒ͤ̏̓̆ͮ͊̕͟͠͝͠();
    }

    private static void By1337̴̶̨̛͚̱̤̤̭̠̘̜̲̹̲̞͕̻̦̦̄̽ͣ̓̓ͯ͋͗͒ͤ̏̓̆ͮ͊̕͟͠͝͠() {
        array5[9][0] = "JURSE3TH8Z8=";
        array5[9][1] = "ȈȁȈȲȂȲȶȃǽȄȵȉȃǽȄȱȀȆǽȱȳȅȆǽȆȈȉȂȅȵȉȳȵȲȅȅ";
        array5[10][0] = "ARabmMdXujo=";
        array5[10][1] = "ȁȆȅȶȱȃȃȁǽȄȴȵȵǽȄȇȂȱǽȉȉȶȴǽȴȀȵȉȂȉȶȶȂȉȅȂ";
        By1337̶̸̡̫̼͙̹̼̪̺̤̥̦͉͚̈̃ͮͭ̾ͧ̏ͧ̌̀̐ͦ̅̅͌ͮ̌͂̀͑ͮ͡͝͝͞();
    }

    private static void By1337̶̸̡̫̼͙̹̼̪̺̤̥̦͉͚̈̃ͮͭ̾ͧ̏ͧ̌̀̐ͦ̅̅͌ͮ̌͂̀͑ͮ͡͝͝͞() {
        array5[11][0] = "3qp+Y68wIK8=";
        array5[11][1] = "ȈȵȇȈȀȲȆȲǽȆȳȇȴǽȄȱȆȀǽȱȶȲȃǽȲȶȇȂȳȲȲȶȀȴȈȳ";
    }
    ////////////////////

    private static void By1337̴̶̡̡̠̳̲̭̱̳̜̠̜̖̞̻͇̟̫͓͔͑̽ͣͤ̿̆̀͐̂̓̿̂̐̅̌́͐ͣͅ() {
        array6[0][0] = "iEXQ/NN7y2k=";
        array6[0][1] = "̌̋̍˘˜̈̈ˠ˔˗˞ˠ̌˔˛˙̉˗˔̉̊˙˝˔̌˜˛˟˘˚˟̈˛̍˗̍";
        array6[1][0] = "8DPoDIJeKEo=";
        array6[1][1] = "˛˗̉ˠ˘˙̍˜˔̍˞˟˞˔˛˛ˠ̋˔̉̉˚˙˔˙˜˝˛̊̈̊˛̍˜˗˘";
        array6[2][0] = "D72aQ/QgxRk=";
        array6[2][1] = "˙˜̊˛˝̊̈˞˔˟˟ˠ̊˔˛˜˘̌˔̉˙˞̋˔̊˛˜˝̉̋̉̋̈˟˘˜";
        By1337͎̣͈͔̗̠̞̠̜̊̅ͪͦͪ̈̊̓ͮͫ̃ͫ̾̿̍̕͞͝ͅͅ͏̳͚̯̻̝͋͂̎̀̚();
    }

    private static void By1337͎̣͈͔̗̠̞̠̜̊̅ͪͦͪ̈̊̓ͮͫ̃ͫ̾̿̍̕͞͝ͅͅ͏̳͚̯̻̝͋͂̎̀̚() {
        array6[3][0] = "OxXBkBNlE+g=";
        array6[3][1] = "˝˞˙̈˚̌˘̌˔̊˗˟˚˔˛̍˛˙˔˟˞̋˛˔̉˚˛˛˝̌̍˜̊̉˚˗";
        array6[4][0] = "x7x32fWTmdI=";
        array6[4][1] = "̈˜̉˛˜̋̌˜˔˙˞̍˚˔˛˜̋˞˔̉̌̉̊˔˜̋˝˝̍̊˘˘˗̍˞˟";
        By1337̵̶̵̧̧̛̞͉͙͓̗̲̣̥̫̯̪͍̝͈͕̲͖̄̈͗̾͂ͮ͗̊̄̍̈́ͮ͛̿͆͠͞();
    }

    private static void By1337̵̶̵̧̧̛̞͉͙͓̗̲̣̥̫̯̪͍̝͈͕̲͖̄̈͗̾͂ͮ͗̊̄̍̈́ͮ͛̿͆͠͞() {
        array6[5][0] = "Co/pNV9qLZ0=";
        array6[5][1] = "˝˘̋̌˚ˠ̍˟˔˟˚˛˚˔˛˙˜˗˔̉˘̌˜˔˛˜˘̊̈̉̌˞̋˛˞˘";
        array6[6][0] = "VbXKQ8ei7sY=";
        array6[6][1] = "̌̈̌̍̊˛˝˛˔̌˟̍˛˔˛˚˝̉˔̉˛̌̉˔˙̍˝˛˚̉̈̋̊˙˗̊";
        By1337̴̷̡̡̡͓͇̙̦̬͖̞̯̱͈͎̮̜͇͖̤͎̇͊́̎́ͭ̆̿ͭ͊͋̈́̾͌ͨͤ̍͘();
    }

    private static void By1337̴̷̡̡̡͓͇̙̦̬͖̞̯̱͈͎̮̜͇͖̤͎̇͊́̎́ͭ̆̿ͭ͊͋̈́̾͌ͨͤ̍͘() {
        array6[7][0] = "F6Q0i8sGNtg=";
        array6[7][1] = "̍˞̉̉˟̍̍̈˔˛̋˜˚˔˛̌̋˝˔ˠ̈̍˘˔˜˚̌˟˚˚˝˛̍̉˗˛";
        array6[8][0] = "ZFHY7O7WGys=";
        array6[8][1] = "˘̌̉˜̍˝˟˜˔̉˜˛˝˔˛˛̋˘˔̉̌ˠ̈˔˘˞˗ˠ˘̌̈̊˞˝˞̉";
        By1337̸̵̡͉̥͓̪̖̹̙̜̥̹͔̟̻̳̜̫͔̬͇́̐͗̊̽̉̉ͪ̽̍̂̉͛ͣ͢͢͠͝();
    }

    private static void By1337̸̵̡͉̥͓̪̖̹̙̜̥̹͔̟̻̳̜̫͔̬͇́̐͗̊̽̉̉ͪ̽̍̂̉͛ͣ͢͢͠͝() {
        array6[9][0] = "xQrBC4ddONk=";
        array6[9][1] = "˗˚̋˞˙̉˝˗˔˛˜˝̉˔˛˝˗̍˔˟˛̍̋˔̊˜˛˟˗˛̈ˠ̍˗˜˙";
        array6[10][0] = "2hFI5p3Xz78=";
        array6[10][1] = "ˠ̋˜̊˚̉̈˞˔˞˝˗˝˔˛̈˟˝˔ˠ˟˗̉˔˝˝̊̉˟˗ˠ̌˛̊˘ˠ";
        By1337̴̸̶̡̮͚͚̠̗͙̦̳̩̻̭̳̦̼͇̃̃̇͐̉ͭ̑ͬ̀͊̔̏̽͆ͧͥ̑͟͜͞ͅ();
    }

    private static void By1337̴̸̶̡̮͚͚̠̗͙̦̳̩̻̭̳̦̼͇̃̃̇͐̉ͭ̑ͬ̀͊̔̏̽͆ͧͥ̑͟͜͞ͅ() {
        array6[11][0] = "x3FOy2k7VLo=";
        array6[11][1] = "˗˚˝̌̍̋̊˚˔˜˜̊̋˔˛̈˘ˠ˔̉̋˘˘˔˚̊̈˞̉˜˞̉̍˝˞˙";
        array6[12][0] = "5x7ayDFMlAA=";
        array6[12][1] = "̋˚̈̌̊̌̉̍˔̋˘˝˘˔˛̍˜˗˔̈˘˝˚˔˙̍̊˝˘˟˚̊̉˞˙˛";
        By1337̤̙͕͚̰͉͓͙̹̱͎̖̠͊̓̅̍ͥ̾̌͋̄ͯ̈̌́͛̐ͮ̍̏̊ͪ̚̕͘̕͢͜͠();
    }

    private static void By1337̤̙͕͚̰͉͓͙̹̱͎̖̠͊̓̅̍ͥ̾̌͋̄ͯ̈̌́͛̐ͮ̍̏̊ͪ̚̕͘̕͢͜͠() {
        array6[13][0] = "a5mDGCdwMDU=";
        array6[13][1] = "̌˙˘˘˗̋ˠ̉˔̉˝˞˙˔˛˞˞˗˔˟˚̈̍˔˗̍̉˗˛˛˞̊˗˘˚̈";
    }


    private static void By1337̸̨̳̟̼̝̟͕̞̼͓̤̠͙̖̤̝̘̮̥͖̅͛̾̄́̓ͫͨͨ͊̆ͭ͘̚͘̕͜͡͠() {
        array7[0][0] = "/9C0+AdDsIM=";
        array7[0][1] = "ɨɪʘʛɪɨʘɮɢʗɩʘɧɢɩʖɫɦɢʗɭɦɮɢɮɪɧʗɫɭʘʘɧɧɬɬ";
        array7[1][0] = "I0SqeamS5eQ=";
        array7[1][1] = "ɩɨʘɦɨɥɪɧɢɪɭʗʘɢɩɧɬʘɢɭɩʗɮɢʙɮɥɩʙɭɩʛɨɩɫɫ";
        array7[2][0] = "byGBQqhLcwM=";
        array7[2][1] = "ɦɫʙɦʗʖʗɨɢɦɩɦɩɢɩɥɥʚɢɭɨɩɨɢɭɩɫɨʗɮɩɫɮʘʖɬ";
        By1337̴̷̧̧̨̢̧̛̛̯͍͕̣̯̱̝͍͔̼̪̫͚̣̙̔ͨ̓̔ͧ͋ͥͯ͑ͤ̽ͥ̂ͭ̕͞();
    }

    private static void By1337̴̷̧̧̨̢̧̛̛̯͍͕̣̯̱̝͍͔̼̪̫͚̣̙̔ͨ̓̔ͧ͋ͥͯ͑ͤ̽ͥ̂ͭ̕͞() {
        array7[3][0] = "WxEz6wPMtNs=";
        array7[3][1] = "ɦɦʛɦɩɩɦɭɢɧʗɪɨɢɩʙʗʗɢʗɮɪʗɢɧɪɧɨʖɭʛʘɪɪɦɮ";
        array7[4][0] = "0Rpw+OE0WQY=";
        array7[4][1] = "ɪɮɭʘɪɧʖʘɢɩɮɩɨɢɩɭɦɨɢʗʛɦʘɢʚɥɫɨʘʙɥɦɦɭɩʙ";
        By1337̵̦͔̠̤̗̘͕̟͓̯͕̯̦͈͚̣̭ͯ͗́̓̉̎͑͛͆͒̌̑ͯ̇̅̿ͮ̃ͫ͝͠͡();
    }

    private static void By1337̵̦͔̠̤̗̘͕̟͓̯͕̯̦͈͚̣̭ͯ͗́̓̉̎͑͛͆͒̌̑ͯ̇̅̿ͮ̃ͫ͝͠͡() {
        array7[5][0] = "nvlBr+fMWgU=";
        array7[5][1] = "ʗɪʖɫʙɥɧɬɢʚɮɮʖɢɩɮʖɭɢɭɮɪɮɢɭɪɦʗɥʚɨɦɥɨɫʙ";
        array7[6][0] = "4o9QqFgs/cg=";
        array7[6][1] = "ɩɫɧʗʗɭʗɪɢʚɦʚʘɢɩʚɫɩɢɭʖɬɫɢʖʛɥɧɬɬɫʛʖʙɫɮ";
        By1337̡̧̧̬̫̱͈͎͇̈́ͦͧ̌́ͨ̒͗ͦͧ̈̋̒̎̓͘͡͏̛̮͖̰̯̓̋ͪ̋̿̐̅̕();
    }

    private static void By1337̡̧̧̬̫̱͈͎͇̈́ͦͧ̌́ͨ̒͗ͦͧ̈̋̒̎̓͘͡͏̛̮͖̰̯̓̋ͪ̋̿̐̅̕() {
        array7[7][0] = "kc6V/IJ+UuY=";
        array7[7][1] = "ɭɩʘʘʗɩʘɫɢɥʘʘɬɢɩɥɬɮɢɭʘɥɧɢɥɮɬʗʗɩɦɮɨɨʘɩ";
        array7[8][0] = "Zq6LBJkSEnQ=";
        array7[8][1] = "ɮɨɥʘɮɫɩɭɢɭʛɫɧɢɩʚʛɬɢʗɪʖɩɢʘɫɭɫɪɭɧɦɮɩɩɩ";
        By1337̸̸̨̨̧̨̤͓̟̱̤̻̯͚͓͓͓͇̘̾͒̀̆̋͆ͩ͂̓̈́͑̈ͦ̾ͥ͂̌̚͟͢͡();
    }

    private static void By1337̸̸̨̨̧̨̤͓̟̱̤̻̯͚͓͓͓͇̘̾͒̀̆̋͆ͩ͂̓̈́͑̈ͦ̾ͥ͂̌̚͟͢͡() {
        array7[9][0] = "md3kfpKZ13g=";
        array7[9][1] = "ʖɭɪʗɩʛʖʖɢʘɨʗɭɢɩɦɨɬɢɭɮɥɦɢɩʚɩʘʖɩʙɮɬɧɪɭ";
        array7[10][0] = "CLfND+MEgAY=";
        array7[10][1] = "ʚɬɧɩʘʗɧɬɢɮɪɩɪɢɩɮɪʛɢʗɮɬɦɢɩɧʖʛɫʙʙʖʛɨɨʛ";
        By1337̴̲͙̲͇̩̹̞͚̩͇̣̮̪̗͖̲͉̱́͐͛̈́ͧ̆ͩ̊́̈͐̎̌̉̍ͬ͘͜͟͡ͅ();
    }

    private static void By1337̴̲͙̲͇̩̹̞͚̩͇̣̮̪̗͖̲͉̱́͐͛̈́ͧ̆ͩ̊́̈͐̎̌̉̍ͬ͘͜͟͡ͅ() {
        array7[11][0] = "H/uitStPyFs=";
        array7[11][1] = "ʛʙʘɨʛɫɦʙɢɥʖʘɬɢɩɥʚʖɢɮʚɮɭɢɦʛɪɭɪɨɭɬʖɪɨɥ";
    }


    private static void By1337̴̧̲̱̮̮̮̙̫͈͖͓̳͓̮̲̔ͦ̓ͦ̈́̎̏̇͛ͨͬ̀ͯ͂̇͋́ͬ̕͜͟͠͞͝() {
        array8[0][0] = "Bm/AO2VRVoQ=";
        array8[0][1] = "ʞˊˇʖˈʖʗˋʓʟˌʞˊʓʚʘˇʖʓˈˇʞˊʓˉˊˌʞʛʗˈʛˉˉʖˉ";
        array8[1][0] = "nUXB3CJ+FFY=";
        array8[1][1] = "ʞʚˈʗʝʝˉʗʓˊˈʖʛʓʚˊʚʖʓʟˌˋʘʓˈʗʝˈʝʛʗʛʝʞʘˇ";
        array8[2][0] = "o+PQ+j+wiCs=";
        array8[2][1] = "ʞʟʞˋˈʙʙˌʓˇʖʝʝʓʚʗˌʙʓʟʙʘˇʓʛʛʘʗʚʟʝʗʞˌʝʝ";
        By1337̷̸̶̵̧̧̨̡͙̟̖̜̖̥̩͔͇̳͈̩̑ͮ̇̉͊ͫͤ͗͗ͭ̈́́̑̕̚͜͜͝͡ͅ();
    }

    private static void By1337̷̸̶̵̧̧̨̡͙̟̖̜̖̥̩͔͇̳͈̩̑ͮ̇̉͊ͫͤ͗͗ͭ̈́́̑̕̚͜͜͝͡ͅ() {
        array8[3][0] = "yzhAU/Hm1Jg=";
        array8[3][1] = "ˉʙʚˉʚˉˇˉʓʝʜʛˋʓʚʘˇʖʓˈʘʛʝʓʟˋˊʖˊˈˇʝʖˊʖʘ";
        array8[4][0] = "SfWgyy6UGho=";
        array8[4][1] = "ˌʞʝʜʜˉʝʚʓˌʜʙˌʓʚʚˊʙʓˇˊʟˊʓʞʙʘʙʙʜʝˈˊˉʜˋ";
        By1337̴̸̨̡͓̘̗̩̬̙̬͔̫̬͙̰͉̤̜̺̟̘̫͊́̎̅̃ͬ̉͛́̒̾ͬͪͦͭ̕͘();
    }

    private static void By1337̴̸̨̡͓̘̗̩̬̙̬͔̫̬͙̰͉̤̜̺̟̘̫͊́̎̅̃ͬ̉͛́̒̾ͬͪͦͭ̕͘() {
    }


    private static void By1337̝̤̰̟̠̱͈̝͖̲ͮ̿͑ͮ́ͮ̄̏̅̒ͥ́ͧ̊̈́̌̊͏̘͓͔͍͌ͥ̄̆ͤͨ̽͞() {
        array9[0][0] = "jw+lPwlSG2I=";
        array9[0][1] = "ਫ਼ਲ਼ਯਸ਼੟ਵਫ਼ਲ਼ਪ੠ਭ੟ਫ਼ਪ਱ਸ਼ਸ਼ਭਪ੟਱ਲ਼ਰਪ੠ਮਮ਱ਵਲਸ਼ਲ਼ਯ਱੠਴";
        array9[1][0] = "43O8haKQLLo=";
        array9[1][1] = "੡਱ਲਫ਼ਫ਼੡ਵ਱ਪ਱ਰਵਲ਼ਪ਱ਲ਼੡ਮਪਸ਼ਲ਼ਮਲਪ਴ਸ਼ਰ਱਱ਮਫ਼ਲ੡੢ਮਯ";
        array9[2][0] = "mQf/H3NHoBw=";
        array9[2][1] = "਴੟ਸ਼ਸ਼ਸ਼ਲ਼ਸ਼ਯਪਲ਱਴ਸ਼ਪ਱ਲ਼ਲ਼ਫ਼ਪਫ਼੣੡੢ਪ੟੢ਫ਼ਰਰ੠ਸ਼ਲ਼ਯ੠ਲਰ";
        By1337̢̧̛̣͇͈͈͖̟̠͕̬̪͈̇ͧ͆̓̇̓̎̎ͪ́̐̚͡͞͏̧̯̯͑͗ͬͩ̌̊͡͏();
    }

    private static void By1337̢̧̛̣͇͈͈͖̟̠͕̬̪͈̇ͧ͆̓̇̓̎̎ͪ́̐̚͡͞͏̧̯̯͑͗ͬͩ̌̊͡͏() {
        array9[3][0] = "+F2awR6txXs=";
        array9[3][1] = "੟ਵਲ੡ਰ਱ਸ਼਱ਪਭਰ਴ਲ਼ਪ਱੟ਲ਼ਲ਼ਪਸ਼੢ਯਵਪ੣ਵਵਵਸ਼ਲ਼ਮਮਯਲਮ੡";
        array9[4][0] = "8xeTiR97x68=";
        array9[4][1] = "੡ਯ੟ਲਵ਱ਵਫ਼ਪ੢ਭਵਸ਼ਪ਱੟੣ਵਪਵਫ਼੠ਭਪਵਮ੢ਸ਼ਯਰਯਭ੠ਵ੣੟";
        By1337̸̷̷̶̛̳̞͚͎̺͇̙̤̙͚̞͎̜̭͈̤͈͒̑̄̐ͦͣ̍́́͛̔ͯ̊̏̋͟͢͞();
    }

    private static void By1337̸̷̷̶̛̳̞͚͎̺͇̙̤̙͚̞͎̜̭͈̤͈͒̑̄̐ͦͣ̍́́͛̔ͯ̊̏̋͟͢͞() {
        array9[5][0] = "iCCGe6Qx7u8=";
        array9[5][1] = "ਫ਼੡ਲ਼ਲ੡ਵ਴੢ਪ੢ਯ਱੣ਪ਱਱ਰਰਪਫ਼੠ਭਯਪ੢੣ਵਲ਼਴੣੡ਯਲਭਲਫ਼";
        array9[6][0] = "8zAIHAW0tHo=";
        array9[6][1] = "ਲਲ਼਴੢ਮਲ਼੠ਵਪਭਲ੡਴ਪ਱ਯਵਮਪਫ਼ਲ਼੣ਭਪਭ੟ਲ੢੣ਲ਱ਸ਼ਵਵ੣ਫ਼";
        By1337̴̶̛̝̹͕̟̱̲̙̩̟̘͔̟̤́̿͑̀̄̇́̅ͫͩͣ̽̈́̏̋͋ͭͮͨ̑͋͟͡͞();
    }

    private static void By1337̴̶̛̝̹͕̟̱̲̙̩̟̘͔̟̤́̿͑̀̄̇́̅ͫͩͣ̽̈́̏̋͋ͭͮͨ̑͋͟͡͞() {
        array9[7][0] = "Hn4Rda4th70=";
        array9[7][1] = "ਯ੢੢ਭਫ਼੠ਸ਼ਸ਼ਪ੡ਵ਴੟ਪ਱੢੢ਰਪਵਮਲ਱ਪਲਫ਼੟ਫ਼੟੢੟੢ਮਵਲਮ";
        array9[8][0] = "yVZpVUyQMFw=";
        array9[8][1] = "੠ਲ਼੠ਲਰ੠ਫ਼ਸ਼ਪਲਭਵ਱ਪ਱ਸ਼੡੟ਪਫ਼੢੣ਲ਼ਪ੠ਲ਼੣ਵਵ੠ਰਭਲ੠ਭ੟";
        By1337̶̸̨̡̡̭͎͈̦̺̳̘̣̹̮̣͕̹̘̠͚̣̇ͦͯ̋̉̀͌͆ͯ́̍ͮͧͧ̎̑ͪ͘();
    }

    private static void By1337̶̸̨̡̡̭͎͈̦̺̳̘̣̹̮̣͕̹̘̠͚̣̇ͦͯ̋̉̀͌͆ͯ́̍ͮͧͧ̎̑ͪ͘() {
        array9[9][0] = "002O2qHIHuw=";
        array9[9][1] = "਱੟ਵ੠੟਴੢ਸ਼ਪਲਮ੣੡ਪ਱਱ਸ਼੢ਪਫ਼ਵਯ਴ਪਰ਱ਫ਼਱ਫ਼੟ਲ਼੟੠਱਴੟";
        array9[10][0] = "KVu3qQp1coc=";
        array9[10][1] = "ਯਭਲ਼ਵਭਸ਼ਰ੠ਪ੡ਮ੡ਫ਼ਪ਱ਭ੠ਲ਼ਪਸ਼਴੣੢ਪਸ਼ਸ਼੣ਮਯਯਮ੣ਲ਼੠ਭਮ";
        By1337̷̢̢̙̪͕͎͇͔̩͕̪̯̬̱͚̣̘̠̱̂́̐̓ͥ́͊ͤ͒̐͐ͮ͂͞ͅͅ͏͙̼͑();
    }

    private static void By1337̷̢̢̙̪͕͎͇͔̩͕̪̯̬̱͚̣̘̠̱̂́̐̓ͥ́͊ͤ͒̐͐ͮ͂͞ͅͅ͏͙̼͑() {
        array9[11][0] = "80VXjLXLwqg=";
        array9[11][1] = "੣ਲ਼ਸ਼੡ਲ਼੣ਸ਼ਭਪ੠਱੟ਯਪ਱੠੠ਲਪ੟ਵਸ਼੣ਪਮਫ਼ਵ੠਴੠੣਱੢ਰਮਵ";
        array9[12][0] = "yC7KnZcbyxI=";
        array9[12][1] = "ਮਰ੠੣ਯ੡ਫ਼ਲਪਰਲ਼ਮਵਪ਱੡ਲ਼ਭਪ੟੠਱਴ਪਮ਱ਰਮਫ਼੡੡੡ਯਭ਴ਰ";
        By1337̢̧̤̼͈̫̤̙̼̗̩̟͔̹ͨ́̿̉̎ͫͯ̀̊̂͆̀͊̃̋ͧͭ̓̃ͦ̚͢͟͠͞͝();
    }

    private static void By1337̢̧̤̼͈̫̤̙̼̗̩̟͔̹ͨ́̿̉̎ͫͯ̀̊̂͆̀͊̃̋ͧͭ̓̃ͦ̚͢͟͠͞͝() {
        array9[13][0] = "vXaZoU8wffs=";
        array9[13][1] = "੡ਲ਼੟੢ਮ੢੣ਯਪਵਫ਼ਲ੡ਪ਱ਰਸ਼ਰਪ੟ਲਲ਼੢ਪ੣੟ਮਲਵ਱ਮਲ਼਴੢਱ਲ਼";
    }

    private static void By1337̡̪̯̘̱͍͕̪͎̤̯͙͔͎̰͖̼̀̋̎ͭ̿ͮ̔ͧ̅̄ͬͥ̀ͮ̏͒ͩͯ̽͠͞ͅͅ() {
        array10[0][0] = "QOHtkE/vbn8=";
        array10[0][1] = "ᅁᄌᄼᄒᄿᄽᄒᄿᄈᄋᄋᄋᄎᄈᄏᄎᄾᄓᄈᄓᄋᄎᅀᄈᄿᄋᄽᄐᅁᄓᄽᄍᄾᄔᄏᄎ";
        array10[1][0] = "IFDBX7r2f20=";
        array10[1][1] = "ᄓᄿᄐᄐᄋᄒᄍᄽᄈᄎᄐᄍᄒᄈᄏᄐᄽᄑᄈᄓᄎᅀᄼᄈᅀᄽᄍᄑᄏᄌᅁᄼᄒᄎᄒᄌ";
        array10[2][0] = "VM2UkW+NVb8=";
        array10[2][1] = "ᄾᅁᄽᄐᄿᄐᄏᄔᄈᄍᄏᄔᄒᄈᄏᄌᄎᄐᄈᄼᄒᄽᄔᄈᄾᄐᄒᄍᄎᄑᅀᄏᄿᄼᄔᄔ";
        By1337̸̴̷̶̢̡̞͙̥͇̗̠͚̖͇̫̠͔̙̤̔̉ͭ͐ͬ̈͆̓̀͋̃̈́͒͋̍̑̓͞͝ͅ();
    }

    private static void By1337̸̴̷̶̢̡̞͙̥͇̗̠͚̖͇̫̠͔̙̤̔̉ͭ͐ͬ̈͆̓̀͋̃̈́͒͋̍̑̓͞͝ͅ() {
        array10[3][0] = "ULiKgiCh4PY=";
        array10[3][1] = "ᄍᄿᄐᄿᄍᄒᄍᄿᄈᄏᄒᄽᄽᄈᄏᄏᄑᄍᄈᄔᄍᅀᄍᄈᄋᄓᄾᄍᄔᄐᄽᄔᅁᅀᄌᄔ";
        array10[4][0] = "aESrP+/vfzs=";
        array10[4][1] = "ᄔᄌᄑᄒᄿᄓᄾᅁᄈᄋᄑᄍᄔᄈᄏᄓᄐᄾᄈᄓᄾᄔᄋᄈᄐᅁᄋᄍᄽᄎᄼᄏᄒᄼᅀᄽ";
        By1337̵̵̧͉̺̺͚̲̲͈͐̾́̐ͥ̽͌͗ͩͪͫ̃ͦͨ͛͋ͪ̀̀̓̓̽́ͫ̆͘͘͜͜͠();
    }

    private static void By1337̵̵̧͉̺̺͚̲̲͈͐̾́̐ͥ̽͌͗ͩͪͫ̃ͦͨ͛͋ͪ̀̀̓̓̽́ͫ̆͘͘͜͜͠() {
        array10[5][0] = "42Bt/2XwjmU=";
        array10[5][1] = "ᅁᄔᄏᄿᄐᅀᄐᄍᄈᄍᄼᄍᄾᄈᄏᄒᅀᄾᄈᄔᄑᄿᄼᄈᄌᄐᄌᄍᄎᄒᅀᄎᅀᄿᄾᄋ";
        array10[6][0] = "ZHtSvzmgtpg=";
        array10[6][1] = "ᄾᄑᄽᄏᄽᄍᄌᄌᄈᄏᅁᅀᄍᄈᄏᄼᄔᄾᄈᄼᄔᄑᄍᄈᄽᄿᄒᄼᄒᄏᄒᄑᄽᄒᅁᄾ";
        By1337̸̵̷̧͕̺͙̜̺̟̙̗̟̱͙̻̭͕́̋̔͌͆ͦ͒͊̌̓̉ͬ̈́̀̂͐ͨ̍ͬ̉̓͂();
    }

    private static void By1337̸̵̷̧͕̺͙̜̺̟̙̗̟̱͙̻̭͕́̋̔͌͆ͦ͒͊̌̓̉ͬ̈́̀̂͐ͨ̍ͬ̉̓͂() {
        array10[7][0] = "jLp3GKBkGTQ=";
        array10[7][1] = "ᄏᄿᄾᅀᄑᄌᄾᄋᄈᄑᄐᄔᄾᄈᄏᄾᄼᄓᄈᄓᄑᄏᄼᄈᄎᅀᄔᄑᄿᄑᅀᄋᄼᄒᄍᄒ";
        array10[8][0] = "J0Ux0cbr7no=";
        array10[8][1] = "ᄓᄑᄏᄒᄾᄍᄍᄋᄈᄒᄽᄋᄏᄈᄏᄿᄔᄾᄈᄔᅀᄽᄍᄈᅀᄔᄎᄑᄌᄐᄌᄌᄍᄐᄍᄿ";
        By1337͚̫̘̔ͭ̑ͭ̿ͭ́͛̈͏̷̧͎̤͎͉̯͕̹͓̹̔̋ͦͮ̊̀́͋̃͏̙̫͗̽̄̎();
    }

    private static void By1337͚̫̘̔ͭ̑ͭ̿ͭ́͛̈͏̷̧͎̤͎͉̯͕̹͓̹̔̋ͦͮ̊̀́͋̃͏̙̫͗̽̄̎() {
    }

    private static void By1337̵̨̡̛͖̰͉̰͖̰͍̯̼̼̞̣̬̿͒̀͌͊͒͋̃́̿̑ͥ̑̈́̔̌͂̈́ͬ̕͢͢͢() {
        array11[0][0] = "07VYPz803l4=";
        array11[0][1] = "ቦቪኖቡኖኖናቩ቞ቡኖባብ቞ብኗኗቨ቞ኒቧቦቢ቞ኖኗኔኗናቢናናቧቪቦብ";
        array11[1][0] = "C7Il0S015NM=";
        array11[1][1] = "ኖብቡኖቦናኗቪ቞ቩኒኔኖ቞ብኔኒቡ቞ቪኒናባ቞ቤቧቧቦቧቨቩናቦቧቡብ";
        array11[2][0] = "SKvHz6XWfyM=";
        array11[2][1] = "ብቪቦኖኔኔኗቡ቞ቪቪንቢ቞ብኔናቧ቞ናብባና቞ኗናቦኒቩኔብባቦቦኖና";
        By1337̴̷̵̶̨̢̜̠̗̗͖̤̤̫̰͚̠̭̭̿̾̀̀ͮͭ̔͑͂̏͗̀̌ͤͭ̑ͣ͟͡͝͠();
    }

    private static void By1337̴̷̵̶̨̢̜̠̗̗͖̤̤̫̰͚̠̭̭̿̾̀̀ͮͭ̔͑͂̏͗̀̌ͤͭ̑ͣ͟͡͝͠() {
        array11[3][0] = "vJx9ItpF++M=";
        array11[3][1] = "ባኖቤኔቨናባቡ቞ቧኔቦቪ቞ብኔንኔ቞ቪቦቢቪ቞ቢብቦንቨቨቡቪንኖቢባ";
        array11[4][0] = "c8KbnibLCz4=";
        array11[4][1] = "ቦቦቩቨኔቪቤብ቞ቤቢቡባ቞ብኒቦን቞ናንቨቪ቞ቤቢቤናቦቤብቪቤቡቤቤ";
        By1337̵̸̢̘͎̟̞̲̫̫̠̙̹̬̗ͧ̈́ͬ͆̄́ͯ͊ͣ͒͌̎̀̍̓̿̋̀̊̏̑̐͜͡͠();
    }

    private static void By1337̵̸̢̘͎̟̞̲̫̫̠̙̹̬̗ͧ̈́ͬ͆̄́ͯ͊ͣ͒͌̎̀̍̓̿̋̀̊̏̑̐͜͡͠() {
        array11[5][0] = "G+yYq13uRVo=";
        array11[5][1] = "ኗኒቪንቪቧቨን቞ቨንቡቢ቞ብኖቦኒ቞ቩቡቢኔ቞ኒቪቡቩቡኔቧቧባቨቩቩ";
        array11[6][0] = "xuOhAQyfis8=";
        array11[6][1] = "ቪቦቢቨኗብቦቤ቞ቡኖንን቞ብቤብቧ቞ቪቤቦቩ቞ቩንኗናብኖንቧቦቪቩኗ";
        By1337̴̸̸̷̵̧̧̨͈̝̫̪̳̗͇͉͔͔͍̎́̐ͤ̉͌ͦ̽͛̊͐̌̾ͧͮͪ̅͂͘͢͞();
    }

    private static void By1337̴̸̸̷̵̧̧̨͈̝̫̪̳̗͇͉͔͔͍̎́̐ͤ̉͌ͦ̽͛̊͐̌̾ͧͮͪ̅͂͘͢͞() {
        array11[7][0] = "eqiRHDejeT0=";
        array11[7][1] = "ባኒቪኗኔኗኗቧ቞ቤኒቢቦ቞ብቡንቪ቞ናንቧቨ቞ቩንኒንቧባቤኖኗቧኒቧ";
        array11[8][0] = "x/bSt8Rgd3I=";
        array11[8][1] = "ናቤኒቢቨናኗቪ቞ናንቦኖ቞ብኗኗባ቞ናቦንን቞ቧቧቡኖኖኗቩቤቪቦናቪ";
        By1337̵̢̱͇̙͇̰̫͓͔͙͉͙̯͙͎́̏ͨ͂̉̈ͤͭ͊̂̀̀̈́ͧ̉̆̈ͪ͊ͦ͌̍̚͞();
    }

    private static void By1337̵̢̱͇̙͇̰̫͓͔͙͉͙̯͙͎́̏ͨ͂̉̈ͤͭ͊̂̀̀̈́ͧ̉̆̈ͪ͊ͦ͌̍̚͞() {
        array11[9][0] = "DGpUx9+rVAk=";
        array11[9][1] = "ኖቨባቨቩኔቦኖ቞ባቨኒን቞ብቨቧቩ቞ኒቧቧኔ቞ቩኔቪኔኗብባቡቧቦቩቤ";
        array11[10][0] = "bHQAtaAm+2Q=";
        array11[10][1] = "ብቧቢንቤቧቦኗ቞ቧኖቧኒ቞ብቪብና቞ቪቧቪቩ቞ቤቤኒብኒብናቪቨቡቢን";
        By1337̸̶̳̻͔̫̖̳̃́̒ͭ̐ͥͫͫ́͗́ͧ͊ͨͮ̈́̋ͬͮ̐̌̽͞͡͏͔͍̤ͨ̇̋̄();
    }

    private static void By1337̸̶̳̻͔̫̖̳̃́̒ͭ̐ͥͫͫ́͗́ͧ͊ͨͮ̈́̋ͬͮ̐̌̽͞͡͏͔͍̤ͨ̇̋̄() {
        array11[11][0] = "kXXcwbceWNQ=";
        array11[11][1] = "ናባብቡንቡብኗ቞ቪቨቦኖ቞ብቪንቡ቞ቪኒቪኒ቞ብንቤኗኗናቢናቧቤባባ";
        array11[12][0] = "noW5fpwn7Ac=";
        array11[12][1] = "ብቦኔብቤቡቤቪ቞ቪቢኖቪ቞ብቢቪቦ቞ኒኔቧቢ቞ቨንኖቤቨቩቪኒቡኔቪባ";
        By1337̷̼̼̪̳̰̼̯̤̯̜͚̮͌͛ͬ͑̍̋̉̋̑͌̅̄̓̋͒̀́̎̇͊̌̕͜͢͢͟͞();
    }

    private static void By1337̷̼̼̪̳̰̼̯̤̯̜͚̮͌͛ͬ͑̍̋̉̋̑͌̅̄̓̋͒̀́̎̇͊̌̕͜͢͢͟͞() {
        array11[13][0] = "0F0sojyyO5U=";
        array11[13][1] = "ኔቦቨባናኖኗቨ቞ቩናቤቤ቞ብንብብ቞ናቡናን቞ኔኖኒኒንቪቤኖብኔቪባ";
        array11[14][0] = "8YeEkHyvIDU=";
        array11[14][1] = "ኒብንቤቢብቦባ቞ቤቨብኖ቞ብኖቧባ቞ቪቨናብ቞ቪኗንንቧኖቩንኖባቪኒ";
        By1337̵̴̙̣͉͖̠̯̯͈͙̟̏̂ͯͫ͆̊̌́́ͮ͌ͤͯ̉ͫ̑̇ͥͯ̏͛ͦͤ̆́̈́͞ͅ();
    }

    private static void By1337̵̴̙̣͉͖̠̯̯͈͙̟̏̂ͯͫ͆̊̌́́ͮ͌ͤͯ̉ͫ̑̇ͥͯ̏͛ͦͤ̆́̈́͞ͅ() {
        array11[15][0] = "XRsrodqaQcE=";
        array11[15][1] = "ኔቢቩኔቨቦቧኔ቞ብናቩቩ቞ብኖባብ቞ኒኗናቧ቞ናኔንቦኔቡቪቢቡቤብቢ";
        array11[16][0] = "Py6YPTb4BFE=";
        array11[16][1] = "ብቪኔቪቢቢባኒ቞ቤቤኔና቞ብቤቨኖ቞ናቢቩኖ቞ቤቧኒቪቧቩባኔቩቢቩና";
        By1337̸̵̶̨̻̣͙̼̭̩̥̜̥̯̱͂̓͒̂̀͑̑͂ͤ͛̔͑̎ͩ̌ͮͤ̋ͣ̓̕͢͜͡͠();
    }

    private static void By1337̸̵̶̨̻̣͙̼̭̩̥̜̥̯̱͂̓͒̂̀͑̑͂ͤ͛̔͑̎ͩ̌ͮͤ̋ͣ̓̕͢͜͡͠() {
        array11[17][0] = "E2yT0NqSD9M=";
        array11[17][1] = "ኗኔኒኖኔቡብን቞ብቨኖኗ቞ብኔቢቪ቞ኒናኗቡ቞ኔብቡቩቦኗቤቪኖንቤቤ";
        array11[18][0] = "Sjffh4TJUu4=";
        array11[18][1] = "ኗቤኖኖቧኗኔቤ቞ኗኔኔቦ቞ብኗኗን቞ኒኔቢቧ቞ቪቪናንቤቧቢብብናቩብ";
        By1337̶̢̢̢̝̲̻͖͕̯͈̯̳̮̦̙̻̺̜̜͙͙̗̋̀̾ͫ̏ͯ̃ͭ̾̌́̔̕͟͢͜͠();
    }

    private static void By1337̶̢̢̢̝̲̻͖͕̯͈̯̳̮̦̙̻̺̜̜͙͙̗̋̀̾ͫ̏ͯ̃ͭ̾̌́̔̕͟͢͜͠() {
        array11[19][0] = "F7LYuAkq/rM=";
        array11[19][1] = "ናብኖቦቦቤቩባ቞ኒኗቢቧ቞ብቦናቡ቞ናብናና቞ቦኒቩኒቤቦቦኔባቤቨቪ";
        array11[20][0] = "F7xe72Behbw=";
        array11[20][1] = "ባቡባኖኗቡቦቨ቞ቨቦቨና቞ብቩቦን቞ቪቪቤኖ቞ቧባቪቤቢኖኔብቨኔቢን";
        By1337̸̶̸̗̥̭͖͖͈̟̠̥̬̳̖̪͉̠͖̬̝ͦ̍ͬ̆ͦ̓ͧ͗̓̓̄ͤ͘͘͢͢͜͠͡();
    }

    private static void By1337̸̶̸̗̥̭͖͖͈̟̠̥̬̳̖̪͉̠͖̬̝ͦ̍ͬ̆ͦ̓ͧ͗̓̓̄ͤ͘͘͢͢͜͠͡() {
        array11[21][0] = "KV7v9oaEA5k=";
        array11[21][1] = "ቪቨቪቪቪቡኖቧ቞ባቨቧቡ቞ብቪቦን቞ቪባንኗ቞ቩቡናኔኔኖቨባቤቢኒኔ";
        array11[22][0] = "ClL9QknAfGo=";
        array11[22][1] = "ቦኗኔኗንባቪቧ቞ንቤንቢ቞ብኖናቨ቞ቩብቨቩ቞ቪኔቪናናቢኖቡቦቤቤን";
        By1337̐͐̂͏̪̥̺̜͔ͬ̀͌̚͏̶̷̶̮͈͈͈͓̗̦̩̘̾ͨ͆̏̑ͫͣ͗̀́̽͘͘͡();
    }

    private static void By1337̐͐̂͏̪̥̺̜͔ͬ̀͌̚͏̶̷̶̮͈͈͈͓̗̦̩̘̾ͨ͆̏̑ͫͣ͗̀́̽͘͘͡() {
        array11[23][0] = "vhqDgBKOdrc=";
        array11[23][1] = "ቢባቤኗንቤቢኗ቞ኗቩቧቪ቞ብቧቢቨ቞ናቢብኖ቞ናኖባባብቩቪቧንቪብቧ";
        array11[24][0] = "+D69yGsLUS0=";
        array11[24][1] = "ኗቩኖቩኖናቩና቞ናቩኖና቞ብናባቪ቞ቩብቡቤ቞ቩቩኒቪቨቪቡባኒቤባኖ";
        By1337̟̗̮̺̖̰͉̳͎̥̩̦͔̄͆͊̽̌̃ͧ̒ͯ̀͂̓̀ͣ͑ͧͯ̾̕͘͢͜͜͜͝ͅͅ();
    }

    private static void By1337̟̗̮̺̖̰͉̳͎̥̩̦͔̄͆͊̽̌̃ͧ̒ͯ̀͂̓̀ͣ͑ͧͯ̾̕͘͢͜͜͜͝ͅͅ() {
        array11[25][0] = "XcScpnrroEI=";
        array11[25][1] = "ኖቦብኖቤቩባቪ቞ቧኒንባ቞ብኗቤባ቞ናኗቪኖ቞ቦቢኔኗቡኔቦኖቡቨቧኖ";
        array11[26][0] = "jKOfKYfABQI=";
        array11[26][1] = "ቦኒቧቡብቢባኒ቞ቡቨቦቡ቞ብብኗቢ቞ናባቩቢ቞ቨንቡቤኔቤቦቧቨናባኔ";
        By1337̵̸̨̡̢͚͇͓̜̤̱̼͍͙͉̭̩͍̼̲̆̓͛̽ͬͩ̿̏ͧ̅͛ͯ͌ͣ͜͟͞͠͡ͅ();
    }

    private static void By1337̵̸̨̡̢͚͇͓̜̤̱̼͍͙͉̭̩͍̼̲̆̓͛̽ͬͩ̿̏ͧ̅͛ͯ͌ͣ͜͟͞͠͡ͅ() {
        array11[27][0] = "Nd2yBsQ7WTw=";
        array11[27][1] = "ኗቨብቧቧቡናቦ቞ቡቪናቡ቞ብኗናኒ቞ቪናቤኗ቞ኖቪቪቡቪኒብቨቦቡኗቡ";
        array11[28][0] = "I5u0A7WSV9U=";
        array11[28][1] = "ኖቦቪቤኖብንኔ቞ቨባቩቡ቞ብቪቪኖ቞ናባናቦ቞ኖኔኒንቪኖባቧኒናብኔ";
        By1337̶̷̷̰̝͕̦̤̳̦̘̌̄ͦ͂̏͂͛ͬ̆̄͟͝͡͏̵̤͍͇̦̖͋̈́ͮ̽͐͋̏́͜();
    }

    private static void By1337̶̷̷̰̝͕̦̤̳̦̘̌̄ͦ͂̏͂͛ͬ̆̄͟͝͡͏̵̤͍͇̦̖͋̈́ͮ̽͐͋̏́͜() {
        array11[29][0] = "KutP8m8KMA0=";
        array11[29][1] = "ቤኒኒናብቪቦቩ቞ቨቡኖኖ቞ብኔቨቪ቞ቪኗቩቪ቞ቧቩባኒቢናቩቪቩኔቨቧ";
        array11[30][0] = "JpRBkgJ6vuI=";
        array11[30][1] = "ንብቩኖቨኒብና቞ኔቤባን቞ብቦኗቪ቞ቩኔኒና቞ኖቪቦኔቦቢቩቡቦኔቤኗ";
        By1337̸̵̧̧̠̥̝͕̹̝͔̦͇̤̿ͧ̌̾͆ͧ̉̋̀͆͋͊́ͥ̈ͯ͐̌͐̓͘̚͢͝͞͝();
    }

    private static void By1337̸̵̧̧̠̥̝͕̹̝͔̦͇̤̿ͧ̌̾͆ͧ̉̋̀͆͋͊́ͥ̈ͯ͐̌͐̓͘̚͢͝͞͝() {
        array11[31][0] = "JVWQ1Dm085U=";
        array11[31][1] = "ቢንቡኗብቨንኒ቞ብቦቪቢ቞ብቡቩቦ቞ቩናቤን቞ቩብቪቪናባኗኔቪቤኗኒ";
        array11[32][0] = "eh3Uu9RH8Ag=";
        array11[32][1] = "ቡቧኖናብቦቢቦ቞ባቧኒቦ቞ብቢቢኒ቞ኒቪባቪ቞ቩቩቡናቧቩቢንኗቡኖኗ";
        By1337̷̷̢̛͖̪̳̖̺̪͇͍̯͌̑ͦͬ̾ͨ̊̽̉̊́ͨ̌͟͏̲̯͕̞̼͂̎͛͛̃̀͘();
    }

    private static void By1337̷̷̢̛͖̪̳̖̺̪͇͍̯͌̑ͦͬ̾ͨ̊̽̉̊́ͨ̌͟͏̲̯͕̞̼͂̎͛͛̃̀͘() {
        array11[33][0] = "J7rD3FvH1fI=";
        array11[33][1] = "ባቢባቨቩብኖባ቞ኔቡቤቩ቞ብቢኒብ቞ኒቪቪብ቞ኖቢኒቤቡቤንኔቢኒቪቪ";
    }

    private static void By1337̸̵͔̙̥̳̥͈͇̬͚̹̦̥̰̳̟ͫ̓́͌̉́͆͂̇ͩͪͩͤ̈́ͣ͆̈́͑ͨͮ̚͟͢() {
        array12[0][0] = "lDyI3oIVLrw=";
        array12[0][1] = "ૹ્૊ૼૻૼ૑૏ૅ૽ૉૈૼૅૌ૑ૉૼૅૹ૎ૌોૅો૽ૹ૊ૐૉૐ૽૏ૺ૊૑";
        array12[1][0] = "wrEN14rpWao=";
        array12[1][1] = "૏૽ૺ૑ૈ૏્ૈૅૐ૊ૹૉૅૌ૊ૌ૏ૅૐૈો૏ૅૻ૑ૻ૑્ૼ૽ૻૹ૏ૺૼ";
        array12[2][0] = "LzJwjVe2pZo=";
        array12[2][1] = "ૻ૽૎ૈૉો૊૊ૅૻૐૐ્ૅૌ૽ૌ૾ૅૐૹૹ૏ૅૈ૊ૹૐ૎ૌૌૻ૽ૻૼ૾";
        By1337̴̴̢̡͖͉̖͙̭̭̤̺̼̱͉͇̮̱͖͉ͦͥ̿̾͑̍͋̏͑̇ͣ̍ͮ͗͒̌ͥ̎̏̕();
    }

    private static void By1337̴̴̢̡͖͉̖͙̭̭̤̺̼̱͉͇̮̱͖͉ͦͥ̿̾͑̍͋̏͑̇ͣ̍ͮ͗͒̌ͥ̎̏̕() {
        array12[3][0] = "A8W5bugTJgY=";
        array12[3][1] = "ૺૼ૊ૼૉૼ૑૑ૅ૑૾૊૽ૅૌૉ૎૑ૅ૑ૐ૽૏ૅ્૊ૐ૽ૻ૊૑૑ૌૺૈ૊";
        array12[4][0] = "yKGDFczJ+J0=";
        array12[4][1] = "્ૹૻૺ૽ૐ૏ૺૅ૑૑્ૈૅૌૉૺ૏ૅૐ૑ૌૈૅૺૺ૾૏ૻૼ૾૑૊ૻૉૹ";
        By1337̶̡̨̖͖̫͈̝̟͚̯̼̩̻ͪ̓͐̆̓̍̍ͨ̀̀́̿ͮ̀̀̈̈́̑͒̕̚͟͡͝͡ͅ();
    }

    private static void By1337̶̡̨̖͖̫͈̝̟͚̯̼̩̻ͪ̓͐̆̓̍̍ͨ̀̀́̿ͮ̀̀̈̈́̑͒̕̚͟͡͝͡ͅ() {
        array12[5][0] = "NGniHejK3AU=";
        array12[5][1] = "૎ૈ૾ૼૺૐ્૊ૅ૽૾૽ૺૅૌ૽૎ૐૅ૑ૺૌ૎ૅૻૐ૎ો૽૏૊ૼો૏૾ૼ";
        array12[6][0] = "ckgHliQV2No=";
        array12[6][1] = "૊૑ૐૈ૏ૉ૾ૐૅૼૈ૏૑ૅૌૼ૏૎ૅૐૻૌૈૅૺૉ૾૽૑૊૾૏ૌોોૻ";
        By1337̨͇̞̭͇̟̼̪̖̪̦̹̰̰͉͓́ͯ͒́̕͏̯̩̺͈͉̺͇̺̒̅́͏̛̪ͭ̀͆̓();
    }

    private static void By1337̨͇̞̭͇̟̼̪̖̪̦̹̰̰͉͓́ͯ͒́̕͏̯̩̺͈͉̺͇̺̒̅́͏̛̪ͭ̀͆̓() {
        array12[7][0] = "ACAZ+Aplg80=";
        array12[7][1] = "૽૑૊૏ૻૈૻ૑ૅ૾૽ૌૹૅૌૼૈ૑ૅૹ૽૽્ૅ૽૊૽૾ૻ૊ો૊ૼૻો્";
        array12[8][0] = "jDQWcsYRMnU=";
        array12[8][1] = "૑૏૽ૻ૑૎૽ૹૅૈૼૐ૏ૅૌૺૹ૑ૅૐ૊ૼૐૅ૑૽૾ૹૌ૽ૌ૏ૹૉૺ૑";
        By1337̵̵̢̢̢͍̮̠̼͕͎̼̝̥͓͍̣̳̗͙̥̀̐̓͒̇ͭ̏̎́̒ͩͧͩ̂̃̈́͑̚ͅ();
    }

    private static void By1337̵̵̢̢̢͍̮̠̼͕͎̼̝̥͓͍̣̳̗͙̥̀̐̓͒̇ͭ̏̎́̒ͩͧͩ̂̃̈́͑̚ͅ() {
        array12[9][0] = "HwugoiZTkXc=";
        array12[9][1] = "ૈો૾૽૑૑ૼૻૅ૑ૻૻ૽ૅૌૌ૎ોૅૹ૑ૉૉૅ૎ૺૉ્ૹ૽્૊્ૻૹૺ";
        array12[10][0] = "thPuN6dSr5E=";
        array12[10][1] = "૊ૺ૽ૐ૏૽૾ૌૅૐૌ૊૎ૅૌ૾૾ૐૅ૑૎ોૐૅ્્ૺૉૼ૊૑્ૉ૊૊ૹ";
        By1337̸̶̸̡̨̧̻̩͙͕͙̰͖͚͇̰ͥ̒̉ͤͩ̆͋̈́̄͆͒ͯͤͩ͂́͒͘̕͟͞͞͞͠();
    }

    private static void By1337̸̶̸̡̨̧̻̩͙͕͙̰͖͚͇̰ͥ̒̉ͤͩ̆͋̈́̄͆͒ͯͤͩ͂́͒͘̕͟͞͞͞͠() {
        array12[11][0] = "gNLHhWwrGrM=";
        array12[11][1] = "ૐૺ૾ો્્ૈૐૅ૽ૺ૾૊ૅૌ૏૎ૻૅૹ૎૊ૉૅૺ૊ો૑ૐૌૐૼૺ૑ૺૹ";
        array12[12][0] = "OgNKBYYcUZM=";
        array12[12][1] = "ો૑ૈો૎ૺ૎્ૅૈ૾૾૏ૅૌૹૉ૏ૅ૑૎ૌૻૅૹૉ૎ૹો૏ોૹૌ૎ૻૼ";
        By1337̷̢̧͕͚̜͎̩̤̬͈̻ͬ̊̈̍͊͌̋ͣ̎̉ͯͥͨ͗̔́͌͂̊̃ͯͨͨ͘̕͝͝ͅ();
    }

    private static void By1337̷̢̧͕͚̜͎̩̤̬͈̻ͬ̊̈̍͊͌̋ͣ̎̉ͯͥͨ͗̔́͌͂̊̃ͯͨͨ͘̕͝͝ͅ() {
        array12[13][0] = "ymUUMTMFkE0=";
        array12[13][1] = "૾ૺૻ૎૊૏ૺૹૅ૏૑૾૑ૅૌ૏ૌૹૅ૑ૌ૊ૻૅૌ્ૌૹ૊ૹૈૻ૏ોૐ૊";
        array12[14][0] = "Jo28Xfh1O+I=";
        array12[14][1] = "૏૊ૌૺૺૼ૏ૐૅૐ્ૈ૏ૅૌૺૐૉૅૹૈ્ોૅ૎ૉો૎૊ૐ૑૑ૹૺ૽૏";
        By1337̵̵̷̡̬̖͖̹̪̙͔̞̟̳͚̠ͧ̍͌͂̐ͮ̈͑̉̋̾̔͌̏́̆̃̕͢͡͡͝͝͡();
    }

    private static void By1337̵̵̷̡̬̖͖̹̪̙͔̞̟̳͚̠ͧ̍͌͂̐ͮ̈͑̉̋̾̔͌̏́̆̃̕͢͡͡͝͝͡() {
        array12[15][0] = "yI5g0018Haw=";
        array12[15][1] = "ૻ૾ૉૼૼ૊૊ોૅૉૈૹોૅૌૺૺ૊ૅૹૉ૎ૌૅો્ૺૹૈ૏્્ૌ૑૽૏";
        array12[16][0] = "11gTeLyrDr8=";
        array12[16][1] = "ૌ૽ૼૹ૑ૻૼોૅૻૹો૊ૅૌ૑૊ૺૅૺૉૉ૊ૅ૑૑૊ોૼૹ૾૾૾ો૾૑";
        By1337̸̡̡̧̛͎̘͓̥̦̱̞͙͖͉̦̳͙͙͕̤̺͓̿́̊̀̌̂̈́͋͐̄̈́̆ͧ͛̀͘͢();
    }

    private static void By1337̸̡̡̧̛͎̘͓̥̦̱̞͙͖͉̦̳͙͙͕̤̺͓̿́̊̀̌̂̈́͋͐̄̈́̆ͧ͛̀͘͢() {
        array12[17][0] = "FRizM3LMllU=";
        array12[17][1] = "્૏ૐૺ૑૽ૐ૊ૅૐ૊૑ૌૅૌૼ૊૾ૅૺૼ૎૏ૅૹ૑૽ૼ૾૊૎ૉૹૉૺ૎";
        array12[18][0] = "L8bkXNUXO9s=";
        array12[18][1] = "૑ૌૻૐ૾ૻૹૼૅ૑ોો૽ૅૌ૑ૺૼૅૺૌૹૺૅો્૽૎ૻ૊ૺો૾૑૎ૉ";
        By1337̷̸̸̨̢͍͚͎̦̱̯͇͉̦ͥ́̔̿̈ͪ̌͆ͤͪ̔ͥ̊͌̿̽̔̑̓́̿̇͟͠͡͠();
    }

    private static void By1337̷̸̸̨̢͍͚͎̦̱̯͇͉̦ͥ́̔̿̈ͪ̌͆ͤͪ̔ͥ̊͌̿̽̔̑̓́̿̇͟͠͡͠() {
        array12[19][0] = "W4gV9QjIvHM=";
        array12[19][1] = "ૺો્૊૎ૹ૏ૐૅૹૉૼ૽ૅૌ૽૎ૼૅૐ૎ૻૻૅ૏૊ૼૻ૎ૹ્૽૊્૊ૻ";
        array12[20][0] = "A8T1KvcKRzw=";
        array12[20][1] = "૊ૌ૊૎ૼો૾૊ૅ૾૏ો૑ૅૌૈૐૐૅૐ૽૾ૐૅ૏ૐ૽ૹૺૺૺ્ૼૌ૾ૻ";
        By1337̢̨̹̮̠̥͖́ͧͣ͛ͫ̀̒͑̇̑͋̈́͆͐͘͝͞͏̛̱̞͓̳́͛͌ͪ́͂ͣ͏̿̉();
    }

    private static void By1337̢̨̹̮̠̥͖́ͧͣ͛ͫ̀̒͑̇̑͋̈́͆͐͘͝͞͏̛̱̞͓̳́͛͌ͪ́͂ͣ͏̿̉() {
    }

    private static void By1337̵̡̛̤̥̥̬̠͕̪̯̥̤̞͇ͦ̽͗̑̑ͬ̄͋ͤ̆͗̿̾ͭ̎ͫ̈́̔ͥ̕͝͡ͅͅ͏() {
        array13[0][0] = "CGqRd1LKDw0=";
        array13[0][1] = "ᄪᅖᅗᅖᅓᄨᅓᄥᄞᄪᄦᄡᄨᄞᄥᄦᅔᄢᄞᄪᅗᅒᄧᄞᄢᅓᄤᄩᅓᅖᄥᄧᅖᄢᅓᅗ";
        array13[1][0] = "AbzfWYmLpE0=";
        array13[1][1] = "ᄩᄧᅕᄡᅖᄩᄦᄥᄞᄣᅓᄤᅖᄞᄥᅗᄧᅗᄞᅓᄥᄩᄣᄞᄣᅖᅖᄧᄥᄦᅔᅖᄢᄦᅕᄩ";
        array13[2][0] = "+QPJz+h+4/A=";
        array13[2][1] = "ᄡᄣᄡᅒᄤᄡᄧᄤᄞᅗᅕᅓᄢᄞᄥᄦᄥᄡᄞᄩᅒᄢᅒᄞᄡᄩᅒᅒᄥᄨᅗᄣᄢᅗᄣᄪ";
        By1337̴͔̦̪̘̯̰͓̜̪̖̝̲̭͎͆̌͛ͯ̃̓͌̂̉̓̍̊̄͌̑̉̍ͨ̋ͭ̚͟͟͞ͅ();
    }

    private static void By1337̴͔̦̪̘̯̰͓̜̪̖̝̲̭͎͆̌͛ͯ̃̓͌̂̉̓̍̊̄͌̑̉̍ͨ̋ͭ̚͟͟͞ͅ() {
        array13[3][0] = "A8xGuxAg4Og=";
        array13[3][1] = "ᅖᄣᄡᅖᄢᄢᄩᄪᄞᄦᄧᄧᅓᄞᄥᄪᄣᄧᄞᄩᄦᅕᅗᄞᄧᅕᄢᅗᅓᄧᅓᄤᄨᄡᄣᄪ";
    }

    private static void By1337̸̴̧̛̬͉̼̙̦͔̻̼͍̳̲̀̽́ͯ̇ͫ̃̂͛̅̌ͯ̂͑̏̐͊̂͗̐͟͡͡ͅͅ() {
        array14[0][0] = "U18TFKuZeHU=";
        array14[0][1] = "ࣻࣇࣉࣻࣅࣅࣸࣆࣂࣸࣇࣹࣆࣂࣉࣹࣈ࣍ࣂࣷࣅࣈ࣎ࣂࣸࣈࣶࣸ࣋ࣻࣻࣈ࣎࣊࣍ࣸ";
        array14[1][0] = "mckidh33K+Q=";
        array14[1][1] = "ࣹࣶࣻࣻ࣊ࣻࣻࣆࣂࣹࣶࣷ࣍ࣂࣉࣺࣉࣻࣂࣷࣉࣇࣷࣂ࣋ࣷࣅࣻࣷࣉࣶࣉ࣌࣍ࣅࣅ";
        array14[2][0] = "yFsmDXkENec=";
        array14[2][1] = "ࣺࣉࣅࣻࣸ࣍࣊ࣷࣂࣶࣷ࣌ࣻࣂࣉ࣎ࣷ࣌ࣂ࣎࣋ࣅࣻࣂࣶ࣌ࣸࣇࣉࣺ࣎࣌ࣷࣷ࣌ࣈ";
        By1337̸̷̸̡͇̲̩͓̻̠̞̘̼̜̮̠̣͉͕̥͎̽̀ͥͫ̇̃͌͒ͥ́̈́ͨ́ͩ͂ͩ̇̚͞();
    }

    private static void By1337̸̷̸̡͇̲̩͓̻̠̞̘̼̜̮̠̣͉͕̥͎̽̀ͥͫ̇̃͌͒ͥ́̈́ͨ́ͩ͂ͩ̇̚͞() {
        array14[3][0] = "PddEJzLbflA=";
        array14[3][1] = "ࣶࣶࣻ࣎ࣻࣷࣈࣻࣂ࣋࣎ࣆࣸࣂࣉࣺࣉࣈࣂ࣍ࣅࣹࣷࣂࣈ࣋࣌࣊ࣆࣺ࣌ࣅࣶ࣊ࣸࣷ";
        array14[4][0] = "DuWQ6zdKBf0=";
        array14[4][1] = "ࣺࣻࣆ࣊࣎ࣉࣸ࣋ࣂࣺࣆࣶࣻࣂࣉࣉࣇࣉࣂࣹ࣍ࣅࣅࣂࣺ࣋ࣻ࣌ࣅࣈࣇࣉ࣌ࣸࣉࣸ";
        By1337̨̜̭̻͖̺̠͙͓͔͈͗͆ͭ̌ͪ̀̂͌̆͗̃̿͊̿͆͐͏̸͚̹̤̪̒̒͂̌̎̔ͅ();
    }

    private static void By1337̨̜̭̻͖̺̠͙͓͔͈͗͆ͭ̌ͪ̀̂͌̆͗̃̿͊̿͆͐͏̸͚̹̤̪̒̒͂̌̎̔ͅ() {
        array14[5][0] = "Y9QvvtwpP6M=";
        array14[5][1] = "ࣶ࣍࣎࣍࣌ࣻ࣌ࣈࣂ࣋࣌ࣸ࣍ࣂࣉࣇࣈࣶࣂ࣍ࣇࣆࣸࣂࣈ࣎ࣻࣈࣈࣶࣻࣷࣉࣅࣇࣅ";
        array14[6][0] = "BxeNtSdHjGY=";
        array14[6][1] = "ࣶࣺࣻ࣌ࣸ࣋ࣅ࣍ࣂࣹࣅࣶࣉࣂࣉࣸࣅࣹࣂࣶࣸ࣍࣌ࣂࣉࣻ࣌ࣸࣉࣸࣅࣆࣻ࣊ࣈࣈ";
        By1337̴̴̨̰̗̻̟̳̦̻͍̻͕̭̫͎̯̟̻̙̪̎̓̍͛̏̓ͬ͋͆̏̓̈̑́ͬ͆ͩ͘͟();
    }

    private static void By1337̴̴̨̰̗̻̟̳̦̻͍̻͕̭̫͎̯̟̻̙̪̎̓̍͛̏̓ͬ͋͆̏̓̈̑́ͬ͆ͩ͘͟() {
        array14[7][0] = "87eRrtq72K4=";
        array14[7][1] = "ࣻࣆࣆ࣋࣋࣊࣋࣍ࣂࣈ࣌ࣇࣷࣂࣉ࣋ࣸ࣎ࣂࣷࣻࣻࣇࣂࣈࣺࣺࣺ࣊ࣇࣶࣇ࣍ࣇࣺࣆ";
        array14[8][0] = "teVyE3H/Vhs=";
        array14[8][1] = "ࣶ࣊࣎࣊ࣻࣆ࣋࣌ࣂࣉࣺࣷࣅࣂࣉࣆࣶࣅࣂࣶࣹ࣊ࣷࣂ࣌ࣇࣉࣇࣅࣇࣅࣹࣺࣹࣸࣸ";
        By1337̡̠̥͙̱̮̦̥̮̼̘͖̦͉̩̰̼͍̙̈͛̇̏ͭ̈́ͪͥ͗͗ͪ͌ͦͧ̀̀͌͘͡͡͠();
    }

    private static void By1337̡̠̥͙̱̮̦̥̮̼̘͖̦͉̩̰̼͍̙̈͛̇̏ͭ̈́ͪͥ͗͗ͪ͌ͦͧ̀̀͌͘͡͡͠() {
        array14[9][0] = "lY/xSaNrBQA=";
        array14[9][1] = "࣋ࣅࣷࣉࣈ࣌ࣅࣅࣂࣆࣇࣸ࣋ࣂࣉࣅࣇࣈࣂ࣎࣍ࣉࣻࣂ࣊ࣅࣶࣅࣹࣷࣷࣸࣸࣆࣸࣇ";
        array14[10][0] = "7A5pRpmL7X8=";
        array14[10][1] = "ࣺࣇࣇࣷࣆࣇ࣌ࣅࣂࣆࣺ࣊ࣅࣂࣉࣹࣶ࣎ࣂ࣎ࣇࣉࣷࣂࣈࣻࣉ࣋࣋ࣆࣻࣷ࣍ࣇ࣋ࣇ";
        By1337̷̳̥̪͇̫̩̘̭͉͍́ͧ̅̾ͤͪͤ͊ͧͥ͌̉̊ͦ̆̾̏͘̕͞͏͓͙ͫ̇ͣͦ͘ͅ();
    }

    private static void By1337̷̳̥̪͇̫̩̘̭͉͍́ͧ̅̾ͤͪͤ͊ͧͥ͌̉̊ͦ̆̾̏͘̕͞͏͓͙ͫ̇ͣͦ͘ͅ() {
        array14[11][0] = "N2qG4oBjeBo=";
        array14[11][1] = "ࣅࣉ࣊ࣅࣶࣆ࣍ࣇࣂࣶࣶࣈ࣎ࣂࣉࣅࣶࣻࣂࣷ࣌ࣷࣆࣂࣅࣶࣉࣅࣺࣹࣷࣇࣇࣹ࣋࣊";
        array14[12][0] = "/olqymsLbjY=";
        array14[12][1] = "ࣺࣹ࣊࣎࣎ࣅ࣎࣊ࣂࣺ࣌ࣻ࣋ࣂࣉࣈࣸࣅࣂࣶࣺࣹ࣍ࣂࣈࣆࣅࣆࣇࣶࣹ࣌ࣻ࣎࣋࣋";
        By1337̸̶̢̡̨̨̫̙͍̟̰̣̹͓͕̫̺͍̙͔͍̍̇͒̏̾͌͑ͣ͌͌̀̽̈̚͢͟͟͞͝();
    }

    private static void By1337̸̶̢̡̨̨̫̙͍̟̰̣̹͓͕̫̺͍̙͔͍̍̇͒̏̾͌͑ͣ͌͌̀̽̈̚͢͟͟͞͝() {
        array14[13][0] = "JQ2uBHZ+PMY=";
        array14[13][1] = "ࣹࣷࣻࣇࣺ࣍࣋ࣷࣂࣶ࣎࣋ࣈࣂࣉࣺࣻ࣍ࣂ࣎࣍࣎࣋ࣂࣆࣇࣶࣈࣶ࣎࣊ࣈ࣊ࣅࣆࣹ";
        array14[14][0] = "nsWdgWJddpg=";
        array14[14][1] = "ࣸࣉ࣊ࣆ࣋ࣸࣉࣺࣂࣺࣷ࣊ࣻࣂࣉࣈࣺࣻࣂࣹ࣎ࣸ࣎ࣂࣺࣶࣺࣻࣸ࣎ࣻ࣋ࣸ࣎࣎ࣅ";
        By1337̷̸̱̼̟̥̱̱͔̠͈̹̞̝̬̺͎̜ͪ͋ͪ̌̉̑̅̽̆͛̊̌̌ͤ̉̋ͨ͋ͫ̃̚ͅ();
    }

    private static void By1337̷̸̱̼̟̥̱̱͔̠͈̹̞̝̬̺͎̜ͪ͋ͪ̌̉̑̅̽̆͛̊̌̌ͤ̉̋ͨ͋ͫ̃̚ͅ() {
        array14[15][0] = "dmmqM3k5tuw=";
        array14[15][1] = "ࣆࣹࣺࣷࣷࣻࣸࣆࣂࣹࣆ࣊࣋ࣂࣉࣶࣺࣻࣂࣶࣅࣇࣶࣂࣺࣹ࣋࣊ࣻࣻ࣊࣊ࣈࣻࣆࣇ";
        array14[16][0] = "84VjZtWd6ck=";
        array14[16][1] = "ࣶࣉࣹࣅ࣊࣎࣋ࣉࣂࣆࣇࣆ࣋ࣂࣉ࣊ࣉ࣌ࣂࣹࣷࣷ࣋ࣂ࣊ࣆࣶ࣌ࣆࣺ࣊࣎ࣅࣆࣹࣸ";
        By1337̢̢̡͚̘̬͖̙͈͕̙̼̱̹͙̼̍̈̔͌̄͋̋͛ͪ͒̉̃̓͑̚͡͠͏̠̭̊̍́̚();
    }

    private static void By1337̢̢̡͚̘̬͖̙͈͕̙̼̱̹͙̼̍̈̔͌̄͋̋͛ͪ͒̉̃̓͑̚͡͠͏̠̭̊̍́̚() {
        array14[17][0] = "TDFnYIKUHsA=";
        array14[17][1] = "ࣈࣹ࣎ࣷࣻࣷ࣍࣋ࣂࣶ࣊ࣸ࣊ࣂࣉࣅ࣌࣌ࣂࣶࣅࣷࣅࣂࣇ࣋ࣉ࣌ࣆ࣊࣌ࣆࣈ࣎࣍࣎";
        array14[18][0] = "tk9eYz5SZmw=";
        array14[18][1] = "࣌ࣸࣆࣶ࣋࣌࣌ࣻࣂ࣌ࣻࣈࣅࣂࣉ࣊ࣆࣶࣂࣹࣹࣷ࣎ࣂࣸ࣌ࣇࣇࣺ࣍ࣻࣅࣈࣉࣶࣆ";
        By1337̸̶̴̭͖̫̭͇͇̦̜͉͉͓̬̲̘̩̦̲ͮ͗ͤ͆̎ͧͧͭ̽̂́̍͌̕͘̕͟͝͝ͅ();
    }

    private static void By1337̸̶̴̭͖̫̭͇͇̦̜͉͉͓̬̲̘̩̦̲ͮ͗ͤ͆̎ͧͧͭ̽̂́̍͌̕͘̕͟͝͝ͅ() {
        array14[19][0] = "o4BPD0r5xpQ=";
        array14[19][1] = "ࣆࣻ࣊ࣸࣅ࣎ࣻࣆࣂࣹࣅ࣎ࣉࣂࣉ࣍ࣷ࣌ࣂࣹࣶࣷࣆࣂࣶࣻࣅࣅࣶࣹࣶࣺࣻ࣊ࣈ࣋";
        array14[20][0] = "LC+jpyUDjrM=";
        array14[20][1] = "ࣸ࣌࣎ࣇࣉࣶ࣌ࣈࣂࣶ࣌࣋ࣇࣂࣉࣷࣻ࣋ࣂࣺࣺ࣍ࣸࣂ࣊࣍࣌ࣇࣹࣶ࣎ࣷࣷࣈࣺ࣌";
        By1337̴̧̭͖̙̬̰͖͖̭͔͚̻̺͈͙̩͑ͩͤ̀ͤ̈́̎̽̓̏̇ͨͩ̇̊ͭ͒̑̒́͠͠͞();
    }

    private static void By1337̴̧̭͖̙̬̰͖͖̭͔͚̻̺͈͙̩͑ͩͤ̀ͤ̈́̎̽̓̏̇ͨͩ̇̊ͭ͒̑̒́͠͠͞() {
        array14[21][0] = "AtSLMKOHrSM=";
        array14[21][1] = "ࣺࣶࣶࣹ࣊࣍ࣸࣈࣂࣺࣷࣷࣻࣂࣉ࣊ࣷ࣋ࣂࣶࣷ࣊ࣻࣂ࣊ࣇࣷࣉࣹ࣊ࣉࣅࣶ࣍ࣸࣷ";
    }

    private static void By1337̢̛̘̟͕̪̞̮̳͇͖̘̞̭͓̼̤̩ͭ̌ͬ̏ͦ̊̽ͧ̊̎̋ͭ͐̔͑͗̽ͧ̆ͬ͆̂() {
        array15[0][0] = "fMJZmZOeLeg=";
        array15[0][1] = "эНКэКТНъЖПТСяЖНъяПЖыьОПЖэТРКяяОКюПюю";
        array15[1][0] = "TqDKD5ZIB3U=";
        array15[1][1] = "ЙПыяъПьОЖНыКРЖНТЙыЖТьЙьЖЛьТКЙЙСПКПЙЛ";
        array15[2][0] = "V2hkPZMLaa4=";
        array15[2][1] = "яНПъМНКЛЖКэКьЖНээъЖТЙОМЖыРяНСНТРНПСО";
        By1337̷̷̵̴̷̶̷̧̡͖̩̻͍͚͙̭̯̲̤̩̲̂̈́̐ͧ̔͐̂̽̽ͨ̅́͌ͮ̍̑̚͜ͅ();
    }

    private static void By1337̷̷̵̴̷̶̷̧̡͖̩̻͍͚͙̭̯̲̤̩̲̂̈́̐ͧ̔͐̂̽̽ͨ̅́͌ͮ̍̑̚͜ͅ() {
        array15[3][0] = "0Wwn/g7ZgeM=";
        array15[3][1] = "ьэМСРКяСЖМПюНЖННЙЛЖыОыэЖСЛКТОюМНЙъюП";
        array15[4][0] = "Y0/NRWdVbcs=";
        array15[4][1] = "эьСюЙМъьЖьЙМОЖНОЙНЖТЙЙТЖРСюыюСЙНОНЙК";
        By1337̶̧̧̜̝̤̞̟̹͈̲̘̤̮͖̖̬̿͛́ͥ̂ͧͥ̀̉̏̃͌̈́͂ͥ͋̽ͨ̉̏̈́͜͠();
    }

    private static void By1337̶̧̧̜̝̤̞̟̹͈̲̘̤̮͖̖̬̿͛́ͥ̂ͧͥ̀̉̏̃͌̈́͂ͥ͋̽ͨ̉̏̈́͜͠() {
        array15[5][0] = "Va6IcKNmwRw=";
        array15[5][1] = "ЛМЙСяыМьЖРПТСЖНюММЖъКьНЖьююМТъТЛНьъъ";
        array15[6][0] = "MeZN/MlFHPM=";
        array15[6][1] = "ПяНъыьъЙЖьКьПЖНяТОЖыПКяЖююТТТыМСОэюЛ";
        By1337̷̧̧͖͎̟̘͙̪̼͉̝̘̪̫̤̘̥͋̒̇͗̐̿ͦ̓̋̎̃͂̎̉ͯ̈̕̚͟͟͝ͅ();
    }

    private static void By1337̷̧̧͖͎̟̘͙̪̼͉̝̘̪̫̤̘̥͋̒̇͗̐̿ͦ̓̋̎̃͂̎̉ͯ̈̕̚͟͟͝ͅ() {
        array15[7][0] = "jtG0Qv4RLAY=";
        array15[7][1] = "ЙЙПююЙьОЖяяПКЖНэЛюЖСОяНЖэКюОъОЙЛЙЛОы";
        array15[8][0] = "N1Itv63pDDg=";
        array15[8][1] = "яОяПъЙююЖЛюКПЖНЛяяЖТэТъЖэЙССЛНюКРъыэ";
        By1337̴̸̡̧̢̟͎̥̦̲̙̩̰̫̑̑͂̏̓̿͛̈̍ͭ̿͆̀̒̽́̌̽̇̅͌͌͝͞ͅͅ();
    }

    private static void By1337̴̸̡̧̢̟͎̥̦̲̙̩̰̫̑̑͂̏̓̿͛̈̍ͭ̿͆̀̒̽́̌̽̇̅͌͌͝͞ͅͅ() {
        array15[9][0] = "M0z6F+5ZHp0=";
        array15[9][1] = "ъьэПюНЙТЖЛяОНЖНОЙПЖТККСЖМЙПЛюПъЛыССМ";
        array15[10][0] = "Bq0b+2PXsiY=";
        array15[10][1] = "эъьРТъяРЖСяНыЖНЙНСЖТСНяЖКяюъЙыОъМъэя";
        By1337̧̪̒̀̒̾̉͜͢ͅͅ͏̷̧͖̠̼̝̩̬̯͕̪͉̙̭́ͧ̿̊ͣͯͤ̽͌̔ͨ̇͟͝();
    }

    private static void By1337̧̪̒̀̒̾̉͜͢ͅͅ͏̷̧͖̠̼̝̩̬̯͕̪͉̙̭́ͧ̿̊ͣͯͤ̽͌̔ͨ̇͟͝() {
        array15[11][0] = "dPZ4zEc8qPs=";
        array15[11][1] = "НРМСяяМТЖЛьОПЖНэьъЖТъННЖОММыыыРяРьНЙ";
        array15[12][0] = "gay/Do/Djsg=";
        array15[12][1] = "ЙыюЛКэКРЖПКьэЖНОСПЖъЛППЖэНюЛОЙэРСРРН";
        By1337̵̵̴̴̧̡̢͎͔̩͎̦̲̱̟̗͍̤̩̾̊̑͊̆͑ͯ̃̿͊̉̒̿̍͆̾̌̕͢͜ͅ();
    }

    private static void By1337̵̵̴̴̧̡̢͎͔̩͎̦̲̱̟̗͍̤̩̾̊̑͊̆͑ͯ̃̿͊̉̒̿̍͆̾̌̕͢͜ͅ() {
        array15[13][0] = "+8zVxaBxJ2A=";
        array15[13][1] = "КъЙТъТюМЖъПЛъЖНЙПэЖТюЙьЖяэъСЙРьМъъъь";
        array15[14][0] = "c30edtv0vxM=";
        array15[14][1] = "ПКЛъНЙНьЖРОРОЖНьОэЖТТМыЖЙТНэКНРОяэТы";
        By1337̷̭͚ͮ̋͏̷̴̴̴̷̡̧̛̫͔̳͇̦̯̙̩̆ͨ͗ͬͬ̿̾̄́̉́́̏̌̿͘̕͜();
    }

    private static void By1337̷̭͚ͮ̋͏̷̴̴̴̷̡̧̛̫͔̳͇̦̯̙̩̆ͨ͗ͬͬ̿̾̄́̉́́̏̌̿͘̕͜() {
        array15[15][0] = "Hyt8jCSGM88=";
        array15[15][1] = "ТПРТМяьПЖыюСЛЖНСьыЖыьОыЖьРЙЙыъюСРъЛъ";
        array15[16][0] = "F3G44siL7cU=";
        array15[16][1] = "НыыПэКъКЖНъНСЖНСРЙЖыСюНЖТьТСТяНКяЙъК";
        By1337̸̨͖̠̦̪̲͙͚̩̣̪͍̩̭̹̺̎ͩ̓̍͛́͂ͦ̄͑̏͋̊͆ͭ̑ͯͣ̾ͦ̕͜͡();
    }

    private static void By1337̸̨͖̠̦̪̲͙͚̩̣̪͍̩̭̹̺̎ͩ̓̍͛́͂ͦ̄͑̏͋̊͆ͭ̑ͯͣ̾ͦ̕͜͡() {
        array15[17][0] = "XJsda01kUEA=";
        array15[17][1] = "юОКЙыыЙКЖНяыЛЖНЛРМЖыМэяЖПНКэюТРКРЙыТ";
    }

    private static void By1337̵̧͕̞̻̣͖̠̥͈̙̤̫̺͉̯̥ͬͪ̌ͨ̏̌͋̂ͤͣͣ̇ͮͮ͗ͨ̾̈́ͭ͑̕͟͡() {
        array16[0][0] = "E0rCJxPk0LU=";
        array16[0][1] = "୍୍ଝଚ୎ଣ୍୍ଗଢଞଡ୐ଗଞଟଚ୏ଗୌୌଝଟଗଜଝଝଞଚୋ୎୐୎ଡଝଚ";
        array16[1][0] = "OkkHKqvLQ4Y=";
        array16[1][1] = "ଜଣୌଟଡଝଣଞଗଟଞଢଚଗଞ୍ଟଡଗଢଛଜଠଗୌ୏ଠଣ୎ଟ୏୎ଟ୍ଛଛ";
        array16[2][0] = "D+w/HfyJvj4=";
        array16[2][1] = "ଞ୎ଡୌ୎ଠଜ୐ଗଚଚଝୋଗଞଣଠଠଗୌୋଢଛଗୌଝଢୋ୏୍୏୐୐ଞଛଡ";
        By1337̸̵̷̵̧̢̦̹̬̮̞̲͎̲̪̥̥̈ͣ͌ͮ́ͨ̉̒ͩ̌ͩ̿̋͐̈́ͨ̓ͨͤ̚̕͠͏();
    }

    private static void By1337̸̵̷̵̧̢̦̹̬̮̞̲͎̲̪̥̥̈ͣ͌ͮ́ͨ̉̒ͩ̌ͩ̿̋͐̈́ͨ̓ͨͤ̚̕͠͏() {
        array16[3][0] = "BkO4TryAj3Y=";
        array16[3][1] = "ଝୌଝଜଞଢଜ୐ଗଜଢ୏ଠଗଞ୐ଢଛଗଣୌ୎୎ଗଟଡଡୋୌ୎ଝଚଡୋଛଝ";
        array16[4][0] = "xD2nKKIa7es=";
        array16[4][1] = "୍ଟଣଛଜଛୋଜଗଞଝଠଠଗଞଟଝୌଗୌଝଠୌଗ୏ଝ୏୍ଠଝଢଚଣ୎ଛ୏";
        By1337̸̵̵̸̨̨̨͔̰͔͓̮͙͎̠̼̦̝̖͌ͮ̄͛̍ͥ̐ͦ̋͗ͤ̐̄̾̂̌̏͢͢͟͜();
    }

    private static void By1337̸̵̵̸̨̨̨͔̰͔͓̮͙͎̠̼̦̝̖͌ͮ̄͛̍ͥ̐ͦ̋͗ͤ̐̄̾̂̌̏͢͢͟͜() {
    }

    private static void By1337̭̟̞͙͉̱̹̭͓̭̘̖̺̘̬̌ͮ̑ͬ́ͯ͊ͮ̔ͬͩ͆͐̋̿͌ͥ̆̌͂̐ͪͩ͂̽() {
        array17[0][0] = "qDglNI+TtiM=";
        array17[0][1] = "ѴѵѳѵѰѱѴѵѩҠѴѳѯѩѰѭҞѭѩѵѴҢҟѩѭѯѰѯѬѰѬҝҞѰѭѵ";
        array17[1][0] = "JZ30siSvbNM=";
        array17[1][1] = "ҠҡѭѴҞҟѱҞѩѲѬҞҠѩѰѵѮҞѩҞѭҢѵѩѯҢѴѯѳѴѲѮѯѲѵѴ";
        array17[2][0] = "SV5yGhtEqB4=";
        array17[2][1] = "ҡҡѮѴѯѱѮѯѩѱҞѮҝѩѰѯѮѭѩѴѮѱѱѩҡѳҟѳҢѮҢҢѱѵҢѵ";
        By1337̸̵̢̢̜͖͖̦̻̠̠̟̦͙͙͖̱͓̬̖̰͑͐̓̃͂ͣͫ̎ͭ̇ͮ̐͒̓̚̕͞͞ͅ();
    }

    private static void By1337̸̵̢̢̜͖͖̦̻̠̠̟̦͙͙͖̱͓̬̖̰͑͐̓̃͂ͣͫ̎ͭ̇ͮ̐͒̓̚̕͞͞ͅ() {
        array17[3][0] = "q6TvQdjNSXo=";
        array17[3][1] = "ҡѭѲѲҠҠҢѬѩѲѯѭѱѩѰѮҢѴѩҝҢҡѳѩҟѬѴҞѵѵҟѱѮҝѴѲ";
        array17[4][0] = "6LBi/pS9/w4=";
        array17[4][1] = "ѬѭҞҡҞѬѯѲѩҟѭѲѱѩѰѯѬѱѩҝҠѳҟѩҞҟѮҟҢѵѯѳѲҢѮҠ";
        By1337̵̢̡̰̭̭̯̳͙͕̤̼͓͓̞̻̭̬͖̪̲̊͑ͤͣ́͑ͧͯͨ̅́ͥ̂̍̔̍ͪ͟͝();
    }

    private static void By1337̵̢̡̰̭̭̯̳͙͕̤̼͓͓̞̻̭̬͖̪̲̊͑ͤͣ́͑ͧͯͨ̅́ͥ̂̍̔̍ͪ͟͝() {
        array17[5][0] = "It3YPJ6g0r0=";
        array17[5][1] = "ѱѴѬѬѬѰѲҢѩѵѯҝѲѩѰҢѴѱѩѵѯҝѵѩҢѱҡѴѰѴѭҠѱѱҟҡ";
        array17[6][0] = "DgaXBfqAOaA=";
        array17[6][1] = "ѵѰѱѭҢѰҢѮѩҞҟѮҝѩѰҡѴҞѩҞѱҠҠѩѮѲѲѵҢѭҡҡѮѴѳѲ";
        By1337̶̱͙̣͕̋͛̓̉͋̎͋͜͡͏̶͓̫̤͙̒̀͐̔̀͛͜͏̷̢͚̮͙̰̙̹̰̓͟͞();
    }

    private static void By1337̶̱͙̣͕̋͛̓̉͋̎͋͜͡͏̶͓̫̤͙̒̀͐̔̀͛͜͏̷̢͚̮͙̰̙̹̰̓͟͞() {
        array17[7][0] = "EphuPyw7zrY=";
        array17[7][1] = "ѬѵҟѵѬѭѳҟѩѴѰѭҡѩѰѮѴҢѩѵѵҟѲѩҢѭѬѱҝҝѳҢѱѵҠѴ";
        array17[8][0] = "a3075/z0YYY=";
        array17[8][1] = "ҢѵѳѴѴѱѮѴѩѱҟҠҠѩѰҞҞҟѩѵѱҢѳѩҠѭҡѱѰҝѵѵҢѯѮѬ";
        By1337̶̢̧̗̻͙͍̙̪̞͔͉̥̞̖̫̻̬̯̜͍̼͑͑ͨ̃̇͛̃̔͆̆̐̄̐̈̎ͫ͋̅();
    }

    private static void By1337̶̢̧̗̻͙͍̙̪̞͔͉̥̞̖̫̻̬̯̜͍̼͑͑ͨ̃̇͛̃̔͆̆̐̄̐̈̎ͫ͋̅() {
        array17[9][0] = "ZHoZQ0JcUm8=";
        array17[9][1] = "ѰѵҞҞѳѮѱѴѩѱѮѵҟѩѰѰѲҝѩѵѱҡѲѩѰѮҟѰҝҡѲѬѱѭҠѵ";
        array17[10][0] = "osWhO9VbcbU=";
        array17[10][1] = "ҞѯѵѲѱѮҟѴѩѵѭҢѰѩѰѮѮѱѩҝѱҡҡѩѭѱѮҢѬҠѳҝҞҟѲѲ";
        By1337̷̸̶̡͔̝͓̫̘̺̜͚̩̲̬̦͖̤̗ͮ̓̒̽̀̀ͬ͐ͦͪ͒̋̑ͣ͌̀ͯ̕̕̚͞();
    }

    private static void By1337̷̸̶̡͔̝͓̫̘̺̜͚̩̲̬̦͖̤̗ͮ̓̒̽̀̀ͬ͐ͦͪ͒̋̑ͣ͌̀ͯ̕̕̚͞() {
        array17[11][0] = "SIeW9A+Z5rc=";
        array17[11][1] = "ѯҟѵҞѵѵҝҡѩѰҝѰҞѩѰѮѴҟѩѵѵѴѱѩҝѴҡѵѰҠҢѵҡѬҟҝ";
        array17[12][0] = "n5FvvT7+Vw8=";
        array17[12][1] = "ҡѲѱѬҢҞѰѲѩѱҡѳҠѩѰѲҠѱѩѵѴѵҢѩѬѱҞҠѲѯѴѳѮѴѯҡ";
        By1337̶̼̬̱̘͍̬̱̠̹̥̼̰̘͕̖̽̅̾͛̅̍͗ͦ̓̃̂ͧ͑̓͐ͤ̇ͮ̑̒̋̕̕ͅ();
    }

    private static void By1337̶̼̬̱̘͍̬̱̠̹̥̼̰̘͕̖̽̅̾͛̅̍͗ͦ̓̃̂ͧ͑̓͐ͤ̇ͮ̑̒̋̕̕ͅ() {
        array17[13][0] = "GwogyDe9i2o=";
        array17[13][1] = "ѱѳѱѱѱѯѬҟѩѬѵѴѵѩѰѲѲѵѩѴҞҟҟѩѴҡҞҟҟѲҟҝҢҢҠѯ";
        array17[14][0] = "VySUE/d6jgo=";
        array17[14][1] = "ҟѱѲѮҟҟѲѭѩѰҟҢҢѩѰѲѱѱѩҝѭѬҟѩҞѬѵѱҞҠҞҝѬѭѯѲ";
        By1337̸̶̢̛̬̼͔͚͇̗̟͍̭̗͕͕͉͉̗̭͚̅ͧ̀́̍̉ͪ͑̄̓͋ͩ́̏̆ͣ̉̋̐();
    }

    private static void By1337̸̶̢̛̬̼͔͚͇̗̟͍̭̗͕͕͉͉̗̭͚̅ͧ̀́̍̉ͪ͑̄̓͋ͩ́̏̆ͣ̉̋̐() {
        array17[15][0] = "cm3LjeKs1FE=";
        array17[15][1] = "ҢҢѭѳҟҢѰҠѩѳҟѴҢѩѰѳҢѲѩҞѱҡѮѩѴѴѰѴѰҟѬѴҝѭѴҢ";
        array17[16][0] = "rQyxY6QD69s=";
        array17[16][1] = "ѳҟѰѭҞѵѱҠѩҡҞѭѳѩѰѮѯҢѩѵѱѴҢѩѬҡѮѰҠѬҢҠѬѲѯҟ";
        By1337̸̛̛͖͇͓̤͉͕̣̻͇̯̺̟̑̓̊̓̏̑ͬ̌̍́̃ͩ̽̀͐͋̅̌ͦ͘̕͠͠͡͞();
    }

    private static void By1337̸̛̛͖͇͓̤͉͕̣̻͇̯̺̟̑̓̊̓̏̑ͬ̌̍́̃ͩ̽̀͐͋̅̌ͦ͘̕͠͠͡͞() {
        array17[17][0] = "aH6ADi8vowg=";
        array17[17][1] = "ҡҟѱҝҢѴѭҢѩѰѭҡѬѩѰҞѮҠѩѴѲѭҠѩѴѭҟҢѯҠѲҢҟѬѯѯ";
        array17[18][0] = "qSR+NywIxNU=";
        array17[18][1] = "ѲѯѰѵҢҢҡѴѩҞѵѳѬѩѰѳѲҠѩҞҞѮѳѩҟѱѮѳѮѵҝѯѭѭҝѮ";
        By1337̨̛̤͕̜̘̗̦̩̤̫̼̳̰̬̱̬͂̏̀ͨ́ͪͧ̾̈́̏͊̓ͯ̈ͣ̔ͧͬͦ́̿͊͜();
    }

    private static void By1337̨̛̤͕̜̘̗̦̩̤̫̼̳̰̬̱̬͂̏̀ͨ́ͪͧ̾̈́̏͊̓ͯ̈ͣ̔ͧͬͦ́̿͊͜() {
        array17[19][0] = "7p3yCDMjHM0=";
        array17[19][1] = "ѳѰѯѳҞѭҝѬѩҠѬѱҢѩѰѱѴѬѩѴѰѱѰѩҞѴҟҡѱѳѲѯҡѬѵѳ";
        array17[20][0] = "LYr7kAuo4QY=";
        array17[20][1] = "ѵѮҞѴҠҟѯѳѩѵѰѰѰѩѰѯѳҞѩѴҞҠѯѩҡѮѲѴѳѲҡѭҢѯѱѳ";
        By1337̵̬̤̟̤̫̞̲ͬ̓͑͒̄́̓̂͂͒̿̔̕͟ͅ͏̶̻̹͎̬̣̖̌̂̎ͮ̀͆̇̚͜();
    }

    private static void By1337̵̬̤̟̤̫̞̲ͬ̓͑͒̄́̓̂͂͒̿̔̕͟ͅ͏̶̻̹͎̬̣̖̌̂̎ͮ̀͆̇̚͜() {
        array17[21][0] = "NABq5l9HN5A=";
        array17[21][1] = "ѱҡѳѮѴҢҝѲѩѰѳѮѰѩѰѴѲѰѩѵҞѴҡѩѰѬѵҢҞҞѱҝѭѭѬҟ";
        array17[22][0] = "NzC0EAcw0xs=";
        array17[22][1] = "ѴѯѰҝҡѭѬѱѩѳҟѰѵѩѰҞѯҢѩѴҠѵѰѩѭѵѳѯѰѮѬҡѲҡѯѰ";
        By1337͉͍̭̱ͨ̄͊͑̅͗͐͏̴̨̡͚̞͇̞̞̤̣̣̹͊͌͆ͬ̋̀̄̀̍ͮ͊ͪ̇ͥ͝ͅ();
    }

    private static void By1337͉͍̭̱ͨ̄͊͑̅͗͐͏̴̨̡͚̞͇̞̞̤̣̣̹͊͌͆ͬ̋̀̄̀̍ͮ͊ͪ̇ͥ͝ͅ() {
        array17[23][0] = "g/O3hgQSRyM=";
        array17[23][1] = "ѰҢҠҞѱѴҝѱѩҟҟҝѭѩѰѲҟѬѩҞҡҞҡѩѲѬѱҡѯѲѲҡѯҟѱҟ";
        array17[24][0] = "IGrt3Ibll8Y=";
        array17[24][1] = "ҠѱѬҞҟҟѮѲѩѳҝѱҞѩѰҝѴҢѩѵҡҞѴѩѵѳѳѴѬѲѴҠҡѴѲҝ";
        By1337̴̵̡̢̲͚̻̻̬͓͇͕̲̻̙̦͔̻̯̱̰̻̾̂͆͗͊ͣ͑̈͑̀ͬͣͩ̍̿̓ͮ̕();
    }

    private static void By1337̴̵̡̢̲͚̻̻̬͓͇͕̲̻̙̦͔̻̯̱̰̻̾̂͆͗͊ͣ͑̈͑̀ͬͣͩ̍̿̓ͮ̕() {
    }

    private static void By1337̶̵̢̹͍̣̺̗̱̠̳̣͐ͧ̀̓̊̃ͪ̋̓̀̏ͨ̔̏̅ͥ̂͋ͥ̂̅̀͆ͤ͜͢͝͝() {
        array18[0][0] = "xcTNGXOONQo=";
        array18[0][1] = "๝๗๛ຍຍ๞๗๙๔ຈ๗๟๛๔๛ຊຍຌ๔ຈ๝ຍ๗๔๠๛ຊຊຌ๛๞๗๛ຍ๝๛";
        array18[1][0] = "4h1221phc/I=";
        array18[1][1] = "๠ຍຉ๚๝๝ຌ๠๔๞๘๚ຌ๔๛๙๚๛๔ຉ๗๟๞๔๛຋๗ຍຍ຋๟๘๠ຌຊ๜";
        array18[2][0] = "32B1+XDqPEA=";
        array18[2][1] = "ຉ๚๛ຉ๙ຌ๝๙๔๗ຌຉຍ๔๛ຍ๟๚๔ຈຈ๟๠๔๚๟๘ຊຊ๜๚๟ຍຊ๞๗";
        By1337̴̧̨̢̗̥̫̟̖̼̳̙̬͇͎̑͛ͭ̆̓̀̏ͧ̇͒̉̏͆̉̈́̍̽̈́ͯ̚͜͠͞͠͞();
    }

    private static void By1337̴̧̨̢̗̥̫̟̖̼̳̙̬͇͎̑͛ͭ̆̓̀̏ͧ̇͒̉̏͆̉̈́̍̽̈́ͯ̚͜͠͞͠͞() {
        array18[3][0] = "LYoqQkW14C4=";
        array18[3][1] = "๜ຈ๛ຊຌ๟຋ຊ๔๗๘๟๞๔๛๛ຍຉ๔ຉຊ๘๟๔๚๛๠๛๠๝ຉ๞ຈ๞๟๝";
        array18[4][0] = "1hQ9jbTHQzA=";
        array18[4][1] = "ຌ๟๞๘๜຋๘ຈ๔๚ຍ๗๗๔๛๠຋๝๔๠๝ຍຈ๔ຌຊ๝๘๟๘๞ຍ๟ຌ຋ຈ";
        By1337͕̄̉͏̸̛̖̫̳͍̺̻̖͎̩̱̖͔̬̓ͬͪ̍̒̌͂̽͋̾́ͬ͆̇̃͂ͤ̎̾̕͞();
    }

    private static void By1337͕̄̉͏̸̛̖̫̳͍̺̻̖͎̩̱̖͔̬̓ͬͪ̍̒̌͂̽͋̾́ͬ͆̇̃͂ͤ̎̾̕͞() {
        array18[5][0] = "zW8a1nNK2t4=";
        array18[5][1] = "๜๚๚ຉ๙๞๙๘๔๗๝ຊ๘๔๛๙๗๟๔ຈ๞๙๜๔๚๘๘๛๘ຉ๞ຉ๠ຉຉ๜";
        array18[6][0] = "5eeXkX7vX3c=";
        array18[6][1] = "຋๗๝๝ຈຌ຋๝๔๘๛ຊຌ๔๛ຊ๘๛๔๠๚ຊ๠๔๜ຉຌຊ๟ຊ๞຋๜๠๟๠";
        By1337̴̵̷̢̛̛̞̣̮̪̱͇͉͉͔̹̼̂̔̓̒ͧ̿͑̿̃͆ͣ͐ͦͭ̑̈́̃̓̎ͩ̀͘͜();
    }

    private static void By1337̴̵̷̢̛̛̞̣̮̪̱͇͉͉͔̹̼̂̔̓̒ͧ̿͑̿̃͆ͣ͐ͦͭ̑̈́̃̓̎ͩ̀͘͜() {
        array18[7][0] = "Rc+x/gDmquo=";
        array18[7][1] = "ຌຊ๠๛๠ຍ๞๗๔ຌ຋ຉຌ๔๛ຊຈ๞๔ຉຌ๠๟๔๜๗ຉ๜๘๛๜๚๚๛๛ຌ";
        array18[8][0] = "vOxn/KUl0pY=";
        array18[8][1] = "๗ຍ๚๝๛๝๙๝๔ຍ๟๚ຍ๔๛๛๗ຍ๔ຉ๗ຌ๗๔๝ຉ๠๜ຍ຋຋ຍຉຍຈຉ";
        By1337̶̨̳̬̞̫̩́̓̇̌̒ͦ̌͋͘͜͏̵̸̸̵̡̤̗̹͉̙̠̠̿͐ͫͩ͆͐̿̄͠͡();
    }

    private static void By1337̶̨̳̬̞̫̩́̓̇̌̒ͦ̌͋͘͜͏̵̸̸̵̡̤̗̹͉̙̠̠̿͐ͫͩ͆͐̿̄͠͡() {
        array18[9][0] = "dQbhb3DiuBE=";
        array18[9][1] = "๟๝ຍ๙๗ຉ๞๟๔ຊ຋ຉ๞๔๛๟๞ຈ๔ຉ๘ຉ๞๔๚๚๙๝๘๘๛ຍ๞๘๞๛";
        array18[10][0] = "PCDLS/kv99E=";
        array18[10][1] = "๘๝๜ຈ๟ຉ຋๘๔຋๠๛຋๔๛๗຋ຌ๔ຉຉ๠๙๔ຍ๝๠ຍ๝๚๠๚๟๚๗๞";
        By1337̸̧̩͓̗̝̼̫̭̝̝͔͕̣̙̩͕͂́͐̋͐͂ͪ̆ͫͬ͒̑̒̽ͭ͑ͪ͌̀͘͟͜͠();
    }

    private static void By1337̸̧̩͓̗̝̼̫̭̝̝͔͕̣̙̩͕͂́͐̋͐͂ͪ̆ͫͬ͒̑̒̽ͭ͑ͪ͌̀͘͟͜͠() {
        array18[11][0] = "V53zOLH3BFU=";
        array18[11][1] = "๙๜๛๠๚๛๘๝๔๜๘ຍຌ๔๛๟ຈ๚๔๠๘๛ຊ๔๛ຈ๛๜๝๘๛๝๜๘ຍ๛";
        array18[12][0] = "bEviProUGdU=";
        array18[12][1] = "๗ຌ๚ຊຍ๛๚๙๔ຈ๞ຉ๠๔๛๝๠๛๔ຈຊຊ๙๔ຍ຋๝຋๝ຉ๟๠๘๗ຉຌ";
        By1337͙̭̳̤̥͓̲̩̜͈̖̖͕̰̲͓̙̂ͯ̎͒ͫ͑ͫ͆̉̊̄̐̃͊̎ͦ́̈́̄͋̕͠();
    }

    private static void By1337͙̭̳̤̥͓̲̩̜͈̖̖͕̰̲͓̙̂ͯ̎͒ͫ͑ͫ͆̉̊̄̐̃͊̎ͦ́̈́̄͋̕͠() {
        array18[13][0] = "xv76bbrGw8c=";
        array18[13][1] = "๚຋๚຋๟ຈ๝๜๔๝๛ຌຈ๔๛ຌ๠๗๔๠๠๠๘๔ຉ๜ຈ๙๛๝ຊ๙ຉຈ຋๘";
        array18[14][0] = "SUfnFqX+n/w=";
        array18[14][1] = "๝๛ຊ຋ຈ๜๝ຍ๔ຊ๝ຉ๘๔๛๙ຈ๙๔ຉ๜๚๞๔๘๛๛ຌຌ๚ຌ๚ຈ๚๟๘";
        By1337̛̺̰̗̹̖̠̥̞͈̫͙̰͖̣̗̎̔ͭͭ͗̽̏ͬ̓ͦ̊ͨ̾̌͐ͫ̕̚͢͢͜͝͞ͅ();
    }

    private static void By1337̛̺̰̗̹̖̠̥̞͈̫͙̰͖̣̗̎̔ͭͭ͗̽̏ͬ̓ͦ̊ͨ̾̌͐ͫ̕̚͢͢͜͝͞ͅ() {
        array18[15][0] = "nbOxS8/EbU8=";
        array18[15][1] = "๝๞ຈ๛ຉ๝ຈ๚๔๙๝๗๘๔๛๜๜๠๔๠๠๚ຌ๔ຈຊ๞๞຋๞๝๝๜ຉ๝๞";
        array18[16][0] = "GZcVKBtzq2c=";
        array18[16][1] = "ຍຉ๝๙๜๙๛ຍ๔๠ຈຉຍ๔๛๜ຍ๜๔๟ຈຍ๜๔ຊ๠ຉ๝๞ຌ๜๗๝๛ຈຊ";
        By1337̶̢̡̛̦̘͍̦͇̺͙̜̣̝̼̝͔̗̪̌̉͂ͩ̍͒͆ͫ̈́̐̀̐ͣͦͤͬ̊͘͟͡͡();
    }

    private static void By1337̶̢̡̛̦̘͍̦͇̺͙̜̣̝̼̝͔̗̪̌̉͂ͩ̍͒͆ͫ̈́̐̀̐ͣͦͤͬ̊͘͟͡͡() {
        array18[17][0] = "AR4YnBLzZa8=";
        array18[17][1] = "ຍຊ๘຋๚๠ຉ๙๔຋ຉຍ๗๔๛๝ຈ๗๔ຈຌ๘๚๔ຊ๙ຊຉ๞๠๚ຉຊ๞ຉ๗";
        array18[18][0] = "K7JZqcr+GE8=";
        array18[18][1] = "ຊຍ๛๟๙๗๛๠๔຋๗ຊຉ๔๛๗ຌຈ๔ຈ๠๛๞๔๘๞ຍຍ๙ຍຍຍ๠๛๝๘";
        By1337̺̱̫͇̫̝̘̬̩̯̞̺͉̗̜ͤ̿ͪͥ͑͒ͨͧ͌ͦ̀̌̆̅̈́́̌ͤ̎̄͘͘͠͞ͅ();
    }

    private static void By1337̺̱̫͇̫̝̘̬̩̯̞̺͉̗̜ͤ̿ͪͥ͑͒ͨͧ͌ͦ̀̌̆̅̈́́̌ͤ̎̄͘͘͠͞ͅ() {
        array18[19][0] = "c5HN32gRINE=";
        array18[19][1] = "຋๗๛ຈ๛ຌຉ຋๔๠๛ຈຍ๔๛຋ຈຌ๔ຈ๝๜๙๔๞๜ຉ๟ຉຉ๟๠๘๟๟຋";
        array18[20][0] = "uYV3Uv5t834=";
        array18[20][1] = "๗๛๚๘๗๚๜๜๔ຍ๚๘๘๔๛๛ຈ຋๔ຉ຋๠๞๔ຉ๚๟๛๠ຍຉຌຈ๗ຊຍ";
        By1337̷̢̨̗̹͇͕̫̯͓̪̗̼̭͉̙̜͈̲̣̍̓ͥ̊ͮͮ̎̽̾̾ͣ̏̃̋̉̀̕̚͟͢();
    }

    private static void By1337̷̢̨̗̹͇͕̫̯͓̪̗̼̭͉̙̜͈̲̣̍̓ͥ̊ͮͮ̎̽̾̾ͣ̏̃̋̉̀̕̚͟͢() {
        array18[21][0] = "zpmgCbEvCX8=";
        array18[21][1] = "๟๗ຍຉຍ๝ຍຊ๔๟๗ຊຍ๔๛๗๝๛๔ຈ๛๜ຊ๔๠๛๗๚ຊຉ๠๞๞๟ຉ๞";
        array18[22][0] = "fzH3pEcgCXQ=";
        array18[22][1] = "ຉ๚๚๠๘ຈ๗๗๔ຌຍ຋๞๔๛ຌ๞๜๔ຉ๗๜๠๔๝ຉ๗๟๠๜๙๘๛๜๞๠";
        By1337̵̶͉͇͍̥ͧ̂ͧ̈ͤ̈͞͏̠͍̭̰̊̊̈́̀ͤ͐ͬͤ̎ͤ́ͭ̌̇̉ͭ͘̚͟͠͝͠();
    }

    private static void By1337̵̶͉͇͍̥ͧ̂ͧ̈ͤ̈͞͏̠͍̭̰̊̊̈́̀ͤ͐ͬͤ̎ͤ́ͭ̌̇̉ͭ͘̚͟͠͝͠() {
        array18[23][0] = "DFnOYZeW8F0=";
        array18[23][1] = "๚๝๠๠຋ຌ๝๞๔຋๙๞๞๔๛๙๝๞๔๟๙๚๠๔຋ຍຊຈ๠ຈ๗๙ຊຈ๞๝";
        array18[24][0] = "sZqlPXQi+PM=";
        array18[24][1] = "ຍ๠๠๝๛๛๛๘๔๚๜๝຋๔๛๝๙๝๔ຈ๚๜๞๔๟ຊ๟๠ຍຊ๟๙๛๞ຉ๞";
        By1337̸̴̵̛̼̮̘͎̠̫̤͓̻̖̭͓͖̮́́̌̒̀̇͊͊̀̍͆̾̏̅͛̒̀̈̿̓͢͟();
    }

    private static void By1337̸̴̵̛̼̮̘͎̠̫̤͓̻̖̭͓͖̮́́̌̒̀̇͊͊̀̍͆̾̏̅͛̒̀̈̿̓͢͟() {
        array18[25][0] = "4hKTXfiIgbY=";
        array18[25][1] = "๛๚๝ຉ๗๛๙๛๔๗๟๚ຊ๔๛๘๗ຌ๔๠ຈ๛๠๔ຌຈ๞๘ຈຊ๗ຈ๜๘ຈຉ";
        array18[26][0] = "hUHnQWBF5XY=";
        array18[26][1] = "຋๜๜ຌຈ๘ຍ๝๔ຉຉຌຈ๔๛๟๗ຍ๔๠ຈຊຈ๔຋๘ຉ๜ຉ๟ຉ๝๟ຈຌຍ";
        By1337̒̇͏̷̸̴̭̲̭̥̺̼͉̦̳͕̬ͨ̐ͮ̌͊ͮ̀̈́ͬ̎̂ͫ̎̑̏͊̀̋͘̚͘͡͠();
    }

    private static void By1337̒̇͏̷̸̴̭̲̭̥̺̼͉̦̳͕̬ͨ̐ͮ̌͊ͮ̀̈́ͬ̎̂ͫ̎̑̏͊̀̋͘̚͘͡͠() {
        array18[27][0] = "RWgu++oleMo=";
        array18[27][1] = "๙๛๙๙๚ຊຊ๘๔๠ຊ๝຋๔๛๟๟ຉ๔๠๟๠ຌ๔๚๘ຉ๜๠ຌ຋ຉ๚๞ຍຍ";
        array18[28][0] = "FLXqGbprk5c=";
        array18[28][1] = "๛຋ຊຊ๙๜ຍ๙๔ຊ๞ຈ๚๔๛ຉ๛ຌ๔๟๘ຊ๗๔ຈ๟ຈຌຊ๝ຈ຋๗๜๜๙";
        By1337̦̥̩̜͔̰̱̮̅́ͧ̓̇ͩ̈́̉ͭ͢͏̮̞̹̰̭͇̏̆ͭ̌͊̀ͩ͗͒̓̈̚̚͟͞();
    }

    private static void By1337̦̥̩̜͔̰̱̮̅́ͧ̓̇ͩ̈́̉ͭ͢͏̮̞̹̰̭͇̏̆ͭ̌͊̀ͩ͗͒̓̈̚̚͟͞() {
        array18[29][0] = "mk1UbG92BsY=";
        array18[29][1] = "๝๜ຈ๘๘ຈຊ๞๔๟๠๚๙๔๛ຍ๜ຌ๔๠๝๗ຌ๔ຉ๝๞ຈ๛຋๘๜๜ຈ๜๚";
        array18[30][0] = "jzUqVEDE4Mg=";
        array18[30][1] = "๙๙ຍ๙຋ຈຊ๚๔๗ຉ๞๝๔๛຋๝๠๔๟ຍຌ๛๔๛๜๜๜ຌ๠๝๞๚๗๟๝";
        By1337͏̴̨̛͚̞͍̲̞̪̳͍͙̪̞̠̮̟͔̠̩́ͯ͆ͦͪͮ͋ͤ̀ͤ̏ͯ̆̊̽͐̎͟͜();
    }

    private static void By1337͏̴̨̛͚̞͍̲̞̪̳͍͙̪̞̠̮̟͔̠̩́ͯ͆ͦͪͮ͋ͤ̀ͤ̏ͯ̆̊̽͐̎͟͜() {
        array18[31][0] = "lpi/DKf3a9E=";
        array18[31][1] = "ຉຍ๗ຉຊ๟๝๚๔๟ຈຌ๠๔๛๙๟຋๔ຈຍ๗ຊ๔๞๞๘๞๗๛ຉຍ๛຋๠๝";
    }

    private static void By1337̶̛̛̫̟̭͕̫ͤ̾ͬͧ͑̽̓̈ͣ͂̚͘͘͝͞͏̵̵̻͚͎͇ͩ̄̾̔̋̀̄̈́̍͠() {
        array19[0][0] = "A7/hp35lgAM=";
        array19[0][1] = "čĻĻĉĹćĶčĂćĉčĻĂĉĶĉčĂčąĶćĂĹĺĉĹąĈčĉĉąĎķ";
        array19[1][0] = "n0sOxJy02UI=";
        array19[1][1] = "ĊĻććĈĻąķĂĸĻĎĶĂĉĎĉĉĂĎĊĻĊĂČĻĆĶĉćķąąĉĶċ";
        array19[2][0] = "lsTDe0WIZwc=";
        array19[2][1] = "ĊĺĊĻĸĊĊķĂĶĸĺčĂĉĉċćĂķķķĊĂĻĶĸĆĸĸĊĹċĸĆĈ";
        By1337̸̷̡̹̥̘̩̮͇̫̭̮̤͉̯̯̤͖̒ͦ͋͗ͬ̍ͬ͆͐̂ͬ̀̂͐͒̌̎̚͟͜͜͝();
    }

    private static void By1337̸̷̡̹̥̘̩̮͇̫̭̮̤͉̯̯̤͖̒ͦ͋͗ͬ̍ͬ͆͐̂ͬ̀̂͐͒̌̎̚͟͜͜͝() {
        array19[3][0] = "buZHQ3YtUYY=";
        array19[3][1] = "ččĺķĻĎąĊĂĺķĆĎĂĉćĻĎĂččąķĂĎččĆĻĻĻĊĹĹķĊ";
        array19[4][0] = "gmzq50QSnGA=";
        array19[4][1] = "čĎĎĊčķĆĎĂċĸĆĺĂĉċĉČĂčĶČćĂąĈĸĺĈČĹćčČĉč";
        By1337̸̸̶̶̡̨̡̭̻̱͔̙̻̪̞̪̤̣̟̩̞̈́͋͛̏̅͊͑̿̌̍ͤͨ̾̍̀̌̊ͨ̕();
    }

    private static void By1337̸̸̶̶̡̨̡̭̻̱͔̙̻̪̞̪̤̣̟̩̞̈́͋͛̏̅͊͑̿̌̍ͤͨ̾̍̀̌̊ͨ̕() {
        array19[5][0] = "qvrjmf9fQnQ=";
        array19[5][1] = "ĊċĎĻĻĆķčĂĺąċćĂĉąĆĈĂĶĈČĺĂąąčČĈĊĸĺĆĊċĶ";
        array19[6][0] = "TQNa6Xsrhl0=";
        array19[6][1] = "ĸĆķĻĻċĶćĂċčċĈĂĉĶĎĶĂĎķĎĉĂĈķčĉĈĊĊćĹĹĻč";
        By1337̸̢̛̛̞̯̬̦̯̘̜͎̈̽̇͆̊ͧ͐̓ͦ̄͛͑ͧ̈͂ͦͧ͑ͫ̕͜͟͠͠͞ͅͅͅ();
    }

    private static void By1337̸̢̛̛̞̯̬̦̯̘̜͎̈̽̇͆̊ͧ͐̓ͦ̄͛͑ͧ̈͂ͦͧ͑ͫ̕͜͟͠͠͞ͅͅͅ() {
        array19[7][0] = "TAsQAhCHVyQ=";
        array19[7][1] = "ċČĹĺĻĸćĆĂĺċĆćĂĉĺČĺĂķĸćĻĂĹĻćčĆċĎĈćċĶċ";
        array19[8][0] = "njmnCaGtAVs=";
        array19[8][1] = "ķćķĈĶĊĈċĂĆąĹĉĂĉčċČĂčĈċķĂčČĉĸČĸķĻĻĻćĈ";
        By1337͡͏̢̢͖̞̭̼͙͇̹̤̙̖̣͑̐̓̄ͩͨ̈́̓͆̾ͦ̋̀̏̐ͪͫ̄ͥ̓͢͢͡ͅͅ();
    }

    private static void By1337͡͏̢̢͖̞̭̼͙͇̹̤̙̖̣͑̐̓̄ͩͨ̈́̓͆̾ͦ̋̀̏̐ͪͫ̄ͥ̓͢͢͡ͅͅ() {
        array19[9][0] = "Xpmcjd7CqRA=";
        array19[9][1] = "ĹćċčĺĈĶčĂĉčććĂĉčĸČĂĶĹĸķĂĆĊĆąĎĆąĶĎĺčĸ";
        array19[10][0] = "/jKPTzCPqcM=";
        array19[10][1] = "ĶĹĹĹćĺąĉĂĸĶĻċĂĉĉĹĸĂĎĊĸčĂąĺĶĊĆĹĺĶćĆĎĺ";
        By1337̵̷̴̡̳̗̤͈͉̲̬̙͈̹̺̩̦̳̒ͣͬ̊̓̅̅ͬ͌ͩ͊ͪ̆̌͂̐̅̉ͦ̋̚ͅ();
    }

    private static void By1337̵̷̴̡̳̗̤͈͉̲̬̙͈̹̺̩̦̳̒ͣͬ̊̓̅̅ͬ͌ͩ͊ͪ̆̌͂̐̅̉ͦ̋̚ͅ() {
        array19[11][0] = "VvdzStRs74M=";
        array19[11][1] = "ķąąĆķĺčĸĂĊĈĊĻĂĉĆċĊĂĶčċčĂĶĻćĻĊĈĈĈČĎćĻ";
        array19[12][0] = "W/f3B3B06Yo=";
        array19[12][1] = "ĺĻĆċąČĹćĂČčĹčĂĉĶčĹĂčąčķĂĆĎķĆĉČĈĈĹĈĺĻ";
        By1337̸̵̸̸̴̛̘̣̪͍͓͕̟͖̫̯̎ͪ̓̽̒̒̍ͤ̿̅͒ͬ͂̔ͦ̿́́̌̔̕͞͝ͅ();
    }

    private static void By1337̸̵̸̸̴̛̘̣̪͍͓͕̟͖̫̯̎ͪ̓̽̒̒̍ͤ̿̅͒ͬ͂̔ͦ̿́́̌̔̕͞͝ͅ() {
        array19[13][0] = "4PiXDAdiWow=";
        array19[13][1] = "ĈĹąċĶĹĹĎĂċķĸčĂĉćĻĎĂĎĻĻĶĂċĆċČķčćĎĸĺĎċ";
        array19[14][0] = "nHP59zEVKrs=";
        array19[14][1] = "ČĉĺąķĎķċĂĶčĶĸĂĉĶĸĆĂčĹĆĹĂĻćĆĊķĹĻćČĉĻċ";
        By1337͔̜̻͌͆ͯ͏̷̵̧̧̫̩̱̖̙̰͔̘͎̰̻̙̑̐̔͂ͯ̔͂ͫ͗̊͋ͦ͑̌̀́̕();
    }

    private static void By1337͔̜̻͌͆ͯ͏̷̵̧̧̫̩̱̖̙̰͔̘͎̰̻̙̑̐̔͂ͯ̔͂ͫ͗̊͋ͦ͑̌̀́̕() {
        array19[15][0] = "JT/oUk6zx90=";
        array19[15][1] = "ČĶĆķĸĺĺćĂČćĶĆĂĉĻćĶĂĶĉĊķĂĎĻķĊĉąĶććčĶč";
        array19[16][0] = "knuVg7T07zc=";
        array19[16][1] = "ČĆĎČąĹĹĆĂĶĎĊĈĂĉĸČĶĂčĊąĆĂćĎċĺĶĎĆćĺĻĊĹ";
        By1337̛͚̹̱͍̀̉͡͏̴̶̛̛̳̣̪̠̟̯̞̳̠͇̰̜̝̆̄̄̍͑̇ͮ̀̽̾ͥͫͩͪ();
    }

    private static void By1337̛͚̹̱͍̀̉͡͏̴̶̛̛̳̣̪̠̟̯̞̳̠͇̰̜̝̆̄̄̍͑̇ͮ̀̽̾ͥͫͩͪ() {
        array19[17][0] = "SPq2WmYTEBs=";
        array19[17][1] = "ĹĈĶĎĎĺĎąĂķĻķĎĂĉĊąĉĂĶĉĹĶĂĈąĆĹĻĺĸĉĆĻąĸ";
    }

    private static void By1337̴̨͍̱̝̮̣͇͕̞̮͉͖͍͍̮̬̬̰͓̩̽ͣ̌͂̓̒̓̽̽͌͋̽̅̍ͯ̀̾̿͝() {
        array20[0][0] = "H8T3DfzobF0=";
        array20[0][1] = "јЪјЬЬЭЯЩУјЬњЩУЪљїЫУїЦћјУЩјЯЮЮЦЭјЭЩњљ";
        array20[1][0] = "DJ6P2qqgvag=";
        array20[1][1] = "љЯјЫЫњЮЦУњЮЯјУЪЦЩЮУЮЯЯЧУЪЭЭЫЩЭЦЬјљЪЪ";
        array20[2][0] = "tQ5hL0aNROg=";
        array20[2][1] = "їЦЫљљїЯїУЫЭљЦУЪЭќЦУјљњЬУЯљњЯЬљЯЦЧШЮј";
        By1337̷̷̡̡̛̜̳͙̪̖͕͚̯̫͔̭͖̟ͭͣ̃ͧ̌͒̉͌͛ͭ̋͋̆ͤ́̅͆̆͂̔ͯ͒();
    }

    private static void By1337̷̷̡̡̛̜̳͙̪̖͕͚̯̫͔̭͖̟ͭͣ̃ͧ̌͒̉͌͛ͭ̋͋̆ͤ́̅͆̆͂̔ͯ͒() {
        array20[3][0] = "nVtDIob4o8M=";
        array20[3][1] = "ћљЭЫљїЦЮУЧЪЬїУЪЮљЮУЯЬЭЩУЧЫЮЯЫїЯШњШЮћ";
        array20[4][0] = "3h/k5+Ws4+c=";
        array20[4][1] = "њћШШїјїјУЪЫЩїУЪЦЮљУЮЯјЬУјЪЭЮЯќЧїЮЮЬј";
        By1337̵̵̧̠̼̗̮̯̺̦̹̝́ͩͨͣ͒͆̑͆͊ͪ̂̕͘̚͟͞͝͏̨̡̥ͥ̔̓́̆ͬ̕();
    }

    private static void By1337̵̵̧̠̼̗̮̯̺̦̹̝́ͩͨͣ͒͆̑͆͊ͪ̂̕͘̚͟͞͝͏̨̡̥ͥ̔̓́̆ͬ̕() {
        array20[5][0] = "TS503zUuFFs=";
        array20[5][1] = "ЦШЭњњћјјУЩЬЫШУЪћљЭУїљЦјУШЪЬјјЧћЪЪЮЪЩ";
        array20[6][0] = "DpBgkKXvXAY=";
        array20[6][1] = "ЦќїЩќЪЬЫУЬњЩЩУЪЪќћУЮЩЫћУЩЮЬјЩЪЬЯЪЫјљ";
        By1337̧͙͓͍̬̗̺̗͎̘̬̯͍̻͖̪̺͔̥̾ͦ̅̏͐̃ͣͩ̓̄ͥͮ̍̾̊ͤ́͟͟͝͠();
    }

    private static void By1337̧͙͓͍̬̗̺̗͎̘̬̯͍̻͖̪̺͔̥̾ͦ̅̏͐̃ͣͩ̓̄ͥͮ̍̾̊ͤ́͟͟͝͠() {
        array20[7][0] = "jPxq5egYpi4=";
        array20[7][1] = "ЭЬЦјљЬћЫУЩћїЮУЪњЬЭУїЦћЪУЩЯЪЪЪШЬЬЧЦЬШ";
        array20[8][0] = "II1xXXKkjSE=";
        array20[8][1] = "ћїјЮњЦЫЧУЬЭїљУЪЧјјУїЪЭЮУЬЫќЭШЪЩљЫњћШ";
        By1337̷̸̵̴̨̡̛̞̣̮͙̟̠̹̺͍͚̹̦ͧ̂̉̊̈͋̎́̇̒̈́ͫ͐̍̊̂̿ͯ͢͝ͅ();
    }

    private static void By1337̷̸̵̴̨̡̛̞̣̮͙̟̠̹̺͍͚̹̦ͧ̂̉̊̈͋̎́̇̒̈́ͫ͐̍̊̂̿ͯ͢͝ͅ() {
        array20[9][0] = "n6PukXaZ4Uw=";
        array20[9][1] = "ЫїЧјќїЩЧУјЭЬїУЪЬЬШУЮїћЯУЩЭЯќЫШЭЪЭЭќЦ";
        array20[10][0] = "INIE+yRNGeI=";
        array20[10][1] = "љЯЧЦќЪЩљУЫЫћЦУЪЭЭЦУјШјЬУћЯЩћќЬШјЦњЩЦ";
        By1337̢̛͇̫̠͍͎̣̩̲̭̺̱̃̀ͪ͊ͯͭ̒͗̚͟͠͏̧̡̰̠̖̻̪̝͙́͂͑̓̽͗();
    }

    private static void By1337̢̛͇̫̠͍͎̣̩̲̭̺̱̃̀ͪ͊ͯͭ̒͗̚͟͠͏̧̡̰̠̖̻̪̝͙́͂͑̓̽͗() {
        array20[11][0] = "bSsJaWQWc/c=";
        array20[11][1] = "ЫЮњјљЪќШУЮШЪЬУЪЯћЮУЮЪЦЩУЭїЦЯЪЯјШШњјћ";
        array20[12][0] = "c4uHhK5H1I4=";
        array20[12][1] = "ЮќЬћЬЫљЪУљїїќУЪќљЭУјЫЬјУЭћЫїЦЦјЭЩЫЧЧ";
        By1337̴̞͉͕͂̓̓̕͏̵̮͒͢͞ͅ͏̴̨̛̱̹̥̟̟̱ͯ͛͒̇̉̿̂̿̐̓ͫ̈́̓͜͠();
    }

    private static void By1337̴̞͉͕͂̓̓̕͏̵̮͒͢͞ͅ͏̴̨̛̱̹̥̟̟̱ͯ͛͒̇̉̿̂̿̐̓ͫ̈́̓͜͠() {
        array20[13][0] = "+wqz25FDD8A=";
        array20[13][1] = "љЪЪЧЯЦЦїУќЧћћУЪЪЩЯУїЦќЩУЦјЯЩњЬЮќЬћЬЦ";
        array20[14][0] = "nyu/Oq5qgIQ=";
        array20[14][1] = "ШњїЪќЭЫЬУЪЦїЩУЪќїњУјЮњЯУЧћЦЦЫљљЪЩїїљ";
        By1337̶̢̥̼͈͋̎̽̋ͫͨ̏ͩ̑̃̀̚͏̵̢͇̲̫̖̩̠͇͓͌͆̓̐̀ͪ̓̊͐̃̔̀();
    }

    private static void By1337̶̢̥̼͈͋̎̽̋ͫͨ̏ͩ̑̃̀̚͏̵̢͇̲̫̖̩̠͇͓͌͆̓̐̀ͪ̓̊͐̃̔̀() {
        array20[15][0] = "OUhf+RuNH4w=";
        array20[15][1] = "ћЧћЦШїќјУЧјЧЬУЪЮЦЪУЯћЧћУїЧЧЯЯЮљљјЮЮљ";
        array20[16][0] = "aaO+5+kku+s=";
        array20[16][1] = "јЭЩЦЦќЩЮУЯЪЮЪУЪќЧњУјћЫЭУЪЪќїЧќќЬЦЦјШ";
        By1337̵̖͔̝̪̥̻͕̮̖̟͇̩̲̠͍̑̈̔̃͒͗̅͋̎͛ͦ͊̂̄͐̉̐ͮ̊͜͠͏̧͈();
    }

    private static void By1337̵̖͔̝̪̥̻͕̮̖̟͇̩̲̠͍̑̈̔̃͒͗̅͋̎͛ͦ͊̂̄͐̉̐ͮ̊͜͠͏̧͈() {
        array20[17][0] = "aTcaETlJFbM=";
        array20[17][1] = "ШїЦЩќљќЦУЭњЪќУЪЪјјУјќћЧУЬћјїШЮЮЩјЪћќ";
        array20[18][0] = "KH1kCDVpANE=";
        array20[18][1] = "їЯЧњќЩЧќУЪјћјУЪЦЧЧУЯЬЮШУћЬљЧЮїЦќќЩћЮ";
        By1337̢̡̨̟͕̝̖̘̰̹̙͉̱̦͖͙̭̫̗̼ͮ̑̈́̾ͣ͛ͣͯ͊́ͪͥ̃̕͜͡͞͏̿͡();
    }

    private static void By1337̢̡̨̟͕̝̖̘̰̹̙͉̱̦͖͙̭̫̗̼ͮ̑̈́̾ͣ͛ͣͯ͊́ͪͥ̃̕͜͡͞͏̿͡() {
        array20[19][0] = "hztsc6l32Io=";
        array20[19][1] = "ћїЮЧЬЭЫЦУјњЩЫУЪЭЭњУїШЫЦУШЮШЬњљќћЭњЪШ";
        array20[20][0] = "fqDfp5IIvbY=";
        array20[20][1] = "ЬЭЯЧЬќЯЬУЦЪЭЬУЪјјїУїЦјјУїќЪЦћЦћЬљќЩЭ";
        By1337̪̤̣̱̠̹̦͙̤̞̤̰͓̼͉̲̐̏̃̎ͩ̽̐ͬ̍̀̎̎̈͋ͬ̒̒͗̈́̚̚͞͝ͅ();
    }

    private static void By1337̪̤̣̱̠̹̦͙̤̞̤̰͓̼͉̲̐̏̃̎ͩ̽̐ͬ̍̀̎̎̈͋ͬ̒̒͗̈́̚̚͞͝ͅ() {
        array20[21][0] = "uWEj7u/KIsU=";
        array20[21][1] = "ЯЮЭљЯЪќЪУЩїљЬУЪЦћћУїЩЭЮУќЩЩњЩЦћЯЭћЬЫ";
        array20[22][0] = "kMJAhD+KOz8=";
        array20[22][1] = "ЦЬЮЯјјјЪУЩјњЧУЪЫЫШУЯЪЫјУЩЬњњЧЩќљїќјњ";
        By1337̴̢̨̛̜̍ͧ̐͐ͯ͏̜̟̰̠̠͍̥̹ͪ́́̀̂ͭ̀͛̇̈́̃̆̆̃ͣ̐͗̀̽̚͡();
    }

    private static void By1337̴̢̨̛̜̍ͧ̐͐ͯ͏̜̟̰̠̠͍̥̹ͪ́́̀̂ͭ̀͛̇̈́̃̆̆̃ͣ̐͗̀̽̚͡() {
        array20[23][0] = "/XdnyOLXpLw=";
        array20[23][1] = "ЬњШЧЯЭЪљУјљїЧУЪЦќњУЮЦљЮУЯЯљќЮЦЦјЮЮЫЩ";
        array20[24][0] = "B7uPSpWccOs=";
        array20[24][1] = "їїјќЪјњЮУЫїЧљУЪњјћУјЪЧЭУЦЫЦљњШЦЭЯћЪЫ";
        By1337̫̹̠̍̋͑̈́̂ͤ͊̚͏̸̴̨̥͇͓͉̖̤̬̩̹̘̀ͪͭͯ̈͒̒ͩ͋̿̌̚̚͘͡();
    }

    private static void By1337̫̹̠̍̋͑̈́̂ͤ͊̚͏̸̴̨̥͇͓͉̖̤̬̩̹̘̀ͪͭͯ̈͒̒ͩ͋̿̌̚̚͘͡() {
        array20[25][0] = "csI20FTk7zY=";
        array20[25][1] = "ЩќЭЭШЮЩШУќЭШЩУЪќњјУЯЭћќУЬљћШЩЭЮћШШШЪ";
        array20[26][0] = "bWFBTDYXxdI=";
        array20[26][1] = "ќЧћїЦјЦќУЩљЫЩУЪЩћЯУЮШјЭУјЯЯЫЮЬЮШќЧЦЭ";
        By1337̴̴̥̟̪̰͉̞̫̼̏͂ͪͫͧͧͣ͋͆̽̍̐ͭ̓ͮͩͯ͑̕̕͏̰͕̻̌̋͏̶̴̀();
    }

    private static void By1337̴̴̥̟̪̰͉̞̫̼̏͂ͪͫͧͧͣ͋͆̽̍̐ͭ̓ͮͩͯ͑̕̕͏̰͕̻̌̋͏̶̴̀() {
        array20[27][0] = "tmSM8O/vznw=";
        array20[27][1] = "ЫЭЪЫЫЩњЫУќШќЩУЪћњЯУїЫљЬУќШЦїћћљШњЫћЪ";
        array20[28][0] = "H7dZc//S1oM=";
        array20[28][1] = "ЯЪјЪЧЧЬЮУљЫШЬУЪЦћЫУЯЯЩШУЬќќШћЯћЯЯїЭї";
        By1337̶̡̢̭̭̜̻͖̫̰̹͙̼̤̦̖͚ͤ̾́̈͋͊̏͒̃ͮͮ̔ͫ̔̓̊͌͗̄͜͟͡ͅ();
    }

    private static void By1337̶̡̢̭̭̜̻͖̫̰̹͙̼̤̦̖͚ͤ̾́̈͋͊̏͒̃ͮͮ̔ͫ̔̓̊͌͗̄͜͟͡ͅ() {
        array20[29][0] = "rmEiF0C5TGg=";
        array20[29][1] = "ЮЯЧЦїїЫЯУЯЦљЫУЪШШїУїјїЪУїЬЪЬЭљЬЦїїЯЪ";
        array20[30][0] = "BFzX5P9bN9Y=";
        array20[30][1] = "їњћЮљћќјУЯЬЩЦУЪћћјУЮњЬШУЩЮјЮЮЭќЦїїњЯ";
        By1337̗͓̺̯͙͓̩̻̦̪̫̘̰̟̀ͫͪ́ͥ͛ͤͯͮ̆̑̔ͩ̇̀̓̀͂ͭ̈̕͘͘͟͜͝();
    }

    private static void By1337̗͓̺̯͙͓̩̻̦̪̫̘̰̟̀ͫͪ́ͥ͛ͤͯͮ̆̑̔ͩ̇̀̓̀͂ͭ̈̕͘͘͟͜͝() {
        array20[31][0] = "xK9703GflG4=";
        array20[31][1] = "ћЬЮјњњїјУЦљќљУЪЫЦјУјЫЭїУЦЯјЮЯЭќЮЩљЩШ";
    }

    private static void By1337͚͉̘̍̾̽̓͗͑̍͌͟͏̛͙͖͔̥͖͔̙̤̻̗͍͖̖̤͂̾́͋̓̓̋ͮͯ͝͞͞() {
        array21[0][0] = "b3CGCv6hK2Q=";
        array21[0][1] = "ᅲᆣᆣᆠᆣᆡᅯᆢᅬᆢᆡᅷᅱᅬᅳᆤᆥᅰᅬᅷᅴᅴᅯᅬᅲᆠᆢᆠᅳᅯᅲᅵᅷᆡᅸᅵ";
        array21[1][0] = "b/lRBj4mCB8=";
        array21[1][1] = "ᆠᅲᅳᅸᅲᅶᆤᆠᅬᅱᅳᅰᆢᅬᅳᅷᆢᅲᅬᅷᅵᅳᅱᅬᆡᅰᅳᅰᅷᅸᆢᆡᅱᆣᅷᆢ";
        array21[2][0] = "57ZOIqaycyk=";
        array21[2][1] = "ᆠᅱᅯᅯᅴᅰᅵᅴᅬᆤᅯᅳᆥᅬᅳᆢᅲᅷᅬᅸᅴᆡᅳᅬᆤᆤᅲᆥᅰᆣᆡᅳᆤᅴᅲᅯ";
        By1337̵̶̶̢̡̡̛̲͈͙̮͉̬̠̻̘͚̣̞̦̜̟̜̤͍̋͒͗̽̄ͮͧ͊͊ͪͮ̚͜͜͝();
    }

    private static void By1337̵̶̶̢̡̡̛̲͈͙̮͉̬̠̻̘͚̣̞̦̜̟̜̤͍̋͒͗̽̄ͮͧ͊͊ͪͮ̚͜͜͝() {
        array21[3][0] = "r4P0vVz9uyo=";
        array21[3][1] = "ᆡᅰᆢᅴᆠᅲᅷᅴᅬᅸᅵᆡᆡᅬᅳᆥᆢᆥᅬᆠᅵᅲᅰᅬᆥᅯᅱᅯᅯᅱᅸᆤᅴᆤᆡᅶ";
        array21[4][0] = "lhgmP66TMYE=";
        array21[4][1] = "ᅷᅯᅲᅯᆤᆤᅸᅸᅬᅴᅵᆡᅸᅬᅳᅳᅱᆣᅬᆠᆡᅵᅰᅬᅰᅸᅴᅲᅲᅸᆥᅷᆠᅷᅵᅷ";
        By1337̠͈͎̠͚̋̆ͮ̒̕͢͏̶̛̠̩̝̪̲͖̭̺̫̱̖̞̥́̒̊̇̎̅ͮ̄̾̀ͪ͜͜();
    }

    private static void By1337̠͈͎̠͚̋̆ͮ̒̕͢͏̶̛̠̩̝̪̲͖̭̺̫̱̖̞̥́̒̊̇̎̅ͮ̄̾̀ͪ͜͜() {
        array21[5][0] = "MWwuF31X/jM=";
        array21[5][1] = "ᆣᅲᆢᆢᆣᅯᆥᆡᅬᅷᆤᅳᅶᅬᅳᅲᅴᅲᅬᅷᅰᆡᅯᅬᆢᆣᆠᅵᅰᅳᅯᆢᅰᅶᅸᅳ";
        array21[6][0] = "cse7DzP6o9s=";
        array21[6][1] = "ᆢᆤᅳᅷᆣᆠᆤᅶᅬᅲᅲᅱᆤᅬᅳᅱᅶᅳᅬᅸᅷᅴᆤᅬᅳᅸᅯᅶᆤᆢᅷᅯᅵᅰᅱᅯ";
        By1337̴̸̶̢̡̧̩̤͕̥̖͖̫͍̙̩̻̰̬͐ͫ̍̅̿͒́ͮ͊ͮ͗͛ͦͫ́ͩ͘͘͟͜͢();
    }

    private static void By1337̴̸̶̢̡̧̩̤͕̥̖͖̫͍̙̩̻̰̬͐ͫ̍̅̿͒́ͮ͊ͮ͗͛ͦͫ́ͩ͘͘͟͜͢() {
        array21[7][0] = "nWXhPg48wJ8=";
        array21[7][1] = "ᆥᅲᅵᅯᅵᅲᅳᆠᅬᅸᅱᆣᆠᅬᅳᆠᅶᆡᅬᆠᆡᅱᅵᅬᅵᅯᅶᅳᆠᅲᅷᆠᅲᅳᆣᆢ";
        array21[8][0] = "tKXoRREkH0E=";
        array21[8][1] = "ᅴᅯᅯᆥᅴᅯᅱᅰᅬᆤᆠᅴᅲᅬᅳᆥᅸᅸᅬᆡᅷᅴᅱᅬᆤᆤᅱᆡᅰᆣᅶᆣᅰᅶᅵᅰ";
        By1337̷̸̵̛̛̺͈̗̜͈̪͉̗̝̣͓̩̮͇͔ͦ͒̄͑ͪͪͭ̈̎ͮ͆ͯ́͛͗̚͜͝ͅͅ();
    }

    private static void By1337̷̸̵̛̛̺͈̗̜͈̪͉̗̝̣͓̩̮͇͔ͦ͒̄͑ͪͪͭ̈̎ͮ͆ͯ́͛͗̚͜͝ͅͅ() {
        array21[9][0] = "KF88GvfrxBk=";
        array21[9][1] = "ᆤᆤᅶᆠᅵᅶᅷᅵᅬᅯᅱᆠᅴᅬᅳᅷᅳᆤᅬᆡᅸᆡᆡᅬᆥᅱᆣᅸᅴᆢᆡᅸᆢᅰᅳᅵ";
        array21[10][0] = "BPwzH72hT2M=";
        array21[10][1] = "ᅵᅵᆣᅶᅷᅸᅴᅰᅬᆤᆠᆢᅯᅬᅳᅶᆡᆣᅬᅷᆤᅶᆥᅬᅳᅴᅵᅳᅯᅰᅷᆤᅵᆤᅳᅴ";
        By1337̷̨̟̤̣̥̺̘̖̜͉͛ͬ͊̾͑͊ͭ̾͌ͥͩͦͭ̂̀͛͗̇͂̇͘͟͝͡͏̹̯̊̾();
    }

    private static void By1337̷̨̟̤̣̥̺̘̖̜͉͛ͬ͊̾͑͊ͭ̾͌ͥͩͦͭ̂̀͛͗̇͂̇͘͟͝͡͏̹̯̊̾() {
        array21[11][0] = "KnRoFxC4f/o=";
        array21[11][1] = "ᅷᅸᆡᆡᅰᅶᆥᆠᅬᆤᅯᅳᅰᅬᅳᆥᆠᆢᅬᅷᅸᅰᆢᅬᅰᅶᅶᆥᅱᅵᆥᅶᆤᅴᆥᅵ";
        array21[12][0] = "Jc5qd0+krTs=";
        array21[12][1] = "ᅰᅰᅷᅷᅰᅳᆠᅲᅬᅲᅷᆣᆡᅬᅳᅴᅱᆡᅬᆡᆢᅵᅷᅬᅳᅲᅰᆥᅲᅰᅸᅳᆣᅯᅷᆡ";
        By1337̶̧̰̼̙̺̺͚̻͍͈̼͇̭̯̭͉͔͓̲̼ͩ̽̾͑͆ͤ̋ͫ́̽ͨ̆̏̊̌̍́͟ͅ();
    }

    private static void By1337̶̧̰̼̙̺̺͚̻͍͈̼͇̭̯̭͉͔͓̲̼ͩ̽̾͑͆ͤ̋ͫ́̽ͨ̆̏̊̌̍́͟ͅ() {
        array21[13][0] = "3p52UJtvQTE=";
        array21[13][1] = "ᅸᅵᅶᆡᆢᅴᆢᅰᅬᅷᆣᅳᆥᅬᅳᅷᅳᆡᅬᆠᅯᅱᆠᅬᅰᆥᅶᅷᆣᆢᆤᅵᅱᆤᆢᅯ";
        array21[14][0] = "NU5Ddkib380=";
        array21[14][1] = "ᆠᆥᅵᆡᅳᅯᆢᆤᅬᅸᆢᅰᅱᅬᅳᅲᅲᅸᅬᅷᅲᅸᆢᅬᅸᅵᆠᅶᅶᆣᅰᆥᅱᅰᅳᅴ";
        By1337͍̭̤͔̪̹͑ͧ̀ͮ̍͌ͨ̍͗ͪ̎́͘͘͜͏̵̨͖̟͈̬̜̘̘̹̦ͮ̂̀̍͌̈́͜();
    }

    private static void By1337͍̭̤͔̪̹͑ͧ̀ͮ̍͌ͨ̍͗ͪ̎́͘͘͜͏̵̨͖̟͈̬̜̘̘̹̦ͮ̂̀̍͌̈́͜() {
        array21[15][0] = "ZCiJCJ/NVS4=";
        array21[15][1] = "ᅶᅯᅸᆥᆢᅵᅵᅴᅬᅶᆠᆢᅯᅬᅳᅰᅱᅷᅬᅷᆥᅱᅵᅬᅲᅲᆠᆥᅱᅯᅸᆥᆤᅰᅷᅯ";
        array21[16][0] = "hRtotr7ia60=";
        array21[16][1] = "ᆣᅳᅶᆡᆣᅴᅵᅱᅬᆢᅷᅶᅳᅬᅳᅸᅳᅶᅬᆠᅳᆣᅶᅬᆠᆢᆡᅲᅴᅯᆢᅱᅸᅸᅷᅳ";
        By1337̶̵̧̧̡͖̯͔̯̳͕̞̜̯̦͔͌͗̋̐̐̉͂͊̀́ͦ̑̎̆̇͗̓̃͜͞͝͠͡͞();
    }

    private static void By1337̶̵̧̧̡͖̯͔̯̳͕̞̜̯̦͔͌͗̋̐̐̉͂͊̀́ͦ̑̎̆̇͗̓̃͜͞͝͠͡͞() {
        array21[17][0] = "lquqHIbE2xM=";
        array21[17][1] = "ᅴᅸᅴᅳᅷᅲᅷᅳᅬᅵᆤᅯᅷᅬᅳᅰᅸᅲᅬᅷᆤᅵᅷᅬᆡᅴᆤᅲᅱᅱᆠᅵᅶᅲᅰᅴ";
        array21[18][0] = "ZdD+cX25MmE=";
        array21[18][1] = "ᆣᅶᅶᅷᅯᅲᅸᅴᅬᅲᅵᆥᅲᅬᅳᅲᅯᅷᅬᅸᅯᆠᅲᅬᅵᅵᆤᅶᅲᆡᅴᆠᅰᅴᅴᅵ";
        By1337̧̦̜̥̦͎̫ͦ̊̂͐͑͛̿ͭ̚͟͏̴͙̭͎̜͕̤̻ͦ̔ͤ͋̂͌͑͂ͪͤ̔̑͐͡();
    }

    private static void By1337̧̦̜̥̦͎̫ͦ̊̂͐͑͛̿ͭ̚͟͏̴͙̭͎̜͕̤̻ͦ̔ͤ͋̂͌͑͂ͪͤ̔̑͐͡() {
        array21[19][0] = "/6bMfM3js/A=";
        array21[19][1] = "ᆤᆤᆠᅱᆠᅷᅯᅶᅬᅱᅰᅸᆤᅬᅳᆠᅯᅴᅬᆡᆢᅵᅱᅬᆠᅶᆥᆣᅷᅰᆤᆠᅳᅲᅵᆤ";
    }

    private static void By1337̷̶̵̢̹̼͚̲̥̣̘͈̯͎͑̈͌̄͌͛ͩ̌ͤ̾͌͊ͮ̒ͥ̉͆ͧ̈̾͘͜͢͜ͅͅ() {
        array22[0][0] = "oSn7EHsjCzk=";
        array22[0][1] = "૫િ૭ુઽ૯૯઺ષ઺ાૃ૭ષાા૰઼ષૂ૫૰઺ષાી૫૮૬઼ૂ૮ૃ૰ઽી";
        array22[1][0] = "9kzKCIrMQ7M=";
        array22[1][1] = "૰ીૃ૫૬ઽૂ૮ષૂ઼ા૰ષા૰િ઼ષ૬઻ૃઽષ઻૯઺ૂ઻૮૮઼ીીુ૭";
        array22[2][0] = "EKVCCOWkbvI=";
        array22[2][1] = "૰ૂ૫૫િ૬ા૬ષ૫ઽ૯૮ષા૫૯૬ષૂાૂ૭ષ૮઼ા૯૯ૃી૰૮૭઻ી";
        By1337̷̷̴̨̧̡̛͇̭͚̞̜̩̠͍̺͔̘͉͚͈̓͆ͬ̃̒ͧͥ̍̈́̅͆͛̃́͢͝͠͡͡();
    }

    private static void By1337̷̷̴̨̧̡̛͇̭͚̞̜̩̠͍̺͔̘͉͚͈̓͆ͬ̃̒ͧͥ̍̈́̅͆͛̃́͢͝͠͡͡() {
        array22[3][0] = "g3Vx1q2xTkg=";
        array22[3][1] = "ૂ઼ૂ૮ુ઼ુ૯ષ૯૭઼ુષાૃૃ૰ષ૫ઽ૫૰ષ૭ા૭૬ૃ૰ઽુ૰઼ુ૰";
        array22[4][0] = "MUJ4WXSUpCk=";
        array22[4][1] = "ુ૫ીૃૂ૯ુૃષ઺ૂી઺ષા઻૫૬ષૂૃુૂષ૬ુા઻઻૫઻ાીૃ઼૭";
        By1337̸̡̨̢̛͇̫͎̩̣̗̺͕̘̼̙̭̯̱̙͓͔̮̠̂͂͌̓ͨͨ͗͊̔̍͛ͩ̔́͘͜();
    }

    private static void By1337̸̡̨̢̛͇̫͎̩̣̗̺͕̘̼̙̭̯̱̙͓͔̮̠̂͂͌̓ͨͨ͗͊̔̍͛ͩ̔́͘͜() {
        array22[5][0] = "QewbgtRiiLk=";
        array22[5][1] = "૯ી૯િુ઼઼ૃષીાુઽષા૮૬૭ષૃ૬૭૰ષ૮૯ી૰઺ાઽ૬ા઺઺૫";
        array22[6][0] = "7endjxqdKtM=";
        array22[6][1] = "ૃ઻ુી૭૯ુુષ૯િ૯ૂષા઺ીાષૂ૰ુ઻ષૂૃૃ઼ીુ઻૯઼ઽ઻૭";
        By1337̷̛̛̜̭͉̥̙̠̫̭͚̬̣̜̱͎͖̏ͬ͗ͮͬ̑͑͛ͫͤ̿ͭͦ̍̿̉ͣ̕͜͟͟͠();
    }

    private static void By1337̷̛̛̜̭͉̥̙̠̫̭͚̬̣̜̱͎͖̏ͬ͗ͮͬ̑͑͛ͫͤ̿ͭͦ̍̿̉ͣ̕͜͟͟͠() {
        array22[7][0] = "z9OzHb8+tuo=";
        array22[7][1] = "઺઺ૃ૭઻ાિ઺ષ૫ી઺૬ષા૮઼ઽષૂા઺િષ૭ૂ૫ી૰઻઻૮ૃીૃ૯";
        array22[8][0] = "GOCbbW70r0k=";
        array22[8][1] = "૬૬ૃૂૂ૬ઽ૬ષ઺૫૭૯ષાૂ૮઻ષ૬૰઻૯ષ઻૰૭િૃિ૮૮઻ુાૃ";
        By1337̴̨̧̖̱̮͖̘͔͎̱͛̌̋͐̈̊ͮ̒̏͑ͤ̀̋̍̇ͭ̚͟͠͏̺͉̖̃ͪ̒̎͠͝();
    }

    private static void By1337̴̨̧̖̱̮͖̘͔͎̱͛̌̋͐̈̊ͮ̒̏͑ͤ̀̋̍̇ͭ̚͟͠͏̺͉̖̃ͪ̒̎͠͝() {
        array22[9][0] = "ILhaqmbz7iY=";
        array22[9][1] = "૫ીૂ૫િઽઽ઺ષ૭ા઻ીષા઺ૂ૰ષૃ૯઺ૃષ઼ી૬૮ુ઼૬ીાુ૮ૂ";
        array22[10][0] = "kOdRig9qQco=";
        array22[10][1] = "ી૭઺િીૂૃ૮ષાુિ૫ષાી૯૰ષ૫ૂ૮઺ષ૬ા૬઼઺૫઺઼ુિ૮૮";
        By1337̡̖̱̪̱͊̏̆͏̨̮͔̖̺̭̼̠̭̣̽̄ͧ͂͑̉̈ͣͦ͋ͬ͌͏̶͎͇͇̐̊͒ͅ();
    }

    private static void By1337̡̖̱̪̱͊̏̆͏̨̮͔̖̺̭̼̠̭̣̽̄ͧ͂͑̉̈ͣͦ͋ͬ͌͏̶͎͇͇̐̊͒ͅ() {
        array22[11][0] = "x1L7jnvqTNI=";
        array22[11][1] = "ઽિ૰૰ી૰ીૂષી૭ુ઺ષા૫૭઺ષ૬૮૮ૂષૃા૬઼િ૭૫૯િ૰૫઺";
        array22[12][0] = "pA0To7FU52M=";
        array22[12][1] = "૯ૂ૫૭ઽ૯ુ૮ષ૫ુ૭૭ષાિ૮૰ષૃા૫૯ષ઼૭૫઻ાિ૯ઽ૮ઽ૬ી";
        By1337̶̶̸̶̸̧̡̨̛̱̹̮̘͓̪̩̜͉̯͇̻͂̒͂̂͊̊̔̈́ͪ͋̃ͦ̌̚̚̚͢͝();
    }

    private static void By1337̶̶̸̶̸̧̡̨̛̱̹̮̘͓̪̩̜͉̯͇̻͂̒͂̂͊̊̔̈́ͪ͋̃ͦ̌̚̚̚͢͝() {
        array22[13][0] = "HH+vXtVE9P0=";
        array22[13][1] = "ઽ૭૫૫ૃિ૰૯ષ૯૯િીષાિ૭૬ષ૫૬ીઽષ૫ઽ૭઻ી૯઺૬૫િ૮઻";
        array22[14][0] = "b9ItCCkfp7A=";
        array22[14][1] = "૬૬૫ીૃુ઼ૂષ૮૬઼ાષાુ૭ૃષૂઽુાષૂ૫૯૮઻૫૯઼૯૯૫ૃ";
        By1337̴̶̡̛̞̝͙̩̥͚̮̟̞̜̫͉̯͍͙̰͈̀ͦ͆̐͌ͫ̈̔̏̏̒ͬ̓ͥ͋̕̚͢͡();
    }

    private static void By1337̴̶̡̛̞̝͙̩̥͚̮̟̞̜̫͉̯͍͙̰͈̀ͦ͆̐͌ͫ̈̔̏̏̒ͬ̓ͥ͋̕̚͢͡() {
        array22[15][0] = "TK0wltcl9E8=";
        array22[15][1] = "઺઻ઽ૬઺઼ીુષ઻઺૯૭ષા૯ૃઽષ૫઺૬઺ષઽ૯૬૮ુૂ૮ા૭૭૰૬";
        array22[16][0] = "2ekJYu0s4ks=";
        array22[16][1] = "૰૰ુઽ૫૮૯ીષ઼૯૰઻ષા૬૮૮ષ૬઺઻૬ષુ૭ીીુુ૭ૂૂ઻િ઺";
        By1337̷̶̶͇̤̠̰̣̣̭̫̘̭̤̭̝͚̬͕ͣ͛ͯ̾ͤ̽̃̍̿ͨ̇̏ͧ̋̇͟͜͢͡͠͝();
    }

    private static void By1337̷̶̶͇̤̠̰̣̣̭̫̘̭̤̭̝͚̬͕ͣ͛ͯ̾ͤ̽̃̍̿ͨ̇̏ͧ̋̇͟͜͢͡͠͝() {
        array22[17][0] = "+EeREGEphEo=";
        array22[17][1] = "૰ુૃ઼૰૰૯ઽષઽ૬િૃષા૯ુ઼ષૂ઻૯ીષૂ૭૫ાૃ઻૰૰૯઻ઽા";
        array22[18][0] = "4esgbBMe0rg=";
        array22[18][1] = "ૂ૬િ૫઼ૂ૰િષ઺઺઻ઽષાાૃિષ૫ીી઼ષી૰ાી઻િ૫૯૮઼ાઽ";
        By1337̶̴̨̧̳̰̘̟̠͉̯̪͚̼͕̗͖̲̟͐̀͐ͥͥ̍̓̈́̀̏̃̂́̿̇ͣ́͐͡͠͠();
    }

    private static void By1337̶̴̨̧̳̰̘̟̠͉̯̪͚̼͕̗͖̲̟͐̀͐ͥͥ̍̓̈́̀̏̃̂́̿̇ͣ́͐͡͠͠() {
        array22[19][0] = "3SaoWbMWxaQ=";
        array22[19][1] = "ૃ૯ુ૰૮ઽઽ૰ષિ૬૯ાષા૬ૂ઺ષૂ૮ુ઺ષ૭ીૃૃ૰િુીિ૰઼઼";
        array22[20][0] = "E/6rKZwqN/I=";
        array22[20][1] = "ૂ઼૮઺૰૬૮૬ષ઻઻ૃ઺ષાિ૭઻ષૃુ઼ાષ૰૯૫઼ાિૂ૭૰઼૰઼";
        By1337̵̵̨̨̛͔̤͇̰͓̝̼̫̦̙͍̥̲͎̭̯ͥͭ̌ͣͪ̏̈́̌ͨ͗́͒̌ͧ̃̎̅ͮ͞();
    }

    private static void By1337̵̵̨̨̛͔̤͇̰͓̝̼̫̦̙͍̥̲͎̭̯ͥͭ̌ͣͪ̏̈́̌ͨ͗́͒̌ͧ̃̎̅ͮ͞() {
        array22[21][0] = "8+Ups4uz4Xc=";
        array22[21][1] = "૰૭ૃ઺઻૰઼૯ષ૮૭૮૫ષા૰ઽ૮ષૃ઺ી૬ષ૮઺૫૰઻૬઼૭િ઼઼ઽ";
        array22[22][0] = "ARc1vGJpGvo=";
        array22[22][1] = "ુ૯૬૮ૂ઼િ૰ષૂ૭૭૮ષા૮઺ૃષૂ૮૰૫ષ઻ઽ઼૬૯ુ઺ૃ઺ુિ઻";
        By1337̵̷̛͈̲͙͚̪̦̠̖̝͕̟̙̍͐̂̇͆̊̓̽ͧ̿̄͗͋̑ͥ͛ͤͩ̒ͨ͘̕͡͡ͅ();
    }

    private static void By1337̵̷̛͈̲͙͚̪̦̠̖̝͕̟̙̍͐̂̇͆̊̓̽ͧ̿̄͗͋̑ͥ͛ͤͩ̒ͨ͘̕͡͡ͅ() {
    }

    private static void By1337̴̵̴̵̢͓̱̲̞̱͓̥͚͊̓̉̑̈͋̐̊ͯ̏̉̊̇̒̈́ͥ͊̎ͩͥ̎̇͟͠͡ͅͅ() {
        array23[0][0] = "5Kn2+jFWtc8=";
        array23[0][1] = "ۍڛڝۋۊښڠڟڕژڛڟڠڕڜۍڛۊڕڡڝڛڜڕژڞێڜڟڙڡۊژڞۉڜ";
        array23[1][0] = "7FNmQeHB4wg=";
        array23[1][1] = "ۋڠڠڙڛڛژڝڕڝڟڛڡڕڜژڙڟڕۉڜڜڟڕڡێڟڞښۍڜۋۍۉۋژ";
        array23[2][0] = "d4l62vfA2ao=";
        array23[2][1] = "ێۉۋڛڙڙڜڙڕژیڡۊڕڜښڛڙڕڠژڝێڕژڛیڡیۍڜڝڝڙڛی";
        By1337̷͉̬̹͉̹͇̬͓͉͇̰̱̲̟̙͕̖̦̜̦̠̗̱͊͂ͩ̈̏̽̐̊ͪ̉̋̿͑͢͢͝();
    }

    private static void By1337̷͉̬̹͉̹͇̬͓͉͇̰̱̲̟̙͕̖̦̜̦̠̗̱͊͂ͩ̈̏̽̐̊ͪ̉̋̿͑͢͢͝() {
        array23[3][0] = "NLsjUhJvRmE=";
        array23[3][1] = "ێڛڜڞڟۋڠڠڕژۋۉێڕڜڠڡۋڕۊڟژیڕڙۋڛۋۊۉێښیڟۉڡ";
        array23[4][0] = "AiJMjVVNu1E=";
        array23[4][1] = "ڝیڝڛۉڞڛڟڕۉۊۊڙڕڜۋۊۉڕڠڛڠیڕڟۋڛڠژڛژێڞییۊ";
        By1337̶̵̸̨̥̭̙͍̘̗̤͈̗̰͍̝̫̂̔ͣ̅̃ͫͧͪ̓̒͋́ͬ̐̆̉̐͂ͯ̌̈͡͝();
    }

    private static void By1337̶̵̸̨̥̭̙͍̘̗̤͈̗̰͍̝̫̂̔ͣ̅̃ͫͧͪ̓̒͋́ͬ̐̆̉̐͂ͯ̌̈͡͝() {
        array23[5][0] = "wxZ+S9INQVQ=";
        array23[5][1] = "یژڙڝڟڞژڟڕۍێۊۉڕڜښژڝڕۉڙڡڜڕڝژښۊڞۊڡڡیښڛڟ";
        array23[6][0] = "fKlvGXWAPhs=";
        array23[6][1] = "ژێڙۋۊۋڞۉڕۋڡژۉڕڜڝۊۊڕڠڠۋۊڕڝڞښۉۍۉڟیڛڝڝڞ";
        By1337̴̸̵̩͔̻̗̩͉̺̭̙͍͖̯̮̙̗̗͉̰̝̙̦̤ͬ͗̈͑̍̆ͯ̍̂͐̇͑̚̕͠();
    }

    private static void By1337̴̸̵̩͔̻̗̩͉̺̭̙͍͖̯̮̙̗̗͉̰̝̙̦̤ͬ͗̈͑̍̆ͯ̍̂͐̇͑̚̕͠() {
        array23[7][0] = "mMxKS43m1eI=";
        array23[7][1] = "ڡڟژۍښژڞۊڕڡۍۊژڕڜۊڛڠڕڠۊییڕۍۋښێښڝۊڛڡۍڞی";
        array23[8][0] = "Kv7QYaJxYwQ=";
        array23[8][1] = "ۍێڙۊڠڜڙیڕڛڟڙښڕڜښڞڛڕڠیڟیڕیڠڠژژۋڡۍۍۉژۉ";
        By1337̶̡̢̡̡̧̠͍͖̲͖̜͙̙͖̟ͦͣ̔̎͌ͦͣ͒̎͂ͤͤ̐̑͋͐ͩ̀̌̚͟͞͝ͅ();
    }

    private static void By1337̶̡̢̡̡̧̠͍͖̲͖̜͙̙͖̟ͦͣ̔̎͌ͦͣ͒̎͂ͤͤ̐̑͋͐ͩ̀̌̚͟͞͝ͅ() {
        array23[9][0] = "hyBiXzWfmXQ=";
        array23[9][1] = "ۉڜۍڛۉۍۊۍڕۍێڠیڕڜیڛێڕۊښژێڕۉښۉۍۋڛێێڡڙڝڡ";
        array23[10][0] = "8KfycKPoOcE=";
        array23[10][1] = "ڡۊۉۍڙیڠۋڕۍڙښښڕڜڟڛۋڕڡڜڙۋڕۉڜڞێڟڡڠڞیۍڛڛ";
        By1337̵͖̂͌́̿̕͏̴̷̢̡̻̰̬̗͇̗͓̻̗́̌̊̃ͣͪͨ̔͗̎̀̅̓̏͘̕͟͡͞();
    }

    private static void By1337̵͖̂͌́̿̕͏̴̷̢̡̻̰̬̗͇̗͓̻̗́̌̊̃ͣͪͨ̔͗̎̀̅̓̏͘̕͟͡͞() {
        array23[11][0] = "OWViAqergMk=";
        array23[11][1] = "ۋێژۍۉۉۋڙڕێژۋۊڕڜێیڞڕڠڜژێڕڙڙۋژښڝێڝۊێڜڟ";
        array23[12][0] = "1wxGibJBYnU=";
        array23[12][1] = "ڙۊڞڡڟۋژڜڕڛڡۍڡڕڜڜڝڜڕڡڙۉڙڕیڠۉیژڞۍۊڟڝڡۊ";
        By1337̡̡̢̡̡̡̭̲̫̭̙̑̈̇́̐͐̎̏̏ͯ̒ͪ̎̀͑ͣ̀ͦ͊ͮ̚̚͢͜͜͢͜͟͠();
    }

    private static void By1337̡̡̢̡̡̡̭̲̫̭̙̑̈̇́̐͐̎̏̏ͯ̒ͪ̎̀͑ͣ̀ͦ͊ͮ̚̚͢͜͜͢͜͟͠() {
        array23[13][0] = "Cqc0ZgfLg6s=";
        array23[13][1] = "ڞۋڡڛڛێښژڕڠڙۋڜڕڜۍڡڛڕڠۋڠڞڕۊڠێژێۉژۍۋڞۉڙ";
        array23[14][0] = "l72HdRco4Z8=";
        array23[14][1] = "ۉژڟڡڜژڟۍڕژژۋۊڕڜژژیڕۊڜژڟڕڡۋۉۊێۉیڙیۋڟڛ";
        By1337̴̵̨̞̱̬͙̬̪̙̜̫̖̰̳̜̑̉͌ͪ̆̐ͣ̐̀̂̔͋̆̈́ͯ̾̄́̃̌̎̚͞ͅ();
    }

    private static void By1337̴̵̨̞̱̬͙̬̪̙̜̫̖̰̳̜̑̉͌ͪ̆̐ͣ̐̀̂̔͋̆̈́ͯ̾̄́̃̌̎̚͞ͅ() {
        array23[15][0] = "VKtl2FpP+zg=";
        array23[15][1] = "ڟۍښۍڞڡڠڡڕێڛژڠڕڜښڙڟڕڡڡڠڛڕڡۉۊڠڞێڠڟڠڟۉژ";
        array23[16][0] = "DB0QCoAGoaw=";
        array23[16][1] = "ڡژۋۉۋڟښڙڕێۉۊڠڕڜۊڟڟڕڠۉڜڛڕڝڠڠڟێڝڟیۊڟێی";
        By1337̨̯̝̖͔̙̖͎͈̠̱̟̖̖̼̗̻̱ͥ̏̍̅̐ͨ̌ͩ̇͋̅͛̏ͭͩ̐ͪͫ͂̍̚ͅ();
    }

    private static void By1337̨̯̝̖͔̙̖͎͈̠̱̟̖̖̼̗̻̱ͥ̏̍̅̐ͨ̌ͩ̇͋̅͛̏ͭͩ̐ͪͫ͂̍̚ͅ() {
        array23[17][0] = "/0xcpJ/GGjA=";
        array23[17][1] = "ڠڠڛڞۍێڟڙڕیڞڝښڕڜۊۍڙڕڠڙژیڕڜیۍڜڙێۍێڝڛڝڝ";
        array23[18][0] = "VTKRIJQl6yk=";
        array23[18][1] = "ڟڠڛۋێڟیڡڕڠۍڜۉڕڜژڠۉڕڡڙڟۉڕژێژۋڡڝێۋۍۍژۉ";
        By1337̶̡̥̺̼̙͚͉̠̘̬̮͇͉͚̠̥̥̒͐̆̀̅͗́͐ͧ͐ͫͪ̄͗ͣ̐̌͐̚̕͜͞();
    }

    private static void By1337̶̡̥̺̼̙͚͉̠̘̬̮͇͉͚̠̥̥̒͐̆̀̅͗́͐ͧ͐ͫͪ̄͗ͣ̐̌͐̚̕͜͞() {
        array23[19][0] = "O4COpXRznqY=";
        array23[19][1] = "ڙښۉۍڠێڝښڕڟڛڛڡڕڜیڛۋڕۊۍۍڟڕڝڞۉڟڛۊڡیښژڜڙ";
        array23[20][0] = "9VllWEzSf0c=";
        array23[20][1] = "ڝۉڞښۉۋیۋڕڡژژۉڕڜڜڛښڕۊڟڡیڕۋۉڠۍژڝێڟڟۉڟژ";
        By1337̴̵̴̸̜̪̯͖̣̭̦̫̘͇͂ͯ̒ͨ̓̇̿̍ͨ̑́ͣ͢͜͡͏͙͙̬͔͎̼̤ͫ̑͆();
    }

    private static void By1337̴̵̴̸̜̪̯͖̣̭̦̫̘͇͂ͯ̒ͨ̓̇̿̍ͨ̑́ͣ͢͜͡͏͙͙̬͔͎̼̤ͫ̑͆() {
        array23[21][0] = "A9670/4aYLQ=";
        array23[21][1] = "ۍښڞیڜښڜژڕڛڟڜڞڕڜۊۊۉڕڡێڝۉڕیڛڠۊڠۍۉڝڛۍڠڟ";
    }

    private static void By1337̵̡̧͓̺͔̼͍̤͎̝̹̐̀̽́͗̎͑̇̐ͬ̔͌ͣͫ͘͝ͅ͏̸̷̫̩̓ͬͫ̔́ͨ() {
        array24[0][0] = "uPifTls+7Is=";
        array24[0][1] = "ત઩ધપન૓થણટ૘૘ણ૔ટદ૕૖ફટ૓૕દતટ૖૖઩૕૔ણ૘૓઩પ૖૘";
        array24[1][0] = "1yubo6wsD4I=";
        array24[1][1] = "ણ૓ઢ૖૘૔ફદટ૗૘઩ણટદનન૘ટફ૖દ૗ટ૓૕૘૓પઢફ૖ઢપ૗ન";
        array24[2][0] = "QMs7f+MvXl0=";
        array24[2][1] = "ઢ૓દ૘૘ફ૕થટધથ઩૔ટદદ઩૖ટ૔ધ૘૕ટનતતદણઢ૕ઢ૖ત૔ધ";
        By1337̶̨͍̦̼͍̳̔ͬͨ͋̿̒ͬͣͮ̄̚ͅ͏̛̛̛̬̲͙͕̲͙͙̝ͤ̈́̎̂ͭ̾̈̒͟();
    }

    private static void By1337̶̨͍̦̼͍̳̔ͬͨ͋̿̒ͬͣͮ̄̚ͅ͏̛̛̛̬̲͙͕̲͙͙̝ͤ̈́̎̂ͭ̾̈̒͟() {
        array24[3][0] = "wDO+J4YuuIE=";
        array24[3][1] = "ફ૘ત૕ધ૖પ૔ટ૖ણ૕૔ટદ઩ફ૘ટફધપ૘ટ૓૓નત઩ધણથ઩ફદ૘";
        array24[4][0] = "R191ppL/yOY=";
        array24[4][1] = "૖થફન૖૔ફપટનધ૖ણટદ૔તણટ૓૗થદટપ૘઩૓નફપ૓દન૓૕";
        By1337̴̷̸̨̢̨̨̛͚̝̺̰̮̬̠̜̬͖̠̼̙̮̠̀̋ͣ͛̓͌͗ͣ͊̍̏ͯ̊ͤ͟͠ͅ();
    }

    private static void By1337̴̷̸̨̢̨̨̛͚̝̺̰̮̬̠̜̬͖̠̼̙̮̠̀̋ͣ͛̓͌͗ͣ͊̍̏ͯ̊ͤ͟͠ͅ() {
        array24[5][0] = "LcBssHfMEiE=";
        array24[5][1] = "઩૖૖૔થફણફટ઩ણ૖તટદદદણટ૓૖૔ઢટન૕૕ફધધફ૔ઢ઩઩૖";
        array24[6][0] = "txvaGWZsusE=";
        array24[6][1] = "૔૔૓તતન૕નટ૗૖દણટદપ઩ઢટ૓૕થ૓ટનત૖દ૖ધપફ૕ણફ૕";
        By1337̶̵̴̡̢̧̛͍̘̺̺̥͙̫̞͓̭̥̩̗͚̘ͯ̾́̉ͪ̑͂͑̂̾̏̔͂́̽̚͜ͅ();
    }

    private static void By1337̶̵̴̡̢̧̛͍̘̺̺̥͙̫̞͓̭̥̩̗͚̘ͯ̾́̉ͪ̑͂͑̂̾̏̔͂́̽̚͜ͅ() {
        array24[7][0] = "V6jKTxDgi0k=";
        array24[7][1] = "૕ધથથ઩દઢ૕ટ૔ધથ૘ટદ૘૖ણટફ૓ધ઩ટપફન૔દફન૔૘૓પધ";
        array24[8][0] = "DEScVsXz2rw=";
        array24[8][1] = "૕ણદ૘ઢ૓૘૕ટપ૗૔ધટદદ૘ઢટ૔ન઩૖ટ૖ઢનઢ૔તઢનફધતધ";
        By1337̡͉̳̳͈̻͎̝͈̞̱̲̲̲̲̱̯̱̖͛ͬ̆̍̀͋̎͆̉͌͑ͨ̑͛͘͜͝͝͠ͅͅ();
    }

    private static void By1337̡͉̳̳͈̻͎̝͈̞̱̲̲̲̲̱̯̱̖͛ͬ̆̍̀͋̎͆̉͌͑ͨ̑͛͘͜͝͝͠ͅͅ() {
        array24[9][0] = "q1eGW5GbKUc=";
        array24[9][1] = "ધ઩૘દફધન઩ટદન૗નટદ૔૘઩ટ૓૔૓તટ઩ન૕પ૖ધઢદ૔૕૔ધ";
        array24[10][0] = "GjPa2HCGcCo=";
        array24[10][1] = "૓૘૗ત૗પપ૖ટઢઢદનટદઢ઩દટપદ઩૔ટણઢધ૗ણફ઩૗ધનણત";
        By1337̷͖̻̮̥̺̠̞͎͓͍̯̝̙̄̂ͦͭ́̾̎̓͋͆ͪ̉͗ͭͪ͗̏͒̎͆͒ͪ͆̕͜͡();
    }

    private static void By1337̷͖̻̮̥̺̠̞͎͓͍̯̝̙̄̂ͦͭ́̾̎̓͋͆ͪ̉͗ͭͪ͗̏͒̎͆͒ͪ͆̕͜͡() {
        array24[11][0] = "vBshXQkf0KM=";
        array24[11][1] = "થઢ઩઩ધફથ૗ટથ૗૕ફટદદ૕ણટપથ૓૖ટ૔દણપણ઩૖નથપતદ";
        array24[12][0] = "AdWEHUF1R10=";
        array24[12][1] = "ત૘ણ૘ત૘થતટપથફ૘ટદથપ૗ટ૓દધ૗ટ૓૖થ૘૓૖દદત૕૕પ";
        By1337̶̡̛͕̮͖͇͙̖̬̬̲͉̬̬̣̘̟̣ͫͩͣ͋̔ͪͫ̄ͩͭ̽ͨ̀ͤ͋̊̓̐̕͘͝();
    }

    private static void By1337̶̡̛͕̮͖͇͙̖̬̬̲͉̬̬̣̘̟̣ͫͩͣ͋̔ͪͫ̄ͩͭ̽ͨ̀ͤ͋̊̓̐̕͘͝() {
        array24[13][0] = "GCY8VDMR32E=";
        array24[13][1] = "૗૖થધદતથ૔ટ઩પ૘૔ટદણ૗ણટફ઩઩ધટદધપપ૘દ૓ણન૔૖પ";
        array24[14][0] = "VQUKEZvWrSU=";
        array24[14][1] = "થ૘તન૘૖ન૔ટધથપ૖ટદનદધટ૔૘નથટ઩઩તનણણતપધણપ૖";
        By1337̢̧͎̼̭̟̻̦̰̮͍̙̼̀͒̾͛̽ͨ̌̐̓ͥ̓ͪ̈́̋̉̽͌ͩ̽ͩ̎̏́̄͢͠ͅ();
    }

    private static void By1337̢̧͎̼̭̟̻̦̰̮͍̙̼̀͒̾͛̽ͨ̌̐̓ͥ̓ͪ̈́̋̉̽͌ͩ̽ͩ̎̏́̄͢͠ͅ() {
    }

    private static void By1337̧̤̫̭̞̫̻̦̹̹̹̻̞̺̭̘̤̞̗̘̗̿̈͌̐́̾ͤͣͧ͗ͤͤ͌̾ͥͥ͘͠͠() {
        array25[0][0] = "EvatLXCZzzw=";
        array25[0][1] = "໢໛ໜ་༎༎ໝ༏໗໚།໠་໗ໞ໡໢໡໗་ໟ໠།໗ໜ༌໢໣་໢༎།་༌ໟ༌";
        array25[1][0] = "eS/nVa8mk0Q=";
        array25[1][1] = "ໞ༌໠༌༎ໟໜ༐໗༏༏໣༎໗ໞ໣໛ໞ໗໢໢༐໠໗ໝ།໠་໢།໚ໟ໡༐༎ໜ";
        array25[2][0] = "t/nThw0UoeA=";
        array25[2][1] = "໢།༌ໟໜ໠༐ໝ໗༎໢༐໣໗ໞ༐ໝໞ໗་༌་໚໗༐༎໠ໞໝໟໞ༎།ໝໝ༎";
        By1337̷̢̭̲͈͚̟̺̖̳̩͖̙̦͎̫͎ͯ̆̑͐ͭ͆̐͗̃͗̋͑ͫ͗͒̾͋̀͘͢͝͠ͅ();
    }

    private static void By1337̷̢̭̲͈͚̟̺̖̳̩͖̙̦͎̫͎ͯ̆̑͐ͭ͆̐͗̃͗̋͑ͫ͗͒̾͋̀͘͢͝͠ͅ() {
        array25[3][0] = "Pc8PmKs+hZA=";
        array25[3][1] = "༎།ໝ໚།໠༌༎໗໡ໜໞໝ໗ໞ໚༌ໜ໗໢໛໡༎໗༐ໞ໣་༏໡༏໢໛༏໢།";
        array25[4][0] = "LZikjPzCYEA=";
        array25[4][1] = "ໜ།་໛༎໡໠ໜ໗ໝໞໟ༐໗ໞໜໜໟ໗໣໡ໞໟ໗໣ໜໞໝໜ༎ໜ་໠་༐༎";
        By1337̝͉̤̈ͬͭ͂̅͑͠͏̷̷͉̲͓͕̼̳̣͎̭̳͋̽͛̄͛͆́̆ͭͫ̄͆̓͜͢͡͝();
    }

    private static void By1337̝͉̤̈ͬͭ͂̅͑͠͏̷̷͉̲͓͕̼̳̣͎̭̳͋̽͛̄͛͆́̆ͭͫ̄͆̓͜͢͡͝() {
        array25[5][0] = "tBlt+cXC8l4=";
        array25[5][1] = "໠໣ໜ໛໣໚༏ໝ໗໛໣༐ໝ໗ໞ໛༎༐໗໢໡໚།໗ໜ໣ໞ໚།ໝ໛༐໠།໢ໟ";
        array25[6][0] = "a4/4G8HjuFw=";
        array25[6][1] = "་ໞໞ།༌໢་་໗༎໚໛໢໗ໞ໡໚ໝ໗༌ໟ༌໚໗໠་ໟ༏༐໛༐ໜ໚໢༎ໜ";
        By1337̷̨͔͔͙̮̭͍̤͙̹̗̬̗̦̝͎̪̬́̃ͨͮͭ̍͊ͣ̈͛̽̑͑̿̏̌̂͘͢͢ͅ();
    }

    private static void By1337̷̨͔͔͙̮̭͍̤͙̹̗̬̗̦̝͎̪̬́̃ͨͮͭ̍͊ͣ̈͛̽̑͑̿̏̌̂͘͢͢ͅ() {
        array25[7][0] = "fIiRSEhPw+k=";
        array25[7][1] = "༐໢༏༎༐་་༎໗ໟ໣་།໗ໞ໣໡་໗໣༐໚໚໗༐ໜໞ໚ໝ༏ໞ༎༎໚༐໚";
        array25[8][0] = "+6BpLPe08n0=";
        array25[8][1] = "ໟ໡།༏།໠໣་໗༌໢ໝ།໗ໞ་໠໣໗໢໛༌໛໗ໜໜ་ໜ།༌໚໣ໜ໢໡໛";
        By1337̸̨͈̼̹͙ͮ̄̈́ͮͤ̿̿̐̏ͬ͝͏̵̵̧̢̯̤͇̤̯̭ͪͥ͆ͨ͂͊̿͂̆͟͡ͅ();
    }

    private static void By1337̸̨͈̼̹͙ͮ̄̈́ͮͤ̿̿̐̏ͬ͝͏̵̵̧̢̯̤͇̤̯̭ͪͥ͆ͨ͂͊̿͂̆͟͡ͅ() {
        array25[9][0] = "mDRaPuagFhU=";
        array25[9][1] = "໢໢໢໚໛ໜ་ໞ໗ໞໞໟ໣໗ໞ໣༌་໗༌໢໢໢໗໣໣໢༎໛໠༐໛໠ໝ༎༐";
        array25[10][0] = "L2p8xk6YMAI=";
        array25[10][1] = "໚໡໠༌༌ໟ༐།໗໚໡ໝໞ໗ໞ༏໠༎໗໢།༎໡໗་།ໜໞ༐༌໚໢ໜໟໝ໚";
        By1337̨̭̣̳͔͓̟͍͊̉ͥ͂̌̓̇ͥ͗̕͏̝̗̯̠̘͍̹ͣͫ͂͆͒̊̈̊ͯ̇̓͟͡͠();
    }

    private static void By1337̨̭̣̳͔͓̟͍͊̉ͥ͂̌̓̇ͥ͗̕͏̝̗̯̠̘͍̹ͣͫ͂͆͒̊̈̊ͯ̇̓͟͡͠() {
        array25[11][0] = "E9F27QH+Eao=";
        array25[11][1] = "༐໢ໜ།༐༐ໝໞ໗໡༎༐༎໗ໞ໢໢໢໗༌ໞໟ໡໗༎໢ໝ໡ໟ་ໞໜ༐໚།໢";
        array25[12][0] = "kya7lNqDgQc=";
        array25[12][1] = "໠໡ໞ་༐༎༌໠໗໣ໜ໠།໗ໞໞໟໝ໗༌໛༐໠໗ໟ໢༎໛༎།໠༐༎໠༎໡";
        By1337̶̴̧̨̛̻̺̤͚͎̝̗͓̪͇̮̣̤̜͕ͣͦ́͋̒̂ͤ̏̈́̽ͦ̽̾͂ͫ̋̊̕͜ͅ();
    }

    private static void By1337̶̴̧̨̛̻̺̤͚͎̝̗͓̪͇̮̣̤̜͕ͣͦ́͋̒̂ͤ̏̈́̽ͦ̽̾͂ͫ̋̊̕͜ͅ() {
        array25[13][0] = "yV9Tn5eCFjA=";
        array25[13][1] = "໚ໝໝ໢།໢་໢໗໚༏་ໞ໗ໞ༌ໝ໠໗໣໚ໞ໠໗༎༌໛ໜ༎໢ໜ་ໝ໚໡༐";
        array25[14][0] = "m0ybrfsZoZw=";
        array25[14][1] = "།໣໚ໞ໚༐ໝ་໗༐໛ໞໟ໗ໞ໚༎་໗༌༐໢ໞ໗໚།།༐་༐໠།༐໠་໢";
        By1337̧̡̮̪͇͉̙̮̺̹͈̰̺̤̩̘͓͐͌̓̿̈́ͭ̋̑̔ͩ̄̓͂̄̅͋ͤ̚̚͟͠͡͡();
    }

    private static void By1337̧̡̮̪͇͉̙̮̺̹͈̰̺̤̩̘͓͐͌̓̿̈́ͭ̋̑̔ͩ̄̓͂̄̅͋ͤ̚̚͟͠͡͡() {
        array25[15][0] = "cQ5qrzKCumw=";
        array25[15][1] = "໠་༌༌༎ໟໜ໚໗ໜໞ༎໢໗ໞ༐໡༌໗໣໡໛༎໗།໠໚໣ໝ་༎༐༏ໜ་༏";
        array25[16][0] = "8HoTVQzY7jE=";
        array25[16][1] = "ໜໞໝໝ໠ໞ໛໡໗༎།໠ໝ໗ໞໟ་໚໗໢ໜ໣།໗༐໠໣໢༌ໝ໣་ໟ໚༌་";
        By1337̴̴͙͔̥͕̳̜̭͈̖̰̹̪̣̟̠̈́̿́ͤ̎̀̐͌̍̐̋̑̉̅ͪͩͤͯͩ̅̕͘͘();
    }

    private static void By1337̴̴͙͔̥͕̳̜̭͈̖̰̹̪̣̟̠̈́̿́ͤ̎̀̐͌̍̐̋̑̉̅ͪͩͤͯͩ̅̕͘͘() {
        array25[17][0] = "qwRKnSjvSjo=";
        array25[17][1] = "໡໡་༎ໟ།໠໠໗ໞ໡༐໢໗ໞ༎໢ໟ໗༌༎༌་໗༏ໟ໠໠༏་།໠ໞ໛༌໢";
        array25[18][0] = "VcP71QPPSRo=";
        array25[18][1] = "໚ໞ໢ໜ໛໛ໞໟ໗ໟ་໛་໗ໞ༎໚໢໗༌༐།་໗།ໟ໚་໢ໟ໠໚ໞ໚໣໣";
        By1337̛̭̠̫̪͕͙͇̜̖͈̼̻ͣ̇́ͥ̀̈́͛ͤ̊͂ͪ͠ͅ͏̷̴̜̩͇̮ͣ́͐̔̀̚͠();
    }

    private static void By1337̛̭̠̫̪͕͙͇̜̖͈̼̻ͣ̇́ͥ̀̈́͛ͤ̊͂ͪ͠ͅ͏̷̴̜̩͇̮ͣ́͐̔̀̚͠() {
        array25[19][0] = "V7oeVrFsY7o=";
        array25[19][1] = "໡ໟ໡໠༎໛໚༏໗།ໜໝ་໗ໞ༎໡໚໗་༏༏໠໗໣ໝ໣໢ໞ།໢༏໠༌ໟ໣";
    }

    private static void By1337̶̴̧̨̼͙̻͙̝̝̯͉͍̫̖͍ͫ̈́͗̓ͯͧͯ̃̀̌́͆̍͛̕͏̷͓̞͌̂̓̄ͣ() {
        array26[0][0] = "9340co/WJeQ=";
        array26[0][1] = "܍ܾܿ݀݀ܐܼ݀܇ܐܐ܌܊܇܎ܻ܋ܼ܇ܓ܌ܼ܏܇܍ܓ܍܌ܻܓ܋܊܊݀܏ܻ";
        array26[1][0] = "uKafzuoQBq8=";
        array26[1][1] = "ܼܼܐܑ܋܋݀ܒ܇ܼ܏ܓܑ܇܎ܾ܏܍܇ܼܓܓܼ܇ܻ܏ܾ܌ܑ܎ܑܼܓܐܒ܍";
        array26[2][0] = "HmSLpUU2wCE=";
        array26[2][1] = "ܿ܍܊܊܍ܾ܋܌܇ܻܿܐ܋܇܎ܐܑܿ܇ܓܑ݀݀܇ܓܓ܌܎ܻܽ܏ܾܼ܍ܾܒ";
        By1337̴̷̴̸͙̩̖͎͇̯̯͕̺̤̺̖͈̠̥̊̊͋̀ͩͬ̅̍̄ͯͤ̇́̍͊́̕̕͢͟͞();
    }

    private static void By1337̴̷̴̸͙̩̖͎͇̯̯͕̺̤̺̖͈̠̥̊̊͋̀ͩͬ̅̍̄ͯͤ̇́̍͊́̕̕͢͟͞() {
        array26[3][0] = "Lk8l9icBJ9s=";
        array26[3][1] = "ܾ݀܋ܐܓܑܼ݀܇܏ܓ݀܊܇܎ܾ܏ܿ܇ܼܾܒ܍܇ܑܻܻ݀܊܌܍ܿ܎݀܎ܾ";
    }

    private static void By1337̷̢̹̫̝̙̻̯̳̠́̈̈́ͦ̆̅̎̀̅͒͏̸̦̮̺̥̱̘͍͉́͆̄̋͐̒̽̀ͬ͞() {
        array27[0][0] = "iKJvQfNI9fI=";
        array27[0][1] = "຦๱๵຤ຢຢ๹ຢ๭ລ຦຤๵๭๴຦຤ຢ๭๸๹๵ຣ๭๶๶๰๳ລຣ๶຦ຢຢ๵๶";
        array27[1][0] = "J7I0teD2n0w=";
        array27[1][1] = "຦ຢລ຤๱๹຤๶๭๶຦຦຦๭๴๲๱຦๭ຢ຤ຣ๴๭๲๶๰๹ຢມມ๶ຢຢລ຤";
        array27[2][0] = "e0yJECms/jQ=";
        array27[2][1] = "๲຦๳๰ລ຦ລ๴๭๳๳๸๲๭๴๹຦ລ๭๸๰๹຤๭ຣຣຣລຣມ๰ມ๴๷ຣ๸";
        By1337̶̜̣̝̩̭͈͇͕͚̤̩͓̠̜̥̜ͮͭ̒́̐̀̈́ͭ̒͒́́́̀̋̄͂̏ͩ̀̓̅̕();
    }

    private static void By1337̶̜̣̝̩̭͈͇͕͚̤̩͓̠̜̥̜ͮͭ̒́̐̀̈́ͭ̒͒́́́̀̋̄͂̏ͩ̀̓̅̕() {
        array27[3][0] = "m4eaapBdh7A=";
        array27[3][1] = "ຢ๶๸๵ມ๲๹๲๭๹๷ລ๷๭๴๴๹ມ๭ຢຢ຤๷๭๹๵๳๵ຢ๳๴຦຤๳ມຣ";
    }

    private static void By1337̶̷̶̴̵̴̧̧̨͉̠̬̣̹͚̭̳̐̓ͯͧ͗̄̈́̄̔̏̉̽ͪ̂̎̉̀͌ͨͭ͘̚͜() {
        array28[0][0] = "7c/ArwmBtks=";
        array28[0][1] = "ྞ࿐ྥ࿐࿍ྞྡྷ࿒ྙ࿎ྞྜ࿒ྙྠྞྥ࿎ྙ࿎ྠྡྷ࿏ྙྜྷྣ࿑ྜྷྣྜ࿎ྞྞ࿎ྥྜ";
        array28[1][0] = "eoRCgd3bCFM=";
        array28[1][1] = "࿏࿒࿍࿑ྤྜྷ࿐ྥྙྞྞ࿏ྥྙྠྡྷྥྜྙྤ࿒ྥྠྙ࿑ྤྟ࿎ྜྷྡ࿍ྞྡྡྡྷ࿎";
        array28[2][0] = "mfa7s3AoqJA=";
        array28[2][1] = "ྞྞ࿐ྡྤ࿐ྞྜྷྙྣྡྷྠྤྙྠྡ࿏ྥྙྥྟ࿑ྜྙྠ࿑ྜྷྣྤྟ࿐ྜ࿎ྜ࿒࿍";
        By1337̸̢̫̦̖̜̦͈͈̜̜̞͖̳̝͇̣͎̹̯͕̈́͂̾͑̅͋̉ͧ͑̓ͨ̂ͬ̂ͮ̄͜͞͞();
    }

    private static void By1337̸̢̫̦̖̜̦͈͈̜̜̞͖̳̝͇̣͎̹̯͕̈́͂̾͑̅͋̉ͧ͑̓ͨ̂ͬ̂ͮ̄͜͞͞() {
        array28[3][0] = "L1R/XKIoZlg=";
        array28[3][1] = "ྡྷ࿎࿍࿒ྞ࿏ྤ࿍ྙ࿒࿍ྜྷྜྙྠྣ࿑ྤྙ࿎ྠྡྷྞྙ࿑ྜ࿒ྞྞ࿒࿒࿏࿏ྡྷྥ࿍";
    }
}