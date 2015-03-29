package csmsquared.main;

public enum RaceType {
	Individual,
	Group,
	ParallelIndividual,
	ParallelGroup;
	
	public String toString() {
		// XXX: May not be needed or maybe should return spelled out name.
		switch(this){
			case Individual:
				return "IND";
			case Group:
				return "GRP";
			case ParallelIndividual:
				return "PARIND";
			case ParallelGroup:
				return "PARGRP";
			default:
				return "UNKNOWN";
		}
	}
}
