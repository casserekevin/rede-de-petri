package rededepetri.sorters;

import java.util.Comparator;

import rededepetri.components.Transicao;

public class PrioritySorter implements Comparator<Transicao>{

	@Override
	public int compare(Transicao o1, Transicao o2) {
		if(o1.getPrioridade() > o2.getPrioridade()) {
			return 1;
		}
		else if (o1.getPrioridade() < o2.getPrioridade()) {
			return -1;
		}
		else {
			return 0;			
		}
	}

}
