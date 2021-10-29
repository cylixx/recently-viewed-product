package com.market.product.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.market.product.MessagingConfig;
import com.market.product.message.ProductMessage;
import com.market.product.model.Product;
import com.market.product.service.ProduceMessageService;

@Service
public class ProduceMessageServiceImpl implements ProduceMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProduceMessageServiceImpl.class);
	
	public static final String PRODUCT_VISITED = "VISITED";
	
	@Value("${application.user.logged.default}")
	private Long loggedUserId;
	
	@Autowired
	private RabbitTemplate template;

	@Override
	public void pushProducVisitedtMessage(Long customerId, Optional<Product> product) {

		if (product.isPresent()) {
			Product prod = product.get();
			ProductMessage message = new ProductMessage(customerId, prod.getId(), PRODUCT_VISITED, "product visited");
			template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, message);
			logger.info("Message pushed on RabbitMQ: {}", message);
		}
	}

}
