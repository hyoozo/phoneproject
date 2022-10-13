package phoneinfoproject;

import java.util.Objects;

public class PhoneInfo implements Comparable<PhoneInfo>{
    private String name;
    private String phoneNumber;
    private String group;

    public PhoneInfo() {

    }

    public PhoneInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.group = "일반";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void showPhoneInfo() {
        System.out.println("*—— "+getGroup()+" ——*");
        System.out.println("이름 : " + name);
        System.out.println("번호 : " + phoneNumber);

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PhoneInfo) {
            PhoneInfo phoneInfo = (PhoneInfo) o;
            return name.equals(phoneInfo.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
//                Objects.hash(name);
    }

    @Override
    public int compareTo(PhoneInfo o) {
        return name.compareTo(o.name);
    }
}

