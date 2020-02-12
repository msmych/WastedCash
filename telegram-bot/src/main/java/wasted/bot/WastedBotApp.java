package wasted.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class WastedBotApp {

    private static final Logger log = LoggerFactory.getLogger(WastedBotApp.class);

    public static void main(String... args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        new TelegramBotsApi().registerBot(new WastedBot(args[0]));
        log.info("Поехали");
    }
}