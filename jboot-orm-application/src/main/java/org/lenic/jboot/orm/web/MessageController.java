package org.lenic.jboot.orm.web;

import java.util.List;

import org.lenic.jboot.orm.domain.Message;
import org.lenic.jboot.orm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

	@Autowired
	private JdbcTemplate	jdbcTemplate;

	@Autowired
	private MessageService	messageService;

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Message message) {
		messageService.deleteMessage(message);
		return "redirect:index";
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		List<Message> messages = messageService.findMessage();
		return new ModelAndView("index", "messages", messages);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Message message) {
		messageService.createMessage(message);
		return "redirect:index";
	}

}
