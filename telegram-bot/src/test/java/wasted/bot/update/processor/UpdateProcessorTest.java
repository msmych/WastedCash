package wasted.bot.update.processor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

class UpdateProcessorTest {

    private TelegramLongPollingBot bot = mock(TelegramLongPollingBot.class);

    private UpdateProcessor updateProcessor = new UpdateProcessor(bot) {
        @Override
        public void process(Update update) {}
    };

    private Update update = mock(Update.class);
    private Message message = mock(Message.class);

    @BeforeEach
    void setUp() {
        when(bot.getBotUsername()).thenReturn("BotUsername");
    }

    @Test
    void isCommand_true_for_slash_command() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/command");
        assertTrue(updateProcessor.isCommand(update, "command"));
        assertTrue(updateProcessor.isCommand(update, "/command"));
    }

    @Test
    void isCommand_true_for_slash_command_at_bot_username() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/command@BotUsername");
        assertTrue(updateProcessor.isCommand(update, "command"));
        assertTrue(updateProcessor.isCommand(update, "/command"));
    }

    @Test
    void isCommand_false_if_has_no_message() {
        when(update.hasMessage()).thenReturn(false);
        assertFalse(updateProcessor.isCommand(update, "command"));
        assertFalse(updateProcessor.isCommand(update, "/command"));
    }

    @Test
    void isCommand_false_if_message_has_no_text() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(false);
        assertFalse(updateProcessor.isCommand(update, "command"));
        assertFalse(updateProcessor.isCommand(update, "/command"));
    }

    @Test
    void isCommand_false_if_message_has_wrong_text() {
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/wrong");
        assertFalse(updateProcessor.isCommand(update, "command"));
        assertFalse(updateProcessor.isCommand(update, "/command"));
    }

}