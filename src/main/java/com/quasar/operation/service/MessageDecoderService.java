package com.quasar.operation.service;

import java.util.Collection;

import com.quasar.operation.exception.MessageDecoderException;

public interface MessageDecoderService {
	
	String getMessage(Collection<String []> messages) throws MessageDecoderException;
}
