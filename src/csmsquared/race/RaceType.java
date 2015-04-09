package csmsquared.race;

public enum RaceType {
	Individual,
	Group,
	ParallelIndividual,
	ParallelGroup;

	public String toString() {
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
