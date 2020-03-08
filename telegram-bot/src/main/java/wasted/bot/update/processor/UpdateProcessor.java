package wasted.bot.update.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class UpdateProcessor {

    private static final Logger log = LoggerFactory.getLogger(UpdateProcessor.class);

    private final TelegramLongPollingBot bot;

    public UpdateProcessor(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public abstract void process(Update update);

    protected boolean isCommand(Update update, String command) {
        if (!update.hasMessage()) {
            return false;
        }
        var message = update.getMessage();
        if (!message.hasText()) {
            return false;
        }
        var text = message.getText();
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        return text.equals("/" + command) || 
            text.equals("/" + command + "@" + bot.getBotUsername());
    }

    protected void sendText(Message message, String text) {
        try {
            bot.execute(new SendMessage(message.getChatId(), text));
        } catch (TelegramApiException e) {
            log.error("Error sending message", e);
        }
    }
}