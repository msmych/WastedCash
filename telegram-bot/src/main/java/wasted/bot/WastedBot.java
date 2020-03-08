package wasted.bot;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import wasted.bot.help.HelpUpdateProcessor;
import wasted.bot.update.processor.AbsUpdateProcessor;

class WastedBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(WastedBot.class);

    private static final String BOT_USERNAME = "SmychTestBot";

    private final Set<AbsUpdateProcessor> updateProcessors = Set.of(new HelpUpdateProcessor(this));

    private final String token;

    public WastedBot(String token) {
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Received update {}", update);
        this.updateProcessors
            .forEach(updateProcessor -> updateProcessor.process(update));
    }
}