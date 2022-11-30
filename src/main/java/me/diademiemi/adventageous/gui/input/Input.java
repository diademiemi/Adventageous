package me.diademiemi.adventageous.gui.input;

import me.diademiemi.adventageous.lang.Message;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class Input {
    public static HashMap<Player, Input> inputs = new HashMap<Player, Input>();
    public Player player;
    public Object[] args;
    public String preamble;

    public Input(Player player, Object... args) {
        this.player = player;
        this.args = args;
        addInput(this);
    }

    public Input(Player player, String preamble, Object... args) {
        this(player, args);
        Message.send(player, preamble);
    }

    public static HashMap<Player, Input> getInputs() {
        return inputs;
    }

    public void addInput(Input input) {
        inputs.put(input.getPlayer(), input);
    }

    public void remove() {
        inputs.remove(this.getPlayer());
    }

    public Player getPlayer() {
        return player;
    }

    public void onInput(String input) {
        inputs.remove(player);
        action(input);
    }

    public abstract void action(String input);

}
