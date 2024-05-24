package alarm_system.service.main;

import java.util.Date;

//측정소 점검 정보 DTO
public class station_inspection {
	private Long code;
	private String measuring_station_name;
	private String measuring_station_code;
	private Date alert_time;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code=code;
	}
	public String getMeasuring_station_name() {
		return measuring_station_name;
	}
	public void setMeasuring_station_name(String measuring_station_name) {
		this.measuring_station_name=measuring_station_name;
	}
	
	public String getMeasuring_station_code() {
		return measuring_station_code;
	}
	public void setMeasuring_station_code(String measuring_station_code) {
		this.measuring_station_code=measuring_station_code;
	}
	
	public Date getAlert_time() {
		return alert_time;
	}
	public void setAlert_time(Date alert_time) {
		this.alert_time=alert_time;
	}
}
