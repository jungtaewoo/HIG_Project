package kr.or.ddit.empappointments.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class AppointmentWrapper {
	
	@Valid
	private List<AppointmentVO> appointmentList;

	
}
