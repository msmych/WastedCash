package wasted.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

class WastedBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(WastedBot.class);

    private static final String BOT_USERNAME = "SmychTestBot";

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
        if (!update.hasMessage()) {
            return;
        }
        var message = update.getMessage();
        if (!message.hasText()) {
            return;
        }
        var text = message.getText();
        if (text.equals("/help") || text.equals("/help@" + BOT_USERNAME)) {
            try {
                execute(new SendMessage(message.getChatId(), "https://telegra.ph/Wasted-cash-03-11"));
            } catch (TelegramApiException e) {
                log.error("Error sending message", e);
            }
        }
    }
}