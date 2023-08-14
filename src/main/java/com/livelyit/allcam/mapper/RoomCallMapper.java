package com.livelyit.allcam.mapper;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository("roomCallMapper")
public class RoomCallMapper extends AbstractDAO {
    public ArrayList<RoomCallDTO> getWaitList(String target_gender) {
        return (ArrayList<RoomCallDTO>) selectList(Utils.SQL_ALLCAM, "roomCallMapper.getWaitList", target_gender);
    }

    public int insertRoomCall(RoomCallDTO dto) {
        return (int) insert(Utils.SQL_ALLCAM, "roomCallMapper.insertRoomCall", dto);
    }
    public int updateStatusRoomCall(RoomCallDTO dto) {
        return (int) update(Utils.SQL_ALLCAM, "roomCallMapper.updateStatusRoomCall", dto);
    }


    public int destroyRoomCall(int user_no) {
        return (int) update(Utils.SQL_ALLCAM, "roomCallMapper.destroyRoomCall", user_no);
    }

    public RoomCallStartDTO startRoomCall(RoomCallStartDTO dto) {
        return (RoomCallStartDTO) selectOne(Utils.SQL_ALLCAM, "roomCallMapper.startRoomCall", dto);
    }

    public int finishRoomCall(CallFinishDTO dto){
        return (int) update(Utils.SQL_ALLCAM, "roomCallMapper.finishRoomCall", dto);
    }

}