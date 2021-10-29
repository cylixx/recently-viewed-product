package com.market.product.viewed.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.market.product.viewed.MessagingConfig;
import com.market.product.viewed.message.ProductMessage;
import com.market.product.viewed.model.ProductViewed;
import com.market.product.viewed.service.ProductViewedService;

@Component
public class ConsumeProductMessage {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeProductMessage.class);

	@Autowired
	private ProductViewedService productViewedService;

	@Value("${application.rabbitmq.queue}")
	private String queueName;

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeProductVisitedMessage(ProductMessage productMessage) {
		logger.info("===== CONSUME message from RabittMQ: {}", productMessage);

		ProductViewed productViewed = new ProductViewed();
		productViewed.setCustomerId(productMessage.getCustomerId());
		productViewed.setProductId(productMessage.getProductId());
		try {
			productViewedService.saveProductViewed(productViewed);
		} catch (Exception e) {
			// Product viewed already exist, so we should update it
			productViewedService.updateProductViewed(productMessage.getCustomerId(), productMessage.getProductId());
		}
	}

}
