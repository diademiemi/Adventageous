package me.diademiemi.adventageous.command;

import me.diademiemi.adventageous.dialogs.AdminMenu;
import me.diademiemi.adventageous.dialogs.MonthOverview;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDate;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        LocalDate date = LocalDate.now();
        if (label.equalsIgnoreCase("adventageous")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("adventageous.admin")) {
                        new AdminMenu().show((Player) sender);
                    } else if (sender.hasPermission("adventageous.advent")){
                        new MonthOverview().show((Player) sender, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
                    } else {
                        Message.send(sender, "no-permission");
                    }
                } else {
                    Message.send(sender, "no-console");
                }
            } else if (args.length == 1) {
                switch (args[0]) {
                    default:
                        Message.send(sender, "unknown-arguments", "label", label);
                        break;
                }
            }
        } else if (label.equalsIgnoreCase("advent")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("adventageous.advent")) {
                    new MonthOverview().show((Player) sender, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
                } else {
                    Message.send(sender, "no-permission");
                }
            } else {
                Message.send(sender, "no-console");
            }
        }
        return true;
    }

}