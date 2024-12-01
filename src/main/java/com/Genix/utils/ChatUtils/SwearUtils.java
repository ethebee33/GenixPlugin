package com.Genix.utils.ChatUtils;

import com.Genix.data.wordsData;
import com.Genix.utils.chatUtils;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class SwearUtils {
    public static void checkForSwearing(AsyncPlayerChatEvent event) {
        String swear = String.valueOf(checkSwear(event.getMessage()));
        if (swear != null) {
            event.setCancelled(true);
            chatUtils.sendMessage(event.getPlayer(), String.join("A word in this sentence is not allowed:", swear), true);
            chatUtils.sendMessage(event.getPlayer(), event.getMessage(), false);
            chatUtils.sendMessage(event.getPlayer(), "If you think this is wrong, please report it", false); //change this to true?? idrk might look better
        }
    }

    public static String checkSwear(String message) {
        if (wordsData.wordsDataConfig == null) {
            return null;
        }
        List<String> warnList = wordsData.wordsDataConfig.getStringList("warnList");
        for (int i = 1; i < warnList.size(); i++) {
            String word = warnList.get(i);
            if (message.contains(word)) {
                return word;
            }
        }
        return null;
    }
}
