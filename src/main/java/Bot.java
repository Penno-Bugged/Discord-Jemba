import events.Overview;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {

    public static void main(String[] args) throws Exception{
        JDA builder = JDABuilder.createDefault(args[0]).build();

        builder.addEventListener(new Overview());
    }


}
