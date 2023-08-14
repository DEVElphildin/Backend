package com.livelyit.allcam.service.impl;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.RoomCallMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.mapper.VersionMapper;
import com.livelyit.allcam.service.RoomCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RoomCallServiceImpl implements RoomCallService {
    @Autowired
    RoomCallMapper roomCallMapper;

    @Autowired
    LiveCallMapper liveCallMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    VersionMapper versionMapper;

    @Override
    public RoomCallListDTO list(String target_gender) {
        ArrayList<RoomCallDTO> roomCallList= roomCallMapper.getWaitList(target_gender);
        RoomCallListDTO resultDto = new RoomCallListDTO();
        resultDto.setRoomCallList(roomCallList);
        resultDto.setResult(Utils.SUCCESS);
        if(roomCallList.size() > 0) {
            resultDto.setReason("채널 목록");
        }else{
            resultDto.setReason("채널 생성 대기중");
        }
        return resultDto;
    }

    @Override
    public DefaultDTO make(RoomCallDTO dto) {
        DefaultDTO resultDto = new DefaultDTO();
        boolean banChkFlag = Utils.filterBanText(dto.getRoom_name(), versionMapper.getBanText("채팅")); //금지어
        if(banChkFlag) {
            resultDto.setReason("제목에 금지어가 포함되어 있습니다.");
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        }else{
            int result = roomCallMapper.insertRoomCall(dto);
            if (result == 1) {
                // 대기 목록에서 제외하기
                IsLiveDTO isDto = new IsLiveDTO();
                isDto.setUser_no(dto.getUser_no());
                isDto.setConnect_user_no(0); //대기룸은 -1인 회원만 포함
                liveCallMapper.isLive(isDto); // wating_room 업데이트
                // 대기 목록에서 제외하기

                resultDto.setResult(Utils.SUCCESS);
            } else {
                resultDto.setReason("생성 실패");
            }
        }
        return resultDto;
    }

    @Override
    public DefaultDTO updateStatus(RoomCallDTO dto) {
        DefaultDTO resultDto = new DefaultDTO();
        int result = roomCallMapper.updateStatusRoomCall(dto);
        if(result == 1) {
            resultDto.setResult(Utils.SUCCESS);
        }else{
            resultDto.setReason("처리 실패");
        }
        return resultDto;
    }

    @Override
    public DefaultDTO destroy(int user_no) {
        DefaultDTO resultDto = new DefaultDTO();
        int result = roomCallMapper.destroyRoomCall(user_no);

        // 대기 목록에 포함
        IsLiveDTO isDto = new IsLiveDTO();
        isDto.setUser_no(user_no);
        isDto.setConnect_user_no(-1); //대기룸은 -1인 회원만 포함
        liveCallMapper.isLive(isDto); // wating_room 업데이트
        // 대기 목록에서 제외하기

        if(result >= 1) {
            resultDto.setResult(Utils.SUCCESS);
        }else{
            resultDto.setReason("변경 실패");
        }
        return resultDto;
    }

    @Override
    public DefaultDTO start(String room_id, int my_user_no, int partner_user_no) {
        DefaultDTO resultDto = new DefaultDTO();

        RoomCallStartDTO roomCallDto = new RoomCallStartDTO();
        roomCallDto.setRoom_id(room_id);
        roomCallDto.setMy_user_no(my_user_no);
        roomCallDto.setPartner_user_no(partner_user_no);
        roomCallMapper.startRoomCall(roomCallDto);	//룸 생성. WATING ROOM connect_user update.

        if(roomCallDto.getResult_code() == 1) {
            resultDto.setResult(Utils.SUCCESS);
        }else{
            resultDto.setReason("통화 연결 실패");
        }

        return resultDto;
    }

    @Override
    public UserBalanceDTO finish(int user_no, String room_id, int use_seconds, int finish_type) {
        //live room  업데이트
        CallFinishDTO callFinishDto = new CallFinishDTO();
        callFinishDto.setRoom_id(room_id);
        callFinishDto.setFinish_user_no(user_no);
        callFinishDto.setFinish_type(finish_type);
        callFinishDto.setFinish_use_seconds(use_seconds);
        roomCallMapper.finishRoomCall(callFinishDto);

        //response
        UserBalanceDTO resultDto = new UserBalanceDTO();
        UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
        resultDto.setUser_no(user_no);
        resultDto.setUser_point(userInfoDto.getUser_point());
        resultDto.setUser_cash(userInfoDto.getUser_cash());
        resultDto.setUser_gender(userInfoDto.getUser_gender());
        resultDto.setResult(Utils.SUCCESS);

        return resultDto;

    }
}
