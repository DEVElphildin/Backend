package com.livelyit.allcam.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductListDTO extends DefaultDTO{
	ArrayList<ProductDTO> productList;

}
