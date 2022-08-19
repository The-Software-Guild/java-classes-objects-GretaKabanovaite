package dvdlibrary.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double input;
        while(true){
            try {
                input = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double input;
        while(true){
            try {
                input = Double.parseDouble(sc.nextLine());
                if(input >= min && input <= max){
                    break;
                } else {
                    System.out.println("Number out of range, please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float input;
        while(true){
            try {
                input = Float.parseFloat(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float input;
        while(true){
            try {
                input = Float.parseFloat(sc.nextLine());
                if(input >= min && input <= max){
                    break;
                } else {
                    System.out.println("Number out of range, please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int input;
        while(true){
            try {
                input = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int input;
        while(true){
            try {
                input = Integer.parseInt(sc.nextLine());
                if(input >= min && input <= max){
                    break;
                } else {
                    System.out.println("Number out of range, please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long input;
        while(true){
            try {
                input = Long.parseLong(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long input;
        while(true){
            try {
                input = Long.parseLong(sc.nextLine());
                if(input >= min && input <= max){
                    break;
                } else {
                    System.out.println("Number out of range, please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
}
