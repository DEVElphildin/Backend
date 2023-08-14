package com.livelyit.allcam.service.impl;

import java.util.ArrayList;

import com.livelyit.allcam.dto.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.ChatMapper;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.mapper.VersionMapper;
import com.livelyit.allcam.service.ChatService;
import com.livelyit.allcam.service.PushService;
import com.livelyit.allcam.service.UserService;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    LiveCallMapper liveCallMapper;

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PushService pushService;

    @Autowired
    VersionMapper versionMapper;

    @Autowired
    UserService userService;

    //내 채팅
    @Override
    public ChatListDTO getMyChat(int user_no) {
        ChatListDTO dto = new ChatListDTO();
        dto.setChatList(chatMapper.getMyChat(user_no));
        dto.setResult(Utils.SUCCESS);
        return dto;
    }

    //내 채팅
    @Override
    public ChatListDTO getMyChatV2(int user_no) {
        ChatListDTO dto = new ChatListDTO();
        dto.setChatList(chatMapper.getMyChatV2(user_no));
        dto.setResult(Utils.SUCCESS);
        return dto;
    }

    //내 채팅
    @Override
    public ChatListDTO getMyChatV3(int user_no) {
        UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
        if (userInfoDTO.getUser_country_code()==null)
            userInfoDTO.setUser_country_code("KR");
        ChatListDTO dto = new ChatListDTO();
        dto.setChatList(chatMapper.getMyChatV3(userInfoDTO));
        dto.setResult(Utils.SUCCESS);
        return dto;
    }

    //1:1 채팅 내용
    @Override
    public ChatListDTO getUserChat(int user_no, int to_user_no) {
        ChatListDTO resultDto = new ChatListDTO();

        ChatUserDTO dto = new ChatUserDTO();
        dto.setUser_no(user_no);
        dto.setTo_user_no(to_user_no);

        resultDto.setChatList(chatMapper.getUserChat(dto));
        resultDto.setResult(Utils.SUCCESS);

        return resultDto;
    }

    //1:1 채팅 내용
    @Override
    public ChatListDTO getUserChatV2(int user_no, int to_user_no) {
        ChatListDTO resultDto = new ChatListDTO();

        ChatUserDTO dto = new ChatUserDTO();
        dto.setUser_no(user_no);
        dto.setTo_user_no(to_user_no);
        dto.setPresent_to_cash_rate(Utils.NMC_PRESENT_TO_CASH_RATE);

        UserInfoDTO userInfo = userMapper.getUserInfo(user_no);
        UserInfoDTO toUserInfo = userMapper.getUserInfo(to_user_no);

        if (userInfo.getUser_country_code()==null)
            dto.setUser_country_code("KR");
        else
            dto.setUser_country_code(userInfo.getUser_country_code());

        resultDto.setUser_country_code(toUserInfo.getUser_country_code());
        resultDto.setUser_language_code(toUserInfo.getUser_language_code());

        resultDto.setChatList(chatMapper.getUserChatV2(dto));
        resultDto.setResult(Utils.SUCCESS);

        return resultDto;
    }

    //1:1 채팅 내용
    @Override
    public ChatListDTO getUserChatV3(int user_no, int to_user_no) {
        ChatListDTO resultDto = new ChatListDTO();

        ChatUserDTO dto = new ChatUserDTO();
        dto.setUser_no(user_no);
        dto.setTo_user_no(to_user_no);
        dto.setPresent_to_cash_rate(Utils.NMC_PRESENT_TO_CASH_RATE);

        UserInfoDTO userInfo = userMapper.getUserInfo(user_no);
        UserInfoDTO toUserInfo = userMapper.getUserInfo(to_user_no);

        if (userInfo.getUser_country_code()==null)
            dto.setUser_country_code("KR");
        else
            dto.setUser_country_code(userInfo.getUser_country_code());

        resultDto.setUser_country_code(toUserInfo.getUser_country_code());
        resultDto.setUser_language_code(toUserInfo.getUser_language_code());

        resultDto.setChatList(chatMapper.getUserChatV3(dto));
        resultDto.setResult(Utils.SUCCESS);

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChat(int user_no, int to_user_no, String chat_text) {
        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setTo_visible(0);

            int pChat_cnt = chatMapper.getUserChatCnt(dto);
            int deductionPoint = 30; // 채팅시 차감 포인트
            if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 30 차감

                int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                UseHistoryDTO hDto = new UseHistoryDTO();
                hDto.setUser_no(user_no);
                hDto.setUse_etc(to_user_no + "");
                hDto.setUse_content("채팅 신청 차감");

                if (deductionFreePoint > 0) { // 무료포인트 사용
                    hDto.setUse_cnt(-deductionFreePoint);
                    hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                    liveCallMapper.insertUseHistory(hDto);
                }
                if (deductionPayPoint > 0) { // 유료포인트 사용
                    hDto.setUse_cnt(-deductionPayPoint);
                    hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                    liveCallMapper.insertUseHistory(hDto);
                }

                UserPointDTO pointDto = new UserPointDTO();
                pointDto.setNo(user_no);
                pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                userMapper.setPoint(pointDto);
            } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                resultDto.setError_code("1");
                resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                return resultDto;
            }

            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChat(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                resultDto.setChatList(chatMapper.getUserChat(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }

            resultDto.setUser_point(userMapper.getMyPoint(user_no).getUser_point());
        }

        return resultDto;
    }

    //중복로그인 오류 관련 push 토큰 업데이트용 오버로드
    public ChatListDTO sendChat(int user_no, int to_user_no, String chat_text, String pushKey) {
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();
        resultDto = sendChat(user_no, to_user_no, chat_text);
        return resultDto;
    }

    @Override
    //중복로그인 오류 관련 push 토큰 업데이트용 오버로드
    public ChatListDTO sendChat(int user_no, int to_user_no, String chat_text, boolean banPush) {
        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setTo_visible(0);

            int pChat_cnt = chatMapper.getUserChatCnt(dto);
            int deductionPoint = 30; // 채팅시 차감 포인트
            if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 30 차감

                int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                UseHistoryDTO hDto = new UseHistoryDTO();
                hDto.setUser_no(user_no);
                hDto.setUse_etc(to_user_no + "");
                hDto.setUse_content("채팅 신청 차감");

                if (deductionFreePoint > 0) { // 무료포인트 사용
                    hDto.setUse_cnt(-deductionFreePoint);
                    hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                    liveCallMapper.insertUseHistory(hDto);
                }
                if (deductionPayPoint > 0) { // 유료포인트 사용
                    hDto.setUse_cnt(-deductionPayPoint);
                    hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                    liveCallMapper.insertUseHistory(hDto);
                }

                UserPointDTO pointDto = new UserPointDTO();
                pointDto.setNo(user_no);
                pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                userMapper.setPoint(pointDto);
            } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                resultDto.setError_code("1");
                resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                return resultDto;
            }

            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChat(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                resultDto.setChatList(chatMapper.getUserChat(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    if (banPush) {
                        pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                    }

                }
            }

            resultDto.setUser_point(userMapper.getMyPoint(user_no).getUser_point());
        }

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChatV2(int user_no, int to_user_no, String chat_text, String pushKey) {

        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = 90; // 채팅시 차감 포인트
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }

            }

            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChat(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                chatDto.setPresent_to_cash_rate(Utils.NMC_PRESENT_TO_CASH_RATE);
                resultDto.setChatList(chatMapper.getUserChatV2(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }

            resultDto.setUser_point(userMapper.getMyPoint(user_no).getUser_point());
        }

        return resultDto;
    }

    @Override
    //중복로그인 오류 관련 push 토큰 업데이트용 오버로드
    public ChatListDTO sendChatV2(int user_no, int to_user_no, String chat_text, boolean banPush) {
        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {


                int pChat_cnt = chatMapper.getUserChatCnt(dto);
                int deductionPoint = 90; // 채팅시 차감 포인트
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text)  && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }

            }

            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChat(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                resultDto.setChatList(chatMapper.getUserChat(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    if (banPush) {
                        pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                    }

                }
            }

            resultDto.setUser_point(userMapper.getMyPoint(user_no).getUser_point());
        }

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChatV2EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans) {

        // 보내는 사람의 FCM key를 수정한다.
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            // 채팅 메세지 설정
            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setChat_text_trans(chat_text_trans);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                // 과금하기 위해 이전 하루 동안 채팅 했는지 정보 가져옴
                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = 90; // 채팅시 차감 포인트

                // 이전 하루 동안 채팅한 적이 없고, 사용자의 포인트가 90포인트가 넘고 채팅 메세지를 보낸 경우
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }
            }

            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChatEN(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                chatDto.setPresent_to_cash_rate(Utils.NMC_PRESENT_TO_CASH_RATE);
                resultDto.setChatList(chatMapper.getUserChatV2(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chat_text);
                    chatData.put("chat_text_trans", chat_text_trans);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    DefaultDTO defaultDTO = pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chat_text_trans); //상대 방에게 푸쉬 발송
                }
            }

            resultDto.setUser_point(userMapper.getMyPoint(user_no).getUser_point());
        }

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChatV3(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type) {

        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = 90; // 채팅시 차감 포인트
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }

            }

            // 상대방이 나를 차단했는지 정보 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }
            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }


    //채팅 보내기
    @Override
    public ChatListDTO sendChatV3EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type) {

        // 보내는 사람의 FCM key를 수정한다.
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            // 나와 상대방 정보 가져오기
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            // 채팅 메세지 설정
            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setChat_text_trans(chat_text_trans);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                // 과금하기 위해 이전 하루 동안 채팅 했는지 정보 가져옴
                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = 90; // 채팅시 차감 포인트

                // 이전 하루 동안 채팅한 적이 없고, 사용자의 포인트가 90포인트가 넘고 채팅 메세지를 보낸 경우
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }


                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);
                    // 남자에게 채팅 과금하기
                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }
            }

            // 내가 상대방에게 차단됐는지 데이터 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            // 채팅 데이터 저장하기
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                // 채팅 전체 목록 가져오기
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chat_text);
                    chatData.put("chat_text_trans", chat_text_trans);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    // 상대방에게 채팅 보냈다는 push 보내기
                    DefaultDTO defaultDTO = pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chat_text_trans); //상대 방에게 푸쉬 발송
                }
            }

            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChatV4(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type) {

        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            // 상대방이 나를 차단했는지 정보 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }
            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }


    //채팅 보내기
    @Override
    public ChatListDTO sendChatV4EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type) {

        // 보내는 사람의 FCM key를 수정한다.
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            // 나와 상대방 정보 가져오기
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            // 채팅 메세지 설정
            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setChat_text_trans(chat_text_trans);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            // 내가 상대방에게 차단됐는지 데이터 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            // 채팅 데이터 저장하기
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                // 채팅 전체 목록 가져오기
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chat_text);
                    chatData.put("chat_text_trans", chat_text_trans);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    // 상대방에게 채팅 보냈다는 push 보내기
                    DefaultDTO defaultDTO = pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chat_text_trans); //상대 방에게 푸쉬 발송
                }
            }

            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }

    //채팅 보내기
    @Override
    public ChatListDTO sendChatMC(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type) {

        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = Utils.MC_FIRST_CHAT_POINT; // 채팅시 차감 포인트
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }

            }

            // 상대방이 나를 차단했는지 정보 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }
            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }


    //채팅 보내기
    @Override
    public ChatListDTO sendChatMCEN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type) {

        // 보내는 사람의 FCM key를 수정한다.
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            // 나와 상대방 정보 가져오기
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            // 채팅 메세지 설정
            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setChat_text_trans(chat_text_trans);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                // 과금하기 위해 이전 하루 동안 채팅 했는지 정보 가져옴
                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = Utils.MC_FIRST_CHAT_POINT; // 채팅시 차감 포인트

                // 이전 하루 동안 채팅한 적이 없고, 사용자의 포인트가 90포인트가 넘고 채팅 메세지를 보낸 경우
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }


                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);
                    // 남자에게 채팅 과금하기
                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }
            }

            // 내가 상대방에게 차단됐는지 데이터 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            // 채팅 데이터 저장하기
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                // 채팅 전체 목록 가져오기
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chat_text);
                    chatData.put("chat_text_trans", chat_text_trans);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    // 상대방에게 채팅 보냈다는 push 보내기
                    DefaultDTO defaultDTO = pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chat_text_trans); //상대 방에게 푸쉬 발송
                }
            }

            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }
