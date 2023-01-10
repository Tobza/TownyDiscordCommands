package net.euromc.townydiscordcommands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class TownCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (!event.getName().equals("town")) {
            return;
        }

        Town t = TownyAPI.getInstance().getTown(event.getInteraction().getOption("name").getAsString());

        if (t == null) {

            EmbedBuilder embFailTown = new EmbedBuilder();
            embFailTown.setDescription("This town does not exist!");
            embFailTown.setColor(Color.RED);
            event.replyEmbeds(embFailTown.build()).queue();
            return;
        }

        EmbedBuilder emb = new EmbedBuilder();
        emb.setTitle("Town Statistics");
        emb.addField("Town:", event.getInteraction().getOption("name").getAsString(), false);
        emb.addField("Mayor:", t.getMayor().getName(), false);
        emb.addField("Board:", t.getBoard(), false);
        if (t.hasNation()) {
            emb.addField("Nation:", t.getNationOrNull().getName(), false);
        }
        emb.addField("Fire:", t.isFire() ? "ON" : "OFF", false);
        emb.addField("Explosions:", t.isExplosion() ? "ON" : "OFF", false);
        emb.addField("Mob Spawns:", t.hasMobs() ? "ON" : "OFF", false);
        emb.addField("Resident Count:" , String.valueOf(t.getNumResidents()), false);
        emb.setColor(Color.GREEN);

        event.replyEmbeds(emb.build()).queue();
        // success

    }
}
