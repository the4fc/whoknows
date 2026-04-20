package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.util.*;

public class CombatBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final Random random;
    private long lastExecution = 0;
    private final String[] combatPhrases;
    
    public CombatBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.combatPhrases = new String[]{
            "¡Cuidado! ¿Escuchaste eso? Podría haber mobs cerca...",
            "¿Tienes suficientes flechas? Nunca se sabe cuándo vendrá un esqueleto.",
            "Siempre llevo comida en el inventario. ¡Los creepers no avisan!",
            "¿Has mejorado tu armadura? Los zombies hacen más daño de noche.",
            "Me gusta luchar contra esqueletos... si te acercas mucho no pueden dispararte.",
            "¡Un creeper! ... espera, solo era mi imaginación, jeje.",
            "Los phantoms son molestos... asegúrate de dormir cada noche.",
            "¿Sabes que los endermen no te atacan si no los miras a los ojos?",
            "Las brujas lanzan pociones raras... ¡cuidado con la de veneno!",
            "En las mazmorras siempre hay spiders... lleva antorchas.",
            "¡Combate es vida! Bueno, sobrevivir al combate es vida, jeje.",
            "¿Has probado a hacer una granja de mobs? ¡Es útil para experiencia!"
        };
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 180000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.25;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = combatPhrases[random.nextInt(combatPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "⚔️ " + phrase);
    }
}
