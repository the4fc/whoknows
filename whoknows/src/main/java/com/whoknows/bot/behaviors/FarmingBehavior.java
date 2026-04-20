package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class FarmingBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] farmingPhrases;
    
    public FarmingBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.farmingPhrases = new String[]{
            "¿Tienes una granja de trigo? El pan nunca sobra.",
            "Las granjas automáticas son increíbles... ¡cosechar sin hacer nada!",
            "Las vacas dan cuero y carne... siempre tengo una granja de vacas.",
            "¿Has probado las granjas de abejas? La miel es súper útil.",
            "Los pollos son fáciles de criar... y dan huevos para pasteles.",
            "Una buena granja de patatas te salva la vida. ¡Y las patatas horneadas curan mucho!",
            "Las zanahorias doradas son la mejor comida... necesitas una granja grande.",
            "¿Sabías que los aldeanos pueden ayudarte en la granja? ¡Son buenos trabajadores!",
            "Las granjas de hierro con aldeanos y zombies son ingeniosas, ¿tienes una?",
            "Cultivar bambú es útil para el comercio con aldeanos.",
            "Las granjas de experiencia con mobs son geniales para encantar equipo.",
            "Criar animales es relajante... ver a los bebés crecer es bonito, jeje.\"
        ];
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 260000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.18;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = farmingPhrases[random.nextInt(farmingPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🌾 " + phrase);
    }
}
