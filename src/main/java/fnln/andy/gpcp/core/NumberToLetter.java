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
    
    enum NumberClass { Unk, Hundred, Thousand, Million, Billion }
    
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
    
    private static NumberClass getNumberClass(final long n)
    {
        if ((n / BILLION) > 0)
            return NumberClass.Billion;
        if ((n / MILLION) > 0)
            return NumberClass.Million;
        if ((n / THOUSAND) > 0)
            return NumberClass.Thousand;
        if ((n / HUNDRED) > 0)
            return NumberClass.Hundred;
        
        return NumberClass.Unk;
    }
    
    private static long getLeadingFromNumberClass(final long n, final NumberClass numberClass)
    {
        switch (numberClass)
        {
            case NumberClass.Billion:
                return n / BILLION;
            case NumberClass.Million:
                return n / MILLION;
            case NumberClass.Thousand:
                return n / THOUSAND;
            case NumberClass.Hundred:
                return n / HUNDRED;
        }
        
        return n;
    }
    
    private static long getTrailingFromNumberClass(final long n, final NumberClass numberClass)
    {
        switch (numberClass)
        {
            case NumberClass.Billion:
                return n % BILLION;
            case NumberClass.Million:
                return n % MILLION;
            case NumberClass.Thousand:
                return n % THOUSAND;
            case NumberClass.Hundred:
                return n % HUNDRED;
        }
        
        return n;
    }
    
    private static int getPowOfTenIndexFromNumberClass(final NumberClass numberClass)
    {
        switch (numberClass)
        {
            case NumberClass.Billion:
                return 3;
            case NumberClass.Million:
                return 2;
            case NumberClass.Thousand:
                return 1;
            case NumberClass.Hundred:
                return 0;
        }
        
        return 0;
    }
    
    private static String getAboveNinetyNine(final long n)
    {
        final NumberClass numberClass = getNumberClass(n);
        final long leading = getLeadingFromNumberClass(n, numberClass);
        final long trailing = getTrailingFromNumberClass(n, numberClass);
        final int powOfTenIndex = getPowOfTenIndexFromNumberClass(numberClass);
        String retVal = "";
        
        if (numberClass == NumberClass.Million || numberClass == NumberClass.Billion
            || (leading > 1 && numberClass == NumberClass.Hundred) 
            || (leading > 1 && numberClass == NumberClass.Thousand))
            retVal = convertToLetter(leading) + " ";
        
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
