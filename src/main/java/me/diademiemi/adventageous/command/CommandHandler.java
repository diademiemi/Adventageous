package me.diademiemi.adventageous.command;

import me.diademiemi.adventageous.advent.Advent;
import me.diademiemi.adventageous.advent.Day;
import me.diademiemi.adventageous.advent.Year;
import me.diademiemi.adventageous.dialogs.AdminMenu;
import me.diademiemi.adventageous.dialogs.MonthOverview;
import me.diademiemi.adventageous.lang.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        LocalDateTime date = LocalDateTime.now();
        if (Advent.getOffset() != null && Advent.getOffset() != "-0") {
            String offset = Advent.getOffset();
            // Get first character
            char firstChar = offset.charAt(0);
            if (firstChar == '+') {
                System.out.println("Offset is positive" + offset);
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                System.out.println(number);
                // Add the number to the date as minutes
                date = date.plusMinutes(number);
            } else if (firstChar == '-') {
                // Get the rest of the string
                String rest = offset.substring(1);
                // Get the number
                int number = Integer.parseInt(rest);
                // Add the number to the date as minutes
                date = date.minusMinutes(number);
            }
        }
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
            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("adventageous.advent")) {
                        new MonthOverview().show((Player) sender, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
                    } else {
                        Message.send(sender, "no-permission");
                    }
                } else {
                    Message.send(sender, "no-console");
                }
            } else if (args.length == 1) {
                switch (args[0]) {
                    case "claim":
                        if (sender.hasPermission("adventageous.advent.claim")) {
                            Year yearObj = Advent.getYear(date.getYear());
                            if (yearObj == null) {
                                Message.send(sender, "no-active-month");
                                return true;
                            }
                            if (yearObj.getMonth(date.getMonth().getValue() - 1) == null) {
                                Message.send(sender, "no-active-month");
                                return true;
                            }
                            Day dayObj = yearObj.getMonth(date.getMonth().getValue() - 1).getDay(date.getDayOfMonth() - 1);
                            if (dayObj != null && dayObj.getRewards().size() > 0 && !dayObj.isHidden()) {
                                if (dayObj.claim((Player) sender)) {
                                    Message.send(sender, "claimed");
                                }
                                return true;
                            }
                        } else {
                            Message.send(sender, "no-permission");
                        }

                        break;
                    default:
                        Message.send(sender, "unknown-arguments", "label", label);
                        break;
                }
            }
        }
        return true;
    }

}