package org.by1337.bairdrop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.by1337.bairdrop.BAirDrop;

import static org.by1337.bairdrop.BAirDrop.instance;

public class Manager {
    String string;//licenseKey
    private static final String[] strings = new String[18];

    private String manager(String s) {//loadAndRegister
        this.string = s;
        Message.logger(strings[0]);
        Message.logger(strings[1]);
        int vt = isIs();
        //  Message.error(vt + "");
        try {
            if (vt == 0b100110110101001011111011100 && getGet().hashCode() == -98892341) {
                Message.logger(infoCode(vt));// valid
                Message.logger(strings[0]);
                BAirDrop.len = getInt(vt >> 23 ^ 0b0011);
                return strings[2];
            } else {
                Message.logger(strings[3]); //ne valid
                Message.logger(infoCode(vt));
                Message.logger(strings[4]);
                Message.logger(strings[0]);
                Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
                Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
                BAirDrop.len = getInt(vt >> 23 ^ 0b0011);
                return strings[5];
            }
        } catch (NumberFormatException e) {
            Message.logger(strings[3]); //ne valid
            Message.logger(infoCode(vt));
            Message.logger(strings[4]);
            Message.logger(strings[0]);
            Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
            Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
            BAirDrop.len = getInt(vt >> 23 ^ 0b0011);
            return strings[5];
        }
    }

    private String infoCode(int code) {//getForCode
        if (code == 0b10110011111001000011000000111000) return strings[6];
        if (code == 0b1100100111110100100011100101010) return strings[7];
        if (code == 0b100110110101001011111011100) return strings[8];
        if (code == 0b11000101111110001010111010110011) return strings[9];
        if (code == 0b10011011000001011011100011010110) return strings[10];
        if (code == 0b1010010001000010101010001110110) return strings[11];
        if (code == 0b1011010101001111001010011011) return strings[12];
        if (code == 0b10000111101111111100110111101000) return strings[13];
        return strings[14];
    }

    static {
        strings[0] = By1337("-+KKKKKKKKKKKKKKKKKKKKKKKKKKKKKK-+"); //[]==============================[]
        strings[1] = By1337("їшутюыуыюуVзVзужфужшъ"); //Соединение с сервером
        strings[2] = By1337(""); //true
        strings[3] = By1337("ÑѭюауысюйVыуVфцэютыцW"); //§cЛицензия не валидна!
        strings[4] = By1337("ÑѩэцхюыVфньэибуыW"); //§cПлагин выключен!
        strings[5] = By1337(""); //false
        strings[6] = By1337("PѨоючьцVзужфужцVэюауысюя"); //&cОшибка сервера лицензий
        strings[7] = By1337("PѨдзедздфеудVэюауысюшыыняVьэибW"); //&cОтсутствует лицензионный ключ!
        strings[8] = By1337("PѭюауысюйVфцэютыц"); //&aЛицензия валидна
        strings[9] = By1337("PѫущжцфюэкыняVшдфудVшдVзужфужцW"); //&cНеправильный ответ от сервера!
        strings[10] = By1337("PѫущжцфюэкыняVьэибW"); //&cНеправильный ключ!
        strings[11] = By1337("P?&VцтжузVыуVфцэютыняW"); //&cIP адрес не валидный!
        strings[12] = By1337("PѫущжцфюэкыняVщэцхюыW"); //&cНеправильный плагин!
        strings[13] = By1337("PѬэибVездцжуэW"); //&cКлюч устарел!
        strings[14] = By1337("PѫуюсфуздыцйVшоючьц"); //&cНеизвестная ошибка
        strings[15] = By1337("I GK"); //?v1=
        strings[16] = By1337("P DK"); //&v2=
        strings[17] = By1337("PK"); //&pl=
    }

