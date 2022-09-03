package team.devblook.akropolis.command.commands.gamemode;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.devblook.akropolis.AkropolisPlugin;
import team.devblook.akropolis.Permissions;
import team.devblook.akropolis.command.InjectableCommand;
import team.devblook.akropolis.config.Messages;
import team.devblook.akropolis.util.TextUtil;

import java.util.List;

public class AdventureCommand extends InjectableCommand {

    public AdventureCommand(AkropolisPlugin plugin, List<String> aliases) {
        super(plugin, "gma", "Change to adventure mode", "/gma [player]", aliases);
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Messages.CONSOLE_NOT_ALLOWED.toComponent());
                return;
            }

            Player player = (Player) sender;

            if (!player.hasPermission(Permissions.COMMAND_GAMEMODE.getPermission())) {
                player.sendMessage(Messages.NO_PERMISSION.toComponent());
                return;
            }

            player.sendMessage(TextUtil.replace(Messages.GAMEMODE_CHANGE.toComponent(), "gamemode", Component.text("ADVENTURE")));
            player.setGameMode(GameMode.ADVENTURE);
        } else if (args.length == 1) {
            if (!sender.hasPermission(Permissions.COMMAND_GAMEMODE_OTHERS.getPermission())) {
                sender.sendMessage(Messages.NO_PERMISSION.toComponent());
                return;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null) {
                sender.sendMessage(TextUtil.replace(Messages.INVALID_PLAYER.toComponent(), "player", Component.text(args[0])));
                return;
            }

            if (sender.getName().equals(player.getName())) {
                player.sendMessage(TextUtil.replace(Messages.GAMEMODE_CHANGE.toComponent(), "gamemode", Component.text("ADVENTURE")));
            } else {
                player.sendMessage(TextUtil.replace(Messages.GAMEMODE_CHANGE.toComponent(), "gamemode", Component.text("ADVENTURE")));
                sender.sendMessage(TextUtil.replace(TextUtil.replace(Messages.GAMEMODE_CHANGE_OTHER.toComponent(), "player", player.name()), "gamemode", Component.text("ADVENTURE")));
            }

            player.setGameMode(GameMode.ADVENTURE);
        }

    }
}
