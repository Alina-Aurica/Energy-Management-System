package com.example.chatmanagementservice.service.implementation;

import com.example.chatmanagementservice.dto.ChatMessageDTO;
import com.example.chatmanagementservice.entity.ChatMessage;
import com.example.chatmanagementservice.repository.ChatMessageRepository;
import com.example.chatmanagementservice.service.ChatMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ChatMessageServiceImplementation implements ChatMessageService {
    private ChatMessageRepository chatMessageRepository;
    private ModelMapper modelMapper;

    public ChatMessageServiceImplementation(ChatMessageRepository chatMessageRepository, ModelMapper modelMapper){
        this.chatMessageRepository = chatMessageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChatMessageDTO saveInDatabase(ChatMessage chatMessage){
        if(chatMessage != null) {
//            ChatMessage message = chatMessageRepository.findBySenderAndReceiverAndContent(chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getContent());
//            if(message == null) {
                chatMessageRepository.save(chatMessage);
//            }
            ChatMessageDTO chatMessageDTO = modelMapper.map(chatMessage, ChatMessageDTO.class);
            return chatMessageDTO;
        }
        else
            return null;
    }

    @Override
    public List<ChatMessageDTO> findAllConversation(ChatMessage chatMessage){
        List<ChatMessage> chatMessages1 = chatMessageRepository.findAllBySenderAndReceiver(chatMessage.getSender(), chatMessage.getReceiver());
        List<ChatMessage> chatMessages2 = chatMessageRepository.findAllBySenderAndReceiver(chatMessage.getReceiver(), chatMessage.getSender());

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        for(ChatMessage chatMessage1: chatMessages1){
            chatMessages.add(chatMessage1);
        }
        for(ChatMessage chatMessage2: chatMessages2){
            chatMessages.add(chatMessage2);
        }

        chatMessages.sort((ChatMessage chatMessage1, ChatMessage chatMessage2) -> chatMessage1.getTimestamp().compareTo(chatMessage2.getTimestamp()));

        List<ChatMessageDTO> chatMessageDTOs = chatMessages.stream().map(message -> modelMapper.map(message, ChatMessageDTO.class)).collect(Collectors.toList());
        return chatMessageDTOs;
    }

    @Override
    public ChatMessageDTO updateChatMessage(ChatMessage chatMessage){
        //System.out.println("Suntem in update!");
        //ChatMessage message = chatMessageRepository.findBySenderAndReceiverAndContent(chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getContent());
        ChatMessage message = chatMessageRepository.findBySenderAndReceiverAndContentAndTimestamp(chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getContent(), chatMessage.getTimestamp());
        if(!message.isSeen()){
            //System.out.println("Suntem in if-ul din update!");
            message.setSeen(true);
            chatMessageRepository.save(message);
        }

        ChatMessageDTO chatMessageDTO = modelMapper.map(chatMessage, ChatMessageDTO.class);
        return chatMessageDTO;
    }
}
