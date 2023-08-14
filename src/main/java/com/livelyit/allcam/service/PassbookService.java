package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.PassBookDTO;
import com.livelyit.allcam.dto.PassBookResultDTO;

public interface PassbookService {	
 	PassBookResultDTO insertPassBook(PassBookDTO dto);
	PassBookResultDTO insertPassBookV2(PassBookDTO dto);
	PassBookResultDTO insertPassBookV3(PassBookDTO dto);
	PassBookResultDTO insertPassBookV4(PassBookDTO dto);
	PassBookResultDTO insertPassBookMC(PassBookDTO dto);
	PassBookResultDTO insertPassBookV5(PassBookDTO dto);

	PassBookResultDTO selectPassbookList();
	DefaultDTO autoPassbookConfirm(String user_name, int user_pay);
}