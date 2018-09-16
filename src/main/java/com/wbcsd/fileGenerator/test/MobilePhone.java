package com.wbcsd.fileGenerator.test;

/**
 * @author Aashish
 * @version $Id: $
 */
public abstract class MobilePhone extends Phone {

    public abstract void sendMessage();

    public void makeCall(boolean isInternational) {
        if (isInternational) {
            System.out.println("InternationalCall");
        }
        super.makeCall();
    }

    // public static void main(String[] args) {
    // IPhone m = new MobilePhone();
    // m.makeCall();
    // // m.sendMessage();
    // // m.makeCall(true);
    // }

}
