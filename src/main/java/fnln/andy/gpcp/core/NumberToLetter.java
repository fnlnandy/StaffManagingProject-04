/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

/**
 *
 * @author andy
 */
public class NumberToLetter {
    public static final long m_MaxSupported = 999_999_999_999l;
    private static final long TEN = 10l;
    private static final long HUNDRED = 100l;
    private static final long THOUSAND = 1_000l;
    private static final long MILLION = 1_000_000l;
    private static final long BILLION = 1_000_000_000l;
    
    private static final String[] m_Digits = { 
        "zéro", "un", "deux", "trois", "quatre", "cinq", 
        "six", "sept", "huit", "neuf"
    };
    private static final String[] m_MultiplesOfTen = {
        "dix", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix",
        "quatre-vingt", "quatre-vingt-dix"
    };
    private static final String[] m_AfterTens = {
        "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept",
        "dix-huit", "dix-neuf"
    };
    private static final String[] m_PowersOfTen = {
        "cent", "mille", "million", "milliard"
    };
    
    private static String getUnderSeventy(final long n)
    {
        final long leading = n / TEN;
        final long trailing = n % TEN;
        String retVal = m_MultiplesOfTen[(int)(leading - 1)];
        
        if (trailing == 1)
            retVal += "-et";
        
        retVal += "-" + m_Digits[(int)trailing];
        
        return retVal;
    }
    
    private static String getUnderHundred(final long n)
    {
        final long leading = n / TEN;
        final long trailing = n % TEN;
        String retVal = m_MultiplesOfTen[(int)(leading - 1)];
        
        if (trailing == 0)
            return retVal;
        else if (n > 70 && n < 80)
        {
            retVal = m_MultiplesOfTen[5];
            
            if (trailing == 1)
                retVal += "-et";
            
            retVal += "-" + m_AfterTens[(int)(trailing - 1)];
        }
        else if (n > 80 && n < 90)
        {
            retVal += "-" + m_Digits[(int)trailing];
        }
        else {
            retVal = m_MultiplesOfTen[7];
            
            retVal += "-" + m_AfterTens[(int)trailing - 1];
        }
        
        return retVal;
    }
    
    private static String getHundred(final long n)
    {
        final long leading = n / HUNDRED;
        final long trailing = n % HUNDRED;
        String retVal = "";
        
        if (n > 1)
            retVal = m_Digits[(int)leading] + " ";
        
        retVal += m_PowersOfTen[0];
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    private static String getThousand(final long n)
    {
        final long leading = n / THOUSAND;
        final long trailing = n % THOUSAND;
        String retVal = "";
        
        if (n > 1)
            retVal = convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[1];
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    private static String getMillion(final long n)
    {
        final long leading = n / MILLION;
        final long trailing = n % MILLION;
        String retVal = convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[2] + (leading > 1 ? "s" : "");
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    private static String getBillion(final long n)
    {
        final long leading = n / BILLION;
        final long trailing = n % BILLION;
        String retVal = convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[3] + (leading > 1 ? "s" : "");
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
        
    }
    
    public static String convertToLetter(final long n)
    {
        String retVal = "";
        
        if (n < m_Digits.length) 
            retVal = m_Digits[(int)n];
        else if (n % 10 == 0 && n < 100) 
            retVal = m_MultiplesOfTen[(int)(n / 10 - 1)];
        else if (n < 20)
            retVal = m_AfterTens[(int)(n - 10 - 1)];
        else if (n < 70)
            retVal = getUnderSeventy(n);
        else if (n < 100)
            retVal = getUnderHundred(n);
        else if (n < 1_000)
            retVal = getHundred(n);
        else if (n < 1_000_000)
            retVal = getThousand(n);
        else if (n < 1_000_000_000)
            retVal = getMillion(n);
        else if (n < 1_000_000_000_000l)
            retVal = getBillion(n);
        
        return retVal;
    }
}
