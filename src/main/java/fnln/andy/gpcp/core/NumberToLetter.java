/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

/**
 *
 * @author andy
 * 
 * @brief Class with a static
 * method to convert a number
 * into its French string
 * representation.
 */
public class NumberToLetter {
    /**
     * @brief The max convertible nomber this
     * class can take.
     */
    public static final long MAX_CONVERTIBLE = 999_999_999_999l;
    /**
     * @brief Representation of ten.
     */
    private static final long TEN = 10l;
    /**
     * @brief Representation of a hundred.
     */
    private static final long HUNDRED = 100l;
    /**
     * @brief Representation of a thousand.
     */
    private static final long THOUSAND = 1_000l;
    /**
     * @brief Representation of a million.
     */
    private static final long MILLION = 1_000_000l;
    /**
     * @brief Representation of a billion.
     */
    private static final long BILLION = 1_000_000_000l;
    
    /**
     * @brief Array of all the powers of ten.
     */
    private static final long[] POWERS_OF_TEN = { HUNDRED, THOUSAND, MILLION, BILLION };
    
    /**
     * @brief Number classes.
     */
    private static final int UNK_NUMBER_CLASS = 0;
    private static final int HUNDRED_NUMBER_CLASS = 1;
    private static final int THOUSAND_NUMBER_CLASS = 2;
    private static final int MILLION_NUMBER_CLASS = 3;
    private static final int BILLION_NUMBER_CLASS = 4;
    
    /**
     * @brief Digits as string.
     */
    private static final String[] m_DigitsReps = { 
        "zéro", "un", "deux", "trois", "quatre", "cinq", 
        "six", "sept", "huit", "neuf"
    };
    /**
     * @brief Multiples of ten as string.
     */
    private static final String[] m_MultiplesOfTenReps = {
        "dix", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix",
        "quatre-vingt", "quatre-vingt-dix"
    };
    /**
     * @brief The numbers above 10 and under 20
     * as string.
     */
    private static final String[] m_AfterTensReps = {
        "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept",
        "dix-huit", "dix-neuf"
    };
    /**
     * @brief Powers of ten as string.
     */
    private static final String[] m_PowersOfTen = {
        "cent", "mille", "million", "milliard"
    };
    
    /**
     * @param n
     * 
     * @brief Deduces a number's class
     * by how big it is.
     * 
     * @return 
     */
    private static int deduceNumberClass(final long n)
    {
        for (int i = BILLION_NUMBER_CLASS; i > UNK_NUMBER_CLASS; i--)
            if (n / POWERS_OF_TEN[i - 1] > 0)
                return i;
        
        return UNK_NUMBER_CLASS;
    }
    
    /**
     * @param n
     * 
     * @brief Gets the string representation
     * for a two-digit number.
     * 
     * @return 
     */
    private static String getTwoDigitNumber(final long n)
    {
        final long leading = n / TEN;
        final long trailing = n % TEN;
        final boolean isUsingAfterTens = (n > 70 && n < 80) || n > 90;
        String retVal = m_MultiplesOfTenReps[(int)(leading - 1)];
        
        if (isUsingAfterTens)
            retVal = m_MultiplesOfTenReps[(int)(leading - 1 - 1)];
        
        if (trailing == 1 && n < 80)
            retVal += "-et";
        
        if (isUsingAfterTens)
            retVal += "-" + m_AfterTensReps[(int)(trailing - 1)];
        else
            retVal += "-" + m_DigitsReps[(int)(trailing)];
        
        return retVal;
    }
    
    /**
     * @param n
     * 
     * @brief Gets a wide number's
     * string representation.
     * 
     * @return 
     */
    private static String getAboveHundredNumber(final long n)
    {
        final int numberClass = deduceNumberClass(n);
        
        if (numberClass == UNK_NUMBER_CLASS)
            return "";
        
        final int powerOfTenIndex = numberClass - 1;
        final long leading = n / POWERS_OF_TEN[powerOfTenIndex];
        final long trailing = n % POWERS_OF_TEN[powerOfTenIndex];
        String retVal = "";
        
        if ((numberClass >= MILLION_NUMBER_CLASS) || 
                (leading > 1 && numberClass >= HUNDRED_NUMBER_CLASS))
            retVal += convertToLetter(leading) + " ";
        
        retVal += m_PowersOfTen[powerOfTenIndex];
        
        if (leading > 1 && (numberClass >= MILLION_NUMBER_CLASS || 
            (numberClass == HUNDRED_NUMBER_CLASS && trailing == 0)))
            retVal += "s";
        
        if (trailing > 0)
            retVal += " " + convertToLetter(trailing);
        
        return retVal;
    }
    
    /**
     * @param n
     * 
     * @brief Main function to convert
     * a long into its French string
     * representation.
     * 
     * @return 
     */
    public static String convertToLetter(final long n)
    {
        String retVal = "";
        
        if (n < 0)
            return retVal;
        
        if (n < m_DigitsReps.length) 
            retVal += m_DigitsReps[(int)n];
        else if (n % 10 == 0 && n < 100)
        {
            retVal += m_MultiplesOfTenReps[(int)(n / 10 - 1)] + (n == 80 ? "s" : "");
        }
        else if (n < 20)
            retVal += m_AfterTensReps[(int)(n - 10 - 1)];
        else if (n < HUNDRED)
            retVal += getTwoDigitNumber(n);
        else if (n <= MAX_CONVERTIBLE)
            retVal += getAboveHundredNumber(n);
        
        return retVal;
    }
}
