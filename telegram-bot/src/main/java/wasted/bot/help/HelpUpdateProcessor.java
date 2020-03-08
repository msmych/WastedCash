package wasted.bot.help;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import wasted.bot.update.processor.UpdateProcessor;

public class HelpUpdateProcessor extends UpdateProcessor {

    public HelpUpdateProcessor(TelegramLongPollingBot bot) {
        super(bot);
    }

    @Override
    public void process(Update update) {
        if (isCommand(update, "help")) {
            sendText(update.getMessage(), "https://telegra.ph/Wasted-cash-03-11");   
        }
    }
}