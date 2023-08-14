package com.livelyit.allcam.dto;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HeartHistoryListDTO extends DefaultDTO {
    ArrayList<HeartHistoryDTO> heartList;
}