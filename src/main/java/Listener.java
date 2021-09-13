import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Listener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime messageTime = time.withHour(20).withMinute(30).withSecond(0);

        Duration durationUntilFirstLesson = Duration.between(time, messageTime);
        long initialDelayFirstMessage= durationUntilFirstLesson.getSeconds();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            JDA jda = event.getJDA();
            String bot = event.getJDA().getSelfUser().getName();
            String avatar = event.getJDA().getSelfUser().getAvatarUrl();

            try {
                jda.awaitReady();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Guild guild : jda.getGuilds()) {
                Objects.requireNonNull(guild.getDefaultChannel()).sendMessageEmbeds(new QuoteApiRequest().randomQuotes(avatar, bot).build()).queue();
            }
        }, initialDelayFirstMessage, TimeUnit.DAYS.toSeconds(1),TimeUnit.SECONDS);
    }
}