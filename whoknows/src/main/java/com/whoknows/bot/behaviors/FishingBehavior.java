package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class FishingBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] fishingPhrases;
    
    public FishingBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.fishingPhrases = new String[]{
            "Pescar es tan relajante... podrías pasar horas sin darte cuenta.",
            "¿Sabías que puedes conseguir tesoros con la caña? ¡He encontrado una silla de montar!",
            "La pesca automática con note blocks es ingeniosa, ¿tienes una?",
            "A veces solo pesco para relajarme. Es como meditar, jeje.",
            "El pescado cocinado es buena comida de emergencia. ¡Y fácil de conseguir!",
            "¿Has probado a hacer una granja de peces tropicales? Son bonitos en acuarios.",
            "Los libros encantados que sacas pescando pueden ser muy útiles.",
            "Pescar de noche es tranquilo... solo el sonido del agua y las estrellas.",
            "Una caña con buen encantamiento saca cosas increíbles. ¡Vale la pena enchantearla!",
            "Los peces globo son raros... y venenosos. ¡Cuidado si te los comes!"
        };
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 280000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.15;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = fishingPhrases[random.nextInt(fishingPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🎣 " + phrase);
    }
}
