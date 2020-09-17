package com.challenge.plena;

import java.util.*;

public class Plena {
    public static void main(String[] args) {
        //assume Input String is not null or empty, and characters must be in a-z or A-Z, otherwise print "Invalid input"
        Plena plena = new Plena();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string: ");
        //take input
        String str = in.nextLine();
        if (str == null || str.length() == 0) {
            System.out.println("Invalid input! Input length should be at least 1!");
            return;
        }
        //process the input String
        str = plena.processStr(str);
        if (str == null) {
            System.out.println("Invalid input! Input character must be in a-z or A-Z!");
            return;
        }
        //output sorted sting
        System.out.println("Output: " + str);
    }

    private String processStr(String str) {
        Character[] array = new Character[str.length()];
        //freq tracks the frequency of each letter
        Map<Character,Integer> freq = getFreq(str, array);
        if (freq == null) {
            return null;
        }
        //sort based on number of occurrences and order from the inputted string
        Arrays.sort(array, (c1, c2) -> {
            Integer ct1 = freq.get(Character.toLowerCase(c1));
            Integer ct2 = freq.get(Character.toLowerCase(c2));
            if (ct1.equals(ct2)) {
                return 0;
            }
            return Integer.compare(ct1, ct2);
        });
        //convert to string
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append(c);
        }
        return sb.toString();
    }

    private Map<Character,Integer> getFreq(String str, Character[] array) {
        Map<Character,Integer> freq = new HashMap<>();
        //stack track the order of first occurrence of each letter
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            array[i] = str.charAt(i);
            if ((array[i] <= 'z' && array[i] >= 'a') || (array[i] <= 'Z' && array[i] >= 'A')) {
                char c = Character.toLowerCase(array[i]);
                Integer ct = freq.get(c);
                if (ct == null) {
                    freq.put(c, 1);
                    stack.offerFirst(array[i]);
                } else {
                    freq.put(c, ct + 1);
                }
            } else {
                return null;
            }
        }
        boolean findNonRepeated = false;
        while (!stack.isEmpty()) {
            char c = stack.pollLast();
            if (freq.get(Character.toLowerCase(c)) == 1) {
                //find the find non-repeated letter
                System.out.println("The first letter that is not repeated: " + c);
                findNonRepeated = true;
                break;
            }
        }
        //all letters are repeated
        if (!findNonRepeated) {
            System.out.println("All letters are repeated!");
        }
        return freq;
    }
}
