package com.ws.wiseSaying.service;

import java.util.List;

import com.ws.wiseSaying.entity.WiseSaying;
import com.ws.wiseSaying.repository.WiseSayingRepository;

public class WiseSayingService {// WiseSayingService클래스

	private WiseSayingRepository wiseSayingRepository;// wiseSayingRepository클래스를 써먹기 위해 만든 변수.

	public WiseSayingService() {
		wiseSayingRepository = new WiseSayingRepository();// wiseSayingRepository클래스를 써먹으려고 만든 객체
	}

	public int write(String content, String author) {// WiseSayingController한테 인자값으로 content와 author를 넘겨받아wiseSayingRepository로 write함수를 실행한다.
		return wiseSayingRepository.write(content, author);// return값으로 wiseSayingRepository.write(content, author)를 실행한 결과값(정수값)을 남기겠다
	}

	public void remove(WiseSaying wiseSaying) {// WiseSayingController한테 인자값으로 wiseSaying를 넘겨받아wiseSayingRepository로 remove함수를 실행한다.
		wiseSayingRepository.remove(wiseSaying);
	}

	public void modify(WiseSaying wiseSaying, String content, String author) {// WiseSayingController한테 인자값으로 wiseSaying와 content, author를 넘겨받아 wiseSayingRepository로 modify함수를 실행한다.
		wiseSayingRepository.modify(wiseSaying, content, author);
	}

	public List<WiseSaying> findAll() {
		return wiseSayingRepository.findAll();
	}

	public WiseSaying findById(int id) {
		return wiseSayingRepository.findById(id);
	}
}
