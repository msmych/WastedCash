package wasted.bot.help;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import wasted.bot.update.processor.AbsUpdateProcessor;

public class HelpUpdateProcessor extends AbsUpdateProcessor {

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