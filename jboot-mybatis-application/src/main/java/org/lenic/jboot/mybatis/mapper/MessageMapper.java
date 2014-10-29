package org.lenic.jboot.mybatis.mapper;

import java.util.List;

import org.lenic.jboot.mybatis.domain.Message;

public interface MessageMapper {

	public List<Message> findMessage();
	
	public String findMaxId();

	public void deleteMessage(String id);

	public void createMessage(Message message);
}