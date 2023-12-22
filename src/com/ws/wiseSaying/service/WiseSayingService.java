package com.ws.wiseSaying.service;

import java.util.ArrayList;
import java.util.List;

import com.ws.wiseSaying.entity.WiseSaying;

public class WiseSayingService {
	
	private int lastWiseSayingId;
	private List<WiseSaying> wiseSayings;
	
	public WiseSayingService() {
		lastWiseSayingId = 0;
		wiseSayings = new ArrayList<>();
	}
	
	public WiseSaying findById(int id) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (wiseSaying.getId() == id) {
				return wiseSaying;
			}
		}
		return null;
	}
	
	public int write(String content, String author) {
		int id = lastWiseSayingId + 1;

		WiseSaying wiseSaying = new WiseSaying(id, content, author);
		wiseSayings.add(wiseSaying);

		lastWiseSayingId = id;
		
		return id;
	}
	
	public List<WiseSaying> list() {
		
		return wiseSayings;

	}
	
	public void remove(WiseSaying wiseSaying) {
		wiseSayings.remove(wiseSaying);
	}

	public void modify(WiseSaying wiseSaying,String content, String author) {
	
		wiseSaying.setContent(content);
		wiseSaying.setAuthor(author);

	}

	public List<WiseSaying> findAll() {
		// TODO Auto-generated method stub
		return wiseSayings;
	}
}
