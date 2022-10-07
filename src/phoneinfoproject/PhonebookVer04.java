package phoneinfoproject;

import simplephoneinfo.MenuViewer;

import java.util.InputMismatchException;

public class PhonebookVer04 {
    public static int num = 0;
    public static int saveNum = 0;

    public static void showArr(PhoneInfo[] arr) { // 저장된 배열 보여주는 메서드
        System.out.println("[ 전화번호 리스트 ]");
        for (int i = 0; i < arr.length; i++) {
            try {
                System.out.println((i + 1) + ". " + arr[i].getName());
            } catch (NullPointerException e) {
                continue;
            }
        }
        System.out.println("[-------------]\n");
    }

    public static void main(String[] args) {
        PhoneBookManager manager = PhoneBookManager.getInstance();

        int choice;
        while (true) {
            try {
                MenuViewer.showMainMenu();
                choice = MenuViewer.scan.nextInt();
                MenuViewer.scan.nextLine();
                MenuViewer.checkBound(choice, 1, 4);  //checkBound 메서드에서 오류잡기

                switch (choice) {
                    case 1:
                        manager.saveData();
                        break;
                    case 2:
                        manager.findPhoneInfo();
                        break;
                    case 3:
                        manager.deletePhoneInfo();
                        break;
                    case 4:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("문자를 입력하였습니다. 다시 선택하세요.");
                MenuViewer.scan.nextLine();
            } catch (OutOfBoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}