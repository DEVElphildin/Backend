package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class ProductDTO {
	int no;
	String name;
	int price;
	int ori_price;
	int bonus_point;
	String play_store_product_id;
}
