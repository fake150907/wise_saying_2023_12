package com.ws;

public class Main {//실행 주체가 될 클래스.
	public static void main(String[] args) {// 실행 시킬 때 필요한 메인 메서드
		Container.init(); // Container클래스에서 App().run()을 하기위해 Scanner가져오기

		new App().run(); // App클래스를 통해 run 메서드 실행

		Container.close(); // run메서드가 끝나면 scanner를 끝내주는 기능
	}
}