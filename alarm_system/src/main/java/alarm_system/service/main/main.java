package alarm_system.service.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//메인 클래스이다.
public class main {
	
	public static void main(String[] args) {
		//csv 데이터를 읽어온다
		String file="C:\\2023년3월_서울시_미세먼지.csv";
		String csvsp=",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
		String line;
		
		try {
			
			FileReader csvfilereader = new FileReader(file);
			BufferedReader br=new BufferedReader(csvfilereader);
			br.readLine();
			int PM10=0;
			int PM25=0;
			
			//한줄씩 읽어온다.
			while((line=br.readLine())!=null) {
				
				dust_analysis_service service=new dust_analysis_service();
				String[] datalist=line.split(csvsp,-1);
				
				String strdate=datalist[0];
				String location=datalist[1];
				String locationcode=datalist[2];
				PM10=datalist.length > 3 && !datalist[3].isEmpty() ? Integer.parseInt(datalist[3]) : 0;
				PM25=datalist.length > 4 && !datalist[4].isEmpty() ? Integer.parseInt(datalist[4]) : 0;
				service.dust_analysis_2(strdate, location, locationcode, PM10, PM25);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//csv 데이터가 끝날때 까지 실행한다
		
		
	}
}