    public int getInt(int length) {//generateRandomBinaryNumber
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

    private String builder(String v1, String v2) {
        StringBuilder urlBuilder = new StringBuilder(getGet());
        urlBuilder.append(strings[15].toLowerCase()).append(v1);
        urlBuilder.append(strings[16].toLowerCase()).append(v2);
        urlBuilder.append(strings[17].toLowerCase()).append(instance.getName());
        return builderToString(urlBuilder);
    }

    private String builderToString(StringBuilder sb) {
        return sb.toString();
    }

    private String Universal(String v1, String v2) throws IOException {//requestServer
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

    public int isIs() {//isValidationType
        String rand = toS(UUID.randomUUID().toString());
        String sKey = toS("2APZ5JCR2nuIapCO7eT04knQ");
        String key = toS(string);

        try {
            String response = Universal(up(rand, sKey), up(rand, key));
            //  Message.error(response);

            if (response.startsWith("<")) {
                return 0b10110011111001000011000000111000; //PAGE_ERROR //10110011111001000011000000111000
            }
            if (response.hashCode() == 0b10011011000001011011100011010110) {//KEY_NOT_FOUND
                if (key.length() == 0)
                    return 0b1100100111110100100011100101010;
                return 0b10011011000001011011100011010110; //ключ не валидный //10011011000001011011100011010110
            }
            if (response.hashCode() == 0b1010010001000010101010001110110) //NOT_VALID_IP
                return 0b1010010001000010101010001110110; //1010010001000010101010001110110
            if (response.hashCode() == 0b1011010101001111001010011011) //INVALID_PLUGIN
                return 0b1011010101001111001010011011; //1011010101001111001010011011
            if (response.hashCode() == 0b10000111101111111100110111101000) //KEY_OUTDATED
                return 0b10000111101111111100110111101000; //10000111101111111100110111101000

            String respRand = up(up(response, key), sKey);
            if (rand.startsWith(respRand))
                return 0b100110110101001011111011100; //VALID //100110110101001011111011100
            else
                return 0b11000101111110001010111010110011; //WRONG_RESPONSE //11000101111110001010111010110011

        } catch (IOException e) {
            //  e.printStackTrace();
            return 0b10110011111001000011000000111000; //PAGE_ERROR //10110011111001000011000000111000
        }
    }

    private String toS(String s) {//toBinary
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

    public static String up(String s1, String s2) {//xor
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
                result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
            return result.toString();
        } catch (NumberFormatException e) {
            return "<";
        }
    }

    private static String By1337(String string) {//encryption
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while (n < string.length()) {
            stringBuilder.append((char) (string.charAt(n) ^ 0x76));
            ++n;
        }
        return stringBuilder.toString();
    }

    private String getGet() {//getVerifyUrl
        return (new Object() {
            int t;

            public String toString() {
                byte[] buf = new byte[30];
                t = 646293480;
                buf[0] = (byte) (t >>> 20);
                t = -1882527427;
                buf[1] = (byte) (t >>> 9);
                t = 1010017847;
                buf[2] = (byte) (t >>> 11);
                t = -831856194;
                buf[3] = (byte) (t >>> 9);
                t = 1278899551;
                buf[4] = (byte) (t >>> 9);
                t = 1902665687;
                buf[5] = (byte) (t >>> 9);
                t = 778041700;
                buf[6] = (byte) (t >>> 17);
                t = 1095601118;
                buf[7] = (byte) (t >>> 10);
                t = 891116275;
                buf[8] = (byte) (t >>> 1);
                t = -1186584604;
                buf[9] = (byte) (t >>> 13);
                t = 2040455124;
                buf[10] = (byte) (t >>> 19);
                t = 1032009294;
                buf[11] = (byte) (t >>> 12);
                t = -897991253;
                buf[12] = (byte) (t >>> 11);
                t = 1515980195;
                buf[13] = (byte) (t >>> 17);
                t = 851028959;
                buf[14] = (byte) (t >>> 15);
                t = -1148855386;
                buf[15] = (byte) (t >>> 19);
                t = 1058413521;
                buf[16] = (byte) (t >>> 12);
                t = 965619623;
                buf[17] = (byte) (t >>> 18);
                t = -1167280557;
                buf[18] = (byte) (t >>> 13);
                t = 1655451413;
                buf[19] = (byte) (t >>> 8);
                t = -1875162278;
                buf[20] = (byte) (t >>> 15);
                t = 848626008;
                buf[21] = (byte) (t >>> 23);
                t = 980592881;
                buf[22] = (byte) (t >>> 16);
                t = -891497507;
                buf[23] = (byte) (t >>> 9);
                t = 303934671;
                buf[24] = (byte) (t >>> 5);
                t = 1808310129;
                buf[25] = (byte) (t >>> 19);
                t = 280668396;
                buf[26] = (byte) (t >>> 18);
                t = 915569539;
                buf[27] = (byte) (t >>> 3);
                t = -1390497296;
                buf[28] = (byte) (t >>> 21);
                t = -1602064253;
                buf[29] = (byte) (t >>> 8);
                return new String(buf);
            }
        }.toString());
    }//http://by1337.space/verify.php
}