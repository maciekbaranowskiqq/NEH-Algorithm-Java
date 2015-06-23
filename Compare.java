import java.util.Comparator;

package com.company;


public class Compare implements Comparator<Task> {

	public int compare(Task one, Task two) {

		if	(one.getPrior() < two.getPrior())
			return 1;
		else if (one.getPrior() == two.getPrior())
			return 0;
		else
			return -1;
	}

}
