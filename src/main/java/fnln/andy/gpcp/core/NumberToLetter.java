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
    public static final long MAX_CONVERTIBLE = 999_999_999_999l;
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
    
    private static String getAboveNinetyNine(final long n)
    {
        enum NumberClass { Hundred, Thousand, Million, Billion }
        NumberClass numberClass = NumberClass.Hundred;
        String retVal = "";
        long leading = 0;
        long trailing = 0;
        int powOfTenIndex = 0;
        
        if ((leading = n / BILLION) > 0)
        {   
            numberClass = NumberClass.Billion;
            trailing = n % BILLION;
            powOfTenIndex = 3;
            
            retVal = convertToLetter(leading) + " ";
        }
        else if ((leading = n / MILLION) > 0)
        {   
            numberClass = NumberClass.Million;
            trailing = n % MILLION;
            powOfTenIndex = 2;
            
            retVal = convertToLetter(leading) + " ";
        }
        else if ((leading = n / THOUSAND) > 0)
        {   
            numberClass = NumberClass.Thousand;
            trailing = n % THOUSAND;
            powOfTenIndex = 1;
            
            if (leading > 1)
                retVal = convertToLetter(leading) + " ";
        }
        else if ((leading = n / HUNDRED) > 0)
        {
            numberClass = NumberClass.Hundred;
            trailing = n % HUNDRED;
            powOfTenIndex = 0;
            
            if (leading > 1)
                retVal = convertToLetter(leading) + " ";
        }
        
        retVal += m_PowersOfTen[powOfTenIndex];
        
        // Only "million" and "milliard" take an "s", apparently, when the leading number is
        // superior to 1.
        if ((numberClass == NumberClass.Million || numberClass == NumberClass.Billion) && leading > 1)
            retVal += "s";
        
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
        else if (n < HUNDRED)
            retVal = getUnderHundred(n);
        else if (n <= MAX_CONVERTIBLE)
            retVal = getAboveNinetyNine(n);
        
        return retVal;
    }
}
