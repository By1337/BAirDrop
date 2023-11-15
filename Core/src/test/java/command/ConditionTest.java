package command;

import org.by1337.bairdrop.observer.requirement.condition.ConditionParse;
import org.junit.Assert;
import org.junit.Test;

public class ConditionTest {
    private static final String compact = "if(\"{time-to-start} < 600 && {x} == ? && {y} == ? && {z} == ?\"){[\"[TRY_GEN_LOC]\"]}if(\"{time-to-start} > 0\"){[\"[PROPERTY] set time-to-start match[{time-to-start} - 1]\"]}if(\"{time-to-start} == 0\"){if(\"{is-spawned} == false\"){[\"[CALL_EVENT] START_EVENT\"]}else if(\"{time-to-unlock} > 0\"){if(\"{start-countdown-after-click} == true\"){if(\"{activated} == true\"){[\"[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]\"]}}else{[\"[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]\"]}}else if(\"{opened} == false\"){[\"[CALL_EVENT] UNLOCK_EVENT\"]}else if(\"{time-to-end} > 0\"){if(\"{time-stop-event-must-go} == true || {opened} == true\"){[\"[PROPERTY] set time-to-end match[{time-to-end} - 1]\"]}}else{[\"[CALL_EVENT] END_EVENT\"]}}";
    private static final String src = "if (\"{time-to-start} < 600 && {x} == ? && {y} == ? && {z} == ?\") {/* comment */\n" +
            "    [ \"[TRY_GEN_LOC]\" ]/* comment */\n" +
            "}/* comment */\n" +
            "if (\"{time-to-start} > 0\") {\n" +
            "    [ \"[PROPERTY] set time-to-start match[{time-to-start} - 1]\" ]/* comment */\n" +
            "}/* comment */ // comment\n" +
            "if (\"{time-to-start} == 0\") {/* comment */\n" +
            "    if (\"{is-spawned} == false\") {/* comment */\n" +
            "        [ \"[CALL_EVENT] START_EVENT\" ]/* comment */\n" +
            "    } else if (\"{time-to-unlock} > 0\") {/* comment */\n" +
            "        if (\"{start-countdown-after-click} == true\") {/* comment */\n" +
            "            if (\"{activated} == true\") {/* comment */\n" +
            "                [ \"[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]\" ]/* comment */\n" +
            "            }\n" +
            "\t\t\t/*\n" +
            " * comment\n" +
            " */\n" +
            "        } else {/* comment \"\"\"\"\"\" ///// \\\\\\\\\\\\\\*/ // comment\n" +
            "            [ \"[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]\" ] // comment \n" +
            "        }\n" +
            "\t\t/*\n" +
            " * comment\n" +
            " */\n" +
            "    } else if (\"{opened} == false\") {/* comment */\n" +
            "        [ \"[CALL_EVENT] UNLOCK_EVENT\" ]/* comment */\n" +
            "    } else if (\"{time-to-end} > 0\") {/* comment */\n" +
            "        if (\"{time-stop-event-must-go} == true || {opened} == true\") {/* comment */\n" +
            "            [ \"[PROPERTY] set time-to-end match[{time-to-end} - 1]\" ]/* comment */\n" +
            "        }/* comment */\n" +
            "\t\t/*\n" +
            " * comment\n" +
            " */\n" +
            "    } else {/* comment */\n" +
            "        [ \"[CALL_EVENT] END_EVENT\" ]/* comment */\n" +
            "    }/* comment */\n" +
            "}" +
            "// comment";

    @Test
    public void run() {
        Assert.assertEquals(ConditionParse.parse(compact).toString(true), compact);
        Assert.assertEquals(ConditionParse.parse(src).toString(true), compact);

        System.out.println(ConditionParse.parse(compact).toString());

    }
}
