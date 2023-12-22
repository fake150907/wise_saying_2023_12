package com.ws;

import com.ws.system.controller.SystemController;
import com.ws.wiseSaying.controller.WiseSayingController;

public class App { // 명령이 들어오면 어디쪽으로가서 일 처리해~ 를 담당할 App클래스
	private byte sysytem_status = 1; // run안쪽 while문의 조건의 상태를 결정짓는 변수. 

	public void run() { // 명언 앱을 실행시키기 위한 메서드
		System.out.println("== 명언 앱 실행 ==");

		SystemController systemController = new SystemController();// systemController한테 SystemController객체를 연결시켜주면서 SystemController의 일을 맡기려고 만든 변수.
		WiseSayingController wiseSayingController = new WiseSayingController();// wiseSayingController한테 WiseSayingController객체를 연결시켜주면서 WiseSayingController의 일을 맡기려고 만든 변수.

		while (sysytem_status == 1) {// 무한반복을 통해서 앱을 실행해야해서 안쪽 조건문이 참일 때 무한반복
			System.out.print("명령어) ");// 여기 명령어 입력해주세요의 명령어)
			String cmd = Container.getScanner().nextLine().trim();// cmd라는 변수안에 Container를 통해서 Scanner실행하고 nextLine통해서 명령어 입력받고 입력받은 명령어의 좌우 공백을 trim이 제거해준다.
			Rq rq = new Rq(cmd); // Rq타입의 rq를 Rq객체를 연결시키면서 cmd를 인자값으로 넘겨주면서 기능에 맞게 일처리를 해달라고 부탁한다.
			
			switch(rq.getActionCode()) { // rq에 연결되어있는 Rq객체로 getActionCode메서드를 실행해서 어떤 일을 할지 라우터해준다.
			case "종료": // rq.getActionCode()통해서 얻은 값이 종료라면 아래 코드를실행해라.
				systemController.exit(); // systemController한테 exit메서드를 실행해달라고 요청함.
				sysytem_status = 0; // 그다음 while문의 조건이 false가 되게 만들어서 종료시키기 위해서 sysytem_status의 값을 0으로 재할당한다.
				break;// switch문을 빠져나감.
			case "등록":// rq.getActionCode()통해서 얻은 값이 등록이라면 아래 코드를실행해라.
				wiseSayingController.write(); // wiseSayingController한테 write()메서드를 실행해달라고 요청함.
				break;// switch문을 빠져나감.
			case "목록":// rq.getActionCode()통해서 얻은 값이 목록이라면 아래 코드를실행해라.
				wiseSayingController.list();// wiseSayingController한테 list()메서드를 실행해달라고 요청함.
				break;// switch문을 빠져나감.
			case "삭제":// rq.getActionCode()통해서 얻은 값이 삭제라면 아래 코드를실행해라.
				wiseSayingController.remove(rq);// wiseSayingController한테 remove(rq)메서드를 실행해달라고 요청함.			
				break;// switch문을 빠져나감.
			case "수정":// rq.getActionCode()통해서 얻은 값이 수정이라면 아래 코드를실행해라.
				wiseSayingController.modify(rq);// wiseSayingController한테 modify(rq)메서드를 실행해달라고 요청함.
				break;// switch문을 빠져나감.
			default:// 위의 case에 해당하지 않는 명령어라면 아래를 출력함.
				System.out.println("존재하지 않는 명령어입니다.");// 존재하지 않는 명령어라는걸 알려주기 위해 안의 내용을 출력함.
			}	
		}
	}
}