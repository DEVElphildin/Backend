package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.RoomCallDTO;
import com.livelyit.allcam.dto.RoomCallListDTO;
import com.livelyit.allcam.dto.UserBalanceDTO;

public interface RoomCallService {
    RoomCallListDTO list(String target_gender);
    DefaultDTO make(RoomCallDTO dto);
    DefaultDTO updateStatus(RoomCallDTO dto);
    DefaultDTO destroy(int user_no);
    DefaultDTO start(String room_id, int my_user_no, int partner_user_no);
    UserBalanceDTO finish(int user_no, String room_id, int use_seconds, int finish_type);
}
