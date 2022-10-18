package phoneinfoproject;
import simplephoneinfo.MenuViewer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
public class PhoneBookManager implements InputMenu, Menu {
//    private static final int MAX_CNT = 100;
//    PhoneInfo[] obj;  //부모 클래스의 타입으로 객체 배열 선언
//    int count;
    //3, 4단계 생성자.
//    public PhoneBookManager() {
//        obj = new PhoneInfo[MAX_CNT];
//        count = 0;
//    }
    //현재 생성자 접근 제어자를 private 로 설정.
//    private PhoneBookManager() {
//        count = 0;
//        obj = new PhoneInfo[MAX_CNT];
//    }

    private static PhoneBookManager instance = null;
    public static PhoneBookManager getPhoneBookManager() {  //싱글톤 패턴사용
        if (instance == null) {
            instance = new PhoneBookManager();
        }
        return instance;
    }
    private PhoneBookManager(){}

    //7 단계 HashSet 필드와 생성자 구현
    private Iterator<PhoneInfo> itr;
    private  HashSet<PhoneInfo> phoneHashset = new HashSet<>();

    public void showDataList(){
        itr = phoneHashset.iterator(); // 저장된 모든 데이터 이름 출력
        while (itr.hasNext()) {
            System.out.println("\t☞ "+itr.next().getName());
        }
    }

    //데이더 저장
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
    public void saveData() throws OutOfBoundException {
//        if (obj[MAX_CNT-1] != null){
//            System.out.println("저장 공간이 없습니다. 연락처를 삭제하거나 종료해주세요.");
//        } else {b
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
                /*
                switch (choice) {
                    case 1:
                        info = readFriendInfo();
                        break;
                    case 2:
                        info = readUnivFriendInfo();
                        break;
                    case 3:
                        info = readCompanyFriendInfo();
                        break;
                }
                 */
        phoneHashset.add(phoneInfo);
        System.out.println("데이터의 입력이 완료되었습니다.");
//            obj[count++] = info;
//            PhonebookVer04.showArr(obj);
        showDataList();  // 저장된 리스트 이름 보여주는 메소드 호출
    }

    public PhoneInfo serchPhoneInfoByName(String name) {
        itr = phoneHashset.iterator();
        while (itr.hasNext()) {
            PhoneInfo phoneInfo = itr.next();
            if (name.equals(phoneInfo.getName())) {
                return phoneInfo;
            }
        }
        return null;
    }
    //검색 메소드
    public void findPhoneInfo() {
        System.out.println("데이터 검색을 시작합니다.");
        System.out.println("[PhoneBook LIST]");
        showDataList();

        System.out.print("검색할 이름 : ");
        String name = MenuViewer.scan.nextLine();

        if (serchPhoneInfoByName(name) == null) {
            System.out.println("해당하는 데이터가 존재하지 않습니다.");
            return;
        }
        serchPhoneInfoByName(name).showPhoneInfo();
//        int dataIdx = search(name);
//        if (dataIdx < 0) {
//            System.out.println("해당하는 데이터가 존재하지 않습니다. \n");
//        } else {
//
//                obj[dataIdx].showPhoneInfo();
//                System.out.println("데이터 검색이 완료되었습니다. \n");
//            }
    }

        //데이터 삭제
        public void deletePhoneInfo () {
            System.out.println("데이터 삭제를 시작합니다.");

            System.out.print("이름 : ");
            String name = MenuViewer.scan.nextLine();

            if ((serchPhoneInfoByName(name) == null)) {
                System.out.println("해당하는 데이터가 존재하지 않습니다.");
            }
            phoneHashset.remove(serchPhoneInfoByName(name));
            System.out.println("삭제가 완료되었습니다.");
//        int dataIdx = search(name);
//        if (dataIdx < 0) {
//            System.out.println("해당하는 데이터가 존재하지 않습니다 \n");
//        } else {
//            for (int idx = dataIdx; idx < (count-1); idx++){
//                obj[idx] = obj[idx+1];
//            }
//            count--;
//            System.out.println("데이터 삭제가 완료되었습니다. \n");
//            PhonebookVer04.showArr(obj);

        }


//    3단계 배열에 데이터 검색
//        private int search (String name){
//            for (int idx = 0; idx < count; idx++) {
//                PhoneInfo countInfo = obj[idx];
//                if (name.compareTo(countInfo.getName()) == 0) {
//                    return idx;
//                }
//            }
//            return -1;
//        }
    }