//채팅 보내기 v5
    @Override
    public ChatListDTO sendChatV5(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type) {

        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = Utils.NMC_FIRST_CHAT_POINT; // 채팅시 차감 포인트
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }

                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);

                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }

            }

            // 상대방이 나를 차단했는지 정보 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String chatText = chat_text;
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chatText);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText); //상대 방에게 푸쉬 발송
                }
            }
            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }


    //채팅 보내기
    @Override
    public ChatListDTO sendChatV5EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type) {

        // 보내는 사람의 FCM key를 수정한다.
        UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
        userPushTokenDto.setNo(user_no);
        userPushTokenDto.setUser_push_key(pushKey);
        userMapper.setPushKey(userPushTokenDto);

        ChatListDTO resultDto = new ChatListDTO();

        boolean banChkFlag = Utils.filterBanText(chat_text, versionMapper.getBanText("채팅")); //금지어

        if (banChkFlag) {
            resultDto.setError_code("2");//금지어 에러는 늘 2번
        } else {
            // 나와 상대방 정보 가져오기
            UserInfoDTO fromUserDto = userMapper.getUserInfo(user_no);
            UserInfoDTO toUserDto = userMapper.getUserInfo(to_user_no);

            // 채팅 메세지 설정
            ChatDTO dto = new ChatDTO();
            dto.setChat_text(chat_text);
            dto.setChat_text_trans(chat_text_trans);
            dto.setTo_user_no(to_user_no);
            dto.setFrom_user_no(user_no);
            dto.setChat_type(chat_type);
            dto.setTo_visible(0);

            if (fromUserDto.getUser_gender().equals("남성")) {

                // 과금하기 위해 이전 하루 동안 채팅 했는지 정보 가져옴
                int pChat_cnt = chatMapper.getUserOneDayChatCnt(dto);
                int deductionPoint = Utils.NMC_FIRST_CHAT_POINT; // 채팅시 차감 포인트

                // 이전 하루 동안 채팅한 적이 없고, 사용자의 포인트가 90포인트가 넘고 채팅 메세지를 보낸 경우
                if (pChat_cnt == 0 && fromUserDto.getUser_point() >= deductionPoint && !"영상 통화 신청".equals(chat_text) && !"보이스팅 신청".equals(chat_text) && !"부재 중 영상통화 신청".equals(chat_text)) {    //채팅 처음 신청이면 포인트 90 차감

                    int deductionFreePoint = (fromUserDto.getUser_free_point() >= deductionPoint ? deductionPoint : fromUserDto.getUser_free_point());
                    int deductionPayPoint = (fromUserDto.getUser_free_point() >= deductionPoint ? 0 : deductionPoint - deductionFreePoint);

                    UseHistoryDTO hDto = new UseHistoryDTO();
                    hDto.setUser_no(user_no);
                    hDto.setUse_etc(to_user_no + "");
                    hDto.setUse_content("채팅 신청 차감");

                    if (deductionFreePoint > 0) { // 무료포인트 사용
                        hDto.setUse_cnt(-deductionFreePoint);
                        hDto.setUse_type(2);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }
                    if (deductionPayPoint > 0) { // 유료포인트 사용
                        hDto.setUse_cnt(-deductionPayPoint);
                        hDto.setUse_type(0);    //0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트

                        liveCallMapper.insertUseHistory(hDto);
                    }


                    UserPointDTO pointDto = new UserPointDTO();
                    pointDto.setNo(user_no);
                    pointDto.setUser_free_point(fromUserDto.getUser_free_point() - deductionFreePoint);
                    pointDto.setUser_pay_point(fromUserDto.getUser_pay_point() - deductionPayPoint);
                    // 남자에게 채팅 과금하기
                    userMapper.setPoint(pointDto);
                } else if (pChat_cnt == 0 && fromUserDto.getUser_point() < deductionPoint) {
                    resultDto.setError_code("1");
                    resultDto.setReason("채팅 " + deductionPoint + "포인트 부족");

                    return resultDto;
                }
            }

            // 내가 상대방에게 차단됐는지 데이터 가져오기
            int blackCnt = userService.chkUserBlackList(to_user_no, user_no);

            if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임
                dto.setTo_visible(1);
            }
            // 채팅 데이터 저장하기
            int result = chatMapper.sendChatV3(dto);

            if (result == 1) {
                resultDto.setResult(Utils.SUCCESS);

                ChatUserDTO chatDto = new ChatUserDTO();
                chatDto.setUser_no(user_no);
                chatDto.setTo_user_no(to_user_no);
                String user_country_code = fromUserDto.getUser_country_code() !=null? fromUserDto.getUser_country_code(): "KR";
                chatDto.setUser_country_code(user_country_code);
                // 채팅 전체 목록 가져오기
                resultDto.setChatList(chatMapper.getUserChatV3(chatDto));

                if (blackCnt == 0) {    //블랙 당한경우 채팅 푸쉬 발송 안함
                    String nickNameText = fromUserDto.getUser_nick_name();

                    JSONObject chatData = new JSONObject();
                    chatData.put("from_user_no", user_no);
                    chatData.put("chat_text", chat_text);
                    chatData.put("chat_text_trans", chat_text_trans);
                    chatData.put("chat_type", chat_type);
                    chatData.put("from_user_nick", nickNameText);
                    chatData.put("from_user_thumnail", "NONE".equals(fromUserDto.getUser_imgs()) ? "NONE" : Utils.getImgType(new JSONArray(fromUserDto.getUser_imgs()), "small"));

                    // 상대방에게 채팅 보냈다는 push 보내기
                    DefaultDTO defaultDTO = pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chat_text_trans); //상대 방에게 푸쉬 발송
                }
            }

            UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
            resultDto.setUser_type(Integer.parseInt(userInfoDTO.getUser_type()));
            resultDto.setUser_point(userInfoDTO.getUser_point());
        }

        return resultDto;
    }

    //채팅 삭제
    @Override
    public ChatListDTO deleteChat(int user_no, String arr_to_user_no) {
        ChatListDTO resultDto = new ChatListDTO();

        ChatUserDTO dto = new ChatUserDTO();

        // 다중 채팅 삭제 추가
        dto.setArr_to_user_no(arr_to_user_no);
        dto.setUser_no(user_no);

        int fromResult = chatMapper.deleteFromChat(dto);
        int toResult = chatMapper.deleteToChat(dto);

        if (fromResult > 0 || toResult > 0) {
            resultDto.setResult(Utils.SUCCESS);
            resultDto.setChatList(new ArrayList<ChatDTO>());
        }

        return resultDto;
    }
}