package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class BuildingBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] buildingPhrases;
    
    public BuildingBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.buildingPhrases = new String[]{
            "Construir es mi parte favorita... puedes crear lo que imagines.",
            "¿Has visto algún tutorial de construcción? ¡Aprendo mucho de ellos!",
            "Me gusta mezclar diferentes tipos de madera. Da más profundidad a las construcciones.",
            "Una buena base necesita buenas luces. ¡Los mobs no perdonan!",
            "¿Eres de los que hace casas bonitas o funcionales? Yo intento ambas cosas.",
            "Las escaleras y losas son tus mejores amigas para decorar.",
            "Estoy pensando en hacer una granja de árboles automática. ¿Tienes alguna?",
            "El cuarzo del Nether queda genial en construcciones modernas.",
            "¿Sabías que las vallas y los muros pueden usarse para decorar? ¡Dan mucho juego!",
            "Una casa con personalidad es mejor que un cubo perfecto, ¿no crees?",
            "Me encanta hacer jardines con flores de colores. ¡Alegran cualquier base!",
            "Las ventanas de cristal negro quedan muy elegantes, deberías probarlas."
        };
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 300000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.2;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = buildingPhrases[random.nextInt(buildingPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🔨 " + phrase);
    }
}
