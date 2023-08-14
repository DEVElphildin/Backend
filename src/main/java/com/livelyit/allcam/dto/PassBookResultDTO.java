package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PassBookResultDTO extends DefaultDTO {

	ArrayList<PassBookDTO> passbookList;
	PassBookDTO addPassbook;
}
