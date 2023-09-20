import org.bukkit.Material;
import org.bukkit.Particle;

import java.util.Arrays;

public class Test {
    @org.junit.Test
    public void run(){
       f: for (Particle particle : Particle.values()){

            for (Material material : Material.values()){
                if ((
                        particle.name().contains(material.name()) ||
                                material.name().contains(particle.name()) ||
                                Arrays.stream(material.name().split("_")).toList().stream().findAny().filter( s -> Arrays.stream(particle.name().split("_")).toList().stream().findAny().filter(s::contains).orElse(null) != null).isPresent()
                ) && material.isItem())
                {
                    System.out.println(particle.name() + "->" + material.name());
                    continue f;
                }
            }
           System.out.println(particle.name() + "->DIRT");
        }
    }
}
