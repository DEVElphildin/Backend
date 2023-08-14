package com.livelyit.allcam.controller;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.service.RoomCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roomCall")
public class RoomCallController {
    @Autowired
    RoomCallService roomCallService;


    // 라이브 룸(대기중) 목록
    @RequestMapping("/list")
    @ResponseBody
    public RoomCallListDTO list(@RequestParam(value = "target_gender", required = !Utils.Debug) String target_gender ) {
        return roomCallService.list(target_gender);
    }

    // 방 만들기
    @RequestMapping(value = "/make", params = {"user_no", "room_name"})
    @ResponseBody
    public DefaultDTO make(@RequestParam(value = "user_no", required=!Utils.Debug) int user_no,
                                     @RequestParam(value = "room_name", required=!Utils.Debug) String room_name) {
        RoomCallDTO dto = new RoomCallDTO();
        dto.setUser_no(user_no);
        dto.setRoom_name(room_name);

        return roomCallService.make(dto);
    }

    // 방 상태 변경
    @RequestMapping(value = "/updateStatus", params = {"no", "room_status"})
    @ResponseBody
    public DefaultDTO make(@RequestParam(value = "no", required=!Utils.Debug) int no,
                           @RequestParam(value = "room_status", required=!Utils.Debug) int room_status) {
        RoomCallDTO dto = new RoomCallDTO();
        dto.setNo(no);
        dto.setRoom_status(room_status);

        return roomCallService.updateStatus(dto);
    }

    // 라이브 룸(대기중) 나가기
    @RequestMapping("/destroy")
    @ResponseBody
    public DefaultDTO destroy(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no ) {
        //RoomCallDTO dto = new RoomCallDTO();
        //dto.setRoom_id(room_id);
        return roomCallService.destroy(user_no);
    }

    // 라이브 룸(대기중) 통화 시작
    @RequestMapping("/start")
    @ResponseBody
    public DefaultDTO start(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
                            @RequestParam(value = "my_user_no", required = !Utils.Debug) int my_user_no,
                            @RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no) {
        //RoomCallDTO dto = new RoomCallDTO();
        //dto.setRoom_id(room_id);
        //dto.setPartner_user_no(partner_user_no);
        return roomCallService.start(room_id, my_user_no, partner_user_no);
    }

    // 라이브 룸(대기중) 통화 종료
    @RequestMapping("/finish")
    @ResponseBody
    public UserBalanceDTO finish(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
                                 @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
                                 @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
                                 @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
        return roomCallService.finish(user_no, room_id, use_seconds, finish_type);
    }
}
