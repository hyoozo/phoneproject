package phoneinfoproject;

import simplephoneinfo.MenuViewer;

import java.util.InputMismatchException;

public class PhonebookVer04 {

//    public static void showArr(PhoneInfo[] arr) { // 저장된 배열 보여주는 메서드
//        System.out.println("[ 전화번호 리스트 ]");
//        for (int i = 0; i < arr.length; i++) {
//            try {
//                System.out.println((i + 1) + ". " + arr[i].getName());
//            } catch (NullPointerException e) {
//                continue;
//            }
//        }
//        System.out.println("[-------------]\n");
//    }

    public static void main(String[] args) {
        PhoneBookManager manager = PhoneBookManager.getPhoneBookManager();
        //싱글톤으로 .getPhoneBookManager 로 객체 생성.
        //다른곳에서 getPhoneBookManager 로 객체 생성하여도 동일한 주솟값을 가진다.

        int choice;
        while (true) {
            try {
                MenuViewer.showMainMenu();
                choice = MenuViewer.scan.nextInt();
                MenuViewer.scan.nextLine();
                OutOfBoundException.checkBound(choice, 1, 4);  //checkBound 메서드에서 오류잡기

                switch (choice) {
                    case Menu.INPUT -> manager.saveData();
                    case Menu.SEARCH -> manager.findPhoneInfo();
                    case Menu.DELETE -> manager.deletePhoneInfo();
                    case Menu.EXIT -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
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