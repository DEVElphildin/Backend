package com.livelyit.allcam.dto;

        import java.util.ArrayList;

        import lombok.Data;
        import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GetNewbieUserListDTO extends DefaultDTO {
    ArrayList<NewbieUserDTO> newbie_user_list;
}