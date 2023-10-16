package org.by1337.bairdrop.airdrop;

import org.by1337.api.match.BMatch;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.chat.Placeholderable;

public class PlaceholderableDefault implements Placeholderable {
    private final Airdrop airdrop;

    public PlaceholderableDefault(Airdrop airdrop) {
        this.airdrop = airdrop;
    }

    @Override
    public String replace(String string) {
        return Message.messageBuilder(airdrop.replace(BMatch.match(string)));
    }
}
