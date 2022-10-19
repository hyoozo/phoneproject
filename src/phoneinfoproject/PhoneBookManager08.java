package phoneinfoproject;

import simplephoneinfo.MenuViewer;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class PhoneBookManager08 {
    //7 단계 HashSet 필드와 생성자 구현
    private Iterator<PhoneInfo> itr;
    private HashSet<PhoneInfo> set = new HashSet<>();
    private static PhoneBookManager08 instance = null;

    public static PhoneBookManager08 getInstance() {  //싱글톤 패턴사용
        if (instance == null) {
            instance = new PhoneBookManager08();
        }
        return instance;
    }
    private PhoneBookManager08() {

    }
    ObjectInputStream in = null;
    ObjectOutputStream out = null;


    // 일반을 선택했을 때 호출할 메서드 (참조값 반환)
    private PhoneInfo readFriendInfo() {
        System.out.print("이름 :");
        String name = MenuViewer.scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = MenuViewer.scan.nextLine();
        return new PhoneInfo(name, phone);
    }

    //대학을 선택했을 때 호출할 메서드 (참조값 반환)
    private PhoneInfo readUnivFriendInfo() {
        System.out.print("이름 : ");
        String name = MenuViewer.scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = MenuViewer.scan.nextLine();
        System.out.print("전공 : ");
        String major = MenuViewer.scan.nextLine();
        System.out.print("학년 : ");
        int year = MenuViewer.scan.nextInt();
        return new PhoneUnivInfo(name, phone, major, year);
    }

    //회사를 선택했을 때 호출할 메서드(참조값 반환)
    private PhoneInfo readCompanyFriendInfo() {
        System.out.print("이름 : ");
        String name = MenuViewer.scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = MenuViewer.scan.nextLine();
        System.out.print("회사 : ");
        String company = MenuViewer.scan.nextLine();
        return new PhoneCompanyInfo(name, phone, company);
    }

    //저장 메소드
    public void saveData() throws OutOfBoundException, IOException {
        //직렬화
        out = new ObjectOutputStream(new FileOutputStream("PhoneBook.dat"));

        System.out.println("데이터 입력을 시작합니다.");
        MenuViewer.showSubMenu(); //서브 메뉴 호출

        int choice = MenuViewer.scan.nextInt();
        MenuViewer.scan.nextLine();
        OutOfBoundException.checkBound(choice, 1, 3); //서브 메뉴 checkBound 메서드에서 오류잡기

        PhoneInfo phoneInfo = null;
        switch (choice) {
            case InputMenu.NORMAL:
                phoneInfo = readFriendInfo();
                break;
            case InputMenu.UNIV:
                phoneInfo = readUnivFriendInfo();
                break;
            case InputMenu.COMPANY:
                phoneInfo = readCompanyFriendInfo();
                break;
        }
        boolean result = insertPhoneInfo(phoneInfo);
        if(!result)
            System.out.println("이미 등록된 데이터 입니다.");
        else System.out.println("데이터 입력이 완료되었습니다.");
    }

    //중복값을 확인하기 위해만들어짐
    public boolean insertPhoneInfo(PhoneInfo phoneInfo) {
        return set.add(phoneInfo);
    }

    //검색 메소드
    public void findPhoneInfo() {
        if (set.isEmpty()) { //set 이 비어있다면 저장하세요.
            System.out.println("저장된 정보가 없습니다. 번호를 새롭게 저장하세요!");
            return;
        } else { // 내용이 있다면 검색할 수 있는 데이터를 보여줍니다.
            showDataList();
        }

        System.out.println("데이터 검색을 시작합니다.");
        System.out.print("검색할 이름 : ");
        String name = MenuViewer.scan.nextLine();
        if (checkByName(name) == null) {
            System.out.println("리스트에 검색할 데이터가 없습니다.\n");
        }else{ checkByName(name).showPhoneInfo();}

    }

    //데이터 삭제
    public void deletePhoneInfo() {
        System.out.println("삭제를 원하는 이름을 적으세요.");
        System.out.print("이름 : ");
        String name = MenuViewer.scan.nextLine();

        if ((checkByName(name) == null)) {
            System.out.println("삭제하시고자 하는 전화번호 정보가 없습니다.\n");
        }
        set.remove(checkByName(name));
        System.out.println("삭제가 완료되었습니다.");
    }

    //set 안에 이름으로 체크하여 값 반환하기
    public PhoneInfo checkByName(String name) {
        itr = set.iterator();
        while (itr.hasNext()) {
            PhoneInfo phoneInfo = itr.next();
            if (name.equals(phoneInfo.getName())) {
                return phoneInfo;
            }
        }
        return null;
    }

    //저장된 모든 데이터 이름 출력하여 보여주기
    public void showDataList() {
        System.out.println("[PhoneBook LIST]");
        itr = set.iterator(); // 저장된 모든 데이터 이름 출력
        while (itr.hasNext()) {
            System.out.println("\t☞ " + itr.next().getName());
        }
    }
    public void startPhoneInfo() {
        //파일에 저장된 객체를 읽어 들이기 위해 객체 생성
        set = new HashSet<PhoneInfo>();
        try {
            in = new ObjectInputStream(new FileInputStream("PhoneBook.dat"));
        } catch (FileNotFoundException e) {
            System.out.println("등록된 데이터가 없습니다.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(in != null)
            {
                PhoneInfo phoneInfo = (PhoneInfo)in.readObject();
                while(phoneInfo != null)
                {
                    insertPhoneInfo(phoneInfo);
                    try {
                        phoneInfo = (PhoneInfo)in.readObject();
                    }
                    catch(EOFException e)
                    {
                        break;
                    }

                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try {
            if(in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quitPhoneInfo() {
        //프로그램 종료시 파일 유지
        if (set.size() == 0) return;

        try {
            out = new ObjectOutputStream(new FileOutputStream("PhoneBook.dat"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            itr = set.iterator();
            while (itr.hasNext())
                out.writeObject(itr.next());//
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e1) {
            System.out.println("파일 닫기 오류");
            e1.printStackTrace();
        }
    }

}



