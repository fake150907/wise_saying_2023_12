package com.ws.wiseSaying.controller;

import java.util.List;

import com.ws.Container;
import com.ws.Rq;
import com.ws.wiseSaying.entity.WiseSaying;
import com.ws.wiseSaying.service.WiseSayingService;

public class WiseSayingController {

	private WiseSayingService wiseSayingService;

	public WiseSayingController() {
		wiseSayingService = new WiseSayingService();
	}

	public void write() {
		System.out.print("명언 : ");
		String content = Container.getScanner().nextLine().trim();
		System.out.print("작가 : ");
		String author = Container.getScanner().nextLine().trim();

		int id = wiseSayingService.write(content, author);

		System.out.printf("%d번 명언이 등록되었습니다.\n", id);
	}

	public void list() {
		List<WiseSaying> wiseSayings = wiseSayingService.findAll();

		System.out.println("번호  /  작가  /  명언  ");
		System.out.println("=====================");
		for (int i = wiseSayings.size() - 1; i >= 0; i--) {
			WiseSaying ws = wiseSayings.get(i);
			System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent());

		}
		System.out.println("등록 된 명언이 없습니다.");
	}

	public void remove(Rq rq) {

		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		WiseSaying wiseSaying = wiseSayingService.findById(id);

		if (wiseSaying == null) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}
		wiseSayingService.remove(wiseSaying);
		System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
	}

	public void modify(Rq rq) {

		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		
		WiseSaying wiseSaying = wiseSayingService.findById(id);

		if (wiseSaying == null) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
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