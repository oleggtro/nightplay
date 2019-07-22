package gq.not11.bot;

import gq.not11.bot.commands.PingCommand;
import gq.not11.bot.core.command.CommandHandler;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Bot {

    private ShardManager shardManager;
    private CommandHandler commandHandler;

    private Bot(String[] args) {
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder()
                .setToken(System.getenv("DISCORD_TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.listening(".help"));

        commandHandler = new CommandHandler(this);
        commandHandler.register(new PingCommand());
        builder.addEventListeners(commandHandler);

        try {
            shardManager = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Bot(args);
    }
}