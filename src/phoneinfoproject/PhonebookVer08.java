package phoneinfoproject;

import simplephoneinfo.MenuViewer;
import java.io.*;
import java.util.InputMismatchException;
public class PhonebookVer08{

    public static void main(String[] args) {

        PhoneBookManager08 manager = PhoneBookManager08.getInstance();

        manager.startPhoneInfo();

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
                        manager.quitPhoneInfo();
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("문자를 입력하였습니다. 다시 선택하세요.");
                MenuViewer.scan.nextLine();
            } catch (OutOfBoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("파일이 저장되지 않았습니다.");
            }

        }
    }
}