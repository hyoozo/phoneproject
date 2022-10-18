package phoneinfoproject;

import java.io.Serializable;

//대학(학교) 동기들의 전화번호 저장
public class PhoneUnivInfo extends PhoneInfo implements Serializable {
    private String major;
    private int year;

    public PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
        super(name, phoneNumber);
        this.major = major;
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void showPhoneInfo() {
        super.setGroup("동료");
        super.showPhoneInfo();
        System.out.println("전공 : "+major);
        System.out.println("학년 : "+year);
    }
}
