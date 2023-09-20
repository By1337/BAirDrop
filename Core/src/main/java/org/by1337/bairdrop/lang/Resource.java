package org.by1337.bairdrop.lang;

import java.util.List;

public class Resource {
    private final String path;

    public Resource(String path) {
        this.path = path;
    }
    public String getString(){
        return Lang.getLang().getMessage(path);
    }
    public List<String> getList(){
        return Lang.getLang().getList(path);
    }
}
