package net.euromc.townydiscordcommands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RegisterCommands extends ListenerAdapter {

    public List<CommandData> getCommands() {
        List<CommandData> commandData = new ArrayList<>();
        OptionData optionTown = new OptionData(OptionType.STRING, "name", "Specify the town name!", true );
        commandData.add(Commands.slash("town", "Check information about a town!").addOptions(optionTown));
        OptionData optionResident = new OptionData(OptionType.STRING, "name", "Specify the resident name!", true );
        commandData.add(Commands.slash("resident", "Check information about a resident!").addOptions(optionResident));
        OptionData optionNation = new OptionData(OptionType.STRING, "name", "Specify the nation name!", true );
        commandData.add(Commands.slash("nation", "Check information about a nation!").addOptions(optionNation));
        return commandData;
    }
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(getCommands()).queue();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        event.getGuild().updateCommands().addCommands(getCommands()).queue();
    }

}
