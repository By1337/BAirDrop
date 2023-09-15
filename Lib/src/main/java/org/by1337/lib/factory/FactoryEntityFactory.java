package org.by1337.lib.factory;


import org.by1337.api.factory.EntityFactory;
import org.by1337.lib.Version;
import org.by1337.v1_17.factory.EntityFactoryV1_17;

public class FactoryEntityFactory {
    public static final FactoryEntityFactory factory = new FactoryEntityFactory();

    public EntityFactory create(){
        if (Version.version == Version.V1_16_5) {

        } else if (Version.version == Version.V1_17) {
            return new EntityFactoryV1_17();
        } else if (Version.version == Version.V1_17_1) {

        } else if (Version.version == Version.V1_18) {

        } else if (Version.version == Version.V1_18_1) {

        } else if (Version.version == Version.V1_18_2) {

        } else if (Version.version == Version.V1_19) {

        } else if (Version.version == Version.V1_19_1) {

        } else if (Version.version == Version.V1_19_2) {

        } else if (Version.version == Version.V1_19_3) {

        } else if (Version.version == Version.V1_19_4) {

        } else if (Version.version == Version.V1_20_1) {

        } else{
            throw new IllegalStateException("Unsupported version");
        }
        return null;
    }
}
