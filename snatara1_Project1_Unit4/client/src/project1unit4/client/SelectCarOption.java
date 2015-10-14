/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit4.client;

import java.io.*;
import java.util.ArrayList;
import project1unit4.model.Automobile;


public class SelectCarOption {
	public void StartConfigureAction(Automobile selectedAuto) {
		
		String userInput = null;
		/* take input from user */
		BufferedReader in = new BufferedReader(new InputStreamReader(
				System.in));
		
		for (int i = 0; i < selectedAuto.getOptionSetListSize(); i++) {
			String optionSetName = selectedAuto.printOptionSetName(i);
			System.out.println(optionSetName);
			selectedAuto.printOption(optionSetName);

			System.out.println("SELECT YOUR CHOICE for OPTIONSET:  " + "(" + optionSetName
					+ ")");

			try {
				userInput = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			/* Choose from the options displayed */
			while (!userInput.matches("[0-9]+")
					|| Integer.parseInt(userInput) < 0
					|| Integer.parseInt(userInput) > selectedAuto
							.getOptionListSize(optionSetName) - 1) {
				System.out.println("Please input within the given range");
				try {
					userInput = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			
			int optionIndex = Integer.parseInt(userInput);

			System.out.println();
			/* Set the option choice for the OptionSet */
			selectedAuto.setOptionChoice(optionSetName, optionIndex);

		}
	}
}
