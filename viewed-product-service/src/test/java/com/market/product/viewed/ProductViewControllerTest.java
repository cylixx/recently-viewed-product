package com.market.product.viewed;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.product.viewed.dto.ProductViewedDto;
import com.market.product.viewed.model.ProductViewed;
import com.market.product.viewed.service.ProductViewedService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest
public class ProductViewControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	ProductViewedService service;
	
	ObjectMapper om = new ObjectMapper();
	
	
	@Test
	public void saveTest() throws Exception {
		ProductViewed productViewed = new ProductViewed();
		productViewed.setCustomerId(100L);
		productViewed.setProductId(10L);
		String jsonRequest = om.writeValueAsString(productViewed);
		
		Mockito.when(service.saveProductViewed(productViewed)).thenReturn(productViewed);
		
		MvcResult result = mockMvc
				.perform(post("/api/product/viewed").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.CREATED.value()); 
	
	}
	
	
	@Test
	public void findAllRecentlyProductViewedTest() throws Exception {
		ProductViewedDto dto = new ProductViewedDto(10L, "product 10", 120, 100, new Date());
		List<ProductViewedDto> response = new ArrayList<ProductViewedDto>();
		response.add(dto);
		Mockito.when(service.findAllRecentlyViewedByCustomerId(100L)).thenReturn(response);
		
		MvcResult result = mockMvc
		.perform(get("/api/product/viewed/100"))
		.andExpect(status().isOk())
		.andReturn();
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value()); 
	}
	
	
	@Test
	public void findAllRecentlyProductViewedNotFoundTest() throws Exception {
		List<ProductViewedDto> response = new ArrayList<ProductViewedDto>();
		Mockito.when(service.findAllRecentlyViewedByCustomerId(100L)).thenReturn(response);
		
		MvcResult result = mockMvc
		.perform(get("/api/product/viewed/100"))
		.andExpect(status().isNotFound())
		.andReturn();
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value()); 
	}

}
