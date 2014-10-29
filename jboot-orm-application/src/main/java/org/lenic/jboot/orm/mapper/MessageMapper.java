package org.lenic.jboot.orm.mapper;

import java.util.List;

import org.lenic.jboot.orm.domain.Message;

public interface MessageMapper {

	public List<Message> findMessage();
	
	public String findMaxId();

	public void deleteMessage(String id);

	public void createMessage(Message message);
}