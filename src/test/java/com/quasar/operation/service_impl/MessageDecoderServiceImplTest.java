package com.quasar.operation.service_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quasar.operation.exception.MessageDecoderException;

@SpringBootTest
public class MessageDecoderServiceImplTest {
	
	private static final String EXPECTED_MESSAGE = "este es un mensaje secreto";
	private static final String [] MSG1 = {"este", "", "", "mensaje", ""};
	private static final String [] MSG2 = {"", "es", "", "", "secreto"};
	private static final String [] MSG3 = {"este", "", "un", "", ""};
	private static final String EXPECTED_ERROR_MSG_SIZE_INCORRECT = "Size of message is incorrect";
	private static final String EXPECTED_ERROR_MSG_ERROR_DECODE = "Have not posibillity to decode message";
	
	@Autowired
	MessageDecoderServiceImpl messagesService;
	
	@Test
	public void whenAssertingEqualitytMessageExpected() throws MessageDecoderException {
		List<String[]> messages = new ArrayList<>();
		messages.add(MSG1);
		messages.add(MSG2);
		messages.add(MSG3);
		
		String resultMessage = messagesService.getMessage(messages);
		assertEquals(EXPECTED_MESSAGE, resultMessage);
	}
	
	@Test
	public void whenAssertingEqualityThrowAndErrorMessageSizeIncorrect() throws MessageDecoderException{
		String[] msg3 = {};
		List<String[]> messages = new ArrayList<>();
		messages.add(MSG1);
		messages.add(MSG2);
		messages.add(msg3);
		
		Exception exception = assertThrows(MessageDecoderException.class, () -> {
			messagesService.getMessage(messages) ;
		});
		
		String errorMessage = exception.getMessage();
		
		assertEquals(EXPECTED_ERROR_MSG_SIZE_INCORRECT, errorMessage);
	}
	
	@Test
	public void whenAssertingEqualityThrowAndErrorToDecodeMessage() throws MessageDecoderException{
		List<String[]> messages = new ArrayList<>();
		messages.add(MSG1);
		messages.add(MSG2);
		
		Exception exception = assertThrows(MessageDecoderException.class, () -> {
			messagesService.getMessage(messages) ;
		});
		
		String errorMessage = exception.getMessage();
		
		assertEquals(EXPECTED_ERROR_MSG_ERROR_DECODE, errorMessage);
	}
	
	
}
