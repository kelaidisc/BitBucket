package org.tamasenco.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.tamasenco.dto.MessageDto;

@NoArgsConstructor
@AllArgsConstructor
public class MessageRepository {

  Map<String, List<String>> messages = new HashMap<>();
  List<String> currentMessages = new ArrayList<>();

  public void createMessage(String uid, MessageDto messageDto) {
    String currentMessage = messageDto.getMessage();
    currentMessages.add(currentMessage);
    messages.put(uid, currentMessages);
  }

  public List<String> getMessages(String uid) {
    return messages.get(uid);
  }
}