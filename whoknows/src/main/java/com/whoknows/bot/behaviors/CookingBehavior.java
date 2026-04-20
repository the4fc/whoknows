package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class CookingBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] cookingPhrases;
    
    public CookingBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.cookingPhrases = new String[]{
            "¡Cocinando carne! La comida cocinada cura mucho más.",
            "¿Sabías que el horno doble es súper útil? Cocina dos cosas a la vez.",
            "Me gusta tener siempre comida cocinada... nunca se sabe cuándo habrá emergencia.",
            "El pan es fácil de hacer y muy efectivo. ¡Las granjas de trigo son geniales!",
            "¿Has probado el estofado de remolacha? No cura tanto pero es bonito, jeje.",
            "Los hornos de fumar cocinan comida más rápido. ¡Son ideales para grandes cantidades!",
            "Cocinar pescado fresco... ¡ñam! El sushi es mi favorito.",
            "¿Tienes una granja automática de comida? Yo estoy planeando hacer una.",
            "La zanahoria dorada es la mejor comida del juego, ¿no crees?",
            "Siempre dejo el horno trabajando mientras hago otras cosas. ¡Multitarea!"
        };
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 240000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.2;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = cookingPhrases[random.nextInt(cookingPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🍳 " + phrase);
    }
}
