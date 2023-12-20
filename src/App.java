import java.util.Scanner;

public class App {
	private Scanner sc;
	App(Scanner sc){
		this.sc = sc;
	}
	
	public void run() {
		System.out.println("== 명언 앱 실행 ==");
		
		while(true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.equals("종료")) {
				System.out.println("== 명언 앱 종료 ==");
				break;
			}
		}
	}

}
