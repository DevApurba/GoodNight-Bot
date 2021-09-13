import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class Main{
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(System.getenv().get("TOKEN"));
        builder.setActivity(Activity.watching("Time"));
        builder.addEventListeners(new Listener());
        builder.build();
    }
}