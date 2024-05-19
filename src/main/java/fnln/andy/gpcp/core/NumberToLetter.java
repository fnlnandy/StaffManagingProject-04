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
    public static final int m_MaxSupported = 999_999_999;
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
        "cent", "mille", "dix-mille", "cent-mille", "million",
        "milliard"
    };
    
    private static String getUnderSeventy(final int n)
    {
        final int digit = n % 10;
        String retVal = m_MultiplesOfTen[n/10 - 1];
        
        if (digit == 1)
            retVal += "-et";
        
        retVal += "-" + m_Digits[digit];
        
        return retVal;
    }
    
    private static String getUnderHundred(final int n)
    {
        final int digit = n % 10;
        String retVal = m_MultiplesOfTen[n/10 - 1];
        
        if (digit == 0)
            return retVal;
        else if (n > 70 && n < 80)
        {
            retVal = m_MultiplesOfTen[5];
            
            if (digit == 1)
                retVal += "-et";
            
            retVal += "-" + m_AfterTens[digit - 1];
        }
        else if (n > 80 && n < 90)
        {
            retVal += "-" + m_Digits[digit];
        }
        else {
            retVal = m_MultiplesOfTen[7];
            
            retVal += "-" + m_AfterTens[digit - 1];
        }
        
        return retVal;
    }
    
    private static String getHundred(final int n)
    {
        final int leading = n / 100;
        final int trailing = n % 100;
        String retVal = "";
        
        if (n > 1)
            retVal = m_Digits[leading] + " ";
        
        retVal += m_PowersOfTen[0];
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    private static String getThousand(final int n)
    {
        final int leading = n / 1_000;
        final int trailing = n % 1_000;
        String retVal = "";
        
        if (n > 1)
            retVal = convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[1];
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    private static String getMillion(final int n)
    {
        final int leading = n / 1_000_000;
        final int trailing = n % 1_000_000;
        String retVal = "";
        
        if (n > 1)
            retVal = convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[4] + (leading > 1 ? "s" : "");
        retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    public static String convertToLetter(final int n)
    {
        String retVal = "";
        
        if (n < m_Digits.length) 
        {
            retVal = m_Digits[n];
        }
        else if (n % 10 == 0 && n < 100) 
        {
            retVal = m_MultiplesOfTen[n / 10 - 1];
        }
        else if (n < 20)
        {
            retVal = m_AfterTens[n - 10 - 1];
        }
        else if (n < 70)
        {
            retVal = getUnderSeventy(n);
        }
        else if (n < 100)
        {
            retVal = getUnderHundred(n);
        }
        else if (n < 1_000)
        {
            retVal = getHundred(n);
        }
        else if (n < 1_000_000)
        {
            retVal = getThousand(n);
        }
        else if (n < 1_000_000_000)
        {
            retVal = getMillion(n);
        }
        
        return retVal;
    }
}
