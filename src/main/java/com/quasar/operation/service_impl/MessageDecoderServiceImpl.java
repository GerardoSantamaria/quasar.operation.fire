package com.quasar.operation.service_impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.quasar.operation.exception.MessageDecoderException;
import com.quasar.operation.service.MessageDecoderService;

@Service
public class MessageDecoderServiceImpl implements MessageDecoderService {
	
	@Override
	public String getMessage(Collection<String[]> messages) throws MessageDecoderException{
		List<List<String>> messageParse = parseMessages(messages);
		List<String> msgPhrases = getMessagePhrases(messageParse);
		validateMessagesSize(messageParse, msgPhrases.size());
		
		removeGap(messageParse,msgPhrases.size());
		String finalMessage = completeMessage(messageParse);
		validateMessagePhrases(msgPhrases,finalMessage);
		
		return finalMessage;
	}

    private List<List<String>> parseMessages(Collection<String[]> messages) {
    	List<List<String>> parseMessage =  new ArrayList<List<String>>();
		
		for (String[] arrayMessage : messages) {
			ArrayList<String> listMessage =  new ArrayList<String>();
			Collections.addAll(listMessage, arrayMessage);
			parseMessage.add(listMessage);
		}
		
		return parseMessage;
	}

    private List<String> getMessagePhrases(List<List<String>> msgList){

        List<String> listWords = new ArrayList<String>();
        for( List<String> msg : msgList){
            listWords = Stream.concat(listWords.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords;
    }

    private void removeGap(List<List<String>> msgList, int gapSize){

        int s = 0;
        for(int i = 0; i < msgList.size(); i++){
            s = msgList.get(i).size();
            msgList.set(i,msgList.get(i).subList(s-gapSize, s));
        }
        
    }

    private String completeMessage(List<List<String>> msgList){

        String phrase = "";
        for(List<String> m : msgList){

        	if(m.size()>0 && !m.get(0).equals("")){
                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
                msgList.stream().forEach( s -> s.remove(0));
                return  phrase + completeMessage(msgList);
            }
        }
        
        return "";
    }

    private void validateMessagesSize(List<List<String>> messages, int size) throws MessageDecoderException {
        for(List<String> m : messages){
            if(m.size() < size){
            	throw new MessageDecoderException("Size of message is incorrect");
            }
        }
    }

    private void validateMessagePhrases(List<String> phrases, String message) throws MessageDecoderException{
        List<String> msg = Arrays.stream(message.split(" ")).collect(Collectors.toList());
        Collections.sort(phrases);
        Collections.sort(msg);
        
        if(!Arrays.equals(phrases.toArray(), msg.toArray())) {
        	throw new MessageDecoderException("Have not posibillity to decode message");
        }
    }
    
}
