package simplephoneinfo;

import phoneinfoproject.OutOfBoundException;

import java.util.Scanner;

public class MenuViewer {
    public static Scanner scan = new Scanner(System.in);
    public enum  MainMenu {
        데이터입력,데이터검색,데이터삭제,프로그램종료
    };


    public static void showMainMenu() {  //전화번호 메인 메뉴 메서드
        System.out.println("  ---------------- [ 전 화 번 호 프 로 그 램 ] ----------------");
        System.out.print("1. 데이터 입력 \t2. 데이터 검색 \t3. 데이터 삭제 \t4. 프로그램 종료 \n선택 : ");
    }

    public static void showSubMenu() {  //전화번호 입력 메뉴 메서드
        System.out.println("");
        System.out.print("1. 일반 \t2. 대학\t3. 회사\n선택>> ");
    }
    public static void checkBound(int num, int bound1, int bound2) throws OutOfBoundException {
        if (num < bound1 || bound2 < num) {
            throw new OutOfBoundException(num + "에 해당하는 선택은 존재하지 않습니다.\n메뉴 선택을 처음부터 다시 진행합니다.");
        }
    }
}