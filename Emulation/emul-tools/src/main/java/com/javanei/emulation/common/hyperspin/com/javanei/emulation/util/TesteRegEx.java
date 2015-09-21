package com.javanei.emulation.common.hyperspin.com.javanei.emulation.util;

/**
 * Created by Vanei on 16/09/2015.
 */
public class TesteRegEx {
    public static void main(String[] args) {
        String s = "(Rev A)";
        String regex = ".*\\(Rev .+?\\).*";

        String s2 = "Teste (v1.2) (ok)";
        String regex2 = ".*\\(v.+?\\).*";

        String s3 = "Teste (Beta 1) (ok)";
        String s31 = "(Beta)";
        String regex3 = ".*\\(Beta.*\\).*";

        String s4 = "Teste (Proto 1) (ok)";
        String regex4 = ".*\\(Proto.*\\).*";

        String s5 = "Teste (Sample 1) (ok)";
        String regex5 = ".*\\(Sample.*\\).*";

        String s6 = "(En,Es,Pt)";
        String regex6 = ".*\\(.*Es.*\\).*";

        System.out.println(s.matches(regex));
        //System.out.println("a(Rev 1.2)".matches(regex));
        //System.out.println(s2.matches(regex2));
        //System.out.println(s3.matches(regex3));
        //System.out.println(s4.matches(regex4));
        //System.out.println(s5.matches(regex5));
        //System.out.println(s6.matches(regex6));
        //System.out.println(s31.matches(regex3));
    }
}
