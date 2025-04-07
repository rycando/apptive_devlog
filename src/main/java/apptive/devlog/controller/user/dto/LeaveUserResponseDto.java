package apptive.devlog.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeaveUserResponseDto {
    private String userId;

//    public static LeaveUserResponseDto buildFromLeaveUserResultDto(LeaveUserResultDto leaveUserResultDto) {
//        LeaveUserResponseDto leaveUserResponseDto = new LeaveUserResponseDto();
//
//        leaveUserResponseDto.userId = leaveUserResultDto.userId;
//        return leaveUserResponseDto;
//    }
}
