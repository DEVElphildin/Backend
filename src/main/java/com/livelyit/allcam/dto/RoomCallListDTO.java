package com.livelyit.allcam.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RoomCallListDTO extends DefaultDTO{
    ArrayList<RoomCallDTO> roomCallList;

}