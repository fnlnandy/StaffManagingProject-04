/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.inputverifiers;

import javax.swing.JComponent;
import javax.swing.JSpinner;

/**
 *
 * @author andy
 */
public class DayInDateInputVerifier {
    public static void validateSpinnerValue(int currentMonth, int currentYear, JSpinner dest)
    {
        final int FEBRUARY = 1;
        final int[] maxDaysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        final int correctMonth = currentMonth % maxDaysInMonths.length;
        int currMaxDay = maxDaysInMonths[correctMonth];
        
        if ((correctMonth == FEBRUARY) && (currentYear % 4 == 0))
            currMaxDay++;
        
        int destCurrVal = (int)dest.getValue();
        
        if (destCurrVal <= 0)
            dest.setValue(1);
        else if (destCurrVal > currMaxDay)
            dest.setValue(currMaxDay);
    }
}
