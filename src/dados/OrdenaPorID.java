package dados;

import java.util.Comparator;

public class OrdenaPorID implements Comparator<Pessoa> {

	@Override
	public int compare(Pessoa p1, Pessoa p2) {
		if (p1.getID() > p2.getID())
			return 1;
		if (p1.getID() == p2.getID())
			return 0;
		return -1;
	}

}
