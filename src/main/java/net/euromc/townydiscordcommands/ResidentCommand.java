package net.euromc.townydiscordcommands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ResidentCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (!event.getName().equals("resident")) {
            return;
        }

        Resident r = TownyAPI.getInstance().getResident(event.getInteraction().getOption("name").getAsString());

        if (r == null) {
            EmbedBuilder embFailRes = new EmbedBuilder();
            embFailRes.setDescription("This resident does not exist!");
            embFailRes.setColor(Color.RED);
            event.replyEmbeds(embFailRes.build()).queue();
            return;
        }

        EmbedBuilder emb = new EmbedBuilder();
        emb.setTitle("Resident Statistics");
        emb.addField("Resident:", r.getName(), false);
        if (r.hasTown()) {
            emb.addField("Town:", r.getTownOrNull().getName(), false);
        }
        if (r.hasNation()) {
            emb.addField("Nation:" , r.getNationOrNull().getName(), false);
        }
        emb.addField("Activity:", r.isOnline() ? "ONLINE" : "OFFLINE" , false);
        emb.setColor(Color.GREEN);

        event.replyEmbeds(emb.build()).queue();
    }
}
