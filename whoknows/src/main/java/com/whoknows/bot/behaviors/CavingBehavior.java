package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class CavingBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] cavingPhrases;
    
    public CavingBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.cavingPhrases = new String[]{
            "Las cuevas de queso suizo son mis favoritas... ¡tantos túneles!",
            "¿Has encontrado alguna cueva de lava? Es peligrosa pero tiene diamantes.",
            "Las ravinas son increíbles... a veces encuentras minerales expuestos.",
            "Siempre llevo bloques para pillar. ¡Las caídas en cuevas son traicioneras!",
            "¿Te gusta explorar las nuevas cuevas de la 1.18+? ¡Son gigantescas!",
            "Los geodos de amatista son preciosos... y útiles para lentes.",
            "Cuidado con los mobs en las cuevas oscuras. ¡Lleva antorchas!",
            "A veces me pierdo en cuevas enormes... pero vale la pena explorarlas.",
            "¿Sabías que hay cuevas con musgo y vegetación? Son como junglas subterráneas.",
            "Las cuevas de agua pueden ser peligrosas... cuidado con ahogarte.",
            "Explorar cuevas es como una aventura constante. ¡Nunca sabes qué encontrarás!",
            "Los fósiles en las cuevas son geniales... huesos de dinosaurios, ¿eh?"
        };
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 220000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.2;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = cavingPhrases[random.nextInt(cavingPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🕳️ " + phrase);
    }
}
