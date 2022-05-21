import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BotCreator extends ListenerAdapter {


   public static void main(String[] args) throws LoginException
   {
       if (args.length < 1) {
           System.out.println("Please only provide the token as an arg");
           System.exit(1);
       }

       JDA jda = JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
               .addEventListeners(new BotCreator()).setActivity(Activity.playing("Jemba")).build();

       //jda.upsertCommand("pung", "Calculate ping of the bot").queue();

       // These commands take up to an hour to be activated after creation/update/delete
       CommandListUpdateAction commands = jda.updateCommands();
       commands.addCommands(new CommandData("compliment", "We all need loving words now and then"));
       commands.addCommands(new CommandData("pung", "Lets see how fast I am today"));

       // Send the new set of commands to discord, this will override any existing global commands with the new set provided here
       commands.queue();
   }



   @Override
   public void onSlashCommand(SlashCommandEvent event) {
       System.out.println("Command Event Received");
       switch (event.getName()) {
           case "pung" -> PingCommand(event);
           case "compliment" -> Insult(event);
       }
   }



   @Override
   public void onMessageReceived(MessageReceivedEvent event)
   {
       Message msg = event.getMessage();
       switch (msg.getContentRaw()) {
           case ".testbutton" -> ButtonTest(event);
       }
   }








   private void PingCommand(SlashCommandEvent event){
       System.out.println("Received request for ping");
       long time = System.currentTimeMillis();
       event.reply("My Ping is: ").setEphemeral(true).flatMap(v -> event.getHook().
               editOriginalFormat("Peng: %d ms", System.currentTimeMillis() - time)).queue();
   }

   private void Insult(SlashCommandEvent event){
       System.out.println("Insult request received");
       try{
           BufferedReader insultsReader = new BufferedReader(new FileReader("src/main/resources/insults.txt"));
           String[] insults = insultsReader.readLine().split(",");

           insultsReader.close();

           Random random = new Random();
           int i = random.nextInt(22);

           event.reply(insults[i]).queue();

       }
       catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println("Insult request done");
   }


   private static EmbedBuilder defaultEmbed(){
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       Date date = new Date();
       return new EmbedBuilder().setFooter(formatter.format(date),
               "https://t4.ftcdn.net/jpg/01/93/99/63/360_F_193996380_W4rNTe0W5d3PEuUoMp6rJJAH9ekMH4Kd.jpg")
               .setColor(Color.RED);
   }



   private void ButtonTest(MessageReceivedEvent event){

       MessageChannel channel = event.getMessage().getChannel();
       channel.sendMessageEmbeds(BotCreator.defaultEmbed().setTitle("Jemba")
               .setDescription("Text test toast")
               .build()).queue();
       MessageBuilder builder = new MessageBuilder("\u200E");
       ActionRow ar = ActionRow.of(Button.primary("hello", "Click me"));
       builder.setActionRows(ar);
       channel.sendMessage(builder.build()).queue();

   }


   @Override
   public void onButtonClick(ButtonClickEvent event) {
       if (event.getComponentId().equals("hello")) {
           event.reply("Bye bye :(").queue(); // send a message in the channel
       } else if (event.getComponentId().equals("emoji")) {
           event.editMessage("That button didn't say click me").queue(); // update the message
       }
   }





}
