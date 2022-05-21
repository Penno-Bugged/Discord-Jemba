package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class Overview extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent e){
        String messageSent = e.getMessage().getContentRaw();
        JSONParser parser = new JSONParser();
        try {
            // parsing file "JSONExample.json"
            Object ob = new JSONParser().parse(new FileReader("rules.json"));

            // typecasting ob to JSONObject
            JSONObject js = (JSONObject) ob;

            String firstName = (String) js.get("firstName");
            String lastName = (String) js.get("lastName");

            System.out.println("First name is: " + firstName);
            System.out.println("Last name is: " + lastName);
        } catch (Exception Except) {
            Except.printStackTrace();
        }
    }



//
//            BufferedReader pieceReader = new BufferedReader(new FileReader("src/main/resources/insults.txt"));
//            String[] piecesArray = pieceReader.readLine().split(",");
//            pieceReader.close();
//            if (FileProcess(piecesArray, messageSent)) {
//
//            }
//        }
//        catch(Exception exception) {
//            exception.printStackTrace();
//        }
//    }


    private boolean FileProcess(String[] piecesArray, String message) {
        for (String item : piecesArray) {
            if (item.equalsIgnoreCase(message)) { return true; }
        }
        return false;
    }

    public static void EmbedBuilder(@NotNull MessageReceivedEvent e, String header, String body, Color colour) {
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(colour);
        usage.setTitle(header);
        usage.setDescription(body);
        e.getChannel().sendMessageEmbeds(usage.build()).queue();
    }

}
