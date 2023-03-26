/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author marilena_sch
 */
public class AttackPrevent {

    public static String PreventXSS(String input) {

        StringBuffer string = new StringBuffer(input.length());
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    string.append("&lt;");
                    break;
                case '>':
                    string.append("&gt;");
                    break;
                case '"':
                    string.append("&quot;");
                    break;
                case '&':
                    string.append("&amp;");
                    break;
                default:
                    string.append(c);
            }
        }
        return string.toString();
    }

}
