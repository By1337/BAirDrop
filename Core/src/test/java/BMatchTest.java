import org.by1337.bairdrop.util.BMatch;
import org.junit.Assert;
import org.junit.Test;

public class BMatchTest {
    @Test
    public void run() {
        Assert.assertEquals(BMatch.match("match[10 > 9]"), "1");
        Assert.assertEquals(BMatch.match("match[10 % 5 == 0]"), "1");
        Assert.assertEquals(BMatch.match("match[10 > 10]"), "0");
        Assert.assertEquals(BMatch.match("match[10 >= 10]"), "1");
        Assert.assertEquals(BMatch.match("match[9 <= 10]"), "1");
        Assert.assertEquals(BMatch.match("match[9 != 10]"), "1");
        Assert.assertEquals(BMatch.match("match[1 && 0]"), "0");
        Assert.assertEquals(BMatch.match("match[(10 > 9) && (10 < 11)]"), "1");
        Assert.assertEquals(BMatch.match("match[(10 < 9) || (10 < 11)]"), "1");
        Assert.assertEquals(BMatch.match("match[1 && 1]"), "1");
        Assert.assertEquals(BMatch.match("match[1 && (1 || 0)]"), "1");
        Assert.assertEquals(BMatch.match("match[!0 && !0]"), "1");
        Assert.assertEquals(BMatch.match("match[1 && (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || (1 || 0)))))))))))))]"), "1");
        Assert.assertEquals(BMatch.match("match[0 && 0]"), "0");
        Assert.assertEquals(BMatch.match("match[0 && 0 && 0]"), "0");
        Assert.assertEquals(BMatch.match("match[1 && 1 && 1]"), "1");
        Assert.assertEquals(BMatch.match("match[10 == 10 && 1 && 1]"), "1");
        Assert.assertEquals(BMatch.match("match[9 < 10 && 1 && 1 && 90 < 100]"), "1");
        Assert.assertEquals(BMatch.match("match[10 < 9]"), "0");
        Assert.assertEquals(BMatch.match("match[-10 + 10]"), "0");
        Assert.assertEquals(BMatch.match("match[10 + 9]"), "19");
        Assert.assertEquals(BMatch.match("match[10 == 9]"), "0");
        Assert.assertEquals(BMatch.match("match[10 == 10]"), "1");
        Assert.assertEquals(BMatch.match("match[10 + 10 + 10 - 5]"), "25");
        Assert.assertEquals(BMatch.match("match[10 - 1 >= 9]"), "1");
        Assert.assertEquals(BMatch.match("match[-10+-10]"), "-20");
        Assert.assertEquals(BMatch.match("match[-10+-10+-20-10]"), "-50");
        Assert.assertEquals(BMatch.match("match[99 + 999 + 2]"), "1100");
        Assert.assertEquals(BMatch.match("match[777 + 9 + 643 + 87 / 123 * 7]"), "1429");
        Assert.assertEquals(BMatch.match("match[4327 + 654- 123 +-435 * 34 / 34]"), "4423");
        Assert.assertEquals(BMatch.match("match[34527563 + 9783455]"), "44311018");
        Assert.assertEquals(BMatch.match("match[819 + 340 - 831 - 18 + 676 / 723 / 112 - 982 * 692 + 524]"), "-678710");
        Assert.assertEquals(BMatch.match("match[492 / 526 * 712 * 802 * 455 + 249 / 350 - 941 / 225 / 972 / 821 * 281 - 504 + 535 - 821 + 921 * 395 / 836 * 613 / 638]"), "-373");
        Assert.assertEquals(BMatch.match("match[2539+489*6527*4391-2191/7721/4763-1276/6618*6018+1070-198-2166-3130+3901/2845-575/8472*8364+1079]"), "1129865180");
        Assert.assertEquals(BMatch.match("match[34527563 + 9783455] match[34527563 + 9783455]"), "44311018 44311018");

    }
}
