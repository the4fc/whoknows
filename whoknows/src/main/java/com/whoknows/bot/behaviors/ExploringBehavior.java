package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class ExploringBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final java.util.Random random;
    private long lastExecution = 0;
    private final String[] exploringPhrases;
    
    public ExploringBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.exploringPhrases = new String[]{
            "¿Has explorado alguna cueva hoy? ¡Siempre hay tesoros escondidos!",
            "Me encanta encontrar aldeas... los aldeanos son buenos para comerciar.",
            "Los templos del desierto tienen botín genial, pero cuidado con la trampa.",
            "¿Has ido al End ya? El Ender Dragon te espera...",
            "El Nether da miedo al principio, pero tiene recursos increíbles.",
            "Las mansiones del bosque son enormes y llenas de loot. ¡Vale la pena!",
            "¿Sabías que hay ruinas submarinas? Tienen tesoros ocultos.",
            "Los barcos hundidos a veces tienen cofres con cosas raras.",
            "Explorar es la mejor forma de encontrar inspiración para construir.",
            "¿Has encontrado alguna aldea de champiñones? ¡Son súper raras!",
            "Las minas abandonadas tienen vagonetas con cofres. ¡Busca bien!",
            "Siempre llevo un mapa cuando exploro. No quiero perderme, jeje.\"
        ];
    }
    
    @Override
    public boolean debeEjecutarse() {
        long now = System.currentTimeMillis();
        if (now - lastExecution < 200000) return false;
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.25;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String phrase = exploringPhrases[random.nextInt(exploringPhrases.length)];
        
        bot.enviarMensajePrivado(randomPlayer, "🗺️ " + phrase);
    }
}
