package com.ws.wiseSaying.controller;

import java.util.List;

import com.ws.Container;
import com.ws.Rq;
import com.ws.wiseSaying.entity.WiseSaying;
import com.ws.wiseSaying.service.WiseSayingService;

public class WiseSayingController {// App한테 받은 명령을 수행하려고 만든 클래스

	private WiseSayingService wiseSayingService;// WiseSayingService타입의 wiseSayingService변수.

	public WiseSayingController() { // 변수에 객체연결이나 값을 넣어주려고 생성자 메서드를 이용한다.
		wiseSayingService = new WiseSayingService();// 명령어에 따른 요청을 처리하기 위해 wiseSayingService변수에 WiseSayingService객체를 연결.
	}

	public void write() { // wiseSayingController가 write()메서드를 실행하려고 만든 메서드
		System.out.print("명언 : ");// 등록해야할 명언을 입력받으려고 명언 : 을 줄바꿈 없이 출력한다.
		String content = Container.getScanner().nextLine().trim();// content안에 Container를 통해서 Scanner를 가져와서 nextLine()통해서 어떤 명언을 입력받을지 적고 trim()을 통해서 좌우공백 제거를 해준다.
		System.out.print("작가 : ");// 등록해야할 작가를 입력받으려고 작가 : 을 줄바꿈 없이 출력한다.
		String author = Container.getScanner().nextLine().trim();// author안에 Container를 통해서 Scanner를 가져와서 nextLine()통해서 어떤 작가를 입력받을지 적고 trim()을 통해서 좌우공백 제거를 해준다.

		int id = wiseSayingService.write(content, author);// 오직 정수만을 담을 수 있는 id라는 공간안에 wiseSayingService를 통해서 write(content, author)메서드를 실행시키고 return받은 int값을 저장한다.

		System.out.printf("%d번 명언이 등록되었습니다.\n", id);// printf출력문의 서식지정자의 값에 위의 저장된 id값을 통해서 몇번 명언이 등록되었다는걸 알려주기위한 출력문이다.
	}

	public void list() { // wiseSayingController가 list()메서드를 실행하려고 만든 메서드
		List<WiseSaying> wiseSayings = wiseSayingService.findAll();// WiseSaying타입만 담을 수 있는 List를 연결할 wiseSayings라는 공간안에 wiseSayingService한테 findAll() 메서드를 실행시켜서 return받은 List<WiseSaying>타입의 List를 담겠다.

		System.out.println("번호  /  작가  /  명언  ");// 목록화를 어떤 형식으로 해줄지 출력한다.
		System.out.println("=====================");// 목록화를 어떤 형식으로 해줄지 출력한다.
		for (int i = wiseSayings.size() - 1; i >= 0; i--) { // wiseSayings사이즈에 따라서 제일 최신글부터 보여주려고 만든 초기값, 종료조건,스텝이다.
			WiseSaying ws = wiseSayings.get(i);// 오직 WiseSaying타입만 담을 수 있는 ws라는 공간안에 wiseSayings의 list를 i값의 변화에 따라서 get함수를 이용해서 담을 것이다.
			System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent()); // 그리고 wiseSayings에 어떤 list가 담겨있는지 출력해준다.
		}

		if (wiseSayings.size() == 0) {// 만약 wiseSayings의 사이즈가 0이라면 아래 실행문을 출력하겠다.
			System.out.println("등록 된 명언이 없습니다.");// 등록 된 명언이 없을시에 이 출력문을 출력함.
		}
	}

	public void remove(Rq rq) {// wiseSayingController가 인자값으로 Rq타입의 rq를 받는 remove()메서드를 실행하려고 만든 메서드

		int id = rq.getIntParam("id", -1);// 오직 int타입만을 담을 수 있는 id라는 공간안에 매개변수를 통해 받은 인자 rq를 통해서 getIntParam("id", -1)실행시키고 return받은 값을 담겠다.

		if (id == -1) {// id의 값이 -1이라면 if문 안쪽을 실행할것이다. // 만약 rq에 연결된 rq객체로 넘어온 cmd라는 인자가 기존에 갖춰진 형식에 맞지 않게 입력을 했다면 -1을 리턴한다.
			System.out.println("id(정수)를 제대로 입력해주세요");// id(정수)를 제대로 입력해주세요 출력해준다.
			return; // return;해주면서 void 메서드가 끝나게해준다.
		}
		WiseSaying wiseSaying = wiseSayingService.findById(id);// 오직 WiseSaying만을 담을 수 있는 wiseSaying공간에 wiseSayingService로 findById(id) 메서드를 실행시키고 return된 값을 담겠다.

		if (wiseSaying == null) { // 만약 wiseSaying가 null이라면 if문 안쪽을 실행할 것이다. 
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id); // printf출력문의 서식지정자의 값에 위의 저장된 id값을 통해서 몇번 명언은 존재하지 않다는걸 알려주기위한 출력문이다.
			return;// return;를 통해 void메서드를 종료시킨다.
		}
		wiseSayingService.remove(wiseSaying);// 위의 if문들을 다 통과해서 여기까지 왔다면 wiseSayingService로 object기반으로 remove(wiseSaying)메서드를 실행시킨다.
		System.out.printf("%d번 명언이 삭제되었습니다.\n", id);// 위의 메서드가 다 끝나면 printf출력문의 서식지정자의 값에 위의 저장된 id값을 통해서 몇번 명언이 삭제 되었는지 알려주는 출력문이다.
	}

	public void modify(Rq rq) {// wiseSayingController가 인자값으로 Rq타입의 rq를 받는 modify()메서드를 실행하려고 만든 메서드

		int id = rq.getIntParam("id", -1);// // 오직 int타입만을 담을 수 있는 id라는 공간안에 매개변수를 통해 받은 인자 rq를 통해서 getIntParam("id", -1)실행시키고 return받은 값을 담겠다.

		if (id == -1) {// id의 값이 -1이라면 if문 안쪽을 실행할것이다.
			System.out.println("id(정수)를 제대로 입력해주세요");// id(정수)를 제대로 입력해주세요 출력해준다.
			return;// return;해주면서 void 메서드가 끝나게해준다.
		}

		WiseSaying wiseSaying = wiseSayingService.findById(id);// 오직 WiseSaying만을 담을 수 있는 wiseSaying공간에 wiseSayingService로 findById(id) 메서드를 실행시키고 return된 값을 담겠다.

		if (wiseSaying == null) {// 만약 wiseSaying가 null이라면 if문 안쪽을 실행할 것이다. 
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);// printf출력문의 서식지정자의 값에 위의 저장된 id값을 통해서 몇번 명언은 존재하지 않다는걸 알려주기위한 출력문이다.
			return;// return;해주면서 void 메서드가 끝나게해준다.
		}

		System.out.println("(기존)명언 : " + wiseSaying.getContent());
		System.out.println("(기존)작가 : " + wiseSaying.getAuthor());

		System.out.print("명언 : ");
		String content = Container.getScanner().nextLine().trim();
		System.out.print("작가 : ");
		String author = Container.getScanner().nextLine().trim();

		wiseSayingService.modify(wiseSaying, content, author);

		System.out.printf("%d번 명언이 수정되었습니다.\n", id);
	}

}