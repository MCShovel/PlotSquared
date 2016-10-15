package com.intellectualcrafters.plot.commands;

import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.Updater;
import com.intellectualcrafters.plot.config.C;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.intellectualcrafters.plot.util.MainUtil;
import com.plotsquared.general.commands.CommandDeclaration;

import java.net.MalformedURLException;
import java.net.URL;

@CommandDeclaration(
        command = "update",
        permission = "plots.admin.command.update",
        description = "Update the plugin",
        usage = "/plot update",
        requiredType = RequiredType.NONE,
        aliases = {"updateplugin"},
        category = CommandCategory.ADMINISTRATION)
public class Update extends SubCommand {

    @Override
    public boolean onCommand(PlotPlayer player, String[] args) {
        URL url;
        if (args.length == 0) {
            url = Updater.getUpdate();
        } else if (args.length == 1) {
            try {
                url = new URL(args[0]);
            } catch (MalformedURLException ignored) {
                MainUtil.sendMessage(player, "&cInvalid URL: " + args[0]);
                MainUtil.sendMessage(player, C.COMMAND_SYNTAX, "/plot update [url]");
                return false;
            }
        } else {
            MainUtil.sendMessage(player, C.COMMAND_SYNTAX, getUsage().replaceAll("\\{label\\}", "plot"));
            return false;
        }
        if (url == null) {
            MainUtil.sendMessage(player, "&cNo update found!");
            MainUtil.sendMessage(player, "&cTo manually specify an update URL: /plot update <url>");
            return false;
        }
        if (PS.get().update(player, url) && (url == PS.get().update)) {
            PS.get().update = null;
        }
        return true;
    }

}
